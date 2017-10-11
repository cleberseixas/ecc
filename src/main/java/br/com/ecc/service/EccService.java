package br.com.ecc.service;

import br.com.ecc.model.Ecc;
import br.com.ecc.repository.EccRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos ECCs.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Eccs nada mais é do que a os ECCs existentes ano a ano na PSLG.
 * Um Ecc obrigatoriamente deve ser composto por Equipes (Circulos, Encontristas).
 * É o núcleo do sistema, diante deste cadastro que serão definidas as demais ações
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 10/10/2017
 */
public class EccService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EccRepository eccRepository;

	@Transactional
	public void salvar(Ecc ecc){
		try{
			if (!ecc.getNumero().contains("º")) {
				ecc.setNumero(ecc.getNumero()+"º");
			}
			this.eccRepository.salvar(ecc);
			FacesMessages.info("ECC gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void alterar(Ecc ecc){
		try{
			if (!ecc.getNumero().contains("º")) {
				ecc.setNumero(ecc.getNumero()+"º");
			}
			this.eccRepository.salvar(ecc);
			FacesMessages.info("ECC alterado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}
	}

	@Transactional
	public void excluir(Ecc ecc){
		ecc = eccRepository.carregar(ecc.getId());
		this.eccRepository.excluir(ecc);
	}		
	
	public Ecc carregar(Long id){
		try {
			return eccRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Ecc> listar(){
		try {
			return eccRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}	
}
