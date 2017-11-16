package br.com.ecc.service;

import br.com.ecc.model.Atividade;
import br.com.ecc.repository.AtividadeRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio das Atividades.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Atividades nada mais é do que qual a função do ENCONTREIRO no ECC.
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 25/10/2017
 */
public class AtividadeService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private AtividadeRepository atividadeRepository;

	@Transactional
	public void salvar(Atividade atividade){
		try{
			this.atividadeRepository.salvar(atividade);
			//FacesMessages.info("Registro gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(Atividade atividade){
		atividade = atividadeRepository.carregar(atividade.getId());
		this.atividadeRepository.excluir(atividade);

	}		
	
	public Atividade carregar(Long id){
		try {
			return atividadeRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Atividade> listar(){
		try {
			return atividadeRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public void removeAtividadeLimbo() {
		atividadeRepository.removeAtividadeLimbo();
	}

}
