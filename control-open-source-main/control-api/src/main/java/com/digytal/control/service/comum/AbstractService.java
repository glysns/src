package com.digytal.control.service.comum;

import com.digytal.control.infra.business.RegistroIncompativelException;
import com.digytal.control.infra.config.RequestInfo;
import com.digytal.control.model.comum.MeioPagamento;
import com.digytal.control.model.comum.Participante;
import com.digytal.control.model.modulo.acesso.empresa.pagamento.FormaPagamentoEntity;
import com.digytal.control.repository.GlobalRepository;
import com.digytal.control.repository.modulo.acesso.empresa.FormaPagamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.Normalizer;
import java.util.UUID;

@Slf4j
public abstract class AbstractService {
    @Autowired
    protected RequestInfo requestInfo;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    protected GlobalRepository globalRepository;
    protected FormaPagamentoEntity consultarFormaPagamento(Integer empresa, MeioPagamento meioPagamento){
        return consultarFormaPagamento(empresa, meioPagamento, 1);
    }
    protected FormaPagamentoEntity consultarFormaPagamento(Integer empresa, MeioPagamento meioPagamento, Integer numeroParcelas){
        FormaPagamentoEntity formaPagamento = formaPagamentoRepository.findByEmpresaAndMeioPagamentoAndNumeroParcelas(empresa,meioPagamento,numeroParcelas);
        if(formaPagamento==null)
            throw new RegistroIncompativelException("O meio de pagamento " + meioPagamento.getDescricao() + " não se encontra vinculado a nenhuma conta empresa");
        return  formaPagamento;
    }
    public boolean validarIntegridadeOrganizacional(Integer organizacao){
        return requestInfo.getOrganizacao().equals(organizacao);
    }
    public void checarIntegridadeOrganizacional(Integer organizacao){
        if(!validarIntegridadeOrganizacional(organizacao))
            throw new RegistroIncompativelException("Este usuário não tem permissão para realizar esta operação");
    }
    public boolean validarIntegridadeEmpresarial(Integer empresa){
        return requestInfo.getEmpresa().equals(empresa);
    }
    public void checarIntegridadeEmpresarial(Integer empresa){
        if(!validarIntegridadeEmpresarial(empresa))
            throw new RegistroIncompativelException("Este usuário não tem permissão para realizar esta operação");
    }
    protected Participante definirParticipantes(Integer cadastro){
        Participante participante = new Participante();
        participante.setOrganizacao(requestInfo.getOrganizacao());
        participante.setEmpresa(requestInfo.getEmpresa());
        participante.setUsuario(requestInfo.getUsuario());
        participante.setCadastro(cadastro==null || cadastro ==0 ? 1 : cadastro);
        globalRepository.existsCadastro(participante.getCadastro());
        return participante;
    }
    public String gerarLocalizador(){
        return String.format("%d.%d.%d.%s",requestInfo.getOrganizacao(),requestInfo.getEmpresa(),requestInfo.getUsuario(), UUID.randomUUID().toString().substring(0, 4));
    }
    protected static String normalizar(String ... campos){
        return Normalizer.normalize(String.join(" ",campos).toUpperCase(),Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}
