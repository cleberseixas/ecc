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
 * Classe responsável por interligar a view à regra de negócio - CirculoEcc
 * @author Cleber Seixas
 * @since 11/11/2017
 */
@Named
@ViewScoped
public class CirculoEstudoEccBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private CirculoEstudoEcc circuloEstudoEcc = new CirculoEstudoEcc();

	private Ecc ecc = new Ecc();

	private Circulo circulo = new Circulo();

	private Ficha casalRelator = new Ficha();


	private List<CirculoEstudoEcc> listaCirculoEstudoEcc;

	private List<Circulo> listaCirculo;

	private List<Ficha> listaCasalRelator;


	private Ecc idEcc;

	private List<CirculoEstudoEcc> listaUltimoCirculoEstudo;

	private String statusEcc = "ENCERRADO";

	private boolean habilitaBotaoEditarCirculoEstudoEcc = true;

	private boolean habilitaBotaoExcluirCirculoEstudoEcc = true;


	@Inject
	private CirculoEstudoEccService circuloEstudoEccService;

	@Inject
	private CirculoService circuloService;

	@Inject
	private CirculoEccCasalService circuloEccCasalService;

	@Inject
	private EccService eccService;


	@Inject
	private FichaService fichaService;


	public CirculoEstudoEcc getCirculoEstudoEcc() {
		return circuloEstudoEcc;
	}

	public void setCirculoEstudoEcc(CirculoEstudoEcc circuloEstudoEcc) {
		this.circuloEstudoEcc = circuloEstudoEcc;
	}

	public Ecc getEcc() {
		return ecc;
	}

	public void setEcc(Ecc ecc) {
		this.ecc = ecc;
	}

	public Circulo getCirculo() {
		return circulo;
	}

	public void setCirculo(Circulo circulo) {
		this.circulo = circulo;
	}

	public Ficha getCasalRelator() {
		return casalRelator;
	}

	public void setCasalRelator(Ficha casalRelator) {
		this.casalRelator = casalRelator;
	}


	public Ecc getIdEcc() {
		return idEcc;
	}

	public void setIdEcc(Ecc idEcc) {
		this.idEcc = idEcc;
	}

	public List<CirculoEstudoEcc> getListaUltimoCirculoEstudo() {
		if (this.listaUltimoCirculoEstudo == null) {
			this.listaUltimoCirculoEstudo = circuloEstudoEccService.listaUltimoCirculoEcc();
		}
		return this.listaUltimoCirculoEstudo;

	}

	public void setListaUltimoCirculoEstudo(List<CirculoEstudoEcc> listaUltimoCirculoEstudo) {
		this.listaUltimoCirculoEstudo = listaUltimoCirculoEstudo;
	}

	public String getStatusEcc() {
		return statusEcc;
	}

	public void setStatusEcc(String statusEcc) {
		this.statusEcc = statusEcc;
	}


	public List<CirculoEstudoEcc> getListaCirculoEstudoEcc() {
		if (this.listaCirculoEstudoEcc == null) {
			this.listaCirculoEstudoEcc = circuloEstudoEccService.listar();
		}
		return this.listaCirculoEstudoEcc;
	}

	public void setListaCirculoEstudoEcc(List<CirculoEstudoEcc> listaCirculoEstudoEcc) {
		this.listaCirculoEstudoEcc = listaCirculoEstudoEcc;
	}

	public List<Circulo> getListaCirculo() {
		if (this.listaCirculo == null) {
			this.listaCirculo = circuloService.listar();
		}
		return this.listaCirculo;
	}

	public void setListaCirculo(List<Circulo> listaCirculo) {
		this.listaCirculo = listaCirculo;
	}

	public List<Ficha> getListaCasalRelator() {
		if (this.listaCasalRelator == null) {
			if (circuloEstudoEcc.getEcc() != null && circuloEstudoEcc.getCirculo() != null)
				this.listaCasalRelator = fichaService.listarCasaisRelatoresEcc(circuloEstudoEcc.getEcc().getId(), circuloEstudoEcc.getCirculo().getId());
			else return this.listaCasalRelator;
		}
		return this.listaCasalRelator;
	}

	public void setListaCasalCoordenador(List<Ficha> listaCasalRelator) {
		this.listaCasalRelator = listaCasalRelator;
	}

	public boolean isHabilitaBotaoEditarCirculoEstudoEcc() {
		return habilitaBotaoEditarCirculoEstudoEcc;
	}

	public void setHabilitaBotaoEditarCirculoEstudoEcc(boolean habilitaBotaoEditarCirculoEstudoEcc) {
		this.habilitaBotaoEditarCirculoEstudoEcc = habilitaBotaoEditarCirculoEstudoEcc;
	}

	public boolean isHabilitaBotaoExcluirCirculoEstudoEcc() {
		return habilitaBotaoExcluirCirculoEstudoEcc;
	}

	public void setHabilitaBotaoExcluirCirculoEstudoEcc(boolean habilitaBotaoExcluirCirculoEstudoEcc) {
		this.habilitaBotaoExcluirCirculoEstudoEcc = habilitaBotaoExcluirCirculoEstudoEcc;
	}


	private void habilitaTodosBotoesCirculoEstudoEcc() {
		habilitaBotaoEditarCirculoEstudoEcc = false;
		habilitaBotaoExcluirCirculoEstudoEcc = false;
	}

	private void desabilitaTodosBotoesCirculoEstudoEcc() {
		habilitaBotaoEditarCirculoEstudoEcc = true;
		habilitaBotaoExcluirCirculoEstudoEcc = true;
	}

	public void novoCirculoEstudo() {
		this.circuloEstudoEcc = new CirculoEstudoEcc();
		this.ecc = new Ecc();
		this.circulo = new Circulo();
		this.casalRelator = new Ficha();
	}

	public void salvarCirculoEstudo() {
		if (null == this.ecc) {
			FacesMessages.error("Favor informar o ECC");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == this.circulo) {
			FacesMessages.error("Favor informar o Círculo");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == this.casalRelator) {
			FacesMessages.error("Favor informar o Nome do Casal Relator");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			//preencheComboCasalRelator();
			return;
		} if (null == circuloEstudoEcc.getTema()) {
			FacesMessages.error("Favor informar o Tema");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == circuloEstudoEcc.getPergunta1()) {
			FacesMessages.error("Favor informar a 1ª Pergunta");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == circuloEstudoEcc.getResposta1()) {
			FacesMessages.error("Favor informar a 1ª Resposta");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == circuloEstudoEcc.getPergunta2()) {
			FacesMessages.error("Favor informar a 2ª Pergunta");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == circuloEstudoEcc.getResposta2()) {
			FacesMessages.error("Favor informar a 2ª Resposta");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		} else if (circuloEstudoEccService.circuloEstudoJaExisteEccTema(ecc.getId(), circulo.getId(), circuloEstudoEcc.getTema())) {
					FacesMessages.error("Círculo de Estudo já cadastrado com este Tema para este ECC.");
					RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
					novoCirculoEstudo();
					return;
				} else {
							circuloEstudoEcc.setEcc(ecc);
							circuloEstudoEcc.setCirculo(circulo);
							circuloEstudoEcc.setCasalRelator(casalRelator);
							circuloEstudoEccService.salvar(circuloEstudoEcc);
							this.listaUltimoCirculoEstudo = circuloEstudoEccService.listaUltimoCirculoEcc();
							novoCirculoEstudo();
							desabilitaTodosBotoesCirculoEstudoEcc();
						}
	}

	public void alterarCirculoEstudo() {
		try {
			if (null == circuloEstudoEcc.getTema()) {
				FacesMessages.error("Favor informar o Tema");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			}
			if (null == circuloEstudoEcc.getPergunta1()) {
				FacesMessages.error("Favor informar a 1ª Pergunta");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			}
			if (null == circuloEstudoEcc.getResposta1()) {
				FacesMessages.error("Favor informar a 1ª Resposta");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			}
			if (null == circuloEstudoEcc.getPergunta2()) {
				FacesMessages.error("Favor informar a 2ª Pergunta");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			}
			if (null == circuloEstudoEcc.getResposta2()) {
				FacesMessages.error("Favor informar a 2ª Resposta");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			}
			if (null != this.getCasalRelator()) {
				if (!this.getCasalRelator().getId().equals(circuloEstudoEcc.getCasalRelator().getId())) {
//					if (circuloEccService.casalJaExisteEccCoordenadorCirculo(circuloEcc.getEcc().getId(), this.getCasalCoordenador().getId())) {
//						FacesMessages.error("Casal já é Coordenador de outro círculo neste ECC.");
//						RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
//						return;
					} else {
						this.circuloEstudoEcc.setCasalRelator(this.casalRelator);
						circuloEstudoEccService.alterar(this.circuloEstudoEcc);
						this.listaUltimoCirculoEstudo = circuloEstudoEccService.listaUltimoCirculoEcc();
						this.casalRelator = new Ficha();
					}
			} else {
				circuloEstudoEccService.alterar(this.circuloEstudoEcc);
				this.listaUltimoCirculoEstudo = circuloEstudoEccService.listaUltimoCirculoEcc();
				this.casalRelator = new Ficha();
			}

		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
		}
	}

	public void excluirCirculoEstudo() {
		try {
			circuloEstudoEccService.excluir(circuloEstudoEcc);
			FacesMessages.info("Circulo de Estudo excluído");
			//this.listaCirculoEcc = circuloEccService.listar();
			this.listaUltimoCirculoEstudo = circuloEstudoEccService.listaUltimoCirculoEcc();
			desabilitaTodosBotoesCirculoEstudoEcc();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
		}
	}

	//habilita ou desabilita os botões disponívis na tabela da pagina montagemCirculo.xhtml
	public void onRowSelectCirculoEstudoEcc(SelectEvent selectEvent) {
		this.circuloEstudoEcc = (CirculoEstudoEcc) selectEvent.getObject();
		if (this.circuloEstudoEcc.getEcc().getSituacao().equals("ANDAMENTO")) {
			this.habilitaTodosBotoesCirculoEstudoEcc();
		} else {
			this.desabilitaTodosBotoesCirculoEstudoEcc();
		}
	}
//	public void onChangeCirculo(AjaxBehaviorEvent event) {
//	}


	public void preencheComboCasalRelator() {
		if (this.ecc != null && this.circulo != null) {
			this.listaCasalRelator = fichaService.listarCasaisRelatoresEcc(this.ecc.getId(), circulo.getId());
		}
	}


	public void filtraCirculoEcc() {

		if (null != idEcc) {
			listaUltimoCirculoEstudo = circuloEstudoEccService.filtraCirculoPorEccStatus(idEcc.getId(), statusEcc);
		} else {
			listaUltimoCirculoEstudo = circuloEstudoEccService.filtraCirculoPorEccStatus(0L, statusEcc);
		}
	}
}
