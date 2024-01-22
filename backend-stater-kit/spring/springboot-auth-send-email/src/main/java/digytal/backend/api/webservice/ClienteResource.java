package digytal.backend.api.webservice;

import digytal.backend.api.infra.handler.Response;
import digytal.backend.api.infra.handler.ResponseFactory;
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
    public Response post(@RequestBody ClienteRequest request){//aqui deveria ter o seu DTO
        return ResponseFactory.ok(service.save(request),"Cliente salvo com sucesso");
    }
    @PutMapping("/{id}")
    public Response put(@PathVariable("id") Integer id, @RequestBody ClienteRequest request){
        return ResponseFactory.ok(service.save(request),"Cliente alterado com sucesso");
    }
    @GetMapping()
    public List<ClienteResponse> listAll(){
        return service.listAll();
    }
    @GetMapping("/{id}")
    public Response getOne(@PathVariable("id") Integer id){
        Object response = service.getOne(id);
        return ResponseFactory.okOrNotFound(repository.findById(id));
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Integer id){
        return ResponseFactory.ok(service.delete(id),"Cliente excluído com sucesso");
    }

}
