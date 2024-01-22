package digytal.desktop.app.component;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import digytal.desktop.components.desktop.BotaoAcao;

public class TableActionCellRender extends DefaultTableCellRenderer {
	private PainelAcao painel = new PainelAcao();
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		painel.setBackground(com.getBackground());
		return painel;
	}
	public void adicionar(BotaoAcao botao) {
		painel.adicionar(botao);
	}
}
