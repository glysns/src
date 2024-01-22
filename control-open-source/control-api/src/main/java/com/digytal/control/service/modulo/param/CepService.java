package com.digytal.control.service.modulo.param;

import com.digytal.control.infra.business.RegistroIncompativelException;
import com.digytal.control.infra.business.TamanhoMaximoException;
import com.digytal.control.infra.commons.definition.Definitions;
import com.digytal.control.model.comum.endereco.Cidade;
import com.digytal.control.model.comum.endereco.Endereco;
import com.digytal.control.model.modulo.param.cep.CepEntity;
import com.digytal.control.model.modulo.param.cep.CepResponse;
import com.digytal.control.model.modulo.param.cep.Uf;
import com.digytal.control.repository.modulo.param.CepRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import static com.digytal.control.infra.commons.validation.Attributes.*;

@Service
public class CepService {
    @Autowired
    private CepRepository repository;
    @Transactional
    public Endereco obterEndereco(String cep){
        CepEntity entity = obterCep(cep);
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(entity,endereco);
        Cidade cidade = new Cidade();
        cidade.setUf(entity.getUf());
        cidade.setEstado(entity.getEstado());
        cidade.setNome(entity.getLocalidade());
        endereco.setCidade(cidade);
        return endereco;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CepEntity obterCep(String cep){
        if(cep ==null)
            return criarCepPadrao();
        if(cep.trim().length()<6)
            throw new RegistroIncompativelException("O campo CEP é obrigatório e com mínimo de 6 caracteres");

        cep = String.format("%08d", Integer.valueOf(cep.replaceAll("\\D","")));
        if(cep.length() > 8)
            throw new TamanhoMaximoException("Cep", 8);
        CepEntity entity = repository.findById(cep).orElse(null);
        if(entity==null){
            entity = new CepEntity();
            CepResponse cepResponse = consultar(cep);
            BeanUtils.copyProperties(cepResponse, entity);
            entity.setEstado(Uf.valueOf(cepResponse.getUf().toUpperCase()).getNome());
            entity.setValido(cepResponse.isValido());
            repository.save(entity);
        }
        return entity;
    }
    private CepResponse consultar(String cep){
        try{
            RestTemplate client = new RestTemplate();
            String url = "https://viacep.com.br/ws/{cep}/json/";
            CepResponse codigoPostal = client.getForObject(url, CepResponse.class, cep);
            codigoPostal.setValido(codigoPostal.getCep()!=null);
            if(codigoPostal.isValido()){
                //Definitions.build().upperNormalize(LOGRADOURO, COMPLEMENTO, BAIRRO,UF,LOCALIDADE).define(codigoPostal);
                Definitions.build().onlyDigits(CEP).define(codigoPostal);
            }else {
                codigoPostal.setCep(cep);
                codigoPostal.setUf(Uf.BR.name());
                codigoPostal.setLocalidade(Uf.BR.getNome());
            }
            return codigoPostal;
        }catch (ResourceAccessException ex){
            //ex.printStackTrace();
            return criarCepPadrao();
        }
    }
    private CepResponse criarCepPadrao(){
        CepResponse response = new CepResponse();
        response.setValido(false);
        response.setCep("99999999");
        response.setEstado("BRASILEIRO");
        response.setUf("BR");
        response.setBairro("");
        response.setIbge(0);
        response.setComplemento("");
        response.setLocalidade("BRASIL");
        response.setLogradouro("");
        return response;
    }
}
