package com.digytal.control.repository;

import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.model.modulo.cadastro.CadastroEntity;
import com.digytal.control.model.modulo.cadastro.produto.categoria.CategoriaEntity;
import com.digytal.control.model.modulo.cadastro.produto.marca.MarcaEntity;
import com.digytal.control.model.modulo.cadastro.produto.modelo.ModeloEntity;
import com.digytal.control.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.digytal.control.infra.commons.validation.Attributes.ID;

@Repository
public class GlobalRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public <T> T findById(Class entity, Object id){
       return (T) entityManager.find(entity,id);
    }
    public boolean existsById(Class entity, Object id){
        return findById(entity,id)!=null ;
    }
    public MarcaEntity findMarca(Integer id){
        return findById(MarcaEntity.class,id);
    }

    public ModeloEntity findModelo(Integer id){
        return findById(ModeloEntity.class,id);
    }
    public CategoriaEntity findCategoria(Integer id){
        return findById(CategoriaEntity.class,id);
    }
    public UnidadeMedidaEntity findUnidaMedida(Integer id){
        return findById(UnidadeMedidaEntity.class,id);
    }
    public CadastroEntity findCadatro(Integer id){
        return findById(CadastroEntity.class,id);
    }
    public void existsMarca(Integer id){
        if(id!=null && findMarca(id)==null)
            throw new RegistroNaoLocalizadoException(Entities.MARCA_ENTITY, ID);
    }
    public void existsCategoria(Integer id){
        if(id!=null && findCategoria(id)==null)
            throw new RegistroNaoLocalizadoException(Entities.CATEGORIA_ENTITY, ID);
    }
    public void existsModelo(Integer id){
        if(id!=null && findModelo(id)==null)
            throw new RegistroNaoLocalizadoException(Entities.MODELO_ENTITY, ID);
    }
    public void existsUnidadeMedida(Integer id){
        if(id!=null && findUnidaMedida(id)==null)
            throw new RegistroNaoLocalizadoException(Entities.UNIDADE_MEDIDA_ENTITY, ID);
    }
    public void existsCadastro(Integer id){
        if(id!=null && findCadatro(id)==null)
            throw new RegistroNaoLocalizadoException(Entities.CADASTRO_ENTITY, ID);
    }
}
