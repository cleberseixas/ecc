package br.com.ecc.service;

import br.com.ecc.model.Circulo;
import br.com.ecc.model.Ficha;
import br.com.ecc.repository.CirculoRepository;
import br.com.ecc.repository.FichaRepository;
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
 * Círculos nada mais é do que a os Círculos existentes no ECC.
 * Um Círculo obrigatoriamente faz parte de vários ECCs.
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 09/10/2017
 */
public class FichaService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private FichaRepository fichaRepository;

	@Transactional
	public void salvar(Ficha ficha){
		try{
			ficha.setNomeUsual(ficha.getNomeUsualEle()+" E "+ficha.getNomeUsualEla());
			this.fichaRepository.salvar(ficha);
			FacesMessages.info("Registro gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(Ficha ficha){
		ficha = fichaRepository.carregar(ficha.getId());
		this.fichaRepository.excluir(ficha);
	}		
	
	public Ficha carregar(Long id){
		try {
			return fichaRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Ficha> listar(){
		try {
			return fichaRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Ficha> listarEncontreiroEncontrista(String parametro){
		try {
			return fichaRepository.listarEncontreiroEncontrista(parametro);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}
}
