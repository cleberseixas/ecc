package br.com.ecc.controller;

import br.com.ecc.model.Ficha;
import br.com.ecc.service.FichaService;
import br.com.ecc.util.Constantes;
import br.com.ecc.util.FacesMessages;
import org.primefaces.context.RequestContext;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - Fichas (Encontristas e Encontreiros)
 * @author Cleber Seixas
 * @since 11/10/2017
 */
@Named
@ViewScoped
public class FichaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Ficha ficha = new Ficha();

	private List<Ficha> listaFichas;

	private List<Ficha> listaEncontristas;

	private boolean habilitaBotaoEditarFicha = true;

	private boolean habilitaBotaoExcluirFicha = true;

	private boolean habilitaBotaoIncluiAptidoes = true;

	private boolean habilitaBotaoIncluiAtividades = true;

	private boolean habilitaBotaoDetalhesFicha = true;


	@Inject
	private FichaService fichaService;

	public void novaFicha() {
		this.ficha = new Ficha();
	}

	private void habilitaTodosBotoesFicha() {
		habilitaBotaoEditarFicha = false;
		habilitaBotaoExcluirFicha = false;
		habilitaBotaoIncluiAptidoes = false;
		habilitaBotaoIncluiAtividades = false;
		habilitaBotaoDetalhesFicha = false;
	}

	private void desabilitaTodosBotoesFicha() {
		habilitaBotaoEditarFicha = true;
		habilitaBotaoExcluirFicha = true;
		habilitaBotaoIncluiAptidoes = true;
		habilitaBotaoIncluiAtividades = true;
		habilitaBotaoDetalhesFicha = false;
	}

	public void salvarFicha() {
		try {
//			if (this.ficha.getNomeEle().isEmpty()) {
//				FacesMessages.error("Favor informar o nome dele");
//				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
//				return;
//			}
			fichaService.salvar(ficha);
			this.listaFichas = fichaService.listar();
			this.ficha = new Ficha();
			desabilitaTodosBotoesFicha();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
		}
	}
	/*
	eccService.salvar(ecc);
		this.listaEcc = eccService.listar();
		this.ecc = new Ecc();
		desabilitaTodosBotoesEcc();
	 */

	public void excluir() {
		try {
			fichaService.excluir(ficha);
			FacesMessages.info("Círculo excluído");
			//this.lista = null;
		} catch (Exception e) {
			FacesMessages.error("Não se pode remover um círculo que já está sendo utilizado");
		}
	}


	public List<Ficha> getListaFichas() {
		if (this.listaFichas == null) {
			this.listaFichas = fichaService.listar();
		}
		return this.listaFichas;
	}

	public void setListaFichas(List<Ficha> listaFichas) {
		this.listaFichas = listaFichas;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public List<Ficha> getListaEncontristas() {
		if (this.listaEncontristas == null) {
			this.listaEncontristas = fichaService.listarEncontreiroEncontrista(Constantes.ENCONTREIRO);
		}
		return this.listaEncontristas;
	}

	public boolean isHabilitaBotaoEditarFicha() {
		return habilitaBotaoEditarFicha;
	}

	public void setHabilitaBotaoEditarFicha(boolean habilitaBotaoEditarFicha) {
		this.habilitaBotaoEditarFicha = habilitaBotaoEditarFicha;
	}

	public boolean isHabilitaBotaoExcluirFicha() {
		return habilitaBotaoExcluirFicha;
	}

	public void setHabilitaBotaoExcluirFicha(boolean habilitaBotaoExcluirFicha) {
		this.habilitaBotaoExcluirFicha = habilitaBotaoExcluirFicha;
	}

	public boolean isHabilitaBotaoIncluiAptidoes() {
		return habilitaBotaoIncluiAptidoes;
	}

	public void setHabilitaBotaoIncluiAptidoes(boolean habilitaBotaoIncluiAptidoes) {
		this.habilitaBotaoIncluiAptidoes = habilitaBotaoIncluiAptidoes;
	}

	public boolean isHabilitaBotaoIncluiAtividades() {
		return habilitaBotaoIncluiAtividades;
	}

	public void setHabilitaBotaoIncluiAtividades(boolean habilitaBotaoIncluiAtividades) {
		this.habilitaBotaoIncluiAtividades = habilitaBotaoIncluiAtividades;
	}

	public boolean isHabilitaBotaoDetalhesFicha() {
		return habilitaBotaoDetalhesFicha;
	}

	public void setHabilitaBotaoDetalhesFicha(boolean habilitaBotaoDetalhesFicha) {
		this.habilitaBotaoDetalhesFicha = habilitaBotaoDetalhesFicha;
	}
}
