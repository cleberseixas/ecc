package br.com.ecc.controller;

import br.com.ecc.model.Palestra;
import br.com.ecc.service.PalestraService;
import br.com.ecc.util.FacesMessages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - Palestras
 * @author Cleber Seixas
 * @since 09/10/2017
 */
@Named
@RequestScoped
public class PalestraBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Palestra palestra = new Palestra();
	private List<Palestra> lista;

	private List<Palestra> listaAtivas;

	@Inject
	private PalestraService palestraService;
	
	public void salvar() {
		palestraService.salvar(palestra);
		this.palestra = new Palestra();
	}

	public void excluir() {
		try {
			palestraService.excluir(palestra);
			FacesMessages.info("Palestra excluída");
			this.lista = null;
		} catch (Exception e) {
			FacesMessages.error("Não se pode remover uma palestra que já está sendo utilizada");
		}
	}

	public void ativarInativar() {
		this.palestra.setAtivo(!this.palestra.isAtivo());
		palestraService.salvar(this.palestra);
		this.palestra = new Palestra();
	}
	public void setLista(List<Palestra> lista) {
		this.lista = lista;
	}

	public List<Palestra> getLista() {
		if (this.lista == null) {
			this.lista = palestraService.listar();
		}
		return this.lista;
	}

	public List<Palestra> getListaAtivas() {
		if (this.listaAtivas == null) {
			this.listaAtivas = palestraService.listarAtivas();
		}
		return this.listaAtivas;

	}

	public void setListaAtivas(List<Palestra> listaAtivas) {
		this.listaAtivas = listaAtivas;
	}

	public Palestra getPalestra() {
		return palestra;
	}

	public void setPalestra(Palestra palestra) {
		this.palestra = palestra;
	}

}
