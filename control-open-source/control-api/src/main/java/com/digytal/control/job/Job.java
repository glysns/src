package com.digytal.control.job;


import com.digytal.control.infra.email.Message;
import com.digytal.control.infra.email.SendEmail;
import com.digytal.control.model.modulo.financeiro.parcelamento.parcela.ParcelaEntity;
import com.digytal.control.repository.modulo.fincanceiro.ParcelaRepository;
import com.digytal.control.service.modulo.financeiro.BoletoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class Job {

    @Autowired
    private ParcelaRepository parcelaRepository;
    @Autowired
    private BoletoService boletoService;

    @Autowired
    private SendEmail sendEmail;
    /*
    https://mmarcosab.medium.com/agendando-coisas-em-programas-springboot-com-scheduled-1410bea2dda9
    “@Scheduled(cron = “1 2 3 4 5 6")”
        1: segundo (preenchido de 0 a 59)
        2: minuto (preenchido de 0 a 59)
        3: hora (preenchido de 0 a 23)
        4: dia (preenchido de 0 a 31)
        5: mês (preenchido de 1 a 12)
        6: dia da semana (preenchido de 0 a 6)
     */
    @Scheduled(cron = "0 0 0/6 * * *")
    public void compensarParcelas(){
        try{
            LocalDate dataInicial = LocalDate.now();//.withDayOfMonth(1);
            LocalDate dataFinal = dataInicial.plusMonths(1);
            log.info("Executando o Job de consultarParcelasPendentes junto a Asaas Pagamento via BOLETO ");
            List<ParcelaEntity> parcelas = parcelaRepository.listarParcelasBoleto(dataInicial, dataFinal);
            log.info("Consultando as parcelas com vencimentos entre {} e {}", dataInicial, dataFinal);
            for (ParcelaEntity parcela : parcelas) {
                log.info("Processando a parcela id:{} número{} e boleto número de integração {}", parcela.getId(), parcela.getDetalhe().getNumeroParcela(), parcela.getBoleto().getNumeroAutorizacao());
                boletoService.compensarViaJob(parcela);
                Thread.sleep(1000 * 30);
            }
        }catch (Exception ex){
            log.error("{ERRO} ao processar a compensação de pagamentos via Asaas Pay");
            String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            Message message = new Message();
            message.setTo("gleyson.s@hotmail.com");
            message.setTitle("Resumo de Compensação de Boletos: " + dataHora);
            message.setBody("Erro ao tentar realizar a compensação diária");
            sendEmail.send(message);
        }
    }
}
