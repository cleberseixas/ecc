package br.com.ecc.service;

import br.com.ecc.model.CirculoEcc;
import br.com.ecc.repository.CirculoEccRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Círculos dos ECCs.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * CirculosEcc nada mais é do que são os ENCONTRISTAS divididos por círculos
 * CASAL_COORDENADOR : Casal Coordenador do Círculo.
 * ENCONTRISTAS_COMPOEM_CIRCULO: Casais que foram selecionados para o ECC e inclui no
 * círculo em questão
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 11/11/2017
 */
public class CirculoEccService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CirculoEccRepository circuloEccRepository;


	@Transactional
	public void salvar(CirculoEcc circuloEcc){
		try{
			this.circuloEccRepository.salvar(circuloEcc);
			FacesMessages.info("Círculo gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void alterar(CirculoEcc circuloEcc){
		try{
			this.circuloEccRepository.salvar(circuloEcc);
			FacesMessages.info("Círculo alterado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}
	}

	@Transactional
	public void excluir(CirculoEcc circuloEcc){
		circuloEcc = circuloEccRepository.carregar(circuloEcc.getId());
		this.circuloEccRepository.excluir(circuloEcc);
	}		

	@Transactional
	public void atualiza(CirculoEcc circuloEcc) {
		circuloEccRepository.salvar(circuloEcc);
	}
	public CirculoEcc carregar(Long id){
		try {
			return circuloEccRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<CirculoEcc> listar(){
		try {
			return circuloEccRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public boolean circuloJaExisteEcc(Long ecc, Long circulo) {
		try {
			return circuloEccRepository.circuloJaExisteEcc(ecc, circulo);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

	public boolean casalJaExisteEccCoordenadorCirculo(Long ecc, Long casal) {
		try {
			return circuloEccRepository.casalJaExisteEccCoordenadorCirculo(ecc, casal);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

	public boolean casalJaExisteEccOutroCirculo(Long ecc, Long casal) {
		try {
			return circuloEccRepository.casalJaExisteEccOutroCirculo(ecc, casal);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

	public List<CirculoEcc> circulosPorEcc(Long ecc) {
		try {
			return circuloEccRepository.circulosPorEcc(ecc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

}
