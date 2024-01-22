package digytal.desktop.components.desktop.ss;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import digytal.desktop.components.Fonte;
import digytal.desktop.components.desktop.ss.evento.SSAlteracaoEvento;
import digytal.desktop.components.desktop.ss.evento.SSAlteracaoListener;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaEvento;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


/**
 *@beaninfo
 *   attribute: isContainer false
 * description: Caixa de texto.
 *
 * @author  Frank Marlon M. dos Santos
 * @version 1.0 08/18/08
 * @see SSCampoMascara
 */
public class SSCampoSelecao extends SSComponenteRotulado implements SSComponente {    
    private JCheckBox txtCaixaSelecao = new JCheckBox("");
    
        
    public SSCampoSelecao() {        
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    
    private void jbInit() throws Exception {
    	setComponente(txtCaixaSelecao);    
    }
    
    public void setHorizontalAlignment(int alinhamento) {
        txtCaixaSelecao.setHorizontalAlignment(alinhamento);
    }
    
    public int getHorizontalAlignment() {
        return txtCaixaSelecao.getHorizontalAlignment();
    }
    
    

    /**
     * Adds the specified action listener to receive
     * action events from this textfield.
     *
     * @param l the action listener to be added
     */
    public synchronized void addActionListener(ActionListener l) {
        txtCaixaSelecao.addActionListener(l);
    }

    public synchronized void removeActionListener(ActionListener l) {
        txtCaixaSelecao.removeActionListener(l);
    }

    public synchronized ActionListener[] getActionListeners() {
        return txtCaixaSelecao.getActionListeners();
    }

    
    protected void setCaixaTexto(JCheckBox txtCaixaTexto) {
        this.remove(this.txtCaixaSelecao);
        this.txtCaixaSelecao = txtCaixaTexto;
        try {
            jbInit();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    protected JCheckBox getCaixaTexto() {
        return txtCaixaSelecao;
    }

    
   
   
    public Object getValue() {
        return txtCaixaSelecao.isSelected();
    }
    public boolean isSelected() {
        return txtCaixaSelecao.isSelected();
    }

    public void setValue(Object value) {
        //setText(value != null? value.toString(): null);
        txtCaixaSelecao.setSelected(value==null?false:Boolean.parseBoolean(value.toString()));
        //txtCaixaTexto.setSelected(value==null?false:Boolean.parseBoolean(value.toString()));
    }
    public void setSelected(boolean value) {
    	setValue(value);
    }

	@Override
	public boolean isEditavel() {
		return txtCaixaSelecao.isEnabled();
	}

	@Override
	public void setEditavel(boolean editavel) {
		txtCaixaSelecao.setEnabled(editavel);
		
	}

	@Override
	public String getText() {
		return txtCaixaSelecao.getText();
	}

	@Override
	public void setText(String text) {
		txtCaixaSelecao.setText(text);
	}

   
}
