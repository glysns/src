package digytal.desktop.util.enums.municipio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import digytal.desktop.util.enums.municipio.ufs.Acre;
import digytal.desktop.util.enums.municipio.ufs.Alagoas;
import digytal.desktop.util.enums.municipio.ufs.Amapa;
import digytal.desktop.util.enums.municipio.ufs.Amazonas;
import digytal.desktop.util.enums.municipio.ufs.Bahia;
import digytal.desktop.util.enums.municipio.ufs.Ceara;
import digytal.desktop.util.enums.municipio.ufs.DistritoFederal;
import digytal.desktop.util.enums.municipio.ufs.EspiritoSanto;
import digytal.desktop.util.enums.municipio.ufs.Exterior;
import digytal.desktop.util.enums.municipio.ufs.Goias;
import digytal.desktop.util.enums.municipio.ufs.Maranhao;
import digytal.desktop.util.enums.municipio.ufs.MatoGrosso;
import digytal.desktop.util.enums.municipio.ufs.MatoGrossoSul;
import digytal.desktop.util.enums.municipio.ufs.MinasGerais;
import digytal.desktop.util.enums.municipio.ufs.Para;
import digytal.desktop.util.enums.municipio.ufs.Paraiba;
import digytal.desktop.util.enums.municipio.ufs.Parana;
import digytal.desktop.util.enums.municipio.ufs.Pernambuco;
import digytal.desktop.util.enums.municipio.ufs.Piaui;
import digytal.desktop.util.enums.municipio.ufs.RioGrandeNorte;
import digytal.desktop.util.enums.municipio.ufs.RioGrandeSul;
import digytal.desktop.util.enums.municipio.ufs.RioJaneiro;
import digytal.desktop.util.enums.municipio.ufs.Rondonia;
import digytal.desktop.util.enums.municipio.ufs.Roraima;
import digytal.desktop.util.enums.municipio.ufs.SantaCatarina;
import digytal.desktop.util.enums.municipio.ufs.SaoPaulo;
import digytal.desktop.util.enums.municipio.ufs.Sergipe;
import digytal.desktop.util.enums.municipio.ufs.Tocantins;

public class Estados {
	public static List<Municipio> MUNICICPIOS;
	static {
		MUNICICPIOS = new ArrayList<Municipio>();
		MUNICICPIOS.addAll(Arrays.asList(Acre.values()));
		MUNICICPIOS.addAll(Arrays.asList(Alagoas.values()));
		MUNICICPIOS.addAll(Arrays.asList(Amazonas.values()));
		MUNICICPIOS.addAll(Arrays.asList(Amapa.values()));
		MUNICICPIOS.addAll(Arrays.asList(Bahia.values()));
		MUNICICPIOS.addAll(Arrays.asList(Ceara.values()));
		MUNICICPIOS.addAll(Arrays.asList(DistritoFederal.values()));
		MUNICICPIOS.addAll(Arrays.asList(EspiritoSanto.values()));
		MUNICICPIOS.addAll(Arrays.asList(Goias.values()));
		MUNICICPIOS.addAll(Arrays.asList(Maranhao.values()));
		MUNICICPIOS.addAll(Arrays.asList(MinasGerais.values()));
		MUNICICPIOS.addAll(Arrays.asList(MatoGrossoSul.values()));
		MUNICICPIOS.addAll(Arrays.asList(MatoGrosso.values()));
		MUNICICPIOS.addAll(Arrays.asList(Para.values()));
		MUNICICPIOS.addAll(Arrays.asList(Paraiba.values()));
		MUNICICPIOS.addAll(Arrays.asList(Pernambuco.values()));
		MUNICICPIOS.addAll(Arrays.asList(Piaui.values()));
		MUNICICPIOS.addAll(Arrays.asList(Parana.values()));
		MUNICICPIOS.addAll(Arrays.asList(RioJaneiro.values()));
		MUNICICPIOS.addAll(Arrays.asList(RioGrandeNorte.values()));
		MUNICICPIOS.addAll(Arrays.asList(Rondonia.values()));
		MUNICICPIOS.addAll(Arrays.asList(Roraima.values()));
		MUNICICPIOS.addAll(Arrays.asList(RioGrandeSul.values()));
		MUNICICPIOS.addAll(Arrays.asList(SantaCatarina.values()));
		MUNICICPIOS.addAll(Arrays.asList(Sergipe.values()));
		MUNICICPIOS.addAll(Arrays.asList(SaoPaulo.values()));
		MUNICICPIOS.addAll(Arrays.asList(Tocantins.values()));
		MUNICICPIOS.addAll(Arrays.asList(Exterior.values()));
	}
	public static Cidade cidade(Integer ibge){
		for (Municipio municipio : MUNICICPIOS) {
			if (municipio.getCodigo().equals(ibge)) {
				return cidade(municipio);
			}
		}
		return null;
	}
	public static List<Cidade> cidades(String nome){
		List<Cidade>list = new ArrayList<Cidade>();
		for (Municipio municipio : MUNICICPIOS) {
			if (municipio.getNome().contains(nome.toUpperCase())) {
				list.add(cidade(municipio));
			}
		}
		return list;
	}
	public static Cidade cidade(Municipio municipio) {
		return new Cidade(municipio.getCodigo(), municipio.getNome(), municipio.getSigla(), municipio.getEstadoNome(), municipio.getEstadoSigla(), municipio.getUf(), municipio.getIbge());
	}
}
