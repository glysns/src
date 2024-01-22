package digytal.desktop.app.form.modulo.acesso.empresa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.component.PainelEmpresaConta;
import digytal.desktop.app.component.PainelEmpresaIntegracao;
import digytal.desktop.app.context.Context;
import digytal.desktop.app.form.modulo.cadastro.FrmCadastro;
import digytal.desktop.app.model.modulo.acesso.empresa.EmpresaRequest;
import digytal.desktop.app.model.modulo.acesso.empresa.EmpresaResponse;
import digytal.desktop.app.service.modulo.acesso.EmpresaService;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmEmpresaCadastro extends FrmCadastro {
	private static final Logger LOGGER = LogManager.getLogger(FrmEmpresaCadastro.class);
	private PainelEmpresaConta painelEmpresaConta = new PainelEmpresaConta();
	private PainelEmpresaIntegracao painelEmpresaIntegracao = new PainelEmpresaIntegracao();
	@Autowired
	private EmpresaService service;
	public FrmEmpresaCadastro() {
		setTitulo("Empresa");
		setDescricao("Cadastro das empresas do sistema");
		adicionarTab("Contas", painelEmpresaConta);
		adicionarTab("Asaas", painelEmpresaIntegracao);
	}
	@Override
	public void exibirRegistro(Object registro) {
		this.registro = (EmpresaResponse) registro;
		if(registro!=null) {
			this.exibirRegistro();
			painelEmpresaConta.exibirContas();
		}
	}
	
	@Override
	public void confirmar() {
		try {
			if (SSMensagem.pergunta("Confirma realizar esta operação?")) {
			
				EmpresaRequest cadastro = new EmpresaRequest();
				BeanUtils.copyProperties(atualizarDados(),cadastro);
				cadastro.setOrganizacao(Context.getOrganizacaoId());
				Response<Integer> response= null;
				if(registro!=null)
					response = service.alterar(registro.getId(), cadastro);
				else
					response = service.incluir(cadastro);
				
				SSMensagem.informa(response.getStatus().getMessage());
				
				this.fechar();
				
				
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			LOGGER.error("ERRO AO REQUISITAR API", ex);
			SSMensagem.erro("Erro ao tentar executar a operação");
		}
	}

}
