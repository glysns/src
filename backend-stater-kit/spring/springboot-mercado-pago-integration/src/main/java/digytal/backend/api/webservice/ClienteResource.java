package digytal.backend.api.webservice;

import digytal.backend.api.model.cliente.ClienteRequest;
import digytal.backend.api.model.cliente.ClienteResponse;
import digytal.backend.api.repository.ClienteRepository;
import digytal.backend.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void post(@RequestBody ClienteRequest request){//aqui deveria ter o seu DTO
        service.save(request);
    }
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Integer id, @RequestBody ClienteRequest request){
        service.update(id,request);
    }
    @GetMapping()
    public List<ClienteResponse> listAll(){
        return service.listAll();
    }
    @GetMapping("/{id}")
    public ClienteResponse getOne(@PathVariable("id") Integer id){
        return service.getOne(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }

}
