package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissaoDao {
    private EntityManager entityManager;
    public ProfissaoDao(){
        entityManager = FabricaConexao.getEntityManager();
    }
    public void save (Profissao profissao){
        entityManager.getTransaction().begin();
        entityManager.persist(profissao);
        entityManager.getTransaction().commit();
    }
    public void update(Profissao profissao){
        entityManager.getTransaction().begin();;
        entityManager.merge(profissao);
        entityManager.getTransaction().commit();
    }
    public int delete(Integer id){
        Profissao profissao = findById(id);
        if(profissao!=null){
            entityManager.getTransaction().begin();;
            entityManager.remove(profissao);
            entityManager.getTransaction().commit();
            return id;
        }else
            return 0;

    }
    //buscar um profissao na base atraves do seu id ?
    public Profissao findById(Integer id){
       return entityManager.find(Profissao.class,id);
    }
    public List<Profissao> findAll(){
        //JPQL x CRITERIA
        Query query = entityManager.createQuery("SELECT p FROM Profissao p",Profissao.class);//SELECT sobre a Entidade
        //entityManager.createNativeQuery("SELECT * FROM tab_profissao"); //forma nativa

        /**
         * Se precisar informar os parametros
            Query query = entityManager.createQuery("SELECT p FROM Profissao p WHERE p.nome :nome",Profissao.class);
            query.setParameter("nome", "PROGRAMADOR"); // -> Não esquece as conveções LIKE de SQL
         */

        return query.getResultList();
    }

}
