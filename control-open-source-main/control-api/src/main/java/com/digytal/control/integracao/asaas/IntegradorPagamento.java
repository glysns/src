package com.digytal.control.integracao.asaas;

import com.digytal.control.infra.business.BusinessException;
import com.digytal.control.infra.business.IntegracaoException;
import com.digytal.control.infra.business.RegistroIncompativelException;
import com.digytal.control.infra.commons.definition.Definition;
import com.digytal.control.infra.utils.Calculos;
import com.digytal.control.integracao.asaas.model.*;
import com.digytal.control.model.modulo.acesso.empresa.EmpresaEntity;
import com.digytal.control.model.modulo.acesso.empresa.EmpresaIntegracao;
import com.digytal.control.repository.modulo.acesso.empresa.EmpresaRepository;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IntegradorPagamento {
    @Autowired
    private ObjectMapper mapper;
    @Value("${asaas.url}")
    private String root;
    @Value("${asaas.token}")
    private String token;
    @Value("${asaas.webHookAccessToken}")
    private String webHookAccessToken;
    @Value("${asaas.taxaBoleto:0.0}")
    private Double taxaBoleto;
    @Value("${asaas.taxaPix:0.0}")
    private Double taxaPix;
    private RestTemplate template = new RestTemplate();
    @Autowired
    private EmpresaRepository empresaRepository;
    private AsaasIntegracao integracao;
    private String email;
    public Cadastro obterCadastro(Integer empresa, String id){
        HttpEntity<String> entity = new HttpEntity<String>(null,headers(empresa));
        String url = root+"/customers?id={id}&deleted=false";
        ResponseEntity<Cadastro> response = template.exchange(url, HttpMethod.GET, entity, Cadastro.class,id);
        Cadastro cadastro = response.getBody();
        return cadastro;
    }
    public AsaasIntegracao atualizarCredenciamento(Integer empresa){
        System.out.println("Montando o objeto contendo os parametros de integracao do assas");
        integracao = new AsaasIntegracao();
        integracao.taxaBoleto =  taxaBoleto;
        integracao.taxaPix =  taxaPix;
        integracao.token = token;
        integracao.webHookAccessToken = webHookAccessToken;
        EmpresaEntity entity = empresaRepository.findById(empresa).orElse(null);
        EmpresaIntegracao empresaIntegracao = entity.getIntegracao();
        if(empresaIntegracao!=null){
            integracao.taxaBoleto = Definition.seNulo(empresaIntegracao.getAsaasTaxaEmissaoBoleto(), taxaBoleto) ;
            integracao.token = Definition.seNuloOuVazio(empresaIntegracao.getAsaasToken(), token);
            integracao.contaEmpresa = empresaIntegracao.isContaEmpresa();
            integracao.webHookAccessToken = empresaIntegracao.isContaEmpresa() ? empresaIntegracao.getAsaasWebhookToken() : webHookAccessToken;
            if(integracao.webHookAccessToken==null || integracao.webHookAccessToken.trim().length() == 0)
                throw new BusinessException("A empresa de CNPJ " + entity.getCpfCnpj() + " Não definiu o seu access token");
        }
        email = Definition.seNuloOuVazio(entity.getEmail(),"gleyson@digytal.com.br");
        return  integracao;
    }

    public String getEmail() {
        return email;
    }

    public Double getTaxaEmissaoBoleto(){
        return integracao.taxaBoleto;
    }
    public Double getTaxaEmissaoPix(){
        return integracao.taxaPix;
    }

    public String getWebHookAccessToken() {
        return integracao.webHookAccessToken;
    }
    public boolean isContaEmpresa(){
        return integracao.contaEmpresa;
    }

    public Cadastro cadastrar(Integer empresa, Cadastro cadastro){
        HttpEntity<Cadastro> entity = new HttpEntity<Cadastro>(cadastro,headers(empresa));
        String url = root+"/customers";
        ResponseEntity<Cadastro> response = template.exchange(url, HttpMethod.POST, entity, Cadastro.class);;
        return response.getBody();
    }
    public BoletoResponse gerarBoleto(Integer empresa,BoletoRequest boleto){
        try{
            HttpEntity<BoletoRequest> entity = new HttpEntity<BoletoRequest>(boleto,headers(empresa));
            String url = root+"/payments";
            ResponseEntity<BoletoResponse> response = template.exchange(url, HttpMethod.POST, entity, BoletoResponse.class);;
            return response.getBody();
        }catch (HttpClientErrorException httpex) {
            String message="";
            try {
                JsonNode node = mapper.readTree(httpex.getResponseBodyAsString());
                message = node.get("errors").get(0).get("description").asText();
                throw new IntegracaoException(message);
            } catch (JacksonException e) {
                throw new BusinessException();
            }

        }catch (Exception httpex){
            throw new BusinessException();
        }
    }
    public List<String> listarNotificacoes(Integer empresa, String clienteCodigoIntegracao){
        try{
            List<String> ids = new ArrayList<>();
            HttpEntity<BoletoRequest> entity = new HttpEntity<BoletoRequest>(headers(empresa));
            String url = root+"/customers/"+clienteCodigoIntegracao+"/notifications";
            ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, entity, String.class);
            JsonNode root = mapper.readTree(response.getBody());
            Iterator<JsonNode> notificacoes = root.get("data").iterator();
            while (notificacoes.hasNext()){
                ids.add(notificacoes.next().get("id").asText());
            }
            return ids;
        }catch (Exception ex){
            ex.printStackTrace();
            throw new BusinessException();
        }
    }
    public void desativarNotificacoes(Integer empresa, String clienteCodigoIntegracao){
        try{
            List<String> notificacoes = listarNotificacoes(empresa, clienteCodigoIntegracao);
            NotificacaoLote lote = new NotificacaoLote();
            lote.setCustomer(clienteCodigoIntegracao);
            for(String id: notificacoes){
                lote.getNotifications().add(new Notificacao(id));
            }
            HttpEntity<NotificacaoLote> entity = new HttpEntity<>(lote,headers(empresa));
            String url = root+"/notifications/batch";
            ResponseEntity<String> response = template.exchange(url, HttpMethod.POST, entity, String.class);
            System.out.println(response.getBody());
        }catch (Exception ex){
            ex.printStackTrace();
            throw new BusinessException();
        }
    }
    public BoletoResponse obterBoleto(Integer empresa,String id){
        try{
            HttpEntity<String> entity = new HttpEntity<String>(null,headers(empresa));
            String url = root+"/payments?id={id}";
            ResponseEntity<BoletoResponse> response = template.exchange(url, HttpMethod.GET, entity, BoletoResponse.class, id);;
            return response.getBody();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    private HttpHeaders headers(Integer empresa) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set("access_token", integracao.token);
        return headers;
    }
    class AsaasIntegracao{
        private String token;
        private String webHookAccessToken;
        Double taxaBoleto;
        Double taxaPix;
        private boolean contaEmpresa;

    }
}
