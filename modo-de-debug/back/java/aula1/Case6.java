import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class Case6 {
    public static void main(String[] args) {
        //Realizando a primeira aplicação
        BigDecimal valorAplicado            = new BigDecimal("1256.31");
        BigDecimal margemLuco               = new BigDecimal("0.5");
        BigDecimal lucro                    = valorAplicado.multiply(margemLuco).divide(new BigDecimal(100),5, RoundingMode.HALF_EVEN);//estilo calculadora
        BigDecimal valorCorrigido           = valorAplicado.add(lucro);//estilo calculadora

        BigDecimal saldoReal = valorCorrigido.setScale(2, RoundingMode.HALF_EVEN);
        System.out.println(valorCorrigido);
        System.out.println(saldoReal);

        System.out.println("Não confunda valor literal com valor formatado");

        String valorCorrigidoFormatadoParaReal = NumberFormat.getCurrencyInstance().format(valorCorrigido);
        System.out.println(valorCorrigidoFormatadoParaReal);
        //este resultado é prq minha JVM está em portugues, estude DecimalFormat
    }

}