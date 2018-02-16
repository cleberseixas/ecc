package br.com.ecc.service;

import br.com.ecc.model.Palestrante;
import br.com.ecc.repository.PalestranteRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Palestrantes.
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * Palestrantes nada mais é do que os Palestrantes que irão ministrar as palestras do ECC.
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 30/10/2017
 */
public class PalestranteService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private PalestranteRepository palestranteRepository;

	@Transactional
	public void salvar(Palestrante palestrante){
		try{
			this.palestranteRepository.salvar(palestrante);
			FacesMessages.info("Palestrante gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void alterar(Palestrante palestrante){
		try{
			this.palestranteRepository.salvar(palestrante);
			FacesMessages.info("Palestrante alterado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}
	}

	@Transactional
	public void excluir(Palestrante palestrante){
		palestrante = palestranteRepository.carregar(palestrante.getId());
		this.palestranteRepository.excluir(palestrante);
	}		
	
	public Palestrante carregar(Long id){
		try {
			return palestranteRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Palestrante> listar(){
		try {
			return palestranteRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Palestrante> listarUltimoPalestranteEcc(){
		try {
			return palestranteRepository.listarUltimoPalestranteEcc();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Palestrante> palestrantesPorEcc(Long ecc) {
		try {
			return palestranteRepository.palestrantesPorEcc(ecc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<Palestrante> filtraPalestrantePorEccStatus(Long ecc, String statusEcc) {
		try {
			return palestranteRepository.filtraPalestrantePorEccStatus(ecc, statusEcc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}
}
