package starter.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import starter.service.CadastroMigrationService;
import starter.service.InscricaoAlunoMigrationService;
import starter.service.InscricaoMigrationService;

@Component
@Slf4j
public class MigrationJob { //implements CommandLineRunner {
    @Autowired
    private CadastroMigrationService cadastroMigrationService;
    @Autowired
    private InscricaoMigrationService inscricaoMigrationService;

    @Autowired
    private InscricaoAlunoMigrationService inscricaoAlunoMigrationService;

    public void migrateCadastros(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                cadastroMigrationService.startMigrationIdGreaterThan(27381);
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
    public void migrateInscricoesAluno(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                inscricaoAlunoMigrationService.startMigrationIdGreaterThan(22659);
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public void migrateInscricoes(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                inscricaoMigrationService.startMigrationIdGreaterThan(22819);;
            }
        };
        Thread t = new Thread(r);
        t.start();

    }


}
