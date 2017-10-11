package br.com.ecc.controller;

import br.com.ecc.model.Circulo;
import br.com.ecc.model.Ficha;
import br.com.ecc.service.CirculoService;
import br.com.ecc.service.FichaService;
import br.com.ecc.util.Constantes;
import br.com.ecc.util.FacesMessages;

import javax.enterprise.context.RequestScoped;
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
@RequestScoped
public class FichaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Ficha ficha = new Ficha();

	private List<Ficha> listaEncontristas;

	@Inject
	private FichaService fichaService;

	public void salvar() {
		fichaService.salvar(ficha);
		this.ficha = new Ficha();
	}

	public void excluir() {
		try {
			fichaService.excluir(ficha);
			FacesMessages.info("Círculo excluído");
			//this.lista = null;
		} catch (Exception e) {
			FacesMessages.error("Não se pode remover um círculo que já está sendo utilizado");
		}
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

}
