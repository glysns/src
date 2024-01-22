package open.digytal.model;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassePessoaBizarra {
    private LocalDate dataNascimento;
    private String sexo;
    private String nome;
}
