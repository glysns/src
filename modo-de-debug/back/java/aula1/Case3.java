import java.math.BigDecimal;
import java.math.RoundingMode;

public class Case3 {
    public static void main(String[] args) {
        //Depuração sobre uso dos métodos em sua classe

        /*
            A classe BigDecimal é essencial para a realização de operações
            matemáticas mais complexas
         */

        BigDecimal valorAplicado    = new BigDecimal("1256.31");
        BigDecimal margemLuco       = new BigDecimal("0.5");
        BigDecimal lucro            = calcularLucro(valorAplicado, margemLuco, 2); // 10.0 se não, é um Bug
        BigDecimal valorCorrigido   = valorAplicado.add(lucro);

        System.out.println(valorCorrigido);
    }
    static BigDecimal calcularLucro(BigDecimal valorAplicado, BigDecimal margemLuco, int escala ){
        BigDecimal resultado = valorAplicado.multiply(margemLuco);
        resultado = resultado.divide(new BigDecimal(100),escala, RoundingMode.HALF_EVEN);
        return resultado;
    }
}