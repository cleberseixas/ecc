package br.com.ecc.service;

import br.com.ecc.model.Circulo;
import br.com.ecc.model.Ficha;
import br.com.ecc.repository.CirculoRepository;
import br.com.ecc.repository.FichaRepository;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.NegocioException;
import br.com.ecc.util.Transactional;
import com.sun.xml.internal.messaging.saaj.soap.FastInfosetDataContentHandler;

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
			primeiraSegundaTerceiraEtapa(ficha);
//			if (!ficha.getSituacao().equals("ENCONTRISTA")) {
//				if (ficha.getSegundaEtapa().trim().length() > 0) {
//					if (!ficha.getPrimeiraEtapa().contains("º")) {
//						ficha.setPrimeiraEtapa(ficha.getPrimeiraEtapa()+"º");
//					}
//				}
//				if (ficha.getSegundaEtapa().trim().length() > 0) {
//					if (!ficha.getSegundaEtapa().contains("º")) {
//						ficha.setSegundaEtapa(ficha.getSegundaEtapa()+"º");
//					}
//				}
//				if (ficha.getTerceiraEtapa().trim().length() > 0) {
//					if (!ficha.getTerceiraEtapa().contains("º")) {
//						ficha.setTerceiraEtapa(ficha.getTerceiraEtapa()+"º");
//					}
//				}
//			}
			this.fichaRepository.salvar(ficha);
			FacesMessages.info("Ficha cadastrada");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}	
	}

	@Transactional
	public void excluir(Ficha ficha){
		ficha = fichaRepository.carregar(ficha.getId());
		this.fichaRepository.excluir(ficha);
	}

	@Transactional
	public void alterar(Ficha ficha){
		try{
			ficha.setNomeUsual(ficha.getNomeUsualEle()+" E "+ficha.getNomeUsualEla());
			primeiraSegundaTerceiraEtapa(ficha);

//			if (!ficha.getSituacao().equals("ENCONTRISTA")) {
//				if (ficha.getSegundaEtapa().trim().length() > 0) {
//					if (!ficha.getPrimeiraEtapa().contains("º")) {
//						ficha.setPrimeiraEtapa(ficha.getPrimeiraEtapa()+"º");
//					}
//				}
//				if (ficha.getSegundaEtapa().trim().length() > 0) {
//					if (!ficha.getSegundaEtapa().contains("º")) {
//						ficha.setSegundaEtapa(ficha.getSegundaEtapa()+"º");
//					}
//				}
//				if (ficha.getTerceiraEtapa().trim().length() > 0) {
//					if (!ficha.getTerceiraEtapa().contains("º")) {
//						ficha.setTerceiraEtapa(ficha.getTerceiraEtapa()+"º");
//					}
//				}
//			}
			this.fichaRepository.salvar(ficha);
			FacesMessages.info("Ficha alterada");
		}catch(NegocioException e){
			FacesMessages.error(e.getMessage());
		}
	}

	private void primeiraSegundaTerceiraEtapa(Ficha ficha) {
		if (!ficha.getSituacao().equals("ENCONTRISTA")) {
			if (ficha.getSegundaEtapa().trim().length() > 0) {
				if (!ficha.getPrimeiraEtapa().contains("º")) {
					ficha.setPrimeiraEtapa(ficha.getPrimeiraEtapa()+"º");
				}
			}
			if (ficha.getSegundaEtapa().trim().length() > 0) {
				if (!ficha.getSegundaEtapa().contains("º")) {
					ficha.setSegundaEtapa(ficha.getSegundaEtapa()+"º");
				}
			}
			if (ficha.getTerceiraEtapa().trim().length() > 0) {
				if (!ficha.getTerceiraEtapa().contains("º")) {
					ficha.setTerceiraEtapa(ficha.getTerceiraEtapa()+"º");
				}
			}
		}
	}

	@Transactional
	public void atualiza(Ficha ficha){
		fichaRepository.salvar(ficha);
		//FacesMessages.info("Foto salva");
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

	public List<Ficha> listarEncontristaEcc(Long ecc){
		try {
			return fichaRepository.listarEncontristaEcc(ecc);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}



	public List<Ficha> filtraFichaSituacaoeNomeUsual(String situacao, String nomeUsualCasal){
		try {
			return fichaRepository.filtraFichaSituacaoeNomeUsual(situacao, nomeUsualCasal);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}


	public List<Ficha> listarCasaisCoordenadores(String equipe){
		try {
			return fichaRepository.listarCasaisCoordenadores(equipe);
		} catch(NegocioException e){
			FacesMessages.error(e.getMessage());
			return null;
		}
	}

}
