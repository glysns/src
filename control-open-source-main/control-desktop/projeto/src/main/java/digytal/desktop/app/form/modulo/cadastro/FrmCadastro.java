package digytal.desktop.app.form.modulo.cadastro;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.springframework.beans.factory.annotation.Autowired;

import digytal.desktop.app.component.PainelEndereco;
import digytal.desktop.app.component.PainelPessoa;
import digytal.desktop.app.model.modulo.comum.EntidadeCadastralResponse;
import digytal.desktop.app.model.modulo.comum.cadastramento.CadastroCompletoRequest;
import digytal.desktop.app.model.modulo.comum.endereco.Cidade;
import digytal.desktop.app.model.modulo.comum.endereco.Endereco;
import digytal.desktop.app.service.modulo.param.CepService;
import digytal.desktop.components.desktop.FormularioCadastro;


public abstract class FrmCadastro extends FormularioCadastro {
	@Autowired
	protected CepService cepService;
	protected PainelPessoa painelPessoa = new PainelPessoa();
	protected JTabbedPane tCampos = new JTabbedPane();
	protected JPanel pPapel = new JPanel();
	protected EntidadeCadastralResponse registro;
	public FrmCadastro(JPanel dadosComplementares) {
		this();
		tCampos.addTab("Dados Complementares", dadosComplementares);
	}
	public FrmCadastro() {
		setTitulo("Cadastro");
		setDescricao("Inclusão e Alteração");
		tCampos.addTab("Dados Pessoais", painelPessoa);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		getConteudo().add(tCampos, gbc);
		pPapel.setLayout(new FlowLayout(FlowLayout.LEFT));
		gbc = new GridBagConstraints();
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.NORTHEAST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		getConteudo().add(pPapel, gbc);
		
	}	
	protected CadastroCompletoRequest atualizarDados() {
		CadastroCompletoRequest cadastro = new CadastroCompletoRequest();
		cadastro.setAniversario(painelPessoa.getcAniversario().getLocalDate());
		cadastro.setAtividadeComecialProfissional("");
		cadastro.setCpfCnpj(painelPessoa.getcCpfCnpj().getClipText());
		cadastro.setNomeFantasia(painelPessoa.getcNomeFantasia().getText());
		cadastro.setSobrenomeSocial(painelPessoa.getcNomeRazao().getText());
		cadastro.setEmail(painelPessoa.getPainelContato().getcEmail().getText());
		cadastro.getTelefone().setCelular(painelPessoa.getPainelContato().getcCelular()==null?null:painelPessoa.getPainelContato().getcCelular().getLongValue());
		cadastro.getTelefone().setCelularWhatsApp( (painelPessoa.getPainelContato().getcWhatsApp().isSelected()));
		cadastro.setRgIe(painelPessoa.getcRgIe().getText());
		PainelEndereco end = painelPessoa.getPainelEnderecoContato();
		
		
		Endereco endereco = new Endereco();
		endereco.setCep(end.getcCep().getClipText());
		endereco.setNumero(end.getcNumero().getText());
		endereco.setComplemento(end.getcComplemento().getText());
		endereco.setReferencia(end.getcReferencia().getText());
		endereco.setTelefone(end.getcTelefone() ==null ? null : end.getcTelefone().getLongValue());
		cadastro.setEndereco(endereco);
	
		return cadastro;
	}
	protected void exibirRegistro() {
		PainelPessoa pPessoa = getPainelPessoa();
		pPessoa.setCpfCnpj(registro.getCpfCnpj());
		pPessoa.setId(registro.getId());
		
		pPessoa.getcNomeFantasia().setText(registro.getNomeFantasia());
		pPessoa.getcNomeRazao().setText(registro.getSobrenomeSocial());
		pPessoa.getcRgIe().setText(registro.getRgIe());
		pPessoa.getcAniversario().setData(registro.getAniversario());
		pPessoa.getPainelContato().getcEmail().setValue(registro.getEmail());
		pPessoa.getPainelContato().getcCelular().setText(Objects.toString(registro.getTelefone().getCelular(),""));
		pPessoa.getPainelContato().getcWhatsApp().setSelected(registro.getTelefone().isCelularWhatsApp());
		
		
		Endereco endereco = registro.getEndereco();
		PainelEndereco pEndereco = getPainelPessoa().getPainelEnderecoContato();
		pEndereco.getcCep().setText(endereco.getCep());
		pEndereco.getcLogradouro().setText(endereco.getLogradouro());
		pEndereco.getcNumero().setText(endereco.getNumero());
		pEndereco.getcComplemento().setText(endereco.getComplemento());
		pEndereco.getcReferencia().setText(endereco.getReferencia());
		pEndereco.getcBairro().setText(endereco.getBairro());
		pEndereco.getcTelefone().setText(Objects.toString(endereco.getTelefone(),""));
		
		Cidade cidade = endereco.getCidade();
		if(cidade!=null) {
			pEndereco.getcCidade().setText(cidade.getNome());
			pEndereco.getcUf().setText(cidade.getUf());
			pEndereco.getcPais().setText("BRASIL");
		}
	}
	public void adicionarTab(String titulo, JPanel tab) {
		tCampos.addTab(titulo, tab);
	}
	public PainelPessoa getPainelPessoa() {
		return painelPessoa;
	}
	
}
