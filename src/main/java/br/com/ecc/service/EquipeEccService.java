package br.com.ecc.service;

import br.com.ecc.model.EquipeEcc;
import br.com.ecc.repository.EquipeEccRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio das Equipes dos ECCs.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Equipes nada mais é do que são as pessoas que irão trabalhar no ECC existentes ano a ano na PSLG.
 * Cada Equipe possui um casal coordenador e os casais que fazem parte da EQUIPE.
 * Obrigatoriamente os casais que irão trabalhar na equipe devem ser ENCONTREIROS.
 * CASAL_COORDENADOR : Casal que já foi membro de equipe.
 * CASAIS_COMPOEM_EQUIPE: Pega os casais que trabalharam em outros ECCs INDEPENDENTE DA EQUIPE,
 * porém não pde ter sido coordenador da equipe que está sendo montada
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 01/11/2017
 */
public class EquipeEccService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EquipeEccRepository equipeEccRepository;


	@Transactional
	public void salvar(EquipeEcc equipeEcc){
		try{
			this.equipeEccRepository.salvar(equipeEcc);
			FacesMessages.info("Equipe gravada");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void alterar(EquipeEcc equipeEcc){
		try{
			this.equipeEccRepository.salvar(equipeEcc);
			FacesMessages.info("Equipe alterada");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}
	}

	@Transactional
	public void excluir(EquipeEcc equipeEcc){
		equipeEcc = equipeEccRepository.carregar(equipeEcc.getId());
		this.equipeEccRepository.excluir(equipeEcc);
	}		

	@Transactional
	public void atualiza(EquipeEcc equipeEcc) {
		equipeEccRepository.salvar(equipeEcc);
	}
	public EquipeEcc carregar(Long id){
		try {
			return equipeEccRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<EquipeEcc> listar(){
		try {
			return equipeEccRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<EquipeEcc> listarUltimaEquipeEcc(){
		try {
			return equipeEccRepository.listarUltimaEquipeEcc();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public boolean equipeJaExisteEcc(Long ecc, Long equipe) {
		try {
			return equipeEccRepository.equipeJaExisteEcc(ecc, equipe);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

	public boolean casalJaExisteEccCoordenadorOuEquipe(Long ecc, Long casal) {
		try {
			return equipeEccRepository.casalJaExisteEccCoordenadorOuEquipe(ecc, casal);
		} catch (NegocioException e) {
			FacesMessages.error(e.getMessage());
			return true;
		}
	}

	public List<EquipeEcc> equipesPorEcc(Long ecc) {
		try {
			return equipeEccRepository.equipesPorEcc(ecc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<EquipeEcc> filtraEquipePorEccStatus(Long ecc, String statusEcc) {
		try {
			return equipeEccRepository.filtraEquipePorEccStatus(ecc, statusEcc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

}
