package digytal.desktop.app.model.modulo.param;

import lombok.Data;

@Data
public class CodigoPostal {
    private String cep;
    private String logradouro;
    private String bairro;
    private String complemento;
    private String localidade;
    private String estado;
    private String uf;
    private Integer ibge;
    private boolean valido;
    private String pais = "BRASIL";
}
