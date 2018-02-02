package br.com.ecc.controller;

import br.com.ecc.model.Equipe;
import br.com.ecc.service.EquipeService;
import br.com.ecc.util.FacesMessages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - Equipes
 * @author Cleber Seixas
 * @since 09/10/2017
 */
@Named
@RequestScoped
public class EquipeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Equipe equipe = new Equipe();
	private List<Equipe> lista;

	private List<Equipe> listaDirigentes;

	@Inject
	private EquipeService equipeService;
	
	public void salvar() {
		equipeService.salvar(equipe);
		this.equipe = new Equipe();
	}

	public void excluir() {
		try {
			equipeService.excluir(equipe);
			FacesMessages.info("Equipe excluída");
			this.lista = null;
		} catch (Exception e) {
			FacesMessages.error("Não se pode remover uma equipe que já está sendo utilizada");
		}
	}

	public void ativarInativar() {
		this.equipe.setAtivo(!this.equipe.isAtivo());
		equipeService.salvar(this.equipe);
		this.equipe = new Equipe();
	}
	public void setLista(List<Equipe> lista) {
		this.lista = lista;
	}

	public List<Equipe> getLista() {
		if (this.lista == null) {
			this.lista = equipeService.listar();
		}
		return this.lista;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public List<Equipe> getListaDirigentes() {
		if (this.listaDirigentes == null) {
			this.listaDirigentes = equipeService.listarEquipeDirigentes();
		}
		return this.listaDirigentes;
	}
}
