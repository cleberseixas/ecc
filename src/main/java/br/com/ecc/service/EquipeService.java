package br.com.ecc.service;

import br.com.ecc.model.Equipe;
import br.com.ecc.repository.EquipeRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Circulos.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Equipe nada mais é do que a os Equipe que fazem parte do ECC.
 * Equipe obrigatoriamente faz parte de vários ECCs.
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 09/10/2017
 */
public class EquipeService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EquipeRepository equipeRepository;

	@Transactional
	public void salvar(Equipe equipe){
		try{
			this.equipeRepository.salvar(equipe);
			FacesMessages.info("Registro gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(Equipe equipe){
		equipe = equipeRepository.carregar(equipe.getId());
		this.equipeRepository.excluir(equipe);
	}		
	
	public Equipe carregar(Long id){
		try {
			return equipeRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Equipe> listar(){
		try {
			return equipeRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}
	public List<Equipe> listarEquipeDirigentes(){
		try {
			return equipeRepository.listarEquipeDirigentes();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Equipe> listarEquipeAutomatica(){
		try {
			return equipeRepository.listarEquipeAutomatica();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}


}
