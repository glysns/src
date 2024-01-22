package digytal.desktop.app.form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import digytal.desktop.app.context.Context;
import digytal.desktop.app.form.consulta.FrmConsultaParcelamentoReceita;
import digytal.desktop.app.form.consulta.FrmConsultaTransacaoDespesa;
import digytal.desktop.app.form.consulta.FrmConsultaTransacaoReceita;
import digytal.desktop.app.form.consulta.FrmGerencial;
import digytal.desktop.app.form.modulo.acesso.empresa.FrmAplicacaoConsulta;
import digytal.desktop.app.form.modulo.acesso.empresa.FrmEmpresaCadastro;
import digytal.desktop.app.form.modulo.cadastro.cadastro.FrmCadastroConsulta;
import digytal.desktop.app.form.modulo.cadastro.produto.FrmProdutoConsulta;
import digytal.desktop.app.form.modulo.cadastro.produto.catagoria.FrmCatagoriaConsulta;
import digytal.desktop.app.form.modulo.cadastro.produto.marca.FrmMarcaConsulta;
import digytal.desktop.app.form.modulo.cadastro.produto.modelo.FrmModeloConsulta;
import digytal.desktop.app.form.modulo.cadastro.produto.unidademedida.FrmUnidadeMedidaConsulta;
import digytal.desktop.app.form.modulo.contrato.FrmContratoVendaServico;
import digytal.desktop.app.form.modulo.financeiro.FrmPagamentoSimples;
import digytal.desktop.app.model.modulo.acesso.empresa.EmpresaResponse;
import digytal.desktop.app.model.modulo.acesso.empresa.aplicacao.AplicacaoTipo;
import digytal.desktop.app.model.modulo.contrato.ContratoTipo;
import digytal.desktop.app.service.modulo.acesso.EmpresaService;
import digytal.desktop.components.desktop.MDI;
import digytal.desktop.components.desktop.ss.SSMensagem;
import digytal.desktop.util.utils.business.BusinessException;

@Component
public class FrmPrincipal extends MDI {
	private JToolBar toolBar = new JToolBar();
	private JButton cmdEmissores = new JButton();
    private JButton cmdLotes = new JButton();
    private JButton cmdNfes = new JButton();
    private JButton cmdSair = new JButton();
	private static final Logger LOGGER = LogManager.getLogger(FrmPrincipal.class);
	@Autowired
	private EmpresaService empresaService;
    public FrmPrincipal(){
    	//principal
        JMenu mCadastros = new JMenu("Cadastros");
        JMenu mLancamentos = new JMenu("Lançamentos");
        JMenu mConsultas = new JMenu("Consultas");
        JMenu mRelatorios = new JMenu("Relatórios");
        JMenu mAjuda = new JMenu("Ajuda");
        
        getBarraMenu().add(mCadastros);
        getBarraMenu().add(mLancamentos);
        getBarraMenu().add(mConsultas);
        getBarraMenu().add(mRelatorios);
        getBarraMenu().add(mAjuda);
        
        //CADASTROS
        JMenu mProdutos = new JMenu("Produtos");
        mCadastros.add(mProdutos);
        
        JMenuItem miCadProduto = new JMenuItem("Produto");
        mProdutos.add(miCadProduto);
        miCadProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirProdutos();
			}
		});
        
        JMenuItem miCadMarca = new JMenuItem("Marca");
        mProdutos.add(miCadMarca);
        miCadMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirMarcas();
			}
		});
        
        JMenuItem miCadModelo = new JMenuItem("Modelo");
        mProdutos.add(miCadModelo);
        miCadModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirModelos();
			}
		});
        
        JMenuItem miCadCategoria = new JMenuItem("Categoria");
        mProdutos.add(miCadCategoria);
        miCadCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirCategoria();
			}
		});
        
        JMenuItem miCadUnidadeMedida = new JMenuItem("Unidade Medida");
        mProdutos.add(miCadUnidadeMedida);
        miCadUnidadeMedida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirUnidadeMedida();
			}
		});
        
        JMenuItem miCadCadastros = new JMenuItem("Cadastros");
        mCadastros.add(miCadCadastros);
        miCadCadastros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirCadastros();
			}
		});
        
        JMenu miEmpresa = new JMenu("Empresa");
        mCadastros.add(miEmpresa);
        
        //EMPESA
        
        JMenuItem miArea = new JMenuItem("Centro Custo");
        JMenuItem miCadReceita = new JMenuItem("Receitas");
        JMenuItem miCadDespesa = new JMenuItem("Despesas");
        JMenuItem miCadEmpresa = new JMenuItem("Cadastro");
        
        miCadEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirEmpresas();
			}
		});
        miArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirAplicacao(null);
			}
		});
        
        miCadReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirAplicacao(AplicacaoTipo.RECEITA);
			}
		});
        miCadDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirAplicacao(AplicacaoTipo.DESPESA);
			}
		});
        miEmpresa.add(miCadEmpresa);
        miEmpresa.add(miArea);
        miEmpresa.add(miCadReceita);
        miEmpresa.add(miCadDespesa);
        
        //LANCAMENTOS
        
        JMenuItem miLancaReceita = new JMenuItem("Receita");
        mLancamentos.add(miLancaReceita);
        
        miLancaReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirLancamentoReceita();
			}
		});
         
        JMenuItem miLancaDespesa = new JMenuItem("Despesa");
        mLancamentos.add(miLancaDespesa);
        
        miLancaDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirLancamentoDespesa();
			}
		});
        
        JMenu mnContrato = new JMenu("Contrato");
        mLancamentos.add(mnContrato);
        
        JMenuItem miLancaContratoVendaServico = new JMenuItem("Venda\\Serviço");
        mnContrato.add(miLancaContratoVendaServico);
        
        miLancaContratoVendaServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirLancamentoContratoVendaServico();
			}
		});
        
        
        //CONSULTAS
        JMenuItem miConsultaFinanceiro = new JMenuItem("Gerêncial");
        mConsultas.add(miConsultaFinanceiro);
        
        miConsultaFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirGerencial();
			}
		});
        
        JMenu mnTransacoes = new JMenu("Transações");
        mConsultas.add(mnTransacoes);
        
        
        JMenuItem miConsultaReceita = new JMenuItem("Receitas");
        mnTransacoes.add(miConsultaReceita);
        
        miConsultaReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirTransacoes();
			}
		});
        
        JMenuItem miConsultaDespesa = new JMenuItem("Despesas");
        mnTransacoes.add(miConsultaDespesa);
        
        miConsultaDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirTransacoesDespesa();
			}
		});
        
       
        
        JMenu mnConsultaParcelamento = new JMenu("Parcelamentos");
        mConsultas.add(mnConsultaParcelamento);
        
        JMenuItem miConsultaParcelamentoRecebimento = new JMenuItem("Receber");
        mnConsultaParcelamento.add(miConsultaParcelamentoRecebimento);
        
        miConsultaParcelamentoRecebimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirConsultaParcelamentoRecebimento();
			}
		});
        
        JMenuItem miConsultaParcelamentoPagamento = new JMenuItem("Pagar");
        mnConsultaParcelamento.add(miConsultaParcelamentoPagamento);
        
        
        
        /*
        
        
        
       
       
        
        
        
               

       
        miVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirVenda();
			}
		});
       
        miCaixaLancto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirFluxoCaixa();
			}
		});
        miRecebe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				exibirParcelamentoReceita();
			}
		});
        
       
        
        
        
        
        mCadastros.setIcon(Imagens.png("app/menu/0cadastros"));
        mLancamentos.setIcon(Imagens.png("app/menu/1lancamentos"));
        mConsultas.setIcon(Imagens.png("app/menu/2consultas"));
        mRelatorios.setIcon(Imagens.png("app/menu/3relatorios"));
        
        
        miCadastros.setIcon(Imagens.png("app/menu/clientes"));
    
    
        miEmpresa.setIcon(Imagens.png("app/menu/empresas"));
        miCadEmpresa.setIcon(Imagens.png("app/menu/empresa"));
        miVenda.setIcon(Imagens.png("app/menu/venda"));
        mnTransacoes.setIcon(Imagens.png("app/menu/consulta-transacoes"));
        miConsultaReceita.setIcon(Imagens.png("app/menu/consulta-receitas"));
        miConsultaDespesa.setIcon(Imagens.png("app/menu/consulta-despesas"));
        
        mProdutos.setIcon(Imagens.png("app/menu/produtos"));
        miProduto.setIcon(Imagens.png("app/menu/produto"));
        miMarca.setIcon(Imagens.png("app/menu/marca"));
        miModelo.setIcon(Imagens.png("app/menu/modelo"));
        miParcelamento.setIcon(Imagens.png("app/menu/pagamento"));
        miCaixaLancto.setIcon(Imagens.png("app/menu/caixa"));
        miRecebe.setIcon(Imagens.png("app/menu/recebe"));
        miPaga.setIcon(Imagens.png("app/menu/paga"));
        miReceita.setIcon(Imagens.png("app/menu/receita"));
        miDespesa.setIcon(Imagens.png("app/menu/despesas"));
        miCadReceita.setIcon(Imagens.png("app/menu/receitas"));
        miCadDespesa.setIcon(Imagens.png("app/menu/despesas"));
        miArea.setIcon(Imagens.png("app/menu/centro_custo"));
        miLancaReceita.setIcon(Imagens.png("app/menu/receitas"));
        miPagamento.setIcon(Imagens.png("app/menu/pagamentos"));
        miCategoria.setIcon(Imagens.png("app/menu/categoria"));
        miUnidadeMedida.setIcon(Imagens.png("app/menu/unidade-medida"));
        
        
        cmdEmissores.setToolTipText( "Cadastro de Emissores de Notas Fiscais" );
        cmdEmissores.setIcon( Imagens.png("app/bar/cadastro"));
        cmdEmissores.setText("Emissores");
        
        toolBar.add(cmdEmissores);
        toolBar.add(cmdNfes);
        toolBar.add(cmdLotes);
        toolBar.add(cmdSair);
        //this.getContentPane().add(toolBar, BorderLayout.NORTH);
        
        
        
       */
        
    }
    public void exibirFormularioInicial() {
    	//tabs();
    }
   
    private void exibirEmpresas() {
    	try {
    		EmpresaResponse response = empresaService.buscar(Context.getEmpresaId());
    		FrmEmpresaCadastro frm= Context.getBean(FrmEmpresaCadastro.class);
			frm.exibirRegistro(response);
    		exibir(frm);
    	}catch (BusinessException be) {
    		SSMensagem.avisa(be.getErrorCode(), be.getMessage());
    	}catch (Exception e) {
    		SSMensagem.erro(e.getMessage());
			LOGGER.error(e.getMessage(),e);
		}
	}
    private void exibirCadastros() {
    	FrmCadastroConsulta frm= Context.getBean(FrmCadastroConsulta.class);
		exibir(frm);
    }
    private void exibirGerencial() {
    	FrmGerencial frm= Context.getBean(FrmGerencial.class);
    	frm.filtrar();
		exibir(frm);
    }
    private void exibirAplicacao(AplicacaoTipo tipo) {
    	FrmAplicacaoConsulta frm= Context.getBean(FrmAplicacaoConsulta.class);
    	frm.inicializar(tipo);
		exibir(frm);
    }
    
    private void exibirLancamentoDespesa() {
    	FrmPagamentoSimples frm= Context.getBean(FrmPagamentoSimples.class);
    	frm.inicializar(AplicacaoTipo.DESPESA);
		exibir(frm);
    }
    private void exibirLancamentoContratoVendaServico() {
    	FrmContratoVendaServico frm= Context.getBean(FrmContratoVendaServico.class);
    	frm.iniciar(ContratoTipo.SERVICO);
    	//frm.inicializar(AplicacaoTipo.DESPESA);
		exibir(frm);
    }
    private void exibirLancamentoReceita() {
    	FrmPagamentoSimples frm= Context.getBean(FrmPagamentoSimples.class);
    	frm.inicializar(AplicacaoTipo.RECEITA);
		exibir(frm);
    }
   
    private void exibirProdutos() {
    	FrmProdutoConsulta frm= Context.getBean(FrmProdutoConsulta.class);
		exibir(frm);
    }
   
    private void exibirVenda() {
    	//FrmVendaServicoCadastro frm= Context.getBean(FrmVendaServicoCadastro.class);
    	//frm.iniciar();
		//exibir(frm);
    }
    private void exibirTransacoes() {
    	FrmConsultaTransacaoReceita frm= Context.getBean(FrmConsultaTransacaoReceita.class);
    	exibir(frm);
    	//SSMensagem.informa("Em breve");
    }
    private void exibirTransacoesDespesa() {
    	FrmConsultaTransacaoDespesa frm= Context.getBean(FrmConsultaTransacaoDespesa.class);
    	exibir(frm);
    	//SSMensagem.informa("Em breve");
    }
    private void exibirFluxoCaixa() {
    	//FrmFluxoCaixa frm= Context.getBean(FrmFluxoCaixa.class);
    	//exibir(frm);
    }
    private void exibirParcelamentoReceita() {
    	//FrmParcelamentoReceita frm= Context.getBean(FrmParcelamentoReceita.class);
    	//exibir(frm);
    }
    private void exibirMarcas() {
    	FrmMarcaConsulta frm= Context.getBean(FrmMarcaConsulta.class);
    	exibir(frm);
    }
    private void exibirModelos() {
    	FrmModeloConsulta frm= Context.getBean(FrmModeloConsulta.class);
    	exibir(frm);
    }
    private void exibirUnidadeMedida() {
    	FrmUnidadeMedidaConsulta frm= Context.getBean(FrmUnidadeMedidaConsulta.class);
    	exibir(frm);
    }
    private void exibirCategoria() {
    	FrmCatagoriaConsulta frm= Context.getBean(FrmCatagoriaConsulta.class);
    	exibir(frm);
    }
    
    
    //CONSULTAS
    private void exibirConsultaParcelamentoRecebimento() {
    	FrmConsultaParcelamentoReceita frm= Context.getBean(FrmConsultaParcelamentoReceita.class);
    	exibir(frm);
    } 
   
}
