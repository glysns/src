import cadastros.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;

public class Sistema {
    public static void main(String[] args) {
        Cadastro gleyson = new Cadastro();
        gleyson.setNome("GLEYSON SAMPAIO");
        gleyson.setCpf("618.677.980-77");
        gleyson.setDataNascimento(LocalDate.of(1990, 7, 8));
        gleyson.setSexo(Sexo.MASCULINO);
        gleyson.setEstadoCivil(EstadoCivil.CASADO);

        Endereco endereco = new Endereco();
        endereco.setBairro("Angelim");
        endereco.setCep("64041-700");
        endereco.setLogradouro("Rua Pranchita");
        endereco.setNumero("386");

        Cidade teresina = new Cidade();
        teresina.setNome("Teresina");
        teresina.setUf(Uf.PI);
        endereco.setCidade(teresina);

        gleyson.setEndereco(endereco);

        System.out.println(gleyson.getEndereco().getCidade().getUf());
        System.out.println(gleyson.getEndereco().getCidade().getNome()
        );

    }
    
}

