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
public class CirculoEccBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private CirculoEcc circuloEcc = new CirculoEcc();

	private Ecc ecc = new Ecc();

	private Circulo circulo = new Circulo();

	private Ficha casalCoordenador = new Ficha();

	private Ficha encontrista = new Ficha();

	private CirculoEccCasal circuloEccCasal = new  CirculoEccCasal();

	private List<CirculoEcc> listaCirculoEcc;

	private List<Circulo> listaCirculo;

	private List<Ficha> listaCasalCoordenador;

	private List<Ficha> listaEncontreiros;

	private Ecc idEcc;

	private List<CirculoEcc> listaUltimoCirculo;

	private String statusEcc = "ENCERRADO";

	private boolean habilitaBotaoEditarCirculoEcc = true;

	private boolean habilitaBotaoExcluirCirculoEcc = true;

	private boolean habilitaBotaoIncluiCasaisEncontristas = true;

	@Inject
	private CirculoEccService circuloEccService;

	@Inject
	private CirculoService circuloService;

	@Inject
	private CirculoEccCasalService circuloEccCasalService;

	@Inject
	private EccService eccService;

	@Inject
	private FichaService fichaService;

	@Inject
	private EquipeEccService equipeEccService;

	@Inject
	private DirigenteEccService dirigenteEccService;

	@Inject
	private EncontristaEccCasalService encontristaEccCasalService;

	public CirculoEcc getCirculoEcc() {
		return circuloEcc;
	}

	public void setCirculoEcc(CirculoEcc circuloEcc) {
		this.circuloEcc = circuloEcc;
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

	public Ficha getCasalCoordenador() {
		return casalCoordenador;
	}

	public void setCasalCoordenador(Ficha casalCoordenador) {
		this.casalCoordenador = casalCoordenador;
	}

	public List<Ficha> getListaEncontreiros() {
		if (this.listaEncontreiros == null) {
			if (circuloEcc.getEcc() != null) {
				this.listaEncontreiros = fichaService.listarEncontristaEcc(circuloEcc.getEcc().getId());
			}
		}
		return this.listaEncontreiros;
	}

	public Ecc getIdEcc() {
		return idEcc;
	}

	public void setIdEcc(Ecc idEcc) {
		this.idEcc = idEcc;
	}

	public List<CirculoEcc> getListaUltimoCirculo() {
		if (this.listaUltimoCirculo == null) {
			this.listaUltimoCirculo = circuloEccService.listaUltimoCirculoEcc();
		}
		return this.listaUltimoCirculo;

	}

	public void setListaUltimoCirculo(List<CirculoEcc> listaUltimoCirculo) {
		this.listaUltimoCirculo = listaUltimoCirculo;
	}

	public String getStatusEcc() {
		return statusEcc;
	}

	public void setStatusEcc(String statusEcc) {
		this.statusEcc = statusEcc;
	}

	public Ficha getEncontrista() {
		return encontrista;
	}

	public void setEncontrista(Ficha encontrista) {
		this.encontrista = encontrista;
	}

	public CirculoEccCasal getCirculoEccCasal() {
		return circuloEccCasal;
	}

	public void setCirculoEccCasal(CirculoEccCasal circuloEccCasal) {
		this.circuloEccCasal = circuloEccCasal;
	}

	public List<CirculoEcc> getListaCirculoEcc() {
		if (this.listaCirculoEcc == null) {
			this.listaCirculoEcc = circuloEccService.listar();
		}
		return this.listaCirculoEcc;
	}

	public void setListaCirculoEcc(List<CirculoEcc> listaCirculoEcc) {
		this.listaCirculoEcc = listaCirculoEcc;
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

	public List<Ficha> getListaCasalCoordenador() {
		if (this.listaCasalCoordenador == null) {
			this.listaCasalCoordenador = fichaService.listarCasaisCoordenadores("b");
		}
		return this.listaCasalCoordenador;
	}

	public void setListaCasalCoordenador(List<Ficha> listaCasalCoordenador) {
		this.listaCasalCoordenador = listaCasalCoordenador;
	}

	public boolean isHabilitaBotaoEditarCirculoEcc() {
		return habilitaBotaoEditarCirculoEcc;
	}

	public void setHabilitaBotaoEditarCirculoEcc(boolean habilitaBotaoEditarCirculoEcc) {
		this.habilitaBotaoEditarCirculoEcc = habilitaBotaoEditarCirculoEcc;
	}

	public boolean isHabilitaBotaoExcluirCirculoEcc() {
		return habilitaBotaoExcluirCirculoEcc;
	}

	public void setHabilitaBotaoExcluirCirculoEcc(boolean habilitaBotaoExcluirCirculoEcc) {
		this.habilitaBotaoExcluirCirculoEcc = habilitaBotaoExcluirCirculoEcc;
	}

	public boolean isHabilitaBotaoIncluiCasaisEncontristas() {
		return habilitaBotaoIncluiCasaisEncontristas;
	}

	public void setHabilitaBotaoIncluiCasaisEncontristas(boolean habilitaBotaoIncluiCasaisEncontristas) {
		this.habilitaBotaoIncluiCasaisEncontristas = habilitaBotaoIncluiCasaisEncontristas;
	}

	private void habilitaTodosBotoesCirculoEcc() {
		habilitaBotaoEditarCirculoEcc = false;
		habilitaBotaoExcluirCirculoEcc = false;
		habilitaBotaoIncluiCasaisEncontristas = false;
	}

	private void desabilitaTodosBotoesCirculoEcc() {
		habilitaBotaoEditarCirculoEcc = true;
		habilitaBotaoExcluirCirculoEcc = true;
		habilitaBotaoIncluiCasaisEncontristas = false;
	}

	public void novoCirculo() {
		this.circuloEcc = new CirculoEcc();
		this.ecc = new Ecc();
		this.circulo = new Circulo();
		this.casalCoordenador = new Ficha();
	}

	private void novoEncontrista() {
		this.encontrista = new Ficha();
		this.circuloEccCasal = new CirculoEccCasal();
	}

	public void salvaCasal() {
		int maximo = circuloEcc.getCirculo().getTotalCasais().intValue();
		int total = circuloEcc.getCirculosEccCasais().size();
		int falta = maximo - total;
		//Não pode mais incluir
		if (falta == 0) {
			FacesMessages.error("Círculo já possui a quantidade máxima de casais.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			this.novoEncontrista();
			return;

		} else
		if (circuloEccService.casalJaExisteEccOutroCirculo(circuloEcc.getEcc().getId(), encontrista.getId())) {
			FacesMessages.error("Casal já faz parte de outro círculo neste ECC.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			this.novoEncontrista();
			return;
		} else {
			List<CirculoEccCasal> listAux = new ArrayList<CirculoEccCasal>();
			listAux.addAll(circuloEcc.getCirculosEccCasais());

			circuloEcc.getCirculosEccCasais().clear();
			circuloEccCasal.setCirculo(circuloEcc.getCirculo());
			circuloEccCasal.setEcc(circuloEcc.getEcc());
			circuloEccCasal.setFicha(encontrista);
			circuloEccCasal.setCirculoEcc(circuloEcc);
			circuloEccCasalService.salvar(circuloEccCasal);

			listAux.add(circuloEccCasal);
			this.circuloEcc.setCirculosEccCasais(listAux);
			circuloEccService.atualiza(this.circuloEcc);
			this.novoEncontrista();
			removeCasaisLimbo();
		}
	}
	public void removeCasal() {
		this.circuloEcc.getCirculosEccCasais().remove(circuloEccCasal);
		circuloEccService.atualiza(circuloEcc);
	}


	public void removeCasaisLimbo() {
		encontristaEccCasalService.removeCasaisEncontristasLimbo();
		circuloEccCasalService.removeCasaisEncontristasLimbo();
	}

	public void salvarCirculo() {
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
		if (null == this.casalCoordenador) {
			FacesMessages.error("Favor informar o Nome do Casal Coordenador");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (circuloEccService.casalJaExisteEccCoordenadorCirculo(ecc.getId(), casalCoordenador.getId())) {
			FacesMessages.error("Casal já faz parte de outro círculo neste ECC.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			novoCirculo();
			return;
		} else if (equipeEccService.casalJaExisteEccCoordenadorOuEquipe(ecc.getId(), casalCoordenador.getId())) {
					FacesMessages.error("Casal já faz parte de uma Equipe(Coordenador ou Membro) neste ECC.");
					RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
					novoCirculo();
					return;
				} else if (circuloEccService.circuloJaExisteEcc(ecc.getId(), circulo.getId())) {
							FacesMessages.error("Círculo já cadastrado para este ECC.");
							RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
							novoCirculo();
							return;
						} else if (dirigenteEccService.casalJaExisteEccDirigente(ecc.getId(), casalCoordenador.getId(), 0L)) {
									FacesMessages.error("Casal já faz parte da Equipe de Dirigentes neste ECC.");
									RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
									novoCirculo();
									return;
								} else {
										circuloEcc.setEcc(ecc);
										circuloEcc.setCirculo(circulo);
										circuloEcc.setCasalCoordenador(casalCoordenador);

										circuloEccService.salvar(circuloEcc);
										//this.listaCirculoEcc = circuloEccService.listar();
										this.listaUltimoCirculo = circuloEccService.listaUltimoCirculoEcc();
										novoCirculo();
										desabilitaTodosBotoesCirculoEcc();
										}
	}

	public void alterarCirculo() {
		try {
			if (null != this.getCasalCoordenador()) {
//				FacesMessages.error("Favor informar o Nome do Casal Coordenador");
//				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
//				return;
				if (!this.getCasalCoordenador().getId().equals(circuloEcc.getCasalCoordenador().getId())) {
					if (circuloEccService.casalJaExisteEccCoordenadorCirculo(circuloEcc.getEcc().getId(), this.getCasalCoordenador().getId())) {
						FacesMessages.error("Casal já é Coordenador de outro círculo neste ECC.");
						RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
						return;
					} else if (dirigenteEccService.casalJaExisteEccDirigente(circuloEcc.getEcc().getId(), this.casalCoordenador.getId(), 0L)) {
						FacesMessages.error("Casal já faz parte da Equipe de Dirigentes neste ECC.");
						RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
						return;
					} else {
						this.circuloEcc.setCasalCoordenador(this.casalCoordenador);
						circuloEccService.alterar(this.circuloEcc);
						this.listaUltimoCirculo = circuloEccService.listaUltimoCirculoEcc();
						this.casalCoordenador = new Ficha();
					}
				} else {
					circuloEccService.alterar(this.circuloEcc);
					this.listaUltimoCirculo = circuloEccService.listaUltimoCirculoEcc();
					this.casalCoordenador = new Ficha();
				}
			} else {
				circuloEccService.alterar(this.circuloEcc);
				this.listaUltimoCirculo = circuloEccService.listaUltimoCirculoEcc();
				this.casalCoordenador = new Ficha();
			}

		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
		}
	}

	public void excluirCirculo() {
		try {
			circuloEccService.excluir(circuloEcc);
			FacesMessages.info("Circulo excluído");
			//this.listaCirculoEcc = circuloEccService.listar();
			this.listaUltimoCirculo = circuloEccService.listaUltimoCirculoEcc();
			desabilitaTodosBotoesCirculoEcc();
			removeCasaisLimbo();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
		}
	}

	//habilita ou desabilita os botões disponívis na tabela da pagina montagemCirculo.xhtml
	public void onRowSelectCirculoEcc(SelectEvent selectEvent) {
		this.circuloEcc = (CirculoEcc) selectEvent.getObject();
		if (this.circuloEcc.getEcc().getSituacao().equals("ANDAMENTO")) {
			this.habilitaTodosBotoesCirculoEcc();
		} else {
			this.desabilitaTodosBotoesCirculoEcc();
		}
	}

	public void onChangeCasalCoordenador(AjaxBehaviorEvent event) {
	}

	public void filtraCirculoEcc() {

		if (null != idEcc) {
			listaUltimoCirculo = circuloEccService.filtraCirculoPorEccStatus(idEcc.getId(), statusEcc);
		} else {
			listaUltimoCirculo = circuloEccService.filtraCirculoPorEccStatus(0L, statusEcc);
		}
	}
}
