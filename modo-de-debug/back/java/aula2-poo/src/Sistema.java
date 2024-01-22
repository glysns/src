import java.time.LocalDate;

public class Sistema {
    public static void main(String[] args) {
        Cadastro gleyson = new Cadastro();
        gleyson.cpf="23434";
        gleyson.sexo = Sexo.MASCULINO;
        gleyson.dataNascimento = LocalDate.of(1984,6,30);

        gleyson.endereco = ???

    }
}