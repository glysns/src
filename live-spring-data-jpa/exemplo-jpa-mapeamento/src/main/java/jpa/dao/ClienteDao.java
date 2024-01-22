package jpa.dao;

import jpa.model.Cliente;
import javax.persistence.EntityManager;

public class ClienteDao {
    private EntityManager entityManager;
    public ClienteDao(){
        entityManager = FabricaConexao.getEntityManager();
    }
    public void save (Cliente cliente){
        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();
    }
}
