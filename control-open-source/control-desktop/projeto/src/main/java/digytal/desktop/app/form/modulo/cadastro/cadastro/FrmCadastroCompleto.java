package digytal.desktop.app.form.modulo.cadastro.cadastro;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.form.modulo.cadastro.FrmCadastro;
import digytal.desktop.app.model.modulo.cadastro.CadastroRequest;
import digytal.desktop.app.model.modulo.cadastro.CadastroResponse;
import digytal.desktop.app.service.modulo.cadastro.CadastroService;
import digytal.desktop.components.desktop.ss.SSCampoSelecao;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.util.SSTexto;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmCadastroCompleto extends FrmCadastro {
	private static final Logger LOGGER = LogManager.getLogger(FrmCadastroCompleto.class);
	private SSCampoSelecao cCliente = new SSCampoSelecao();
	private SSCampoSelecao cFornecedor = new SSCampoSelecao();
	@Autowired
	private CadastroService service;
	public FrmCadastroCompleto() {
		setTitulo("Clientes");
		setDescricao("Cadastro dos clientes da empresa");
		cCliente.setRotulo("Cliente");
		cFornecedor.setRotulo("Fornecedor");
		pPapel.add(cCliente);
		pPapel.add(cFornecedor);
		painelPessoa.getcCpfCnpj().addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			
			}

			public void focusLost(FocusEvent e) {
				checarCpfCnpj();
			}
		});
	}
	@Override
	public void exibirRegistro(Object registro) {
		this.registro = (CadastroResponse) registro;
		if(registro!=null)
			exibirCadastro();
	}
	private void exibirCadastro() {
		cCliente.setSelected( ((CadastroResponse)registro).getPerfil().isCliente());
		cFornecedor.setSelected(((CadastroResponse)registro).getPerfil().isFornecedor());
		exibirRegistro();
	}
	
	private void checarCpfCnpj() {
		String cpfCnpj= (painelPessoa.getcCpfCnpj().getClipText());
		registro = service.buscar(cpfCnpj);
		if(registro!=null) {
			SSMensagem.informa("Já existe um cadastro com este CPF/CNPJ");
			exibirRegistro(registro);
		}
			
	}
	@Override
	public void confirmar() {
		try {
			CadastroRequest cadastro = new CadastroRequest();
			BeanUtils.copyProperties(atualizarDados(),cadastro);
			
			if (SSMensagem.pergunta("Confirma realizar esta operação?")) {
			
				cadastro.getPerfil().setCliente(cCliente.isSelected());
				cadastro.getPerfil().setFornecedor(cFornecedor.isSelected());
			
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
