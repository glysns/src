package springdatajpa.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdatajpa.model.Cliente;
import springdatajpa.model.Profissao;
import springdatajpa.model.cliente.Endereco;
import springdatajpa.model.cliente.Telefone;
import springdatajpa.model.cliente.TelefoneTipo;
import springdatajpa.repository.ClienteRepository;
import springdatajpa.repository.ProfissaoRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

//@Component
public class StartBasic implements CommandLineRunner {
    @Autowired
    private ProfissaoRepository profissaoCrud;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        Profissao profissao = incluirProfissao();
        //listarProfissoesNome("GRA");
        //incluirCliente1(profissao);
        //incluirCliente2(profissao);
        listarClientes();
        //buscarClienteComProfissao(1);
        //buscarClienteCompleto(1);

    }
    //Power Interfaces (extends)
    private  Profissao incluirProfissao(){
        Profissao profissao  = profissaoCrud.findById(1).orElse(null);
        if(profissao==null) {
            profissao = new Profissao();
            profissao.setNome("PROGRAMADOR");
            profissaoCrud.save(profissao);
            System.out.println("profissao adicionada com sucesso");
        }
        return profissao;
    }
    //Power Interfaces (extends)
    private  void alterarProfissao(){
        Profissao profissao  = profissaoCrud.findById(1).orElse(null);
        if(profissao!=null){
            profissao.setNome("PROGRAMADOR / INSTRUTOR");
            profissaoCrud.save(profissao);
            System.out.println("profissao adicionado com sucesso");
        }else {
            System.out.println("Não existe a profissão com o id informado");
        }
    }
    //Power Interfaces (extends)
    private  void listarProfissoes(){
        List<Profissao> profissoes = profissaoCrud.findAll();
        for(Profissao p:profissoes){
            System.out.println(p.getId() + "--" + p.getNome());
        }
    }
    //Power Interfaces (extends)
    private  void excluirProfissao(){
        profissaoCrud.deleteById(1);
        System.out.println("profissao excluida com sucesso");
    }
    //Query Methods
    private  void listarProfissoesNome(String nome){
        List<Profissao> profissoes = profissaoCrud.findByNomeContaining(nome);
        for(Profissao p:profissoes){
            System.out.println(p.getId() + "--" + p.getNome());
        }
    }
    //Lazy error
    private void listarClientes(){
        for(Cliente cli: clienteRepository.findAll()){
            System.out.println(cli.getNome());
            System.out.println(cli.getTelefones());
            System.out.println(cli.getEmails());
            System.out.println(cli.getProfissao().getNome());
        }
    }
    //Query Override
    //Entity Graph
    private void buscarClienteComProfissao(Integer id){
        System.out.println("BUSCANDO O CLIENTE E SUA PROFISSAO COM ID: " + id);
        Cliente cliente  = clienteRepository.findClienteWithProfissao(id);
        if(cliente!=null){
            System.out.println(cliente.getNome());
            System.out.println("profissao " + cliente.getProfissao().getNome() );
            //System.out.println(cliente.getTelefones());
            //System.out.println(cliente.getEmails());
        }
    }
    //Query Override
    //Entity Graph
    private void buscarClienteCompleto(Integer id){
        System.out.println("BUSCANDO O CLIENTE COMPLETO COM ID: " + id);
        Cliente cliente  = clienteRepository.findFullCliente(id);
        if(cliente!=null){
            System.out.println(cliente.getNome());
            System.out.println("profissao " + cliente.getProfissao().getNome() );
            System.out.println(cliente.getTelefones());
            System.out.println(cliente.getEmails());
        }
    }
    private void incluirCliente1(Profissao profissao){
        if(!clienteRepository.existsById(1)){
            Cliente cliente = new Cliente();
            cliente.setNome("gleyson sampaio");
            cliente.setDataNascimento(LocalDate.of(1990,10,07));
            cliente.setProfissao(profissao);
            cliente.setEmails(Collections.singleton("gleyson@hotmail.com"));
            cliente.setTelefones(Collections.singleton(new Telefone(TelefoneTipo.COM,11965479876L)));
            Endereco endereco = new Endereco();
            endereco.setCep("45763567");
            endereco.setLogradouro("RUA DAS FLORES");
            endereco.setNumero("45A");
            cliente.setEndereco(endereco);
            clienteRepository.save(cliente);
            System.out.println("primeiro cliente adicionado");
        }else
            System.out.println("Já existe um cliente com o id 1");


    }
    private void incluirCliente2(Profissao profissao){
        if(!clienteRepository.existsById(2)){
            Cliente cliente = new Cliente();
            cliente.setNome("frank marlon");
            cliente.setDataNascimento(LocalDate.of(1993,5,19));
            cliente.setProfissao(profissao);
            cliente.setEmails(Collections.singleton("frankmarlon@hotmail.com"));
            cliente.setTelefones(Collections.singleton(new Telefone(TelefoneTipo.RES,9823870934L)));
            Endereco endereco = new Endereco();
            endereco.setCep("87685586");
            endereco.setLogradouro("AVENIDA MARTE");
            endereco.setNumero("SN");
            cliente.setEndereco(endereco);
            clienteRepository.save(cliente);
            System.out.println("segundo cliente adicionado");
        }else
            System.out.println("Já existe um cliente com o id 2");

    }

}
