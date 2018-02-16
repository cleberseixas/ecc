package br.com.ecc.service;

import br.com.ecc.model.EncontristaEcc;
import br.com.ecc.model.EquipeEcc;
import br.com.ecc.repository.EncontristaEccRepository;
import br.com.ecc.repository.EquipeEccRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Enconstristas dos ECCs.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Enconstristas nada mais é do que são os casais (ENCONTREIROS) que irão participar do ECC existentes ano a ano na PSLG.
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 06/11/2017
 */
public class EncontristaEccService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EncontristaEccRepository encontristaEccRepository;


	@Transactional
	public void salvar(EncontristaEcc encontristaEcc){
		try{
			this.encontristaEccRepository.salvar(encontristaEcc);
			FacesMessages.info("Enconstrista gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void alterar(EncontristaEcc encontristaEcc){
		try{
			this.encontristaEccRepository.salvar(encontristaEcc);
			FacesMessages.info("Encontrista alterado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}
	}

	@Transactional
	public void excluir(EncontristaEcc encontristaEcc){
		encontristaEcc = encontristaEccRepository.carregar(encontristaEcc.getId());
		this.encontristaEccRepository.excluir(encontristaEcc);
	}		

	@Transactional
	public void atualiza(EncontristaEcc encontristaEcc) {
		encontristaEccRepository.salvar(encontristaEcc);
	}
	public EncontristaEcc carregar(Long id){
		try {
			return encontristaEccRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<EncontristaEcc> listar(){
		try {
			return encontristaEccRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<EncontristaEcc> listarUltimoEncontristaEcc(){
		try {
			return encontristaEccRepository.listarUltimoEncontristaEcc();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public boolean jaExisteEcc(Long ecc) {
		try {
			return encontristaEccRepository.jaExisteEcc(ecc);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

	public boolean casalEncontristaJaExisteNoEcc(Long ecc, Long casal) {
		try {
			return encontristaEccRepository.casalEncontristaJaExisteNoEcc(ecc, casal);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

	public List<EncontristaEcc> filtraEncontristaPorEccStatus(Long ecc, String statusEcc) {
		try {
			return encontristaEccRepository.filtraEncontristaPorEccStatus(ecc, statusEcc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}
}
