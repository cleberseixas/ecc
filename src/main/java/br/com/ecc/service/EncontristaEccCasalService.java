package br.com.ecc.service;

import br.com.ecc.model.EncontristaEccCasal;
import br.com.ecc.model.EquipeEccCasal;
import br.com.ecc.repository.EncontristaEccCasalRepository;
import br.com.ecc.repository.EquipeEccCasalRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Casais dos ECCs.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Casais nãda mais é do que os ENCONTRISTAS(casais que vão participar do ECC)
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 11/11/2017
 */
public class EncontristaEccCasalService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EncontristaEccCasalRepository encontristaEccCasalRepository;

	@Transactional
	public void salvar(EncontristaEccCasal encontristaEccCasal){
		try{
			this.encontristaEccCasalRepository.salvar(encontristaEccCasal);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(EncontristaEccCasal encontristaEccCasal){
		encontristaEccCasal = encontristaEccCasalRepository.carregar(encontristaEccCasal.getId());
		this.encontristaEccCasalRepository.excluir(encontristaEccCasal);

	}		
	
	public EncontristaEccCasal carregar(Long id){
		try {
			return encontristaEccCasalRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<EncontristaEccCasal> listar(){
		try {
			return encontristaEccCasalRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public void removeCasaisEncontristasLimbo() {
		encontristaEccCasalRepository.removeCasaisEncontristasLimbo();
	}

}
