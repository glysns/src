package com.digytal.control.model.modulo.acesso.empresa;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class EmpresaIntegracao {
    @Column(name = "asaas_token")
    private String asaasToken;
    @Column(name = "asaas_webhook_token")
    private String asaasWebhookToken;
    @Column(name = "asaas_tx_emissao_boleto")
    private Double asaasTaxaEmissaoBoleto;
    @Column(name = "asaas_tx_emissao_pix")
    private Double asaasTaxaEmissaoPix;
    @Column(name = "asaas_is_conta_empresa")
    private boolean contaEmpresa;
}
