package br.com.ecc.service;

import br.com.ecc.model.Aptidao;
import br.com.ecc.repository.AptidaoRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio das Aptidões.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Aptidões nada mais é do que as Hbilidades dos ENCONTRISTAS,
 * Usado para a montagem da equipe .
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 26/10/2017
 */
public class AptidaoService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AptidaoRepository aptidaoRepository;

	@Transactional
	public void salvar(Aptidao aptidao){
		try{
			this.aptidaoRepository.salvar(aptidao);
			//FacesMessages.info("Registro gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(Aptidao aptidao){
		aptidao = aptidaoRepository.carregar(aptidao.getId());
		this.aptidaoRepository.excluir(aptidao);

	}		
	
	public Aptidao carregar(Long id){
		try {
			return aptidaoRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Aptidao> listar(){
		try {
			return aptidaoRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public void removeAptidaoLimbo() {
		aptidaoRepository.removeAptidaoLimbo();
	}

}
