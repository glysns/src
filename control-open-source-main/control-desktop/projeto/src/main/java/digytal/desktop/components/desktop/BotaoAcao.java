package digytal.desktop.components.desktop;

import javax.swing.border.EmptyBorder;

import digytal.desktop.components.desktop.ss.SSBotao;

public class BotaoAcao extends SSBotao {
	public BotaoAcao(String texto, String icone) {
		//setContentAreaFilled(false);
		//setBorder(new EmptyBorder(2, 2, 2, 2));
		setText(texto);
		setIcone(icone);
		
	}
}
