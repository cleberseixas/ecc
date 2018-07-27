package br.com.ecc.controller;

import br.com.ecc.model.CirculoEstudoEcc;
import br.com.ecc.model.DirigenteNacionalEcc;
import br.com.ecc.model.Ecc;
import br.com.ecc.service.DirigenteNacionalEccService;
import br.com.ecc.service.EccService;
import br.com.ecc.service.FichaService;
import br.com.ecc.util.FacesMessages;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - DirigenteNacionalEcc
 * @author Cleber Seixas
 * @since 11/11/2017
 */
@Named
@ViewScoped
public class DirigenteNacionalEccBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private DirigenteNacionalEcc dirigenteNacionalEcc = new DirigenteNacionalEcc();

	private Ecc ecc = new Ecc();

	private List<DirigenteNacionalEcc> listaDirigenteNacionalEcc;

	private Ecc idEcc;

	private List<DirigenteNacionalEcc> listaUltimoDirigenteNacional;

	private String statusEcc = "ENCERRADO";

	private boolean habilitaBotaoEditarDirigenteNacionalEcc = true;

	private boolean habilitaBotaoExcluirDirigenteNacionalEcc = true;


	@Inject
	private DirigenteNacionalEccService dirigenteNacionalEccService;


	@Inject
	private EccService eccService;


	@Inject
	private FichaService fichaService;


	public DirigenteNacionalEcc getDirigenteNacionalEcc() {
		return dirigenteNacionalEcc;
	}

	public void setDirigenteNacionalEcc(DirigenteNacionalEcc dirigenteNacionalEcc) {
		this.dirigenteNacionalEcc = dirigenteNacionalEcc;
	}

	public Ecc getEcc() {
		return ecc;
	}

	public void setEcc(Ecc ecc) {
		this.ecc = ecc;
	}

	public Ecc getIdEcc() {
		return idEcc;
	}

	public void setIdEcc(Ecc idEcc) {
		this.idEcc = idEcc;
	}

	public List<DirigenteNacionalEcc> getListaUltimoDirigenteNacional() {
		if (this.listaUltimoDirigenteNacional == null) {
			this.listaDirigenteNacionalEcc = dirigenteNacionalEccService.listaUltimoDirigenteNacionalEcc();
		}
		return this.listaUltimoDirigenteNacional;
	}

	public void setListaUltimoDirigenteNacional(List<DirigenteNacionalEcc> listaUltimoDirigenteNacional) {
		this.listaUltimoDirigenteNacional = listaUltimoDirigenteNacional;
	}

	public String getStatusEcc() {
		return statusEcc;
	}

	public void setStatusEcc(String statusEcc) {
		this.statusEcc = statusEcc;
	}


	public List<DirigenteNacionalEcc> getListaDirigenteNacionalEcc() {
		if (this.listaDirigenteNacionalEcc == null) {
			this.listaDirigenteNacionalEcc = dirigenteNacionalEccService.listar();
		}
		return this.listaDirigenteNacionalEcc;
	}

	public void setListaDirigenteNacionalEcc(List<DirigenteNacionalEcc> listaDirigenteNacionalEcc) {
		this.listaDirigenteNacionalEcc = listaDirigenteNacionalEcc;
	}

	public boolean isHabilitaBotaoEditarDirigenteNacionalEcc() {
		return habilitaBotaoEditarDirigenteNacionalEcc;
	}

	public void setHabilitaBotaoEditarDirigenteNacionalEcc(boolean habilitaBotaoEditarDirigenteNacionalEcc) {
		this.habilitaBotaoEditarDirigenteNacionalEcc = habilitaBotaoEditarDirigenteNacionalEcc;
	}

	public boolean isHabilitaBotaoExcluirDirigenteNacionalEcc() {
		return habilitaBotaoExcluirDirigenteNacionalEcc;
	}

	public void setHabilitaBotaoExcluirDirigenteNacionalEcc(boolean habilitaBotaoExcluirDirigenteNacionalEcc) {
		this.habilitaBotaoExcluirDirigenteNacionalEcc = habilitaBotaoExcluirDirigenteNacionalEcc;
	}


	private void habilitaTodosBotoesDirigenteNacionalEcc() {
		habilitaBotaoEditarDirigenteNacionalEcc = false;
		habilitaBotaoExcluirDirigenteNacionalEcc = false;
	}

	private void desabilitaTodosBotoesDirigenteNacionalEcc() {
		habilitaBotaoEditarDirigenteNacionalEcc = true;
		habilitaBotaoExcluirDirigenteNacionalEcc = true;
	}

	public void novoDirigenteNacional() {
		this.dirigenteNacionalEcc = new DirigenteNacionalEcc();
		this.ecc = new Ecc();
	}

	public void salvarDirigenteNacional() {
		if (null == this.ecc) {
			FacesMessages.error("Favor informar o ECC");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == dirigenteNacionalEcc.getTipo()) {
			FacesMessages.error("Favor informar o Tipo");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
		if (null == dirigenteNacionalEcc.getDescricao()) {
			FacesMessages.error("Favor informar os Dados");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			return;
		}
//		 else if (circuloEstudoEccService.circuloEstudoJaExisteEccTema(ecc.getId(), circulo.getId(), circuloEstudoEcc.getTema())) {
//					FacesMessages.error("Círculo de Estudo já cadastrado com este Tema para este ECC.");
//					RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
//					novoDirigenteNacional();
//					return;
//				} else
					 {
							dirigenteNacionalEcc.setEcc(ecc);
							dirigenteNacionalEccService.salvar(dirigenteNacionalEcc);
							this.listaUltimoDirigenteNacional = dirigenteNacionalEccService.listaUltimoDirigenteNacionalEcc();
							novoDirigenteNacional();
							desabilitaTodosBotoesDirigenteNacionalEcc();
						}
	}

	public void alterarDirigenteNacional() {
		try {
			if (null == dirigenteNacionalEcc.getTipo()) {
				FacesMessages.error("Favor informar o Tipo");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			}
			if (null == dirigenteNacionalEcc.getDescricao()) {
				FacesMessages.error("Favor informar os Dados");
				RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
				return;
			} else
				{
				dirigenteNacionalEccService.alterar(this.dirigenteNacionalEcc);
				this.listaUltimoDirigenteNacional = dirigenteNacionalEccService.listaUltimoDirigenteNacionalEcc();
				}

		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
		}
	}

	public void excluirDirigenteNacional() {
		try {
			dirigenteNacionalEccService.excluir(dirigenteNacionalEcc);
			FacesMessages.info("Dirigente Nacional excluído");
			this.listaUltimoDirigenteNacional = dirigenteNacionalEccService.listaUltimoDirigenteNacionalEcc();
			desabilitaTodosBotoesDirigenteNacionalEcc();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
		}
	}

	//habilita ou desabilita os botões disponívis na tabela da pagina montagemCirculo.xhtml
	public void onRowSelectDirenteNacionalEcc(SelectEvent selectEvent) {
		this.dirigenteNacionalEcc = (DirigenteNacionalEcc) selectEvent.getObject();
		if (this.dirigenteNacionalEcc.getEcc().getSituacao().equals("ANDAMENTO")) {
			this.habilitaTodosBotoesDirigenteNacionalEcc();
		} else {
			this.desabilitaTodosBotoesDirigenteNacionalEcc();
		}
	}
	public void filtraDirigenteNacionalEcc() {

		if (null != idEcc) {
			listaUltimoDirigenteNacional = dirigenteNacionalEccService.filtraDirigenteNacionalPorEccStatus(idEcc.getId(), statusEcc);
		} else {
			listaUltimoDirigenteNacional = dirigenteNacionalEccService.filtraDirigenteNacionalPorEccStatus(0L, statusEcc);
		}
	}
}
