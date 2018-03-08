package br.com.ecc.controller;

import br.com.ecc.model.*;
import br.com.ecc.service.*;
import br.com.ecc.util.FacesMessages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - EquipeEcc
 * @author Cleber Seixas
 * @since 01/11/2017
 */
@Named
@ViewScoped
public class EquipeEccBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private EquipeEcc equipeEcc = new EquipeEcc();

	private Ecc ecc = new Ecc();

	private Equipe equipe = new Equipe();

	private Ficha casalCoordenador = new Ficha();

	private Ficha casal = new Ficha();

	private EquipeEccCasal equipeEccCasal = new EquipeEccCasal();

	private List<EquipeEcc> listaEquipeEcc;

	private List<Equipe> listaEquipe;

	private List<Ficha> listaCasalCoordenador;

	private boolean habilitaBotaoEditarEquipeEcc = true;

	private boolean habilitaBotaoExcluirEquipeEcc = true;

	private boolean habilitaBotaoIncluiCasaisEncontreiros = true;

	private Ecc idEcc;

	private List<EquipeEcc> listaUltimaEquipeEcc;

	private String statusEcc = "ENCERRADO";

	@Inject
	private EquipeEccService equipeEccService;

	@Inject
	private EquipeService equipeService;

	@Inject
	private EquipeEccCasalService equipeEccCasalService;

	@Inject
	private CirculoEccService circuloEccService;

	@Inject
	private EccService eccService;

	@Inject
	private FichaService fichaService;

	@Inject
	private
	DirigenteEccService dirigenteEccService;

	public EquipeEcc getEquipeEcc() {
		return equipeEcc;
	}

	public void setEquipeEcc(EquipeEcc equipeEcc) {
		this.equipeEcc = equipeEcc;
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

	public Ficha getCasalCoordenador() {
		return casalCoordenador;
	}

	public void setCasalCoordenador(Ficha casalCoordenador) {
		this.casalCoordenador = casalCoordenador;
	}

	public Ficha getCasal() {
		return casal;
	}

	public void setCasal(Ficha casal) {
		this.casal = casal;
	}

	public EquipeEccCasal getEquipeEccCasal() {
		return equipeEccCasal;
	}

	public void setEquipeEccCasal(EquipeEccCasal equipeEccCasal) {
		this.equipeEccCasal = equipeEccCasal;
	}

	public List<EquipeEcc> getListaEquipeEcc() {
		if (this.listaEquipeEcc == null) {
			this.listaEquipeEcc = equipeEccService.listar();
		}
		return this.listaEquipeEcc;
	}

	public void setListaEquipeEcc(List<EquipeEcc> listaEquipeEcc) {
		this.listaEquipeEcc = listaEquipeEcc;
	}

	public List<Equipe> getListaEquipe() {
		if (this.listaEquipe == null) {
			this.listaEquipe = equipeService.listarEquipeAutomatica();
		}
		return this.listaEquipe;
	}

	public void setListaEquipe(List<Equipe> listaEquipe) {
		this.listaEquipe = listaEquipe;
	}

	public List<Ficha> getListaCasalCoordenador() {
		if (this.listaCasalCoordenador == null) {
			this.listaCasalCoordenador = fichaService.listarCasaisCoordenadores("a");
		}
		return this.listaCasalCoordenador;
	}

	public void setListaCasalCoordenador(List<Ficha> listaCasalCoordenador) {
		this.listaCasalCoordenador = listaCasalCoordenador;
	}

	public boolean isHabilitaBotaoEditarEquipeEcc() {
		return habilitaBotaoEditarEquipeEcc;
	}

	public void setHabilitaBotaoEditarEquipeEcc(boolean habilitaBotaoEditarEquipeEcc) {
		this.habilitaBotaoEditarEquipeEcc = habilitaBotaoEditarEquipeEcc;
	}

	public boolean isHabilitaBotaoExcluirEquipeEcc() {
		return habilitaBotaoExcluirEquipeEcc;
	}

	public void setHabilitaBotaoExcluirEquipeEcc(boolean habilitaBotaoExcluirEquipeEcc) {
		this.habilitaBotaoExcluirEquipeEcc = habilitaBotaoExcluirEquipeEcc;
	}

	public boolean isHabilitaBotaoIncluiCasaisEncontreiros() {
		return habilitaBotaoIncluiCasaisEncontreiros;
	}

	public void setHabilitaBotaoIncluiCasaisEncontreiros(boolean habilitaBotaoIncluiCasaisEncontreiros) {
		this.habilitaBotaoIncluiCasaisEncontreiros = habilitaBotaoIncluiCasaisEncontreiros;
	}

	public Ecc getIdEcc() {
		return idEcc;
	}

	public void setIdEcc(Ecc idEcc) {
		this.idEcc = idEcc;
	}

	public List<EquipeEcc> getListaUltimaEquipeEcc() {
		if (this.listaUltimaEquipeEcc == null) {
			this.listaUltimaEquipeEcc = equipeEccService.listarUltimaEquipeEcc();
		}
		return this.listaUltimaEquipeEcc;
	}

	public void setListaUltimaEquipeEcc(List<EquipeEcc> listaUltimaEquipeEcc) {
		this.listaUltimaEquipeEcc = listaUltimaEquipeEcc;
	}

	public String getStatusEcc() {
		return statusEcc;
	}

	public void setStatusEcc(String statusEcc) {
		this.statusEcc = statusEcc;
	}

	public EquipeService getEquipeService() {
		return equipeService;
	}

	public void setEquipeService(EquipeService equipeService) {
		this.equipeService = equipeService;
	}

	public EquipeEccCasalService getEquipeEccCasalService() {
		return equipeEccCasalService;
	}

	public void setEquipeEccCasalService(EquipeEccCasalService equipeEccCasalService) {
		this.equipeEccCasalService = equipeEccCasalService;
	}

	public EccService getEccService() {
		return eccService;
	}

	public void setEccService(EccService eccService) {
		this.eccService = eccService;
	}

	public FichaService getFichaService() {
		return fichaService;
	}

	public void setFichaService(FichaService fichaService) {
		this.fichaService = fichaService;
	}

	private void habilitaTodosBotoesEquipeEcc() {
		habilitaBotaoEditarEquipeEcc = false;
		habilitaBotaoExcluirEquipeEcc = false;
		habilitaBotaoIncluiCasaisEncontreiros = false;
	}

	private void desabilitaTodosBotoesEquipeEcc() {
		habilitaBotaoEditarEquipeEcc = true;
		habilitaBotaoExcluirEquipeEcc = true;
		habilitaBotaoIncluiCasaisEncontreiros = false;
	}

	public void novaEquipe() {
		this.equipeEcc = new EquipeEcc();
		this.ecc = new Ecc();
		this.equipe = new Equipe();
		this.casalCoordenador = new Ficha();
	}

	private void novoCasal() {
		this.casal = new Ficha();
		this.equipeEccCasal = new EquipeEccCasal();
	}

	public void salvaCasal() {
		int maximo = equipeEcc.getEquipe().getMaximoCasal().intValue();
		int total = equipeEcc.getEquipesEccCasais().size();
		int falta = maximo - total;
		//Não pode mais incluir
		if (falta == 0) {
			FacesMessages.error("Equipe já possui a quantidade máxima de casais.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			this.novoCasal();
			return;

		} else if (equipeEccService.casalJaExisteEccCoordenadorOuEquipe(equipeEcc.getEcc().getId(), casal.getId())) {
					FacesMessages.error("Casal já faz parte de outra equipe neste ECC.");
					RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
					this.novoCasal();
					return;
				} else if (circuloEccService.casalJaExisteEccCoordenadorCirculo(equipeEcc.getEcc().getId(), casal.getId())) {
							FacesMessages.error("Casal já faz parte de um Círculo(Coordenador) neste ECC.");
							RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
							this.novoCasal();
							return;
						} else if (dirigenteEccService.casalJaExisteEccDirigente(equipeEcc.getEcc().getId(), casal.getId(), equipeEcc.getEquipe().getId())) {
								FacesMessages.error("Casal já faz parte da Equipe de Dirigentes neste ECC.");
								RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
								this.novoCasal();
								return;
							} else {
									List<EquipeEccCasal> listAux = new ArrayList<EquipeEccCasal>();
									listAux.addAll(equipeEcc.getEquipesEccCasais());

									equipeEcc.getEquipesEccCasais().clear();
									equipeEccCasal.setEquipe(equipeEcc.getEquipe());
									equipeEccCasal.setEcc(equipeEcc.getEcc());
									equipeEccCasal.setFicha(casal);
									equipeEccCasal.setEquipeEcc(equipeEcc);
									equipeEccCasalService.salvar(equipeEccCasal);

									listAux.add(equipeEccCasal);
									this.equipeEcc.setEquipesEccCasais(listAux);
									equipeEccService.atualiza(this.equipeEcc);
									this.novoCasal();
									removeCasaisLimbo();
								   }
	}

	public void removeCasal() {
		this.equipeEcc.getEquipesEccCasais().remove(equipeEccCasal);
		equipeEccService.atualiza(equipeEcc);
	}


	public void removeCasaisLimbo() {
		equipeEccCasalService.removeCasaisEncontreirosLimbo();
	}

	public void salvarEquipe() {
		if (null == this.ecc) {
			FacesMessages.error("Favor informar o ECC");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == this.equipe) {
			FacesMessages.error("Favor informar a Equipe");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}

		if (null == this.casalCoordenador) {
			FacesMessages.error("Favor informar o Nome do Casal Coordenador");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (equipeEccService.casalJaExisteEccCoordenadorOuEquipe(ecc.getId(), casalCoordenador.getId())) {
			FacesMessages.error("Casal já faz parte de outra equipe neste ECC.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			novaEquipe();
			return;
		} else if (circuloEccService.casalJaExisteEccCoordenadorCirculo(ecc.getId(), casalCoordenador.getId()))  {
			FacesMessages.error("Casal já faz parte de um Círculo(Coordenador) neste ECC.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			novaEquipe();
			return;
		} else if (equipeEccService.equipeJaExisteEcc(ecc.getId(), equipe.getId())) {
					FacesMessages.error("Equipe já cadastrada para este ECC.");
					novaEquipe();
					RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
					return;
				} else if (dirigenteEccService.casalJaExisteEccDirigente(ecc.getId(), casalCoordenador.getId(), equipe.getId())) {
							FacesMessages.error("Casal já faz parte da Equipe de Dirigentes neste ECC.");
							RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
							this.novoCasal();
							return;
						} else {
								equipeEcc.setEcc(ecc);
								equipeEcc.setEquipe(equipe);
								equipeEcc.setCasalCoordenador(casalCoordenador);

								equipeEccService.salvar(equipeEcc);
								//this.listaEquipeEcc = equipeEccService.listar();
								this.listaUltimaEquipeEcc = equipeEccService.listarUltimaEquipeEcc();
								novaEquipe();
								desabilitaTodosBotoesEquipeEcc();
								}
	}

	public void alterarEquipe() {
		try {
			if (null == this.getCasalCoordenador()) {
				FacesMessages.error("Favor informar o Nome do Casal Coordenador");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			}
			if (equipeEccService.casalJaExisteEccCoordenadorOuEquipe(equipeEcc.getEcc().getId(), this.getCasalCoordenador().getId())) {
				FacesMessages.error("Casal já faz parte de outra equipe neste ECC.");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				//novaEquipe();
				return;
			} else if (dirigenteEccService.casalJaExisteEccDirigente(equipeEcc.getEcc().getId(), this.casalCoordenador.getId(), equipeEcc.getEquipe().getId())) {
						FacesMessages.error("Casal já faz parte da Equipe de Dirigentes neste ECC.");
						RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
						return;
					} else {
						this.equipeEcc.setCasalCoordenador(this.casalCoordenador);
						equipeEccService.alterar(this.equipeEcc);
						this.casalCoordenador = new Ficha();
					}

		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
		}
	}

	public void excluirEquipe() {
		try {
			equipeEccService.excluir(equipeEcc);
			FacesMessages.info("Equipe excluída");
			//this.listaEquipeEcc = equipeEccService.listar();
			this.listaUltimaEquipeEcc = equipeEccService.listarUltimaEquipeEcc();
			desabilitaTodosBotoesEquipeEcc();
			removeCasaisLimbo();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
		}
	}

	//habilita ou desabilita os botões disponívis na tabela da pagina montagemEquipe.xhtml
	public void onRowSelectEquipeEcc(SelectEvent selectEvent) {
		this.equipeEcc = (EquipeEcc) selectEvent.getObject();
		if (this.equipeEcc.getEcc().getSituacao().equals("ANDAMENTO")) {
			this.habilitaTodosBotoesEquipeEcc();
		} else {
			this.desabilitaTodosBotoesEquipeEcc();
		}
	}

	public void onChangeCasalCoordenador(AjaxBehaviorEvent event) {
//		this.casalCoordenador = (Ficha) event.getSource();
		//System.out.println("Photo :"+this.casalCoordenador.getFotoEle());
	}

	public void filtraEquipeEcc() {

		if (null != idEcc) {
			listaUltimaEquipeEcc = equipeEccService.filtraEquipePorEccStatus(idEcc.getId(), statusEcc);
		} else {
			listaUltimaEquipeEcc = equipeEccService.filtraEquipePorEccStatus(0L, statusEcc);
		}
	}
}
