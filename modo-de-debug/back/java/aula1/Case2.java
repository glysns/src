import java.math.BigDecimal;
import java.math.RoundingMode;

public class Case2 {
    public static void main(String[] args) {
        //Depuração sobre uso de métodos de outras classes

        /*
            A classe BigDecimal é essencial para a realização de operações
            matemáticas mais complexas
         */

        BigDecimal valorAplicado    = new BigDecimal("1256.31");
        BigDecimal margemLuco       = new BigDecimal("0.5");
        BigDecimal lucro            = valorAplicado.multiply(margemLuco);
        lucro = lucro.divide(new BigDecimal(100),5, RoundingMode.HALF_EVEN);
        BigDecimal valorCorrigido   = valorAplicado.add(lucro);

        System.out.println(lucro);
        System.out.println(valorCorrigido);

    }
}