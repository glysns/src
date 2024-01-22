package digytal.desktop.components.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import digytal.desktop.components.Fonte;
import digytal.desktop.components.Imagens;
import digytal.desktop.components.desktop.ss.util.SSFormatador;
import digytal.desktop.components.model.Empresa;
import digytal.desktop.components.model.Sessao;
import digytal.desktop.components.model.Usuario;

public class MDI extends JFrame {
	private JDesktopPane areaTrabalho = new JDesktopPane();
	protected JMenuBar barraMenu = new JMenuBar();

	private final JLabel imagemFundo = new JLabel();
	private ImageIcon imgFundo;
	private final JPanel barraStatus = new JPanel();

	private final JLabel lDataHora = new JLabel("Data \\ Hora:");
	private final JLabel lCpfCnpj = new JLabel("CPF \\ CNPJ:");
	private final JLabel lRazaoSocial = new JLabel("Razão Social:");
	private final JLabel lEmpresa = new JLabel("Usuário:");
	private final JLabel lLogin = new JLabel("Login:");
	private final JLabel lAmbiente = new JLabel("Ambiente:");
	private final JLabel lEstabelecimento = new JLabel("Estabelecimento:");

	private final JLabel cLogin = new JLabel("master");
	private final JLabel cNome = new JLabel("DIGYTAL");
	private final JLabel cAmbiente = new JLabel("PRODUÇÃO");
	private final JLabel cEstabelecimento = new JLabel("CPF\\CNPJ - NOME DO ESTABELECIMENTO ");
	private final JLabel cEmpresaNomeSocial = new JLabel(" G & S ASSESSORIA E DESENVOLVIMENTO DE SOLUÇÕES");
	private final JLabel cAplicacao = new JLabel("Digytal");
	private final JLabel cVersao = new JLabel("Versão 1.0");
	private final JLabel cEmpresaCpfCnpj = new JLabel("10.654.096/0001-33");
	private final JLabel cDataHora = new JLabel("30/06/2022 16:11");

	public MDI() {
		areaTrabalho.setBackground(new Color(240, 240, 240));
		areaTrabalho.setVisible(true);
		getContentPane().setLayout(new BorderLayout());

		Font t11b = Fonte.TAHOMA_11_BOLD;
		cLogin.setFont(t11b);
		cNome.setFont(t11b);
		cAmbiente.setFont(t11b);
		cEmpresaNomeSocial.setFont(t11b);
		cDataHora.setFont(t11b);
		cEmpresaCpfCnpj.setFont(t11b);
		cVersao.setFont(t11b);
		cEstabelecimento.setFont(t11b);

		cEmpresaNomeSocial.setFont(t11b);
		cAplicacao.setFont(t11b);
		cAplicacao.setFont(t11b);
		/*
		 * Font t11 =Fonte.TAHOMA_11; lDataHora.setFont(t11); lCpfCnpj.setFont(t11);
		 * lRazaoSocial.setFont(t11); lEmpresa.setFont(t11); lLogin.setFont(t11);
		 * lAmbiente.setFont(t11); lConexao.setFont(t11);
		 */
		cLogin.setForeground(Color.BLUE);
		cNome.setForeground(Color.BLUE);
		cAmbiente.setForeground(Color.BLUE);
		cEstabelecimento.setForeground(Color.BLUE);

		JPanel barraSessao = new JPanel();
		barraSessao.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		barraSessao.setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(barraSessao, BorderLayout.NORTH);

		barraSessao.add(lLogin);
		barraSessao.add(cLogin);

		barraSessao.add(lEmpresa);
		barraSessao.add(cNome);

		barraSessao.add(lEstabelecimento);
		barraSessao.add(cEstabelecimento);

		barraSessao.add(lAmbiente);
		barraSessao.add(cAmbiente);

		getContentPane().add(areaTrabalho, BorderLayout.CENTER);
		setJMenuBar(barraMenu);

		setTitle("Digytal");

		this.setIconImage(Imagens.png("app/app-img-32").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1024, 768);
		setLocationRelativeTo(null);

		// areaTrabalho.setBackground(Color.LIGHT_GRAY);
		barraStatus.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		getContentPane().add(barraStatus, BorderLayout.SOUTH);
		GridBagLayout gbl_barraStatus = new GridBagLayout();
		barraStatus.setLayout(gbl_barraStatus);

		GridBagConstraints gbc_lDataHora = new GridBagConstraints();
		gbc_lDataHora.insets = new Insets(5, 5, 5, 0);
		gbc_lDataHora.fill = GridBagConstraints.HORIZONTAL;
		gbc_lDataHora.gridx = 0;
		gbc_lDataHora.gridy = 0;
		barraStatus.add(lDataHora, gbc_lDataHora);

		GridBagConstraints gbc_cDataHora = new GridBagConstraints();
		gbc_cDataHora.insets = new Insets(5, 5, 5, 0);
		gbc_cDataHora.fill = GridBagConstraints.HORIZONTAL;
		gbc_cDataHora.gridx = 1;
		gbc_cDataHora.gridy = 0;
		barraStatus.add(cDataHora, gbc_cDataHora);

		GridBagConstraints gbc_lCpfCnpj = new GridBagConstraints();
		gbc_lCpfCnpj.fill = GridBagConstraints.HORIZONTAL;
		gbc_lCpfCnpj.insets = new Insets(5, 5, 5, 0);
		gbc_lCpfCnpj.gridx = 2;
		gbc_lCpfCnpj.gridy = 0;
		barraStatus.add(lCpfCnpj, gbc_lCpfCnpj);

		GridBagConstraints gbc_cEmpresaCpfCnpj = new GridBagConstraints();
		gbc_cEmpresaCpfCnpj.fill = GridBagConstraints.HORIZONTAL;
		gbc_cEmpresaCpfCnpj.weightx = 1.0;
		gbc_cEmpresaCpfCnpj.insets = new Insets(5, 5, 5, 0);
		gbc_cEmpresaCpfCnpj.gridx = 3;
		gbc_cEmpresaCpfCnpj.gridy = 0;
		barraStatus.add(cEmpresaCpfCnpj, gbc_cEmpresaCpfCnpj);

		GridBagConstraints gbc_lRazaoSocial = new GridBagConstraints();
		gbc_lRazaoSocial.fill = GridBagConstraints.HORIZONTAL;
		gbc_lRazaoSocial.insets = new Insets(5, 5, 5, 0);
		gbc_lRazaoSocial.gridx = 4;
		gbc_lRazaoSocial.gridy = 0;
		barraStatus.add(lRazaoSocial, gbc_lRazaoSocial);

		GridBagConstraints gbc_cEmpresaNomeSocial = new GridBagConstraints();
		gbc_cEmpresaNomeSocial.fill = GridBagConstraints.HORIZONTAL;
		gbc_cEmpresaNomeSocial.weightx = 1.0;
		gbc_cEmpresaNomeSocial.insets = new Insets(5, 5, 5, 0);
		gbc_cEmpresaNomeSocial.gridx = 5;
		gbc_cEmpresaNomeSocial.gridy = 0;
		barraStatus.add(cEmpresaNomeSocial, gbc_cEmpresaNomeSocial);

		GridBagConstraints gbc_cAplicacao = new GridBagConstraints();
		gbc_cAplicacao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cAplicacao.insets = new Insets(5, 5, 5, 0);
		gbc_cAplicacao.gridx = 6;
		gbc_cAplicacao.gridy = 0;
		barraStatus.add(cAplicacao, gbc_cAplicacao);

		GridBagConstraints gbc_cVersao = new GridBagConstraints();
		gbc_cVersao.fill = GridBagConstraints.HORIZONTAL;
		gbc_cVersao.insets = new Insets(5, 5, 5, 5);
		gbc_cVersao.gridx = 7;
		gbc_cVersao.gridy = 0;
		barraStatus.add(cVersao, gbc_cVersao);

		imgFundo = Imagens.jpg("app/app-fundo");
		imagemFundo.setIcon(imgFundo);
		// imagemFundo.setIcon(new
		// ImageIcon(imgFundo.getImage().getScaledInstance(this.getWidth(),this.getHeight(),
		// Image.SCALE_DEFAULT)));
		areaTrabalho.add(imagemFundo);

		areaTrabalho.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				desktopPane_componentResized(e);
			}
		});
	}

	private void desktopPane_componentResized(ComponentEvent e) {

		if (imgFundo == null)
			return;

		int top, left;

		top = (areaTrabalho.getWidth() / 2) - (imgFundo.getIconWidth() / 2);
		left = (areaTrabalho.getHeight() / 2) - (imgFundo.getIconHeight() / 2);

		imagemFundo.setBounds(top, left, imgFundo.getIconWidth(), imgFundo.getIconHeight());
	}

	public void iniciar(Sessao sessao) {

		definir(sessao);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {
						cDataHora.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
						Thread.sleep(1000l * 30);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();

		setVisible(true);

	}

	private void definir(Sessao sessao) {
		Usuario usuario = sessao.getUsuario();
		cLogin.setText(usuario.getLogin());
		cNome.setText(usuario.getNome());

		Empresa empresa = sessao.getEmpresa();
		cEstabelecimento
				.setText(SSFormatador.formatarCpfCnpj(empresa.getCpfCnpj()) + " - " + empresa.getNomeFantasia());
	}

	public JDesktopPane getAreaTrabalho() {
		return areaTrabalho;
	}

	public JMenuBar getBarraMenu() {
		return barraMenu;
	}

	public void exibir(Formulario formulario) {
		formulario.setMdi(this);
		// formulario.preExibir();
		formulario.exibir();
	}
}
