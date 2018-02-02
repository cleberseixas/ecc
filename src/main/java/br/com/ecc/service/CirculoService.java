package br.com.ecc.service;

import br.com.ecc.model.Circulo;
import br.com.ecc.repository.CirculoRepository;
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
public class CirculoService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CirculoRepository circuloRepository;

	@Transactional
	public void salvar(Circulo circulo){
		try{
			this.circuloRepository.salvar(circulo);
			FacesMessages.info("Registro gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(Circulo circulo){
		circulo = circuloRepository.carregar(circulo.getId());
		this.circuloRepository.excluir(circulo);
	}		
	
	public Circulo carregar(Long id){
		try {
			return circuloRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Circulo> listar(){
		try {
			return circuloRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}	
}
