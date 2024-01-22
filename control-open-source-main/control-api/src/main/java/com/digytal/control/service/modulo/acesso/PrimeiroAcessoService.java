package com.digytal.control.service.modulo.acesso;

import com.digytal.control.infra.business.CampoObrigatorioException;
import com.digytal.control.infra.business.CpfCnpjInvalidoException;
import com.digytal.control.infra.business.RegistroIncompativelException;
import com.digytal.control.infra.commons.definition.Text;
import com.digytal.control.infra.commons.validation.Validation;
import com.digytal.control.infra.model.CredenciamentoResponse;
import com.digytal.control.infra.model.TipoLogin;
import com.digytal.control.model.modulo.acesso.empresa.EmpresaEntity;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoEntity;
import com.digytal.control.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import com.digytal.control.model.modulo.acesso.empresa.conta.ContaEntity;
import com.digytal.control.model.modulo.acesso.empresa.pagamento.FormaPagamentoEntity;
import com.digytal.control.model.modulo.acesso.organizacao.OrganizacaoEntity;
import com.digytal.control.model.comum.EntidadeCadastral;
import com.digytal.control.model.comum.MeioPagamento;
import com.digytal.control.model.comum.cadastramento.CadastroSimplificadoRequest;
import com.digytal.control.repository.modulo.acesso.empresa.AplicacaoRepository;
import com.digytal.control.repository.modulo.acesso.empresa.ContaRepository;
import com.digytal.control.repository.modulo.acesso.empresa.FormaPagamentoRepository;
import com.digytal.control.service.comum.CadastroFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.digytal.control.infra.commons.validation.Attributes.CPF_CNPJ;

@Service
public class PrimeiroAcessoService extends CadastroFactory {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private AplicacaoRepository aplicacaoRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Transactional
    public CredenciamentoResponse configurarPrimeiroAcesso(String cpfCnpj, CadastroSimplificadoRequest request){
        cpfCnpj = Text.onlyDigits(cpfCnpj);

        if(Validation.isEmpty(cpfCnpj))
            throw new CampoObrigatorioException(CPF_CNPJ);
        else {
            if (!Validation.cpfCnpj(cpfCnpj))
                throw new CpfCnpjInvalidoException();
        }
        EntidadeCadastral registro = build(request);
        EmpresaEntity entity = new EmpresaEntity();

        OrganizacaoEntity organizacao = cadastrarOrganizacao(cpfCnpj, request.getNomeFantasia().concat(" ").concat(request.getSobrenomeSocial()), request.getEmail());

        BeanUtils.copyProperties(registro, entity);
        entity.setCpfCnpj(cpfCnpj);
        entity.setOrganizacao(organizacao.getId());
        empresaRepository.save(entity);

        cadastrarContaFisica(cpfCnpj.trim().length()==14,entity.getId());
        cadastrarContasDigitais(entity.getId());
        cadastrarAplicacoes(organizacao.getId());

        CredenciamentoResponse response = cadastrarUsuario(entity, TipoLogin.CPF_CNPJ);
        return response;
    }

    private OrganizacaoEntity cadastrarOrganizacao(String cpfCnpj, String nome, String email){
        email = Text.lowerCase(email);
        if(organizacaoRepository.existsByCpfCnpjOrEmail(cpfCnpj, email))
            throw new RegistroIncompativelException("Não é possível concluir o credenciamento para este CPF/CNPJ e e-mail");

        OrganizacaoEntity organizacaoEntity = new OrganizacaoEntity();
        organizacaoEntity.setNome(Text.maxLength(nome.toUpperCase(),100));
        organizacaoEntity.setCpfCnpj(Text.onlyDigits(cpfCnpj));
        organizacaoEntity.setEmail(email);
        organizacaoRepository.save(organizacaoEntity);
        return organizacaoEntity;
    }
    private void cadastrarAplicacoes(Integer organizacao){
        AplicacaoEntity aplicacao = new AplicacaoEntity();
        aplicacao.setNome("Receitas");
        aplicacao.setLocaliza("RECEITAS");
        aplicacao.setTipo(AplicacaoTipo.RECEITA);
        aplicacao.setPrincipal(true);
        aplicacao.setNatureza(true);
        aplicacao.setOrganizacao(organizacao);
        aplicacaoRepository.save(aplicacao);

        aplicacao = new AplicacaoEntity();
        aplicacao.setNome("Despesas");
        aplicacao.setLocaliza("DESPESAS");
        aplicacao.setTipo(AplicacaoTipo.DESPESA);
        aplicacao.setPrincipal(true);
        aplicacao.setNatureza(true);
        aplicacao.setOrganizacao(organizacao);
        aplicacaoRepository.save(aplicacao);
    }
    private void cadastrarContaFisica(boolean pessoaJuridica, Integer empresa){
        ContaEntity entity = new ContaEntity();
        entity.setSaldo(0.0);
        entity.setBanco(9999);
        entity.setEmpresa(empresa);
        if(pessoaJuridica) {
            entity.setLegenda("Conta Balcão (caixa empresa)");
            entity.setDescricao("Conta destinada as movimentações financeiras de meio de pagamento em DINHEIRO, conhecida como conta caixa ou balcão");
        }else{
            entity.setLegenda("Conta Carteira");
            entity.setDescricao("Conta destinada as movimentações financeiras de meio de pagamento em DINHEIRO, conhecida como conta carteira");
        }
        entity.setAgencia("AG01-0");
        entity.setNumero("CC01-0");
        contaRepository.save(entity);
        cadastrarContaFisicaPagamento(entity.getId(), empresa);
    }
    private void cadastrarContasDigitais( Integer empresa){
        ContaEntity entity = new ContaEntity();
        entity.setSaldo(0.0);
        entity.setBanco(9999);
        entity.setEmpresa(empresa);
        entity.setAgencia("AG02-0");
        entity.setNumero("CC02-0");
        entity.setLegenda("Conta Banco (conta digital)");
        entity.setDescricao("Conta destinada as movimentações financeiras de meio de pagamento como PIX, DEBITO, CREDITO, BOLETO, PRAZO");
        contaRepository.save(entity);
        cadastrarContasDigitaisPagamento(entity.getId(), empresa);
    }
    private void cadastrarContaFisicaPagamento(Integer conta, Integer empresa){
        formaPagamentoRepository.save(forma(MeioPagamento.DINHEIRO, conta, empresa));
    }
    private void cadastrarContasDigitaisPagamento(Integer conta, Integer empresa){
        formaPagamentoRepository.save(forma(MeioPagamento.PIX, conta, empresa));
        formaPagamentoRepository.save(forma(MeioPagamento.DEBITO, conta, empresa));
        formaPagamentoRepository.save(forma(MeioPagamento.CREDITO, conta, empresa));
        formaPagamentoRepository.save(forma(MeioPagamento.PRAZO, conta, empresa));
        formaPagamentoRepository.save(forma(MeioPagamento.BOLETO, conta, empresa));
    }
    private FormaPagamentoEntity forma(MeioPagamento meioPagamento, Integer conta, Integer empresa){
        FormaPagamentoEntity entity = new FormaPagamentoEntity();
        entity.setMeioPagamento(meioPagamento);
        entity.setTaxa(0.0);
        entity.setConta(conta);
        entity.setEmpresa(empresa);
        entity.setNumeroParcelas(1);
        return entity;
    }
}
