package com.digytal.control.service.modulo.cadastro.produto;

import com.digytal.control.infra.business.RegistroNaoLocalizadoException;
import com.digytal.control.infra.commons.definition.Definition;
import com.digytal.control.infra.commons.validation.Entities;
import com.digytal.control.infra.commons.validation.Validations;
import com.digytal.control.infra.utils.Calculos;
import com.digytal.control.model.comum.Associacao;
import com.digytal.control.model.consulta.lancamento.PagamentoFiltro;
import com.digytal.control.model.consulta.produto.ProdutoFiltro;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoApp;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoEntity;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoRequest;
import com.digytal.control.model.modulo.cadastro.produto.ProdutoResponse;
import com.digytal.control.model.modulo.cadastro.produto.marca.MarcaEntity;
import com.digytal.control.model.modulo.cadastro.produto.marca.MarcaResponse;
import com.digytal.control.model.modulo.cadastro.produto.unidademedida.UnidadeMedidaEntity;
import com.digytal.control.model.modulo.financeiro.pagamento.response.PagamentoResponse;
import com.digytal.control.repository.modulo.acesso.OrganizacaoRepository;
import com.digytal.control.repository.modulo.cadastro.ProdutoRepository;
import com.digytal.control.repository.modulo.cadastro.UnidadeMedidaRepository;
import com.digytal.control.service.comum.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.digytal.control.infra.commons.validation.Attributes.*;

@Service
public class ProdutoService extends AbstractService {
    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    public Integer incluir(ProdutoRequest request) {
        return gravar(null, request);
    }

    public Integer alterar(Integer id, ProdutoRequest request) {
        return gravar(id, request);
    }

    @Transactional
    private Integer gravar(Integer id, ProdutoRequest request) {
        Validations.build(NOME, VALOR, UNIDADE_MEDIDA).notEmpty().check(request);

        request.setNomeAbreviado(Definition.seNuloOuVazio(request.getNomeAbreviado(),request.getNome(),25));
        request.setDescricao(Definition.seNuloOuVazio(request.getDescricao(),request.getNome()));
        request.setCodigoBarras(Definition.seNulo(request.getCodigoBarras()));
        request.setSku(Definition.seNulo(request.getSku()));
        request.setTaxaLiquidacao(Calculos.seNuloZera(request.getTaxaLiquidacao()));

        Validations.build(NOME).minLen(2).maxLen(50).check(request);
        Validations.build(NOME_ABREVIADO).maxLen(25).check(request);
        Validations.build(DESCRICAO).maxLen(150).check(request);
        Validations.build(SKU, CODIGO_BARRAS).maxLen(15).check(request);

        globalRepository.existsMarca(request.getMarca());
        globalRepository.existsModelo(request.getModelo());
        globalRepository.existsCategoria(request.getCategoria());
        globalRepository.existsUnidadeMedida(request.getUnidadeMedida());
        globalRepository.existsUnidadeMedida(request.getUnidadeEmbalagem());

        ProdutoEntity entity = new ProdutoEntity();
        if (id == null) {
            entity.setOrganizacao(requestInfo.getOrganizacao());
        } else {
            entity = repository.findById(id).orElseThrow(() -> new RegistroNaoLocalizadoException(Entities.PRODUTO_ENTITY, ID));
        }

        checarIntegridadeOrganizacional(entity.getOrganizacao());
        ProdutoApp app = new ProdutoApp();
        BeanUtils.copyProperties(request,entity);
        entity.setLocaliza(normalizar(request.getNome()));
        entity.setApp(app);
        entity.setSaldo(0.0);

        UnidadeMedidaEntity unidadeMedidaEntity = unidadeMedidaRepository.findById(entity.getUnidadeMedida()).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.UNIDADE_MEDIDA_ENTITY, ID) );
        entity.setUnidadeMedidaSigla(unidadeMedidaEntity.getSigla());

        repository.save(entity);
        return entity.getId();
    }
    public List<ProdutoResponse> consultar(String nome) {
        nome = normalizar(Objects.toString(nome, ""));
        List<ProdutoEntity> list = repository.findByOrganizacaoAndLocalizaContaining(requestInfo.getOrganizacao(), nome);
        List<ProdutoResponse> response = list.stream().map(this::convert).collect(Collectors.toList());
        if (response.isEmpty()) {
            throw new RegistroNaoLocalizadoException(Entities.PRODUTO_ENTITY, NOME);
        }
        return response;

    }
    public ProdutoResponse buscar(Integer id){
        return repository.findById(id).map(this::convert).orElseThrow(()-> new RegistroNaoLocalizadoException(Entities.MARCA_ENTITY, ID));
    }
    private ProdutoResponse convert(ProdutoEntity entity){
        ProdutoResponse response = new ProdutoResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }
    public List<Associacao> listar(String nome) {
        return consultar(nome).stream().map(i -> {
            Associacao item = new Associacao();
            item.setId(i.getId());
            item.setIdentificador(i.getId());
            item.setDescricao(i.getNome());
            return item;
        }).collect(Collectors.toList());

    }
    public List<ProdutoResponse> pesquisar(ProdutoFiltro filtro){
        return repository.pesquisar(requestInfo.getOrganizacao(), filtro);
    }
}
