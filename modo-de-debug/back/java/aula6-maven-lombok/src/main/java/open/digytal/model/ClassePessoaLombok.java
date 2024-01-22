package open.digytal.model;

import lombok.*;

import java.time.LocalDate;

//@Data - abreviação para @Getter e @Setter
@Getter
@RequiredArgsConstructor
public class ClassePessoaLombok {
    //@Setter(AccessLevel.NONE) -> se fosse @Data o ID não deveria ter o setter
    private Integer databaseId;

    // @RequiredArgsConstructor + @NonNull
    //combinacao apropriada para
    //determinar campos que serão
    //requeridos no construtora
    @NonNull
    private LocalDate dataNascimento;
    @NonNull
    private String sexo;
    @Setter
    private String nome;

}
