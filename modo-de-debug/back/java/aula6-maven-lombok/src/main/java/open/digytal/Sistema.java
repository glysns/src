package open.digytal;

import open.digytal.model.ClassePessoa;
import open.digytal.model.ClassePessoaBizarra;
import open.digytal.model.ClassePessoaLombok;

import java.time.LocalDate;

public class Sistema {
    public static void main(String[] args) throws Exception {
        LocalDate data = LocalDate.now();
        ClassePessoa classePessoa = new ClassePessoa(); //construtor sem argumentos
        classePessoa = new ClassePessoa(data,"M","gleyson"); //construtor requerindo todos os atributos

        //chegando no cartorio é necessário registrar a pessoa considerando o seu sobrenome
        //mesmo assim ainda temos algumas "brechas"
        classePessoa.setNome("gleyson sampaio de oliveira");


        ClassePessoaBizarra classePessoaBizarra = ClassePessoaBizarra.builder().dataNascimento(data).nome("gleyson").build(); //e o sexo ??
        classePessoaBizarra = new ClassePessoaBizarra(data,"M","gleyson");
        classePessoaBizarra.setSexo("F");//se o construtor "TENTA" garantir segurança, para que TER o setter ?

        ClassePessoaLombok pessoaLombok = new ClassePessoaLombok(data, "M");
        //pessoaLombok = new ClassePessoaLombok(); // -> impossivel
        pessoaLombok.setNome("gleyson sampaio de oliveira");

        System.out.println(pessoaLombok.getNome() + "," + pessoaLombok.getSexo() + "," + pessoaLombok.getDataNascimento());
        System.out.println(pessoaLombok.getDatabaseId());

    }
}