package jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaConexao {
    private static EntityManager entityManager;
    public static void iniciarConexao(){
        /**
         isso só deve acontecer uma vez ao longo da aplicação
         Se algum dia precisar, estude sobre padrão singleton
         */
        try{
            //simulando apecto de singleton
            if(entityManager==null) {
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("MY_PU");
                entityManager = factory.createEntityManager();
                System.out.println("conexao realizada com sucesso");
            }
        }catch (Exception ex){
            throw new RuntimeException("Erro ao tentar realizar uma conexão");
        }

    }
    public static EntityManager getEntityManager() {
       return  entityManager;
    }
}
