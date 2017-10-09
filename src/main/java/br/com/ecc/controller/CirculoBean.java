package br.com.ecc.controller;

import br.com.ecc.util.FacesMessages;
import br.com.ecc.model.Circulo;
import br.com.ecc.service.CirculoService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - Círculo
 * @author Cleber Seixas
 * @since 09/10/2017
 */
@Named
@RequestScoped
public class CirculoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Circulo circulo = new Circulo();
	private List<Circulo> lista;

	@Inject
	private CirculoService circuloService;
	
	public void salvar() {
		circuloService.salvar(circulo);
		this.circulo = new Circulo();
	}

	public void excluir() {
		try {
			circuloService.excluir(circulo);
			FacesMessages.info("Círculo excluído");
			this.lista = null;
		} catch (Exception e) {
			FacesMessages.error("Não se pode remover um círculo que já está sendo utilizado");
		}
	}

	public void ativarInativar() {
		this.circulo.setAtivo(!this.circulo.isAtivo());
		circuloService.salvar(this.circulo);
		this.circulo = new Circulo();
	}
	public void setLista(List<Circulo> lista) {
		this.lista = lista;
	}

	public List<Circulo> getLista() {
		if (this.lista == null) {
			this.lista = circuloService.listar();
		}
		return this.lista;
	}

	public Circulo getCirculo() {
		return circulo;
	}

	public void setCirculo(Circulo circulo) {
		this.circulo = circulo;
	}

}
