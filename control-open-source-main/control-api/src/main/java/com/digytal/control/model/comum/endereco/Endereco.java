package com.digytal.control.model.comum.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@Embeddable
@Schema(name = "Requisição do endereco",description = "Pré requisito para endereco do cadastro")
public class Endereco {
    @Schema(description="cep do endereço no cadastro", minLength = 11,maxLength = 11, example = "64000110")
    @Column(name = "end_cep")
    private String cep;
    @Schema(description="logradouro do endereço no cadastro", maxLength = 50,example = "PRACA DA SE")
    @Column(name = "end_logradouro")
    private String logradouro;
    @Schema(description="número do endereço no cadastro", maxLength = 50,example = "123-A")
    @Column(name = "end_numero")
    private String numero;
    @Schema(description="bairro do endereço no cadastro", maxLength = 50,example = "CENTRO")
    @Column(name = "end_bairro")
    private String bairro;
    @Schema(description="complemento do endereço no cadastro", maxLength = 50,example = "BL-A AP-121")
    @Column(name = "end_complemento")
    private String complemento;
    @Schema(description="referência do endereço no cadastro", maxLength = 50,example = "EM FRENTE A BI-FARMA")
    @Column(name = "end_referencia")
    private String referencia;
    @Schema(description="telefone do endereço no cadastro", maxLength = 11,example = "11912345678")
    @Column(name = "end_telefone")
    private Long telefone;
    @Schema(description="código IBGE da cidade do endereço no cadastro", maxLength = 7, example = "1200013")
    @Column(name = "end_ibge")
    private Integer ibge;
    @Schema(description="cidade do endereço",subTypes = Cidade.class)
    @Embedded
    private Cidade cidade = new Cidade();
    public String getDomicilio(){
        return String.format("%s,%s%s,%s-%s/%s - %s", logradouro, numero,complemento, bairro, cidade.getNome(), cidade.getUf(), cep);
    }
}
