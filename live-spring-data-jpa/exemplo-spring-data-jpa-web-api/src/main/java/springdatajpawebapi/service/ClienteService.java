package springdatajpawebapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdatajpawebapi.model.Cliente;
import springdatajpawebapi.repository.ClienteRepository;
import springdatajpawebapi.repository.specification.JpaSpecification;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    public void save(Cliente entity){
        //sua logica de negocio
        repository.save(entity);
    }
    //Specification
    public List<Cliente> consultar(Map<String,String> params){
        JpaSpecification especification = new JpaSpecification<Cliente>();
        //sem uso stream do java 8
        for(Map.Entry<String, String> condition: params.entrySet()){
            if(condition.getKey().equals("nome"))
                especification.like(condition.getKey(), condition.getValue());
            if(condition.getKey().equals("dataNascimento")){
                LocalDate data = LocalDate.parse(condition.getValue());
                especification.equal(condition.getKey(), data);
            }
        }
        return repository.findAll(especification);
    }
}
