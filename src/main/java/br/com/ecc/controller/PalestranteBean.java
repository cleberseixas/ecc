package br.com.ecc.controller;

import br.com.ecc.model.Ecc;
import br.com.ecc.model.Ficha;
import br.com.ecc.model.Palestra;
import br.com.ecc.model.Palestrante;
import br.com.ecc.service.PalestranteService;
import br.com.ecc.util.FacesMessages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - Palestrantes
 * @author Cleber Seixas
 * @since 30/10/2017
 */
@Named
@ViewScoped
public class PalestranteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Palestrante palestrante = new Palestrante();

	private Ecc ecc = new Ecc();

	private Palestra palestra = new Palestra();

	private Ficha ficha = new Ficha();

	private List<Palestrante> listaPalestrante;

	private Ecc idEcc;

	private List<Palestrante> listaUltimoPalestrante;

	private String statusEcc = "ENCERRADO";

	private boolean habilitaBotaoEditarPalestrante = true;

	private boolean habilitaBotaoExcluirPalestrante = true;


	@Inject
	private PalestranteService palestranteService;

	public void novoPalestrante() {
		this.palestrante = new Palestrante();
		this.ecc = new Ecc();
		this.palestra = new Palestra();
		this.ficha = new Ficha();
	}
	public void salvarPalestrante() {
		if (null == this.ecc) {
				FacesMessages.error("Favor informar o ECC");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
		}
		if (null == this.palestra) {
			FacesMessages.error("Favor informar a Palestra");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == this.palestrante.getDataPalestra()) {
				FacesMessages.error("Favor informar a data da palestra");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
		}

		if (this.palestra.isCasal()) {
			if (null == this.ficha) {
				FacesMessages.error("Favor informar o Casal Palestrante");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			}
		} else {
			if (this.palestrante.getPalestrante().isEmpty()) {
				FacesMessages.error("Favor informar o Nome do Palestrante");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			}
		}
		palestrante.setEcc(this.ecc);
		palestrante.setPalestra(this.palestra);
		if (this.palestra.isCasal()) {
			palestrante.setFicha(this.ficha);
		}

		palestranteService.salvar(palestrante);
		//this.listaPalestrante = palestranteService.listar();
		this.listaUltimoPalestrante = palestranteService.listarUltimoPalestranteEcc();
		novoPalestrante();
	}

	public void alterarPalestrante() {
		try {
		palestranteService.alterar(this.palestrante);
		desabilitaTodosBotoesPalestrante();
		//this.listaPalestrante = palestranteService.listar();
		this.listaUltimoPalestrante = palestranteService.listarUltimoPalestranteEcc();
	} catch (Exception e) {
		FacesMessages.error(e.getMessage());
		RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
	}
	}

	public void excluirPalestra() {
		try {
			palestranteService.excluir(palestrante);
			FacesMessages.info("Palestrante excluído");
			//this.listaPalestrante = palestranteService.listar();
			this.listaUltimoPalestrante = palestranteService.listarUltimoPalestranteEcc();
		} catch (Exception e) {
			FacesMessages.error("Não se pode remover um palestrante que já está sendo utilizado");
		}
	}

	private void habilitaTodosBotoesPalestrante() {
		habilitaBotaoEditarPalestrante = false;
		habilitaBotaoExcluirPalestrante = false;
	}

	private void desabilitaTodosBotoesPalestrante() {
		habilitaBotaoEditarPalestrante = true;
		habilitaBotaoExcluirPalestrante = true;
	}

	public void onChangePalestra(AjaxBehaviorEvent event) {
		//this.palestra = (Palestra) event.getSource().
		//System.out.println("Palestra :"+this.palestra.isCasal());
	}

	//habilita ou desabilita os botões disponívis na tabela da pagina ecc.xhtml
	public void onRowSelectPalestrante(SelectEvent selectEvent) {
		this.palestrante = (Palestrante) selectEvent.getObject();
		if (this.palestrante.getEcc().getSituacao().equals("ANDAMENTO")) {
			this.habilitaTodosBotoesPalestrante();
		} else {
			this.desabilitaTodosBotoesPalestrante();
		}
	}


	public void setListaPalestrante(List<Palestrante> listaPalestrante) {
		this.listaPalestrante = listaPalestrante;
	}

	public List<Palestrante> getListaPalestrante() {
		if (this.listaPalestrante == null) {
			this.listaPalestrante = palestranteService.listar();
		}
		return this.listaPalestrante;
	}

	public Ecc getIdEcc() {
		return idEcc;
	}

	public void setIdEcc(Ecc idEcc) {
		this.idEcc = idEcc;
	}

	public List<Palestrante> getListaUltimoPalestrante() {
		if (this.listaUltimoPalestrante == null) {
			this.listaUltimoPalestrante = palestranteService.listarUltimoPalestranteEcc();
		}
		return this.listaUltimoPalestrante;
	}

	public void setListaUltimoPalestrante(List<Palestrante> listaUltimoPalestrante) {
		this.listaUltimoPalestrante = listaUltimoPalestrante;
	}

	public String getStatusEcc() {
		return statusEcc;
	}

	public void setStatusEcc(String statusEcc) {
		this.statusEcc = statusEcc;
	}

	public Palestrante getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(Palestrante palestrante) {
		this.palestrante = palestrante;
	}

	public Ecc getEcc() {
		return ecc;
	}

	public void setEcc(Ecc ecc) {
		this.ecc = ecc;
	}

	public Palestra getPalestra() {
		return palestra;
	}

	public void setPalestra(Palestra palestra) {
		this.palestra = palestra;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public boolean isHabilitaBotaoEditarPalestrante() {
		return habilitaBotaoEditarPalestrante;
	}

	public void setHabilitaBotaoEditarPalestrante(boolean habilitaBotaoEditarPalestrante) {
		this.habilitaBotaoEditarPalestrante = habilitaBotaoEditarPalestrante;
	}

	public boolean isHabilitaBotaoExcluirPalestrante() {
		return habilitaBotaoExcluirPalestrante;
	}

	public void setHabilitaBotaoExcluirPalestrante(boolean habilitaBotaoExcluirPalestrante) {
		this.habilitaBotaoExcluirPalestrante = habilitaBotaoExcluirPalestrante;
	}

	public void filtraPalestranteEcc() {

		if (null != idEcc) {
			listaUltimoPalestrante = palestranteService.filtraPalestrantePorEccStatus(idEcc.getId(), statusEcc);
		} else {
			listaUltimoPalestrante = palestranteService.filtraPalestrantePorEccStatus(0L, statusEcc);
		}
	}

}
