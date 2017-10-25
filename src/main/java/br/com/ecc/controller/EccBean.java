package br.com.ecc.controller;

import br.com.ecc.model.Dirigente;
import br.com.ecc.model.Ecc;
import br.com.ecc.model.Equipe;
import br.com.ecc.model.Ficha;
import br.com.ecc.service.DirigenteEccService;
import br.com.ecc.service.EccService;
import br.com.ecc.service.EquipeService;
import br.com.ecc.util.FacesMessages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - Eccs
 * @author Cleber Seixas
 * @since 10/10/2017
 */
@Named
@ViewScoped
public class EccBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Ecc ecc = new Ecc();

	private Equipe equipe = new Equipe();

	private Ficha casal = new Ficha();

	private Dirigente dirigenteEcc = new Dirigente();

	private List<Ecc> listaEcc;

	private List<Equipe> listaEquipe;

	private boolean habilitaBotaoEditarEcc = true;

	private boolean habilitaBotaoExcluirEcc = true;

	private boolean habilitaBotaoIncluiDirigentes = true;

	private boolean habilitaBotaoDetalhesEcc = true;


	@Inject
	private EccService eccService;

	@Inject
	private DirigenteEccService dirigenteEccService;


	@Inject
	private EquipeService equipeService;

	public void setListaEcc(List<Ecc> listaEcc) {
		this.listaEcc = listaEcc;
	}

	public List<Ecc> getListaEcc() {
		if (this.listaEcc == null) {
			this.listaEcc = eccService.listar();
		}
		return this.listaEcc;
	}

	public List<Equipe> getListaEquipe() {
		if (this.listaEquipe == null) {
			this.listaEquipe = equipeService.listarEquipeDirigentes();
		}
		return this.listaEquipe;
	}

	public void setListaEquipe(List<Equipe> listaEquipe) {
		this.listaEquipe = listaEquipe;
	}

	public Ecc getEcc() {
		return ecc;
	}

	public void setEcc(Ecc ecc) {
		this.ecc = ecc;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public Ficha getCasal() {
		return casal;
	}

	public void setCasal(Ficha casal) {
		this.casal = casal;
	}

	public void novoEcc() {
		this.ecc = new Ecc();
	}

	public Dirigente getDirigenteEcc() {
		return dirigenteEcc;
	}

	public void setDirigenteEcc(Dirigente dirigenteEcc) {
		this.dirigenteEcc = dirigenteEcc;
	}

	public boolean isHabilitaBotaoEditarEcc() {
		return habilitaBotaoEditarEcc;
	}

	public void setHabilitaBotaoEditarEcc(boolean habilitaBotaoEditarEcc) {
		this.habilitaBotaoEditarEcc = habilitaBotaoEditarEcc;
	}

	public boolean isHabilitaBotaoExcluirEcc() {
		return habilitaBotaoExcluirEcc;
	}

	public void setHabilitaBotaoExcluirEcc(boolean habilitaBotaoExcluirEcc) {
		this.habilitaBotaoExcluirEcc = habilitaBotaoExcluirEcc;
	}

	public boolean isHabilitaBotaoIncluiDirigentes() {
		return habilitaBotaoIncluiDirigentes;
	}

	public void setHabilitaBotaoIncluiDirigentes(boolean habilitaBotaoIncluiDirigentes) {
		this.habilitaBotaoIncluiDirigentes = habilitaBotaoIncluiDirigentes;
	}

	public boolean isHabilitaBotaoDetalhesEcc() {
		return habilitaBotaoDetalhesEcc;
	}

	public void setHabilitaBotaoDetalhesEcc(boolean habilitaBotaoDetalhesEcc) {
		this.habilitaBotaoDetalhesEcc = habilitaBotaoDetalhesEcc;
	}

	private void novoDirigente() {
		this.casal = new Ficha();
		this.equipe = new Equipe();
		this.dirigenteEcc = new Dirigente();
	}

	public void salvaDirigente() {
		List<Dirigente> listAux = new ArrayList<Dirigente>();
		listAux.addAll(ecc.getDirigentes());

		ecc.getDirigentes().clear();
		dirigenteEcc.setEquipe(equipe);
		dirigenteEcc.setFicha(casal);
		dirigenteEcc.setEcc(ecc);
		dirigenteEccService.salvar(dirigenteEcc);

		listAux.add(dirigenteEcc);
		this.ecc.setDirigentes(listAux);
		eccService.atualiza(this.ecc);
		this.novoDirigente();
		removeDirigenteLimbo();
	}
	public void removeDirigente() {
		this.ecc.getDirigentes().remove(dirigenteEcc);
		eccService.atualiza(ecc);
		//removeDirigenteLimbo();
	}

	public void removeDirigenteLimbo() {
		dirigenteEccService.removeDirigenteLimbo();
	}

	private void habilitaTodosBotoesEcc() {
		habilitaBotaoEditarEcc = false;
		habilitaBotaoExcluirEcc = false;
		habilitaBotaoIncluiDirigentes = false;
		habilitaBotaoDetalhesEcc = false;
	}

	private void desabilitaTodosBotoesEcc() {
		habilitaBotaoEditarEcc = true;
		habilitaBotaoExcluirEcc = true;
		habilitaBotaoIncluiDirigentes = true;
		habilitaBotaoDetalhesEcc = false;
	}

	//Injetar Equipes e Casais (Encontreiros)
	public void equipeDirigente() {

	}

	//Incluir as regras para salvar o Ecc (Equipes)
	public void salvarEcc() {
		eccService.salvar(ecc);
		this.listaEcc = eccService.listar();
		this.ecc = new Ecc();
		desabilitaTodosBotoesEcc();
	}

	public void alterarEcc() {
		try {
//			if (this.ecc.getNumero().trim().length() <= 0) {
//				FacesMessages.error("Favor informar o número");
//				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
//				return;
//			}
//			if (this.ecc.getLocal().isEmpty()) {
//				FacesMessages.error("Favor informar o local");
//				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
//				return;
//			}
//			if (null == this.ecc.getTotal() || (this.ecc.getTotal().intValue() <= 0)) {
//				FacesMessages.error("Favor informar o total");
//				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
//				return;
//			}
////			if (this.ecc.getTema().isEmpty()) {
////				FacesMessages.error("Favor informar o tema");
////				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
////				return;
////			}
//			if (null == this.ecc.getDataInicio()) {
//				FacesMessages.error("Favor informar a data início");
//				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
//				return;
//			}
//			if (null == this.ecc.getDataFim()) {
//				FacesMessages.error("Favor informar a data final");
//				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
//				return;
//			}
			eccService.alterar(this.ecc);
			desabilitaTodosBotoesEcc();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
		}
	}

	public void excluirEcc() {
		try {
			eccService.excluir(ecc);
			FacesMessages.info("ECC excluído");
			this.listaEcc = eccService.listar();
			desabilitaTodosBotoesEcc();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
		}
	}
	//habilita ou desabilita os botões disponívis na tabela da pagina ecc.xhtml
	public void onRowSelectEcc(SelectEvent selectEvent) {
		this.ecc = (Ecc) selectEvent.getObject();
		if (this.ecc.getSituacao().equals("ANDAMENTO")) {
			this.habilitaTodosBotoesEcc();
		} else {
			this.desabilitaTodosBotoesEcc();
		}
	}

	public void removeDirigentesLimbo() {
		dirigenteEccService.removeDirigenteLimbo();
	}
}
