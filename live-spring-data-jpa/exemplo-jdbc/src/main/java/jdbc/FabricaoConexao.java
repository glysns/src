package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaoConexao {
    private static Connection connection;
    public static void iniciarConexao(){
        /**
         isso só deve acontecer uma vez ao longo da aplicação
         Se algum dia precisar, estude sobre padrão singleton
         */
        try{
            //simulando apecto de singleton
            if(connection==null) {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/live-spring-data-jpa", "postgres", "postgres");
                System.out.println("CONEXAO REALIZADA COM SUCESSO");
            }
        }catch (Exception ex){
            ex.printStackTrace();
            throw new RuntimeException("Erro ao tentar realizar uma conexão");
        }
    }
    public static Connection getConnection(){

        return connection;
    }
}
