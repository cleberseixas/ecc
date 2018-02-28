package br.com.ecc.service;

import br.com.ecc.model.CirculoEcc;
import br.com.ecc.model.CirculoEstudoEcc;
import br.com.ecc.repository.CirculoEstudoEccRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Círculos de Estudos dos ECCs.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * CirculosEstudoEcc nada mais é do que são as dinâmicas dos círculos no dia do ECC
 * TEMA : HARMONIA CONJUGAL E PARÁBOLA DO FILHO PRÓDIGO
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 24/02/2018
 */
public class CirculoEstudoEccService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CirculoEstudoEccRepository circuloEstudoEccRepository;


	@Transactional
	public void salvar(CirculoEstudoEcc circuloEstudoEcc){
		try{
			this.circuloEstudoEccRepository.salvar(circuloEstudoEcc);
			FacesMessages.info("Círculo de Estudo gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void alterar(CirculoEstudoEcc circuloEstudoEcc){
		try{
			this.circuloEstudoEccRepository.salvar(circuloEstudoEcc);
			FacesMessages.info("Círculo de Estudo alterado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}
	}

	@Transactional
	public void excluir(CirculoEstudoEcc circuloEstudoEcc){
		circuloEstudoEcc = circuloEstudoEccRepository.carregar(circuloEstudoEcc.getId());
		this.circuloEstudoEccRepository.excluir(circuloEstudoEcc);
	}		

	@Transactional
	public void atualiza(CirculoEstudoEcc circuloEstudoEcc) {
		circuloEstudoEccRepository.salvar(circuloEstudoEcc);
	}
	public CirculoEstudoEcc carregar(Long id){
		try {
			return circuloEstudoEccRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<CirculoEstudoEcc> listar(){
		try {
			return circuloEstudoEccRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<CirculoEstudoEcc> listaUltimoCirculoEcc(){
		try {
			return circuloEstudoEccRepository.listaUltimoCirculoEcc();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public boolean circuloEstudoJaExisteEccTema(Long ecc, Long circulo, String tema) {
		try {
			return circuloEstudoEccRepository.circuloEstudoJaExisteEccTema(ecc, circulo, tema);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

	public List<CirculoEstudoEcc> filtraCirculoPorEccStatus(Long ecc, String statusEcc) {
		try {
			return circuloEstudoEccRepository.filtraCirculoPorEccStatus(ecc, statusEcc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

}
