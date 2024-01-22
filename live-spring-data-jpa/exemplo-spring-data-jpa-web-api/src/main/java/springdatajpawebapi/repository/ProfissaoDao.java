package springdatajpawebapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springdatajpawebapi.model.Profissao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProfissaoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void save (Profissao profissao){
        entityManager.persist(profissao);
    }
    @Transactional
    public void update(Profissao profissao){
        entityManager.merge(profissao);
    }
    @Transactional
    public int delete(Integer id){
        Profissao profissao = findById(id);
        if(profissao!=null){
            entityManager.remove(profissao);
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
        //entityManager.createNativeQuery("SELECT * FROM tab_profissao");

        //query.setParameter();
        return query.getResultList();
    }
}
