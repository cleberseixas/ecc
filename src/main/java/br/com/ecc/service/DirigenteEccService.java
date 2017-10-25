package br.com.ecc.service;

import br.com.ecc.model.Dirigente;
import br.com.ecc.repository.DirigenteEccRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Dirigentes.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Dirigente nada mais é do que a Equipe de Dirigentes existentes em cada ECC.
 * Um Círculo obrigatoriamente faz parte do ECC.
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 25/10/2017
 */
public class DirigenteEccService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private DirigenteEccRepository dirigenteEccRepository;

	@Transactional
	public void salvar(Dirigente dirigenteEcc){
		try{
			this.dirigenteEccRepository.salvar(dirigenteEcc);
			//FacesMessages.info("Registro gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(Dirigente dirigenteEcc){
		dirigenteEcc = dirigenteEccRepository.carregar(dirigenteEcc.getId());
		this.dirigenteEccRepository.excluir(dirigenteEcc);

	}		
	
	public Dirigente carregar(Long id){
		try {
			return dirigenteEccRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Dirigente> listar(){
		try {
			return dirigenteEccRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public void removeDirigenteLimbo() {
		dirigenteEccRepository.removeDirigenteLimbo();
	}

}
