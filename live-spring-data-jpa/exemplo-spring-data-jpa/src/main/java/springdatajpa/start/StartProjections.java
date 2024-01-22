package springdatajpa.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdatajpa.dto.ProfissaoDto;
import springdatajpa.dto.response.ClienteResponse;
import springdatajpa.dto.view.ClienteView;
import springdatajpa.dto.view.ProfissaoView;
import springdatajpa.repository.ClienteRepository;
import springdatajpa.repository.ProfissaoRepository;

import java.util.List;

@Component
public class StartProjections implements CommandLineRunner {
    @Autowired
    private ProfissaoRepository profissaoCrud;

    @Autowired
    private ClienteRepository clienteRepository;

    //https://thorben-janssen.com/spring-data-jpa-query-projections/
    @Override
    public void run(String... args) throws Exception {
        //listarProfissoesProjection();
        //listarProfissoesViewProjection();
        //listarClienteViewProjection();
        listarClienteResponseProjection();
    }
    //Projections Dtos
    private void listarProfissoesProjection(){
        List<ProfissaoDto>  dtos = profissaoCrud.findByNome("PROGRAMADOR");
        for(ProfissaoDto d: dtos){
            System.out.println(d);
        }
    }
    //Projections View - via interfaces
    private void listarProfissoesViewProjection(){
        List<ProfissaoView>  views = profissaoCrud.findViewByNome("PROGRAMADOR");
        for(ProfissaoView v: views){
            System.out.println(v.getId() + "-"+v.getNome());
        }
    }
    //Projections View - JOIN
    private void listarClienteViewProjection(){
        List<ClienteView>  views = clienteRepository.findByNome("gleyson sampaio");
        for(ClienteView v: views){
            System.out.println(v.getId() + "-"+v.getNome() + "-"+v.getProfissaoNome() + "-"+v.getEnderecoLogradouro());
        }
    }

    //Projections Response Interface
    private void listarClienteResponseProjection(){
        List<ClienteResponse>  views = clienteRepository.findByNomeContaining("gleyson");
        for(ClienteResponse v: views){
            System.out.println(v.getId() + "-"+v.getNome() );
            System.out.println(v.getEndereco().getLogradouro() + "-"+v.getEndereco().getNumero() + "-"+v.getEndereco().getCep() );
            System.out.println(v.getProfissao().getId() + "-"+v.getProfissao().getNome() );
        }
    }

}
