package digytal.desktop.app.component;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import digytal.desktop.components.desktop.BotaoAcao;

public class PainelAcao extends JPanel {
	public PainelAcao() {
		setLayout(new FlowLayout());
	}
	public void adicionar(BotaoAcao botao) {
		add(botao);
	}
}
