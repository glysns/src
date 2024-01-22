package digytal.desktop.app.component;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

	public TableActionCellEditor() {
		super(new JCheckBox());
		// TODO Auto-generated constructor stub
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		PainelAcao painel = new PainelAcao();
		painel.setBackground(table.getBackground());
		return painel;
	}
}
