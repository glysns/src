package digytal.desktop.components.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import digytal.desktop.components.desktop.ss.SSBotao;
import digytal.desktop.components.desktop.ss.commons.SSBotaoFechar;
import digytal.desktop.components.enums.TipoOperacao;

public abstract class FormularioCadastro extends Formulario{
	protected JCheckBox cNovo = new JCheckBox("Novo?");
	protected SSBotao bConfirmar = new SSBotao();
	protected SSBotaoFechar bFechar = new SSBotaoFechar();
	protected TipoOperacao tipoOperacao;
	private JComponent primeiroCampo;
	public FormularioCadastro() {
		bConfirmar.setText("Gravar");
		bFechar.setText("Fechar");
		bConfirmar.setIcone("save");
		bFechar.setIcone("close");
		rodape.add(bConfirmar);
		rodape.add(bFechar);
		bConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				confirmar();
			}
		});
		bFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fechar();
			}
		});
	}
	public void setExibirNovo() {
		rodape.add(cNovo);
	}
	
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public void setPrimeiroCampo(JComponent primeiroCampo) {
		this.primeiroCampo = primeiroCampo;
		focus();
	}
	public abstract void exibirRegistro(Object entidade);
	
	private void focus() {
		if(primeiroCampo!=null)
			primeiroCampo.requestFocus();
	}
	public abstract void confirmar();
	public void novoCadastro() {
		try {
			//confirmar();
			if(cNovo.isSelected()) {
				//entidade = entidade.getClass().newInstance();
				//setRegistro(entidade);
				focus();
			}else
				fechar();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
