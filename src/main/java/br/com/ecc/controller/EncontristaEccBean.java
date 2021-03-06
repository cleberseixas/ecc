package br.com.ecc.controller;

import br.com.ecc.model.*;
import br.com.ecc.service.*;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.Util;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - EnconstristaEcc
 * @author Cleber Seixas
 * @since 06/11/2017
 */
@Named
@ViewScoped
public class EncontristaEccBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private EncontristaEcc encontristaEcc = new EncontristaEcc();

	private EncontristaEccCasal encontristaEccCasal = new EncontristaEccCasal();

	private Ecc ecc = new Ecc();

	private Ficha enconstrista = new Ficha();

	private List<EncontristaEcc> listaEncontristaEcc;

	private boolean habilitaBotaoExcluirEncontristaEcc = true;

	private boolean habilitaBotaoIncluiCasaisEncontristas = true;

	private boolean habilitaBotaoImprimirCasaisEncontristas = true;

	@Inject
	private EncontristaEccService encontristaEccService;

	@Inject
	private EncontristaEccCasalService encontristaEccCasalService;

	@Inject
	private EccService eccService;

	@Inject
	private FichaService fichaService;

	@Inject
	private CirculoEccCasalService circuloEccCasalService;

	private Ecc idEcc;

	private List<EncontristaEcc> listaUltimoEncontristaEcc;

	private String statusEcc = "ENCERRADO";

	private String urlRelatorio;

	public EncontristaEcc getEncontristaEcc() {
		return encontristaEcc;
	}

	public void setEncontristaEcc(EncontristaEcc encontristaEcc) {
		this.encontristaEcc = encontristaEcc;
	}


	public EncontristaEccCasal getEncontristaEccCasal() {
		return encontristaEccCasal;
	}

	public void setEncontristaEccCasal(EncontristaEccCasal encontristaEccCasal) {
		this.encontristaEccCasal = encontristaEccCasal;
	}

	public Ecc getEcc() {
		return ecc;
	}

	public void setEcc(Ecc ecc) {
		this.ecc = ecc;
	}

	public Ficha getEncontrista() {
		return enconstrista;
	}

	public void setEncontrista(Ficha enconstrista) {
		this.enconstrista = enconstrista;
	}

	public List<EncontristaEcc> getListaEncontristaEcc() {
		if (this.listaEncontristaEcc == null)
			this.listaEncontristaEcc = encontristaEccService.listar();
		return this.listaEncontristaEcc;
	}

	public void setListaEncontristaEcc(List<EncontristaEcc> listaEncontristaEcc) {
		this.listaEncontristaEcc = listaEncontristaEcc;
	}

	public boolean isHabilitaBotaoExcluirEncontristaEcc() {
		return habilitaBotaoExcluirEncontristaEcc;
	}

	public void setHabilitaBotaoExcluirEncontristaEcc(boolean habilitaBotaoExcluirEncontristaEcc) {
		this.habilitaBotaoExcluirEncontristaEcc = habilitaBotaoExcluirEncontristaEcc;
	}

	public boolean isHabilitaBotaoIncluiCasaisEncontristas() {
		return habilitaBotaoIncluiCasaisEncontristas;
	}

	public void setHabilitaBotaoIncluiCasaisEncontristas(boolean habilitaBotaoIncluiCasaisEncontristas) {
		this.habilitaBotaoIncluiCasaisEncontristas = habilitaBotaoIncluiCasaisEncontristas;
	}

	public boolean isHabilitaBotaoImprimirCasaisEncontristas() {
		return habilitaBotaoImprimirCasaisEncontristas;
	}

	public void setHabilitaBotaoImprimirCasaisEncontristas(boolean habilitaBotaoImprimirCasaisEncontristas) {
		this.habilitaBotaoImprimirCasaisEncontristas = habilitaBotaoImprimirCasaisEncontristas;
	}

	public Ecc getIdEcc() {
		return idEcc;
	}

	public void setIdEcc(Ecc idEcc) {
		this.idEcc = idEcc;
	}

	public List<EncontristaEcc> getListaUltimoEncontristaEcc() {
		if (this.listaUltimoEncontristaEcc == null) {
			this.listaUltimoEncontristaEcc = encontristaEccService.listarUltimoEncontristaEcc();
		}
		return this.listaUltimoEncontristaEcc;
	}

	public void setListaUltimoEncontristaEcc(List<EncontristaEcc> listaUltimoEncontristaEcc) {
		this.listaUltimoEncontristaEcc = listaUltimoEncontristaEcc;
	}

	public String getStatusEcc() {
		return statusEcc;
	}

	public void setStatusEcc(String statusEcc) {
		this.statusEcc = statusEcc;
	}

	public String getUrlRelatorio() {
		return urlRelatorio;
	}

	public void setUrlRelatorio(String urlRelatorio) {
		this.urlRelatorio = urlRelatorio;
	}

	private void habilitaTodosBotoesEnconstristaEcc() {
		habilitaBotaoImprimirCasaisEncontristas = false;
		if (this.encontristaEcc.getEncontristasEccCasais().size() > 0) {
			habilitaBotaoExcluirEncontristaEcc = true;
		} else {
			habilitaBotaoExcluirEncontristaEcc = false;
		}
		habilitaBotaoIncluiCasaisEncontristas = false;
	}

	private void desabilitaTodosBotoesEnconstristaEcc() {
		habilitaBotaoExcluirEncontristaEcc = true;
		habilitaBotaoIncluiCasaisEncontristas = true;
		habilitaBotaoImprimirCasaisEncontristas = false;
	}

	public void novoEncontrista() {
		this.encontristaEcc = new EncontristaEcc();
		this.ecc = new Ecc();
	}

	private void novoCasal() {
		this.enconstrista = new Ficha();
		this.encontristaEccCasal = new EncontristaEccCasal();
	}

	public void salvarEncontrista() {
		if (null == this.ecc) {
			FacesMessages.error("Favor informar o ECC");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (encontristaEccService.jaExisteEcc(ecc.getId())) {
			FacesMessages.error("Já existe um ECC cadastrado.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			novoEncontrista();
			return;
		} else {
			encontristaEcc.setEcc(ecc);

			encontristaEccService.salvar(encontristaEcc);
			this.listaUltimoEncontristaEcc = encontristaEccService.listarUltimoEncontristaEcc();
			//this.listaEncontristaEcc = encontristaEccService.listar();
			novoEncontrista();
			desabilitaTodosBotoesEnconstristaEcc();
		}
	}

	public void excluirEncontrista() {
		try {
			encontristaEccService.excluir(encontristaEcc);
			FacesMessages.info("Encontristas excluídos");
			//this.listaEncontristaEcc = encontristaEccService.listar();
			this.listaUltimoEncontristaEcc = encontristaEccService.listarUltimoEncontristaEcc();
			desabilitaTodosBotoesEnconstristaEcc();
			removeCasaisLimbo();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
		}
	}

	//habilita ou desabilita os botões disponívis na tabela da pagina montagemEquipe.xhtml
	public void onRowSelectEncontristaEcc(SelectEvent selectEvent) {
		this.encontristaEcc = (EncontristaEcc) selectEvent.getObject();
		if (this.encontristaEcc.getEcc().getSituacao().equals("ANDAMENTO")) {
			this.habilitaTodosBotoesEnconstristaEcc();
		} else {
			this.desabilitaTodosBotoesEnconstristaEcc();
		}
	}

	public void salvaCasal() {
		int maximo = encontristaEcc.getEcc().getTotal().intValue();
		int total = encontristaEcc.getEncontristasEccCasais().size();
		int falta = maximo - total;
		//Não pode mais incluir
		if (falta == 0) {
			FacesMessages.error("ECC já possui a quantidade máxima de casais encontristas.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			this.novoCasal();
			return;

		} else
		if (encontristaEccService.casalEncontristaJaExisteNoEcc(encontristaEcc.getEcc().getId(), enconstrista.getId())) {
			FacesMessages.error("Casal já faz parte deste ECC.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			this.novoCasal();
			return;
		} else {
			List<EncontristaEccCasal> listAux = new ArrayList<EncontristaEccCasal>();
			listAux.addAll(encontristaEcc.getEncontristasEccCasais());

			encontristaEcc.getEncontristasEccCasais().clear();


			encontristaEccCasal.setEcc(encontristaEcc.getEcc());
			encontristaEccCasal.setEncontristaEcc(encontristaEcc);
			encontristaEccCasal.setFicha(enconstrista);
			encontristaEccCasalService.salvar(encontristaEccCasal);

			listAux.add(encontristaEccCasal);
			this.encontristaEcc.setEncontristasEccCasais(listAux);
			encontristaEccService.atualiza(this.encontristaEcc);
			this.novoCasal();
			removeCasaisLimbo();
		}
	}
	public void removeCasal() {
		this.encontristaEcc.getEncontristasEccCasais().remove(encontristaEccCasal);
		Long idEncontristaCirculo = circuloEccCasalService.casalEncontristaExistenteCircunoNoEcc(encontristaEcc.getEcc().getId(), encontristaEccCasal.getFicha().getId());
		if (idEncontristaCirculo > 0)
			circuloEccCasalService.removeCasalCirculoQuandoRemoveEncontristaLista(idEncontristaCirculo);
		encontristaEccService.atualiza(encontristaEcc);
	}


	public void removeCasaisLimbo() {
		encontristaEccCasalService.removeCasaisEncontristasLimbo();
	}

	public void onChangeCasalEncontreiro(AjaxBehaviorEvent event) {
	}

	public void filtraEncontristaEcc() {

		if (null != idEcc) {
			listaUltimoEncontristaEcc = encontristaEccService.filtraEncontristaPorEccStatus(idEcc.getId(), statusEcc);
		} else {
			listaUltimoEncontristaEcc = encontristaEccService.filtraEncontristaPorEccStatus(0L, statusEcc);
		}
	}

	public void imprimirEncontristas() throws IOException {
		try {
			System.out.println("ECC " + encontristaEcc.getEcc().getId());

			urlRelatorio = Util.retornaURLRelatorio();

			urlRelatorio += "/rptEncontristas.rptdesign&ecc=" + encontristaEcc.getEcc().getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}
}
