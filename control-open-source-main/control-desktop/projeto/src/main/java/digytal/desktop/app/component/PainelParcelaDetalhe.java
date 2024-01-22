package digytal.desktop.app.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import digytal.desktop.app.model.modulo.financeiro.response.parcelamento.ParcelaResponse;
import digytal.desktop.app.model.modulo.financeiro.response.parcelamento.ParcelamentoDetalhe;
import digytal.desktop.components.ColorUtil;
import digytal.desktop.components.desktop.ss.util.SSFormatador;

public class PainelParcelaDetalhe extends JPanel {
	private JPanel pRegistro = new JPanel(new GridBagLayout());
	private JLabel cDescricao = new JLabel("PACELA DE NR");
	private JLabel cPendencia = new JLabel("Nada consta");
	private JLabel cDataVencimento = new JLabel("01/01/23");
	private JLabel cNumParcelas = new JLabel("000");
	private JLabel cValorCorrecao = new JLabel("R$ 0,00");
	private JLabel cValorJuros = new JLabel("R$ 0,00");
	private JLabel cValorMulta = new JLabel("R$ 0,00");
	private JLabel cAtrasada = new JLabel("Não");
	private JLabel cValorAtual = new JLabel("R$ 0,00");
	private JLabel cValorAmortizado = new JLabel("R$ 0,00");
	private JLabel cValorDesconto = new JLabel("R$ 0,00");
	private JLabel cValorOriginal = new JLabel("R$ 0,00");
	public PainelParcelaDetalhe() {
		this.setLayout(new BorderLayout());
		this.add(pRegistro,BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Descrição");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		pRegistro.add(lblNewLabel, gbc_lblNewLabel);
		
		cDescricao.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cDescricao = new GridBagConstraints();
		gbc_cDescricao.anchor = GridBagConstraints.NORTHEAST;
		gbc_cDescricao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDescricao.weightx = 1.0;
		gbc_cDescricao.gridwidth = 3;
		gbc_cDescricao.insets = new Insets(5, 5, 0, 5);
		gbc_cDescricao.gridx = 1;
		gbc_cDescricao.gridy = 0;
		pRegistro.add(cDescricao, gbc_cDescricao);
		
		JLabel lblNewLabel_1 = new JLabel("Pendência");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		pRegistro.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		cPendencia.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cPendencia = new GridBagConstraints();
		gbc_cPendencia.anchor = GridBagConstraints.NORTHEAST;
		gbc_cPendencia.fill = GridBagConstraints.HORIZONTAL;
		gbc_cPendencia.gridwidth = 3;
		gbc_cPendencia.insets = new Insets(5, 5, 0, 5);
		gbc_cPendencia.gridx = 1;
		gbc_cPendencia.gridy = 1;
		pRegistro.add(cPendencia, gbc_cPendencia);
		
		JLabel lblNewLabel_2 = new JLabel("Data Vencto:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		pRegistro.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		cDataVencimento.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cDataVencimento = new GridBagConstraints();
		gbc_cDataVencimento.insets = new Insets(5, 5, 0, 0);
		gbc_cDataVencimento.gridx = 1;
		gbc_cDataVencimento.gridy = 2;
		pRegistro.add(cDataVencimento, gbc_cDataVencimento);
		
		JLabel lblNewLabel_3 = new JLabel("Num. Parcela:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_3.insets = new Insets(5, 15, 0, 0);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 2;
		pRegistro.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		cNumParcelas.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cNumParcelas = new GridBagConstraints();
		gbc_cNumParcelas.anchor = GridBagConstraints.NORTHEAST;
		gbc_cNumParcelas.fill = GridBagConstraints.HORIZONTAL;
		gbc_cNumParcelas.insets = new Insets(5, 15, 0, 5);
		gbc_cNumParcelas.gridx = 3;
		gbc_cNumParcelas.gridy = 2;
		pRegistro.add(cNumParcelas, gbc_cNumParcelas);
		
		JLabel lblNewLabel_4 = new JLabel("R$ Original:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_4.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 3;
		pRegistro.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		cValorOriginal.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cValorOriginal = new GridBagConstraints();
		gbc_cValorOriginal.insets = new Insets(5, 5, 0, 0);
		gbc_cValorOriginal.gridx = 1;
		gbc_cValorOriginal.gridy = 3;
		pRegistro.add(cValorOriginal, gbc_cValorOriginal);
		
		JLabel lblNewLabel_5 = new JLabel("R$ Multa:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_5.insets = new Insets(5, 15, 0, 0);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 3;
		pRegistro.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		cValorMulta.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cValorMulta = new GridBagConstraints();
		gbc_cValorMulta.anchor = GridBagConstraints.NORTHEAST;
		gbc_cValorMulta.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorMulta.insets = new Insets(5, 15, 0, 5);
		gbc_cValorMulta.gridx = 3;
		gbc_cValorMulta.gridy = 3;
		pRegistro.add(cValorMulta, gbc_cValorMulta);
		
		JLabel lblNewLabel_6 = new JLabel("R$ Juros:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_6.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 4;
		pRegistro.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		
		
		cValorJuros.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cValorJuros = new GridBagConstraints();
		gbc_cValorJuros.insets = new Insets(5, 5, 0, 0);
		gbc_cValorJuros.gridx = 1;
		gbc_cValorJuros.gridy = 4;
		pRegistro.add(cValorJuros, gbc_cValorJuros);
		
		JLabel lblNewLabel_7 = new JLabel("R$ Correção:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_7.insets = new Insets(5, 15, 0, 0);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 4;
		pRegistro.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		cValorCorrecao.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cValorCorrecao = new GridBagConstraints();
		gbc_cValorCorrecao.anchor = GridBagConstraints.NORTHEAST;
		gbc_cValorCorrecao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorCorrecao.insets = new Insets(5, 15, 0, 5);
		gbc_cValorCorrecao.gridx = 3;
		gbc_cValorCorrecao.gridy = 4;
		pRegistro.add(cValorCorrecao, gbc_cValorCorrecao);
		
		JLabel lblNewLabel_8 = new JLabel("R$ Amortizado:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_8.insets = new Insets(5, 5, 0, 0);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 5;
		pRegistro.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		
		cValorAmortizado.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cValorAmortizado = new GridBagConstraints();
		gbc_cValorAmortizado.insets = new Insets(5, 5, 0, 0);
		gbc_cValorAmortizado.gridx = 1;
		gbc_cValorAmortizado.gridy = 5;
		pRegistro.add(cValorAmortizado, gbc_cValorAmortizado);
		
		JLabel lblNewLabel_9 = new JLabel("R$ Desconto:");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_9.insets = new Insets(5, 15, 0, 0);
		gbc_lblNewLabel_9.gridx = 2;
		gbc_lblNewLabel_9.gridy = 5;
		pRegistro.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		
		cValorDesconto.setForeground(new Color(128, 0, 0));
		cValorDesconto.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cValorDesconto = new GridBagConstraints();
		gbc_cValorDesconto.anchor = GridBagConstraints.NORTHEAST;
		gbc_cValorDesconto.fill = GridBagConstraints.HORIZONTAL;
		gbc_cValorDesconto.insets = new Insets(5, 15, 0, 5);
		gbc_cValorDesconto.gridx = 3;
		gbc_cValorDesconto.gridy = 5;
		pRegistro.add(cValorDesconto, gbc_cValorDesconto);
		
		JLabel lblNewLabel_10 = new JLabel("R$ Atual:");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_10.insets = new Insets(5, 5, 5, 0);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 6;
		pRegistro.add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		
		cValorAtual.setForeground(new Color(0, 0, 139));
		cValorAtual.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_cValorAtual = new GridBagConstraints();
		gbc_cValorAtual.insets = new Insets(5, 5, 0, 0);
		gbc_cValorAtual.gridx = 1;
		gbc_cValorAtual.gridy = 6;
		pRegistro.add(cValorAtual, gbc_cValorAtual);
		
		JLabel lblNewLabel_11 = new JLabel("Atrasada:");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_11.insets = new Insets(5, 15, 0, 0);
		gbc_lblNewLabel_11.gridx = 2;
		gbc_lblNewLabel_11.gridy = 6;
		pRegistro.add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		
		cAtrasada.setFont(new Font("Tahoma", Font.BOLD, 11));
		cAtrasada.setForeground(new Color(128, 0, 0));
		GridBagConstraints gbc_cAtrasada = new GridBagConstraints();
		gbc_cAtrasada.anchor = GridBagConstraints.NORTHEAST;
		gbc_cAtrasada.fill = GridBagConstraints.HORIZONTAL;
		gbc_cAtrasada.insets = new Insets(5, 15, 5, 5);
		gbc_cAtrasada.gridx = 3;
		gbc_cAtrasada.gridy = 6;
		pRegistro.add(cAtrasada, gbc_cAtrasada);
	}
	public void setParcela(ParcelaResponse parcela) {
		atualizarCampos(parcela);
	}
	private void atualizarCampos(ParcelaResponse parcela) {
		ParcelaResponse p = parcela;
		ParcelamentoDetalhe n = p.getDetalhe();
		cDataVencimento.setText(SSFormatador.formatarData(n.getDataVencimento()));
		cValorOriginal.setText(SSFormatador.formatar(n.getValorOriginal()));
		cValorJuros.setText(SSFormatador.formatar(n.getValorJuros()));
		cValorMulta.setText(SSFormatador.formatar(n.getValorMulta()));
		cValorCorrecao.setText(SSFormatador.formatar(n.getValorCorrecao()));
		cValorAmortizado.setText(SSFormatador.formatar(n.getValorAmortizado()));
		cValorDesconto.setText(SSFormatador.formatar(n.getValorDesconto()));
		cValorAtual.setText(SSFormatador.formatar(n.getValorAtual()));
		cNumParcelas.setText(String.format("%03d", n.getNumeroParcela()));
		
		String quitada = p.getQuitacao().isEfetuada()?"**QUITADA**":"";
		if(p.getQuitacao().isEfetuada())
			cDescricao.setForeground(ColorUtil.BLUE);
		
		cDescricao.setText(p.getDescricao() + " " + quitada);
		cAtrasada.setText(p.getPendencia().isAtrasada()?"Sim":"Não");
		//cPendencia.setText(SSFormatador.formatarCelular(c.getCelular()) + " / " + c.getSobrenomeSocial() );
	}

}
