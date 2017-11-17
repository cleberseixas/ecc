package br.com.ecc.controller;

import br.com.ecc.model.Ecc;
import br.com.ecc.service.EccService;

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

	private List<Ecc> listaDosEccs;

	private List<Ecc> listaDosEccsAuxiliar;


	@Inject
	private EccService eccService;

	public String getEccFiltro() {
		return eccFiltro;
	}

	public void setEccFiltro(String eccFiltro) {
		this.eccFiltro = eccFiltro;
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

	public void indicadoresIniciais() {
		listaDosEccs = eccService.listar();
		listaDosEccsAuxiliar = listaDosEccs;
	}

	public void filtraPorEcc() {
		List<Ecc> aux = new ArrayList<Ecc>();
		listaDosEccs = listaDosEccsAuxiliar;
		if (!eccFiltro.equals("")) {
			for (Ecc ecc : listaDosEccs) {
				if (ecc.getNumero().equals(eccFiltro)) {
					aux.add(ecc);
				}
			}
			listaDosEccs = aux;
		}
	}

	public void imprimiEcc() {

	}
}
