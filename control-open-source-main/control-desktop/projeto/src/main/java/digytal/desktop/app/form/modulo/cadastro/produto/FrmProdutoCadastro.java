package digytal.desktop.app.form.modulo.cadastro.produto;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.form.modulo.cadastro.produto.catagoria.FrmLocalizaCategoria;
import digytal.desktop.app.form.modulo.cadastro.produto.marca.FrmLocalizaMarca;
import digytal.desktop.app.form.modulo.cadastro.produto.modelo.FrmLocalizaModelo;
import digytal.desktop.app.form.modulo.cadastro.produto.unidademedida.FrmLocalizaUnidadeMedida;
import digytal.desktop.app.model.modulo.cadastro.produto.ProdutoRequest;
import digytal.desktop.app.model.modulo.cadastro.produto.ProdutoResponse;
import digytal.desktop.app.model.modulo.comum.Associacao;
import digytal.desktop.app.service.modulo.cadastro.produto.ProdutoService;
import digytal.desktop.app.utils.Tipos;
import digytal.desktop.components.Formato;
import digytal.desktop.components.desktop.Formulario;
import digytal.desktop.components.desktop.FormularioCadastro;
import digytal.desktop.components.desktop.ss.SSCampoNumero;
import digytal.desktop.components.desktop.ss.SSCampoSelecao;
import digytal.desktop.components.desktop.ss.SSCampoTexto;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaEvento;
import digytal.desktop.components.desktop.ss.evento.SSPesquisaListener;
import digytal.desktop.util.utils.business.BusinessException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FrmProdutoCadastro extends FormularioCadastro {
	private SSCampoTexto cCodigoBarras = new SSCampoTexto();
	private SSCampoSelecao cServico = new SSCampoSelecao();
	private SSCampoTexto cUnidadeMedida = new SSCampoTexto();
	private SSCampoTexto cNome = new SSCampoTexto();
	private SSCampoNumero cValor = new SSCampoNumero();
	private SSCampoSelecao cAtualizaEstoque = new SSCampoSelecao();
	private SSCampoSelecao cInterno = new SSCampoSelecao();
	private SSCampoNumero cSaldo = new SSCampoNumero();
	private SSCampoTexto cMarca = new SSCampoTexto();
	private SSCampoTexto cModelo = new SSCampoTexto();
	private SSCampoTexto cCategoria = new SSCampoTexto();
	
	@Autowired
	private ProdutoService service;
	private ProdutoResponse registro = new ProdutoResponse();
	public FrmProdutoCadastro() {
		
			setTitulo("Produto e Serviço");
			setDescricao("Inclusão e Alteração de Produto");
			GridBagConstraints gbc_cInterno = new GridBagConstraints();
			gbc_cInterno.insets = new Insets(5, 5, 0, 0);
			gbc_cInterno.anchor = GridBagConstraints.NORTHWEST;
			gbc_cInterno.gridx = 1;
			gbc_cInterno.gridy = 0;
			conteudo.add(cInterno, gbc_cInterno);
			
			GridBagConstraints gbc_cCodigoBarras = new GridBagConstraints();
			gbc_cCodigoBarras.fill = GridBagConstraints.HORIZONTAL;
			gbc_cCodigoBarras.insets = new Insets(5, 5, 0, 0);
			gbc_cCodigoBarras.anchor = GridBagConstraints.NORTHWEST;
			gbc_cCodigoBarras.gridx = 0;
			gbc_cCodigoBarras.gridy = 0;
			conteudo.add(cCodigoBarras, gbc_cCodigoBarras);
			
			GridBagConstraints gbc_cMarca = new GridBagConstraints();
			gbc_cMarca.gridwidth = 1;
			gbc_cMarca.weightx = 1.0;
			gbc_cMarca.fill = GridBagConstraints.HORIZONTAL;
			gbc_cMarca.insets = new Insets(5, 5, 0, 0);
			gbc_cMarca.anchor = GridBagConstraints.NORTHWEST;
			gbc_cMarca.gridx = 0;
			gbc_cMarca.gridy = 2;
			cMarca.setColunas(20);
			conteudo.add(cMarca, gbc_cMarca);
			
			GridBagConstraints gbc_cModelo = new GridBagConstraints();
			gbc_cModelo.weightx = 1.0;
			gbc_cModelo.fill = GridBagConstraints.HORIZONTAL;
			gbc_cModelo.gridwidth = 2;
			gbc_cModelo.insets = new Insets(5, 5, 0, 5);
			gbc_cModelo.anchor = GridBagConstraints.NORTHWEST;
			gbc_cModelo.gridx = 1;
			gbc_cModelo.gridy = 2;
			cModelo.setColunas(20);
			conteudo.add(cModelo, gbc_cModelo);
			
			GridBagConstraints gbc_cCategoria = new GridBagConstraints();
			gbc_cCategoria.weightx = 1.0;
			gbc_cCategoria.fill = GridBagConstraints.HORIZONTAL;
			gbc_cCategoria.gridwidth = 2;
			gbc_cCategoria.insets = new Insets(5, 5, 0, 0);
			gbc_cCategoria.anchor = GridBagConstraints.NORTHWEST;
			gbc_cCategoria.gridx = 0;
			gbc_cCategoria.gridy = 3;
			cCategoria.setColunas(20);
			conteudo.add(cCategoria, gbc_cCategoria);

			GridBagConstraints gbc_pnlUsarComo = new GridBagConstraints();
			gbc_pnlUsarComo.insets = new Insets(20, 0, 0, 0);
			gbc_pnlUsarComo.gridwidth = 3;
			gbc_pnlUsarComo.anchor = GridBagConstraints.NORTHWEST;
			gbc_pnlUsarComo.fill = GridBagConstraints.HORIZONTAL;
			gbc_pnlUsarComo.gridx = 2;
			gbc_pnlUsarComo.gridy = 0;

			GridBagConstraints gbc_cValor = new GridBagConstraints();
			gbc_cValor.weighty = 1.0;
			gbc_cValor.anchor = GridBagConstraints.NORTHWEST;
			gbc_cValor.insets = new Insets(5, 5, 5, 0);
			gbc_cValor.fill = GridBagConstraints.HORIZONTAL;
			gbc_cValor.gridx = 0;
			gbc_cValor.gridy = 4;
			conteudo.add(cValor, gbc_cValor);

			GridBagConstraints gbc_cNome = new GridBagConstraints();
			gbc_cNome.gridwidth = 3;
			gbc_cNome.anchor = GridBagConstraints.NORTHWEST;
			gbc_cNome.weightx = 1.0;
			gbc_cNome.insets = new Insets(5, 5, 0, 5);
			gbc_cNome.fill = GridBagConstraints.HORIZONTAL;
			gbc_cNome.gridx = 0;
			gbc_cNome.gridy = 1;
			conteudo.add(cNome, gbc_cNome);
			GridBagConstraints gbc_cUnidadeMedida = new GridBagConstraints();
			gbc_cUnidadeMedida.anchor = GridBagConstraints.NORTHWEST;
			gbc_cUnidadeMedida.insets = new Insets(5, 5, 0, 5);
			gbc_cUnidadeMedida.fill = GridBagConstraints.HORIZONTAL;
			gbc_cUnidadeMedida.gridx = 2;
			gbc_cUnidadeMedida.gridy = 3;
			conteudo.add(cUnidadeMedida, gbc_cUnidadeMedida);

			GridBagConstraints gbc_cServico = new GridBagConstraints();
			gbc_cServico.fill = GridBagConstraints.HORIZONTAL;
			gbc_cServico.anchor = GridBagConstraints.NORTHWEST;
			gbc_cServico.insets = new Insets(5, 5, 0, 5);
			gbc_cServico.gridx = 2;
			gbc_cServico.gridy = 0;
			conteudo.add(cServico, gbc_cServico);

			GridBagConstraints gbc_cAtualizaEstoque = new GridBagConstraints();
			gbc_cAtualizaEstoque.weighty = 1.0;
			gbc_cAtualizaEstoque.anchor = GridBagConstraints.NORTHWEST;
			gbc_cAtualizaEstoque.fill = GridBagConstraints.HORIZONTAL;
			gbc_cAtualizaEstoque.insets = new Insets(5, 5, 5, 0);
			gbc_cAtualizaEstoque.gridx = 1;
			gbc_cAtualizaEstoque.gridy = 4;
			conteudo.add(cAtualizaEstoque, gbc_cAtualizaEstoque);

			GridBagConstraints gbc_cSaldo = new GridBagConstraints();
			gbc_cSaldo.weighty = 1.0;
			gbc_cSaldo.anchor = GridBagConstraints.NORTHWEST;
			gbc_cSaldo.fill = GridBagConstraints.HORIZONTAL;
			gbc_cSaldo.insets = new Insets(5, 5, 5, 5);
			gbc_cSaldo.gridx = 2;
			gbc_cSaldo.gridy = 4;
			conteudo.add(cSaldo, gbc_cSaldo);
		
		definirValorPadrao();
		definirEventos();
	}

	private void definirValorPadrao() {
		cCodigoBarras.setColunas(10);
		cUnidadeMedida.setRotulo("UND");
		cUnidadeMedida.setColunas(10);
		cUnidadeMedida.setPesquisa(true);
		cUnidadeMedida.setEditavel(false, true);
		cValor.setColunas(10);
		

		cServico.setRotulo("Serviço");

		cNome.setRotulo("Nome");
		cNome.setColunas(35);
		cValor.setRotulo("R$ Valor");
		cCodigoBarras.setRotulo("Código Barras");

		cSaldo.setRotulo("Saldo");
		cAtualizaEstoque.setRotulo("Estoque");

		cValor.setFormato(Formato.DECIMAL2);
		cValor.setMascara(Formato.DECIMAL2);
		cSaldo.setFormato(Formato.DECIMAL2);
		cSaldo.setEditavel(false);
		cInterno.setRotulo("Uso Interno?");
		
		cMarca.setRotulo("Marca");
		cMarca.setPesquisa(true);
		cMarca.setEditavel(false, true);
		
		cModelo.setRotulo("Modelo");
		cModelo.setPesquisa(true);
		cModelo.setEditavel(false, true);
		
		cCategoria.setRotulo("Categoria");
		cCategoria.setPesquisa(true);
		cCategoria.setEditavel(false, true);
	}

	private void definirEventos() {
		ActionListener cCreditoActionListener = new ActionListener() {
	      public void actionPerformed(ActionEvent actionEvent) {
	        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        checarServico(selected);
	      }
	    };
	    cServico.addActionListener(cCreditoActionListener);
	    
	    cMarca.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				localizarMarca();
			}
		});
	    cModelo.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				localizarModelo();
			}
		});
	    cCategoria.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				localizarCategoria();
			}
		});
	    cUnidadeMedida.addPesquisaListener(new SSPesquisaListener() {
			@Override
			public void pesquisaListener(SSPesquisaEvento evento) {
				localizarUnidadeMedida();
			}
		});
	}
	private void localizarMarca() {
		cMarca.setText("");
		FrmLocalizaMarca frm = Context.getBean(FrmLocalizaMarca.class);
		Associacao response = (Associacao) Formulario.dialogo(frm);
		if (response != null) {
			cMarca.setText(response.getDescricao());
		}
		registro.setMarca(response);
	}
	private void localizarModelo() {
		cModelo.setText("");
		FrmLocalizaModelo frm = Context.getBean(FrmLocalizaModelo.class);
		Associacao response = (Associacao) Formulario.dialogo(frm);
		if (response != null) {
			cModelo.setText(response.getDescricao());
		}
		registro.setModelo(response);
	}
	private void localizarCategoria() {
		cCategoria.setText("");
		FrmLocalizaCategoria frm = Context.getBean(FrmLocalizaCategoria.class);
		Associacao response = (Associacao) Formulario.dialogo(frm);
		if (response != null) {
			cCategoria.setText(response.getDescricao());
		}
		registro.setCategoria(response);
	}
	private void localizarUnidadeMedida() {
		cUnidadeMedida.setText("");
		FrmLocalizaUnidadeMedida frm = Context.getBean(FrmLocalizaUnidadeMedida.class);
		Associacao response = (Associacao) Formulario.dialogo(frm);
		if (response != null) {
			cUnidadeMedida.setText(response.getAbreviacao() + "(" + response.getDescricao() + ")");
		}
		registro.setUnidadeMedida(response);
	}
	
	private void checarServico(boolean selected) {
		if(selected)
			cAtualizaEstoque.setSelected(false);
		
		
		cAtualizaEstoque.setEditavel(!selected);
	}
	
	
	@Override
	public void exibirRegistro(Object entidade) {
		if(entidade!=null) {
			registro = (ProdutoResponse) entidade;
			cCodigoBarras.setText(registro.getCodigoBarras());
			cNome.setText(registro.getNome());
			cCategoria.setText(registro.getNomeAbreviado());
			//cUnidadeMedida.setText(registro.getUnidadeMedida());
			cServico.setSelected(registro.isServico());
			cAtualizaEstoque.setSelected(registro.isAtualizaSaldo());
			cSaldo.setValue(registro.getSaldo());
			cValor.setValue(registro.getValor());
			cInterno.setSelected(registro.isInterno());
			checarServico(registro.isServico());
		}

	}

	@Override
	public void confirmar() {
		try {
			if (SSMensagem.pergunta("Confirma concluir o cadastro do produto?")) {
				ProdutoRequest request = criarRequest();
				if(registro.getId() != null) {
					service.alterar(registro.getId(), request);
				}else {
					service.incluir(request);
				}
				SSMensagem.informa("Cadastro de produto realizado com sucesso");
				fechar();
			}
		} catch (BusinessException ne) {
			SSMensagem.avisa(ne.getErrorCode(), ne.getMessage());
		} catch (Exception ex) {
			//LOGGER.error("ERRO AO REQUISITAR API", ex);
			ex.printStackTrace();
			SSMensagem.erro("Erro ao tentar executar a operação");
		}

	}
	private ProdutoRequest criarRequest() {
		ProdutoRequest request = new ProdutoRequest();
		request.setAtualizaSaldo(cAtualizaEstoque.isSelected());
		request.setServico(cServico.isSelected());
		request.setCodigoBarras(cCodigoBarras.getText());
		request.setNome(cNome.getText());
		request.setValor(cValor.getDouble());
		request.setInterno(cInterno.isSelected());
		request.setCategoria( Tipos.id(registro.getCategoria()));
		request.setMarca( Tipos.id(registro.getMarca()));
		request.setModelo( Tipos.id(registro.getModelo()));
		request.setUnidadeMedida(Tipos.id(registro.getUnidadeMedida()));
		return request;
	}
}