import java.math.BigDecimal;
import java.math.RoundingMode;

public class Case5 {
    public static void main(String[] args) {
        //Arredondamentos e Formatações
        BigDecimal valor = new BigDecimal(12.75); //12.75
        BigDecimal divisor = new BigDecimal(8); //8 -> 1.59375

        for(RoundingMode rm: RoundingMode.values()){
            if(rm != RoundingMode.UNNECESSARY){
                BigDecimal resultado = valor.divide(divisor, 2, rm);
                System.out.println("O resultado da divisão com o arredondamento " + rm.name() + " é igual a " + resultado);
            }
        }
        //HALF_EVEN é o mais utilizado
        /**
         * Modo de arredondamento para arredondar em direção ao "vizinho mais próximo", a menos que ambos os vizinhos sejam equidistantes;
         * nesse caso, arredondar em direção ao vizinho par. Comporta-se como ROUND_HALF_UP se o dígito à esquerda da fração descartada for ímpar;
         * se comporta como ROUND_HALF_DOWN se for par.

         * Observe que este é o modo de arredondamento que minimiza o erro cumulativo quando aplicado repetidamente em uma sequência de cálculos.
         */
    }

}