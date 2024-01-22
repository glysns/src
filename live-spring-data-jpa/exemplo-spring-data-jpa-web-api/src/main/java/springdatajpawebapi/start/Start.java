package springdatajpawebapi.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdatajpawebapi.model.Profissao;
import springdatajpawebapi.repository.ProfissaoRepository;

@Component
public class Start implements CommandLineRunner {
    @Autowired
    private ProfissaoRepository profissaoRepository;

    @Override
    public void run(String... args) throws Exception {
        boolean exists = profissaoRepository.existsById(10);
        if (!exists) {
            String[] profissoes = new String[]{
                    "3171-2O PROGRAMADOR DE APLICATIVOS EDUCACIONAIS E DE ENTRETENIMENTO",
                    "3171-20 PROGRAMADOR DE CD-ROM",
                    "3171-10 PROGRAMADOR DE COMPUTADOR",
                    "3911-25 PROGRAMADOR DE CONTROLE DE PRODUCAO",
                    "7631-20 PROGRAMADOR DE ENCAIXE (CAD)",
                    "7661-45 PROGRAMADOR DE FOTOCOMPOSICAO",
                    "3171-05 PROGRAMADOR DE INTERNET",
                    "3171-15 PROGRAMADOR DE MAQUINAS - FERRAMENTA COM COMANDO NUMERICO",
                    "3171-20 PROGRAMADOR DE MULTIMIDIA",
                    "3171-10 PROGRAMADOR DE PROCESSAMENTO DE DADOS",
                    "7631-20 PROGRAMADOR DE RISCO E CORTE",
                    "3171-10 PROGRAMADOR DE SISTEMAS DE COMPUTADOR",
                    "3171-10 PROGRAMADOR DE SISTEMAS DE INFORMACAO",
                    "3421-10 PROGRAMADOR DE TRANSPORTE MULTIMODAL",
                    "3423-05 PROGRAMADOR DE TRANSPORTE RODOVIARIO",
                    "3141-10 PROGRAMADOR DE USINAGEM",
                    "3911-35 PROGRAMADOR E CONTROLADOR DE MATERIAIS",
                    "7102-10 PROGRAMADOR FERROVIARIO",
                    "3171-10 PROGRAMADOR FRONT-END",
                    "7661-55 PROGRAMADOR VISUAL GRAFICO"
            };

            //Formatação de texto com String.format
            System.out.println(String.format("Temos um total de %d profissoe em nossa base de dados", profissoes.length));

            for (String nome : profissoes) {
                Profissao profissao = new Profissao();
                profissao.setNome(nome);
                profissaoRepository.save(profissao);
            }

        }
    }
}
