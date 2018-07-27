package br.com.ecc.service;

import br.com.ecc.model.DirigenteNacionalEcc;
import br.com.ecc.repository.DirigenteNacionalEccRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por gerenciar as regras de negócio dos Dirigentes Nacionais dos ECCs.
 *  ASSISTENTE ECLESIASTICO NACIONAL
 	SECRETARIA NACIONAL (DOIS CASAIS)
 	DIRETOR ESPIRITUAL REGIONAL NOROESTE
 	CASAL REGIONAL
 	BISPO ARQUI(DIOCESANO)
 	DIRETOR ESPIRITUAL ARQUI(DIOCESANO)
 	CASAL ARQUIDIOCESANO
 	CASAL LIGACAO SETORIAL
 * </br>-----------------------------------------------------------------------</br>
 *                      <b>Regras de negócio</b>
 * </br>-----------------------------------------------------------------------</br>
 * DirigenteNacionalEcc nada mais é do que os dirigentes nacionais no dia do ECC
 *
 * </br>-----------------------------------------------------------------------</br>
 * @author Cleber Seixas
 * @since 24/02/2018
 */
public class DirigenteNacionalEccService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private DirigenteNacionalEccRepository dirigenteNacionalEccRepository;


	@Transactional
	public void salvar(DirigenteNacionalEcc dirigenteNacionalEcc){
		try{
			dirigenteNacionalEcc.setOrdemExibicao(defineOrgem(dirigenteNacionalEcc));
			this.dirigenteNacionalEccRepository.salvar(dirigenteNacionalEcc);
			FacesMessages.info("Dirigente Nacional gravado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void alterar(DirigenteNacionalEcc dirigenteNacionalEcc){
		try{
			dirigenteNacionalEcc.setOrdemExibicao(defineOrgem(dirigenteNacionalEcc));
			this.dirigenteNacionalEccRepository.salvar(dirigenteNacionalEcc);
			FacesMessages.info("Dirigente Nacional alterado");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}
	}

	@Transactional
	public void excluir(DirigenteNacionalEcc dirigenteNacionalEcc){
		dirigenteNacionalEcc = dirigenteNacionalEccRepository.carregar(dirigenteNacionalEcc.getId());
		this.dirigenteNacionalEccRepository.excluir(dirigenteNacionalEcc);
	}		

	@Transactional
	public void atualiza(DirigenteNacionalEcc dirigenteNacionalEcc) {
		dirigenteNacionalEccRepository.salvar(dirigenteNacionalEcc);
	}
	public DirigenteNacionalEcc carregar(Long id){
		try {
			return dirigenteNacionalEccRepository.carregar(id);
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public Long defineOrgem(DirigenteNacionalEcc dirigenteNacionalEcc) {
		Long retorno = 0L;
		if (dirigenteNacionalEcc.getTipo().equals("ASSISTENTE ECLESIASTICO NACIONAL DO ECC"))
			retorno = 1L;
		else if (dirigenteNacionalEcc.getTipo().equals("CASAIS DA SECRETARIA NACIONAL DO ECC"))
			retorno = 2L;
		else if (dirigenteNacionalEcc.getTipo().equals("DIRETOR ESPIRITUAL DO CONSELHO REGIONAL"))
			retorno = 3L;
		else if (dirigenteNacionalEcc.getTipo().equals("CASAL DO CONSELHO REGIONAL"))
			retorno = 4L;
		else if (dirigenteNacionalEcc.getTipo().equals("BISPO (ARQUI)DIOCESANO"))
			retorno = 5L;
		else if (dirigenteNacionalEcc.getTipo().equals("DIRETOR ESPIRITUAL (ARQUI)DIOCESANO"))
			retorno = 6L;
		else if (dirigenteNacionalEcc.getTipo().equals("CASAL (ARQUI)DIOCESANO"))
			retorno = 7L;
		else retorno = 8L;
	return retorno;
	}

	public List<DirigenteNacionalEcc> listar(){
		try {
			return dirigenteNacionalEccRepository.listar();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

	public List<DirigenteNacionalEcc> listaUltimoDirigenteNacionalEcc(){
		try {
			return dirigenteNacionalEccRepository.listaUltimoDirigenteNacionalEcc();
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

//	public boolean circuloEstudoJaExisteEccTema(Long ecc, Long circulo, String tema) {
//		try {
//			return circuloEstudoEccRepository.circuloEstudoJaExisteEccTema(ecc, circulo, tema);
//		} catch (NegocioException e) {
//			FacesMessages.error(e.getMessage());
//			return true;
//		}
//	}

	public List<DirigenteNacionalEcc> filtraDirigenteNacionalPorEccStatus(Long ecc, String statusEcc) {
		try {
			return dirigenteNacionalEccRepository.filtraDirigenteNacionalPorEccStatus(ecc, statusEcc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

}
