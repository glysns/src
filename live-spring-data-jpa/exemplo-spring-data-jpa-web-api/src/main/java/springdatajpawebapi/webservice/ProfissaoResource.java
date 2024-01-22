package springdatajpawebapi.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springdatajpawebapi.dto.response.ClienteResponse;
import springdatajpawebapi.dto.response.ProfissaoResponse;
import springdatajpawebapi.model.Cliente;
import springdatajpawebapi.model.Profissao;
import springdatajpawebapi.repository.ClienteRepository;
import springdatajpawebapi.repository.ProfissaoRepository;
import springdatajpawebapi.service.ClienteService;
import springdatajpawebapi.service.ProfissaoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profissoes")
public class ProfissaoResource {
    @Autowired
    private ProfissaoService service;
    /**
        em alguns casos o repository pode ser acessado direto pelo controller
        mas claro se houver regra de negócio prévia, deverá se encapsulado no service blz
     */
    @Autowired
    private ProfissaoRepository repository;

    @PostMapping
    public void post(@RequestBody Profissao request){//aqui deveria ter o seu DTO
        service.save(request);
    }
    @PutMapping("/{id}")
    public void put(@PathVariable("id") Integer id, @RequestBody Profissao request){//aqui deveria ter o seu DTO
        service.update(id,request);
    }
    @GetMapping("/all")
    public List<Profissao> getAll(){
        return repository.findAll();
    }
    @GetMapping
    public ResponseEntity<Map<String, Object>> search(@RequestParam(required = false) String nome,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "3") int size){


        Page<ProfissaoResponse> result = service.listar(nome,page,size);
        //este map->responde poderia ser um objeto bem arrumadinho
        Map<String, Object> response = new HashMap<>();
        response.put("data", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());

       return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
