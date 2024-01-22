package digytal.desktop.app.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.service.modulo.acesso.EmpresaService;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaEvento;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaListener;
import digytal.desktop.util.utils.business.BusinessException;
import digytal.desktop.util.utils.http.Response;

public class PainelEmpresaIntegracao extends JPanel {
	private EmpresaService service = Context.getBean(EmpresaService.class);
	private SSCampoTexto cToken = new SSCampoTexto();
	private SSCampoTexto cWebHookToken = new SSCampoTexto();
	private SSCampoNumero cTaxaBoleto = new SSCampoNumero();
	private GridBagConstraints gbc;

	public PainelEmpresaIntegracao() {

		cToken.setRotulo("Asaas Token");
		cWebHookToken.setRotulo("Asaas Webhook Token");
		cTaxaBoleto.setRotulo("R$ Taxa Boleto");
		cToken.setPesquisa(true);
		cTaxaBoleto.setPesquisa(true);
		cTaxaBoleto.setPesquisaIcone("refresh");
		cToken.setPesquisaIcone("refresh");
		
		cWebHookToken.setPesquisa(true);
		cWebHookToken.setPesquisaIcone("refresh");
		cTaxaBoleto.setFormato(Formato.MOEDA);
		cTaxaBoleto.setMascara(Formato.MOEDA);


		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 0, 5);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(cToken, gbc);
		
		gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(cWebHookToken, gbc);
		

		gbc = new GridBagConstraints();
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(cTaxaBoleto, gbc);

		cToken.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				atualizarToken();
			}
		});
		cWebHookToken.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				atualizarWebhookToken();
			}
		});
		cTaxaBoleto.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				atualizarTaxaBoleto();
			}
		});
		cTaxaBoleto.setNumero(2.49);
	}

	private void atualizarToken() {
		try {
			Response res = service.alterarAsaasToken(cToken.getText());
			SSMensagem.informa(res.getStatus().getMessage());
		}catch (BusinessException e) {
			SSMensagem.avisa(e.getMessage());
		}
		

	}
	private void atualizarWebhookToken() {
		try {
			Response res = service.alterarAsaasWebhookToken(cWebHookToken.getText());
			SSMensagem.informa(res.getStatus().getMessage());
		}catch (BusinessException e) {
			SSMensagem.avisa(e.getMessage());
		}

	}

	private void atualizarTaxaBoleto() {
		try {
			Response res = service.alterarAsaasTaxaBoleto(cTaxaBoleto.getDouble());
			SSMensagem.informa(res.getStatus().getMessage());
		}catch (BusinessException e) {
			SSMensagem.avisa(e.getMessage());
		}

	}
}
