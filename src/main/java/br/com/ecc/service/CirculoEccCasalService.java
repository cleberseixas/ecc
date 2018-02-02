package br.com.ecc.service;

import br.com.ecc.model.CirculoEccCasal;
import br.com.ecc.repository.CirculoEccCasalRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Casais dos Circulos.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Nada mais é do que os casais (ENCONTRISTAS) que irão compor os círculos.
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 11/11/2017
 */
public class CirculoEccCasalService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CirculoEccCasalRepository circuloEccCasalRepository;

	@Transactional
	public void salvar(CirculoEccCasal circuloEccCasal){
		try{
			this.circuloEccCasalRepository.salvar(circuloEccCasal);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(CirculoEccCasal circuloEccCasal){
		circuloEccCasal = circuloEccCasalRepository.carregar(circuloEccCasal.getId());
		this.circuloEccCasalRepository.excluir(circuloEccCasal);

	}		
	
	public CirculoEccCasal carregar(Long id){
		try {
			return circuloEccCasalRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<CirculoEccCasal> listar(){
		try {
			return circuloEccCasalRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public Long casalEncontristaExistenteCircunoNoEcc(Long ecc, Long casal){
		try {
			return circuloEccCasalRepository.casalEncontristaExistenteCircunoNoEcc(ecc, casal);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public void removeCasalCirculoQuandoRemoveEncontristaLista(Long casal) {
		circuloEccCasalRepository.removeCasalCirculoQuandoRemoveEncontristaLista(casal);
	}

	public void removeCasaisEncontristasLimbo() {
		circuloEccCasalRepository.removeCasaisEncontristasLimbo();
	}

}
