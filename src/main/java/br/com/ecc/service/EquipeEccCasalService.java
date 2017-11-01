package br.com.ecc.service;

import br.com.ecc.model.DirigenteEcc;
import br.com.ecc.model.EquipeEccCasal;
import br.com.ecc.repository.DirigenteEccRepository;
import br.com.ecc.repository.EquipeEccCasalRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Casais das Equipes.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Dirigente nada mais é do que a Equipe de Dirigentes existentes em cada ECC.
 * Um Círculo obrigatoriamente faz parte do ECC.
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 25/10/2017
 */
public class EquipeEccCasalService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EquipeEccCasalRepository equipeEccCasalRepository;

	@Transactional
	public void salvar(EquipeEccCasal equipeEccCasal){
		try{
			this.equipeEccCasalRepository.salvar(equipeEccCasal);
			//FacesMessages.info("Registro gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(EquipeEccCasal equipeEccCasal){
		equipeEccCasal = equipeEccCasalRepository.carregar(equipeEccCasal.getId());
		this.equipeEccCasalRepository.excluir(equipeEccCasal);

	}		
	
	public EquipeEccCasal carregar(Long id){
		try {
			return equipeEccCasalRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<EquipeEccCasal> listar(){
		try {
			return equipeEccCasalRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public void removeCasaisLimbo() {
		equipeEccCasalRepository.removeCasaisLimbo();
	}

}
