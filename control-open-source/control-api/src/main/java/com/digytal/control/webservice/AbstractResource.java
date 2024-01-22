package com.digytal.control.webservice;

import com.digytal.control.infra.business.ParametroInvalidoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Base64;

public class AbstractResource {
    @Autowired
    private ObjectMapper mapper;
    public <T> T obterFiltro(String jsonBase64, Class classeFiltro){
        Object filtro = null;
        try {
            filtro = mapper.readValue(new String(Base64.getDecoder().decode(jsonBase64)),classeFiltro);
        } catch (JsonProcessingException e) {
            throw new ParametroInvalidoException("Não possível processar a requisição com os filtros informados");
        }
        return (T) filtro;
    }
}
