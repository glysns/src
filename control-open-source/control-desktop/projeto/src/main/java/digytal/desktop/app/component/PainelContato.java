package digytal.desktop.app.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.JPanel;

import digytal.desktop.components.desktop.ss.SSCampoMascara;
import digytal.desktop.components.desktop.ss.SSCampoSelecao;
import digytal.desktop.components.desktop.ss.SSCampoTexto;

public class PainelContato extends JPanel {
	private SSCampoTexto cEmail = new SSCampoTexto();
	private SSCampoMascara cCelular = new SSCampoMascara();
	private SSCampoSelecao cWhatsApp = new SSCampoSelecao();
	
	//private Contato entidade;
	
	public PainelContato() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_cEmail = new GridBagConstraints();
		gbc_cEmail.weightx = 1.0;
		gbc_cEmail.anchor = GridBagConstraints.NORTHEAST;
		gbc_cEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_cEmail.gridx = 0;
		gbc_cEmail.gridy = 0;
		add(cEmail, gbc_cEmail);
		
		
		
		GridBagConstraints gbc_cCelular = new GridBagConstraints();
		gbc_cCelular.anchor = GridBagConstraints.NORTHEAST;
		gbc_cCelular.insets = new Insets(0, 3, 0, 0);
		gbc_cCelular.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCelular.gridx = 1;
		gbc_cCelular.gridy = 0;
		add(cCelular, gbc_cCelular);
		
		
		
		GridBagConstraints gbc_cCelular2 = new GridBagConstraints();
		gbc_cCelular2.insets = new Insets(0, 3, 0, 0);
		gbc_cCelular2.anchor = GridBagConstraints.NORTHEAST;
		gbc_cCelular2.fill = GridBagConstraints.HORIZONTAL;
		gbc_cCelular2.gridx = 2;
		gbc_cCelular2.gridy = 0;
		add(cWhatsApp, gbc_cCelular2);
		
		try {
			cCelular.setMascara("(##)#####-####");
			cEmail.setTudoMinusculo(true);
			cCelular.setColunas(10);
			//cEmail.setColunas(10);
			
			cCelular.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					
				}

				public void focusLost(FocusEvent e) {
					
				}
			});
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cCelular.setRotulo("Celular");
		cWhatsApp.setRotulo("Whats App?");
		
		cEmail.setRotulo("E-mail");
	}
	public SSCampoTexto getcEmail() {
		return cEmail;
	}
	public SSCampoMascara getcCelular() {
		return cCelular;
	}
	public SSCampoSelecao getcWhatsApp() {
		return cWhatsApp;
	}
}
