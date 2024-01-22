package digytal.desktop.app.model.modulo.acesso.empresa;


import lombok.Data;

@Data
public class EmpresaSimplificadaResponse {
    private Integer id;
    private String cpfCnpj;
    private String nomeFantasia;
    private String sobrenomeSocial;
    private Integer organizacao;
    private boolean padrao;
}
