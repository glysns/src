package digytal.desktop.app.model.modulo.comum.telefone;

import lombok.Data;

@Data
public class Telefone {
    private Long fixo;
    private Long celular;
    private boolean celularWhatsApp;
}
