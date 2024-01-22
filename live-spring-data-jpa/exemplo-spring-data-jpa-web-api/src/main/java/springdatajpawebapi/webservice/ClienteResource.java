package springdatajpawebapi.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springdatajpawebapi.dto.response.ClienteResponse;
import springdatajpawebapi.model.Cliente;
import springdatajpawebapi.repository.ClienteRepository;
import springdatajpawebapi.service.ClienteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService service;

    /**
        em alguns casos o repository pode ser acessado direto pelo controller
        mas claro se houver regra de negócio prévia, deverá se encapsulado no service blz
     */
    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public void post(@RequestBody Cliente request){//aqui deveria ter o seu DTO
        service.save(request);
    }
    @GetMapping("/all")
    public List<Cliente> getAll(){
        return repository.findAll();
    }
    @GetMapping()
    public List<ClienteResponse> getByNome(@RequestParam("nome") String nome){
        return repository.findByNomeContaining(nome);
    }
    @GetMapping("/{id}")
    public Cliente getOne(@PathVariable("id") Integer id){
        return repository.getFull(id);
    }
    @GetMapping("/search")
    public List<Cliente> consultar(@RequestParam Map<String,String> params){
        return  service.consultar(params);
    }
}
