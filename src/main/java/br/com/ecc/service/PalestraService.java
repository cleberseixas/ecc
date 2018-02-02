package br.com.ecc.service;

import br.com.ecc.model.Circulo;
import br.com.ecc.model.Palestra;
import br.com.ecc.repository.PalestraRepository;
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
 * Palestras nada mais é do que a os Palestras que fazem parte do ECC.
 * Palestras obrigatoriamente faz parte de vários ECCs.
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 09/10/2017
 */
public class PalestraService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private PalestraRepository palestraRepository;

	@Transactional
	public void salvar(Palestra palestra){
		try{
			this.palestraRepository.salvar(palestra);
			FacesMessages.info("Registro gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(Palestra palestra){
		palestra = palestraRepository.carregar(palestra.getId());
		this.palestraRepository.excluir(palestra);
	}		
	
	public Palestra carregar(Long id){
		try {
			return palestraRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Palestra> listar(){
		try {
			return palestraRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}


	public List<Palestra> listarAtivas(){
		try {
			return palestraRepository.listarAtivas();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}
}
