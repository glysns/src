package jpa;

import jpa.dao.ClienteDao;
import jpa.dao.FabricaConexao;
import jpa.dao.ProfissaoDao;
import jpa.model.Cliente;
import jpa.model.Profissao;
import jpa.model.cliente.Endereco;
import jpa.model.cliente.Telefone;
import jpa.model.cliente.TelefoneTipo;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class JpaClienteApplication {
    private static ClienteDao clienteCrud;
    private static ProfissaoDao profissaoCrud;
    public static void main(String[] args) {
        FabricaConexao.iniciarConexao();
        clienteCrud = new ClienteDao();
        profissaoCrud = new ProfissaoDao();
        Profissao profissao = incluirProfissao();
        incluirCliente1(profissao);
        incluirCliente2(profissao);
    }
    private static Profissao incluirProfissao(){
        Profissao profissao  = profissaoCrud.findById(1);
        if(profissao==null) {
            profissao = new Profissao();
            profissao.setNome("PROGRAMADOR");
            profissaoCrud.save(profissao);
            System.out.println("profissao adicionada com sucesso");
        }
        return profissao;
    }
    private static void incluirCliente1(Profissao profissao){
            Cliente cliente = new Cliente();
            cliente.setNome("gleyson sampaio");
            cliente.setDataNascimento(LocalDate.now());

            //todos abaixo precisam ser cascade ?
            cliente.setEmails(Collections.singleton("gleyson@hotmail.com"));
            cliente.setTelefones(Collections.singleton(new Telefone(TelefoneTipo.COM,11965479876L)));
            Endereco endereco = new Endereco();
            endereco.setCep("45763567");
            endereco.setLogradouro("RUA DAS FLORES");
            endereco.setNumero("45A");
            cliente.setEndereco(endereco);

            //vale o esforço?
            cliente.setProfissao(profissao);

            clienteCrud.save(cliente);

            System.out.println("primeiro cliente adicionado");
    }
    private static void incluirCliente2(Profissao profissao){
            Cliente cliente = new Cliente();
            cliente.setNome("frank marlon");
            cliente.setDataNascimento(LocalDate.now());
            //vale o esforço?
            cliente.setProfissao(profissao);
            cliente.setEmails(Collections.singleton("frankmarlon@hotmail.com"));
            cliente.setTelefones(Collections.singleton(new Telefone(TelefoneTipo.RES,9823870934L)));
            Endereco endereco = new Endereco();
            endereco.setCep("87685586");
            endereco.setLogradouro("AVENIDA MARTE");
            endereco.setNumero("SN");
            cliente.setEndereco(endereco);
            clienteCrud.save(cliente);
            System.out.println("segundo cliente adicionado");
    }
}
