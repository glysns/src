public class Case1 {
    public static void main(String[] args) {
        //Depuração simples em um único fluxo de execução

        Integer numeroInteiro = 10;
        Double numeroDecimal = numeroInteiro + 2.756;

        System.out.println("Qual o valor da variavel numeroDecimal ");
        //Diante do resultado da linha acima, Faz sentido prosseguir?
        System.out.println(numeroDecimal);

        System.out.println("E agora?");
        numeroDecimal = numeroDecimal - 6;
        System.out.println(numeroDecimal);
    }
}