package br.com.ecc.controller;

import br.com.ecc.model.Ecc;
import br.com.ecc.model.util.Aptidao;
import br.com.ecc.model.util.Atividade;
import br.com.ecc.service.AptidaoService;
import br.com.ecc.service.AtividadeService;
import br.com.ecc.service.EccService;
import br.com.ecc.service.FichaService;
import br.com.ecc.util.Constantes;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class EstatisticaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String eccFiltro;

	private String aptidaoFiltro;

	private String atividadeFiltro;

	private List<Ecc> listaDosEccs;

	private List<Ecc> listaDosEccsAuxiliar;

	private List<Aptidao> listaDasAptidoes;

	private List<Aptidao> listaDasAptidoesAuxiliar;

	private List<Atividade> listaDasAtividades;

	private List<Atividade> listaDasAtividadesAuxiliar;


	private int numeroEncontristas;

	private int numeroEncontreiros;

	@Inject
	private EccService eccService;

	@Inject
	private FichaService fichaService;

	@Inject
	private AptidaoService aptidaoService;

	@Inject
	private AtividadeService atividadeService;

	public String getEccFiltro() {
		return eccFiltro;
	}

	public void setEccFiltro(String eccFiltro) {
		this.eccFiltro = eccFiltro;
	}

	public String getAptidaoFiltro() {
		return aptidaoFiltro;
	}

	public void setAptidaoFiltro(String aptidaoFiltro) {
		this.aptidaoFiltro = aptidaoFiltro;
	}

	public String getAtividadeFiltro() {
		return atividadeFiltro;
	}

	public void setAtividadeFiltro(String atividadeFiltro) {
		this.atividadeFiltro = atividadeFiltro;
	}

	public List<Ecc> getListaDosEccs() {
		return listaDosEccs;
	}

	public void setListaDosEccs(List<Ecc> listaDosEccs) {
		this.listaDosEccs = listaDosEccs;
	}

	public List<Ecc> getListaDosEccsAuxiliar() {
		return listaDosEccsAuxiliar;
	}

	public void setListaDosEccsAuxiliar(List<Ecc> listaDosEccsAuxiliar) {
		this.listaDosEccsAuxiliar = listaDosEccsAuxiliar;
	}

	public List<Aptidao> getListaDasAptidoes() {
		return listaDasAptidoes;
	}

	public void setListaDasAptidoes(List<Aptidao> listaDasAptidoes) {
		this.listaDasAptidoes = listaDasAptidoes;
	}

	public List<Aptidao> getListaDasAptidoesAuxiliar() {
		return listaDasAptidoesAuxiliar;
	}

	public void setListaDasAptidoesAuxiliar(List<Aptidao> listaDasAptidoesAuxiliar) {
		this.listaDasAptidoesAuxiliar = listaDasAptidoesAuxiliar;
	}

	public List<Atividade> getListaDasAtividades() {
		return listaDasAtividades;
	}

	public void setListaDasAtividades(List<Atividade> listaDasAtividades) {
		this.listaDasAtividades = listaDasAtividades;
	}

	public List<Atividade> getListaDasAtividadesAuxiliar() {
		return listaDasAtividadesAuxiliar;
	}

	public void setListaDasAtividadesAuxiliar(List<Atividade> listaDasAtividadesAuxiliar) {
		this.listaDasAtividadesAuxiliar = listaDasAtividadesAuxiliar;
	}

	public int getNumeroEncontristas() {
		return numeroEncontristas;
	}

	public void setNumeroEncontristas(int numeroEncontristas) {
		this.numeroEncontristas = numeroEncontristas;
	}

	public int getNumeroEncontreiros() {
		return numeroEncontreiros;
	}

	public void setNumeroEncontreiros(int numeroEncontreiros) {
		this.numeroEncontreiros = numeroEncontreiros;
	}

	public void indicadoresIniciais() {
		listaDosEccs = eccService.listar();
		listaDosEccsAuxiliar = listaDosEccs;

		numeroEncontreiros = fichaService.listarEncontreiroEncontrista(Constantes.ENCONTREIRO).size();
		numeroEncontristas = fichaService.listarEncontreiroEncontrista(Constantes.ENCONTRISTA).size();

		listaDasAptidoes = aptidaoService.totalPorAptidao();
		listaDasAptidoesAuxiliar = listaDasAptidoes;

		listaDasAtividades = atividadeService.totalPorAtividade();
		listaDasAtividadesAuxiliar = listaDasAtividades;
	}

	public void filtraPorEcc() {
		List<Ecc> aux = new ArrayList<Ecc>();
		listaDosEccs = listaDosEccsAuxiliar;
		if (null != eccFiltro) {
			if (!eccFiltro.equals("")) {
				for (Ecc ecc : listaDosEccs) {
					if (ecc.getNumero().equals(eccFiltro)) {
						aux.add(ecc);
					}
				}
				listaDosEccs = aux;
			}
		}

	}

	public void filtraPorAptidao() {
		List<Aptidao> aux = new ArrayList<Aptidao>();
		listaDasAptidoes = listaDasAptidoesAuxiliar;
		if (null != aptidaoFiltro) {
			if (!aptidaoFiltro.equals("")) {
				for (Aptidao aptidao : listaDasAptidoes) {
					//ID da aptidao
					if (aptidao.getAptidao().equals(aptidaoFiltro)) {
						aux.add(aptidao);
					}
				}
				listaDasAptidoes = aux;
			}
		}

	}

	public void filtraPorAtividade() {
		List<Atividade> aux = new ArrayList<Atividade>();
		listaDasAtividades = listaDasAtividadesAuxiliar;
		if (null != atividadeFiltro) {
			if (!atividadeFiltro.equals("")) {
				for (Atividade atividade : listaDasAtividades) {
					//ID da atividade
					if (atividade.getAtividade().equals(atividadeFiltro)) {
						aux.add(atividade);
					}
				}
				listaDasAtividades = aux;
			}
		}

	}

	public void imprimiEcc() {

	}
}
