package com.digytal.control.service.modulo.acesso;
import com.digytal.control.infra.business.BusinessException;
import com.digytal.control.infra.business.CampoObrigatorioException;
import com.digytal.control.infra.business.ErroNaoMapeadoException;
import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.validation.Validation;
import com.digytal.control.infra.commons.validation.Validations;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoEntity;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoRequest;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoResponse;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.repository.modulo.acesso.empresa.AplicacaoRepository;
import com.digytal.control.service.comum.AbstractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.digytal.control.infra.commons.validation.Attributes.*;
import static com.digytal.control.infra.commons.validation.Entities.APLICACAO_ENTITY;

@Service
@Slf4j
public class AplicacaoService extends AbstractService {
    @Autowired
    private AplicacaoRepository repository;
    public List<AplicacaoResponse> listarAreas(String nome){
        nome = Objects.toString(nome,"");
        return convert(repository.listarAreas(requestInfo.getOrganizacao(), nome));
    }
    public List<AplicacaoResponse> listarReceitas(String nome){
        nome = Objects.toString(nome,"");
        return convert(repository.listarNaturezas(requestInfo.getOrganizacao(), AplicacaoTipo.RECEITA, nome));
    }
    public List<AplicacaoResponse> listarDespesas(String nome){
        nome = Objects.toString(nome,"");
        return convert(repository.listarNaturezas(requestInfo.getOrganizacao(), AplicacaoTipo.DESPESA, nome));
    }public List<AplicacaoResponse> listagem(String area){
        return convert(repository.listarNaturezas(requestInfo.getOrganizacao(), AplicacaoTipo.DESPESA, area));
    }
    private List<AplicacaoResponse> convert(List<AplicacaoEntity> list){
        return list.stream().map(e-> {
            AplicacaoResponse item= new AplicacaoResponse();
            BeanUtils.copyProperties(e,item);
            return item;
        }).collect(Collectors.toList());
    }
    public Integer incluirArea(AplicacaoRequest request){
        return incluir(request, null, true, false);
    }
    public Integer incluirReceita(AplicacaoRequest request){
        return incluir(request, AplicacaoTipo.RECEITA, false, true);
    }
    public Integer incluirDespesa(AplicacaoRequest request){
        return incluir(request, AplicacaoTipo.DESPESA, false, true);
    }
    private Integer incluir(AplicacaoRequest request, AplicacaoTipo tipo, boolean area, boolean natureza){
        try{

        Validations.build(NOME).notEmpty().minLen(2).maxLen(50).check(request);

        AplicacaoEntity entity = new AplicacaoEntity();
        entity.setOrganizacao(requestInfo.getOrganizacao());
        entity.setTipo(tipo);
        entity.setNatureza(natureza);
        entity.setArea(area);
        BeanUtils.copyProperties(request,entity);
        entity.setLocaliza(normalizar(request.getNome()));
        repository.save(entity);
        return entity.getId() ;
        }catch (BusinessException ex){
            log.warn(BusinessException.logMessage(ex));
            throw ex;
        }catch (Exception ex){
            log.error(BusinessException.errorMessage("Não foi possível incluir ou alterar o produto [ %s ]", request.getNome()), ex);
            throw new ErroNaoMapeadoException();
        }
    }
    public boolean alterarNome(Integer id,String nome){
        if(Validation.isEmpty(nome))
            throw new CampoObrigatorioException("Nome");

        AplicacaoEntity entity = repository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException(APLICACAO_ENTITY, ID));
        entity.setNome(nome);
        repository.save(entity);
        return true;
    }
}
