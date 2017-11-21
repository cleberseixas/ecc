package br.com.ecc.service;

import br.com.ecc.model.DirigenteEcc;
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
	public void salvar(DirigenteEcc dirigenteEcc){
		try{
			this.dirigenteEccRepository.salvar(dirigenteEcc);
			//FacesMessages.info("Registro gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(DirigenteEcc dirigenteEcc){
		dirigenteEcc = dirigenteEccRepository.carregar(dirigenteEcc.getId());
		this.dirigenteEccRepository.excluir(dirigenteEcc);

	}		
	
	public DirigenteEcc carregar(Long id){
		try {
			return dirigenteEccRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<DirigenteEcc> listar(){
		try {
			return dirigenteEccRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public boolean casalJaExisteEccDirigente(Long ecc, Long casal, Long equipe) {
		try {
			return dirigenteEccRepository.casalJaExisteEccDirigente(ecc, casal, equipe);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

	public void removeDirigenteLimbo() {
		dirigenteEccRepository.removeDirigenteLimbo();
	}

	public List<DirigenteEcc> dirigentesPorEcc(Long ecc) {
		try {
			return dirigenteEccRepository.dirigentesPorEcc(ecc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}
}
