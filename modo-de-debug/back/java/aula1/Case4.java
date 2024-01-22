import java.math.BigDecimal;
import java.math.RoundingMode;

public class Case4 {
    public static void main(String[] args) {
        //Depuração sobre uso das classes de seu projeto

        BigDecimal valorAplicado    = new BigDecimal(1000.0);
        BigDecimal margemLuco       = new BigDecimal(1.0);
        BigDecimal lucro            = calcularLucro(); // 10.0 se não, é um Bug
        BigDecimal valorCorrigido   = valorAplicado.add(lucro);

        System.out.println(valorCorrigido);
    }
    static BigDecimal calcularLucro(){
        ///RoundingMode
        return null;
    }
}