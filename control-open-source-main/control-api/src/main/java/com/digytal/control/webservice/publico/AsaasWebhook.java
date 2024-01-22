package com.digytal.control.webservice.publico;

import com.digytal.control.infra.email.Message;
import com.digytal.control.infra.email.SendEmail;
import com.digytal.control.integracao.asaas.model.PagamentoEvent;
import com.digytal.control.service.modulo.financeiro.BoletoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/public/asaas")
@Tag(name = "Recursos publico")
@Slf4j
public class AsaasWebhook {
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private SendEmail sendEmail;
    @PostMapping("/webhook")
    public void recebendoPagamento( @RequestBody PagamentoEvent pagamento, @RequestHeader Map<String,Object> headers){
        String asaasToken = headers.get("asaas-access-token").toString(); // new StringBuilder("\n\n");
        log.info("RECECEBENDO WEEBHOOK ASAAS com asaas_access_token " + asaasToken);
        String email="gleyson@digytal.com.br";
        if(pagamento.getEvent().equals("PAYMENT_RECEIVED")) {
            try {
                email= boletoService.compensarViaWebhook(asaasToken, pagamento);
                Message message = new Message();
                message.setTo(email);
                message.setTitle("Compensação de Pagamentos");
                message.setBody("Pagamento Compensado com Sucesso " + pagamento.getPayment().getId() );
                log.info("{COMPENSACAO} Pagamento Assas " + pagamento.getPayment().getId());
                sendEmail.send(message);
            }catch (Exception ex){
                Message message = new Message();
                message.setTo(email);
                message.setTitle("Compensação de Pagamentos - ERRO");
                message.setBody(ex.getMessage());
                log.error("{ERRO} AO TENTAR COMPENSAR PAGAMENTO " + ex.getMessage());
                sendEmail.send(message);
            }
        }
        log.info("ASSAS WEBHOOK PAYLOAD REALIZADA  " + pagamento.toString());

    }
}
