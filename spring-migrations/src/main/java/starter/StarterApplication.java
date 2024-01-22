package starter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import starter.job.ApuracaoJob;
import starter.job.MigrationJob;

@SpringBootApplication
@EnableScheduling
public class StarterApplication {
    public static void main(String[] args) {
        //Locale.setDefault(new Locale("pt","BR"));
        //TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        ConfigurableApplicationContext configContext = SpringApplication.run(StarterApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApuracaoJob job) throws Exception {
        return args -> {
            //select * from SIIG_DAT.dbo.PI_LOT_Entidade as Entidade  where Entidade.idGrupo = 4 and Nome like '%RAMA BOA%';
            job.realizarApuracaoViaPlanilha("406 - CETI RAMA BOA - TESTE.xlsx");
            System.out.println("FIM");
        };
    }

}
