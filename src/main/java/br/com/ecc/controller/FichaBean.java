package br.com.ecc.controller;

import br.com.ecc.model.Equipe;
import br.com.ecc.model.Ficha;
import br.com.ecc.service.FichaService;
import br.com.ecc.util.Constantes;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.Util;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;

/**
 * Classe responsável por interligar a view à regra de negócio - Fichas (Encontristas e Encontreiros)
 * @author Cleber Seixas
 * @since 11/10/2017
 */
@Named
@ViewScoped
public class FichaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Ficha ficha = new Ficha();

	private Equipe ultimoTrabalho = new Equipe();

	private List<Ficha> listaFichas;

	private List<Ficha> listaEncontristas;

	private UploadedFile uploadedFile;

	private String tipoFoto = "HOMEM";

	private String pathFoto = Constantes.CAMINHO_FOTOS_LOCAL;

	private boolean habilitaBotaoEditarFicha = true;

	private boolean habilitaBotaoFoto = true;

	private boolean habilitaBotaoExcluirFicha = true;

	private boolean habilitaBotaoIncluiAptidoes = true;

	private boolean habilitaBotaoIncluiAtividades = true;

	private boolean habilitaBotaoDetalhesFicha = true;


	@Inject
	private FichaService fichaService;

	public void novaFicha() {
		this.ficha = new Ficha();
	}

	public Equipe getUltimoTrabalho() {
		return ultimoTrabalho;
	}

	public void setUltimoTrabalho(Equipe ultimoTrabalho) {
		this.ultimoTrabalho = ultimoTrabalho;
	}

	private void habilitaTodosBotoesFicha() {
		habilitaBotaoEditarFicha = false;
		habilitaBotaoExcluirFicha = false;
		habilitaBotaoIncluiAptidoes = false;
		habilitaBotaoIncluiAtividades = false;
		habilitaBotaoDetalhesFicha = false;
		habilitaBotaoFoto = false;
	}

	private void desabilitaTodosBotoesFicha() {
		habilitaBotaoEditarFicha = true;
		habilitaBotaoExcluirFicha = true;
		habilitaBotaoIncluiAptidoes = true;
		habilitaBotaoIncluiAtividades = true;
		habilitaBotaoDetalhesFicha = false;
		habilitaBotaoFoto = true;
	}

	public void salvarFicha() {
		try {
			fichaService.salvar(ficha);
			this.listaFichas = fichaService.listar();
			this.ficha = new Ficha();
			desabilitaTodosBotoesFicha();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
		}
	}

	public void alterarFicha() {
		try {
			fichaService.alterar(this.ficha);
			desabilitaTodosBotoesFicha();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
		}
	}


	public void excluir() {
		try {
			fichaService.excluir(ficha);
			FacesMessages.info("Círculo excluído");
			//this.lista = null;
		} catch (Exception e) {
			FacesMessages.error("Não se pode remover um círculo que já está sendo utilizado");
		}
	}

//	public void salvarFotoCompleta(FileUploadEvent event)  throws Exception {
//		try {
//			UploadedFile uploadedFile = event.getFile();
//			String nomeFoto = uploadedFile.getFileName();
//			//id_eleUsual_nomefile;
//			nomeFoto = Util.removeAcentos(nomeFoto);
//			//HOMEM
//			if (tipoFoto.equals("HOMEM")) {
//				nomeFoto = this.ficha.getId() + "_" + this.ficha.getNomeUsualEle() + "_" + nomeFoto;
//				this.ficha.setFotoEle(nomeFoto);
//			} else {
//				nomeFoto = this.ficha.getId() + "_" + this.ficha.getNomeUsualEla() + "_" + nomeFoto;
//				this.ficha.setFotoEla(nomeFoto);
//			}
//			File file = new File(Constantes.CAMINHO_FOTOS_LOCAL, nomeFoto);
//			OutputStream out = new FileOutputStream(file);
//			out.write(uploadedFile.getContents());
//			out.close();
//			fichaService.atualiza(ficha);
//			setTipoFoto(null);
//
//		} catch (IOException e) {
//		}
//	}

	public void salvarFotoSimples() {
		try {
			//UploadedFile uploadedFile = event.getFile();

			String nomeFoto = uploadedFile.getFileName();

			if (nomeFoto.trim().length() > 0) {
				//id_eleUsual_nomefile;
				nomeFoto = Util.removeAcentos(nomeFoto);

				//HOMEM
				if (tipoFoto.equals("HOMEM")) {
					nomeFoto = this.ficha.getId()+"_"+this.ficha.getNomeUsualEle()+"_"+nomeFoto;
					this.ficha.setFotoEle(nomeFoto);
				} else
				{
					nomeFoto = this.ficha.getId()+"_"+this.ficha.getNomeUsualEla()+"_"+nomeFoto;
					this.ficha.setFotoEla(nomeFoto);
				}
				File file = new File(Constantes.CAMINHO_FOTOS_LOCAL, nomeFoto);
				OutputStream out = new FileOutputStream(file);
				out.write(uploadedFile.getContents());
				out.close();
				fichaService.atualiza(ficha);
			} else {
				FacesMessages.error("Favor escolher a foto");
			}


		} catch(IOException e) {
//			FacesContext.getCurrentInstance().addMessage(
//					null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro", e.getMessage()));
		}

	}


	public List<Ficha> getListaFichas() {
		if (this.listaFichas == null) {
			this.listaFichas = fichaService.listar();
		}
		return this.listaFichas;
	}

	public void setListaFichas(List<Ficha> listaFichas) {
		this.listaFichas = listaFichas;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public List<Ficha> getListaEncontristas() {
		if (this.listaEncontristas == null) {
			this.listaEncontristas = fichaService.listarEncontreiroEncontrista(Constantes.ENCONTREIRO);
		}
		return this.listaEncontristas;
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public String getTipoFoto() {
		return tipoFoto;
	}

	public void setTipoFoto(String tipoFoto) {
		this.tipoFoto = tipoFoto;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public boolean isHabilitaBotaoEditarFicha() {
		return habilitaBotaoEditarFicha;
	}

	public void setHabilitaBotaoEditarFicha(boolean habilitaBotaoEditarFicha) {
		this.habilitaBotaoEditarFicha = habilitaBotaoEditarFicha;
	}

	public boolean isHabilitaBotaoFoto() {
		return habilitaBotaoFoto;
	}

	public void setHabilitaBotaoFoto(boolean habilitaBotaoFoto) {
		this.habilitaBotaoFoto = habilitaBotaoFoto;
	}

	public boolean isHabilitaBotaoExcluirFicha() {
		return habilitaBotaoExcluirFicha;
	}

	public void setHabilitaBotaoExcluirFicha(boolean habilitaBotaoExcluirFicha) {
		this.habilitaBotaoExcluirFicha = habilitaBotaoExcluirFicha;
	}

	public boolean isHabilitaBotaoIncluiAptidoes() {
		return habilitaBotaoIncluiAptidoes;
	}

	public void setHabilitaBotaoIncluiAptidoes(boolean habilitaBotaoIncluiAptidoes) {
		this.habilitaBotaoIncluiAptidoes = habilitaBotaoIncluiAptidoes;
	}

	public boolean isHabilitaBotaoIncluiAtividades() {
		return habilitaBotaoIncluiAtividades;
	}

	public void setHabilitaBotaoIncluiAtividades(boolean habilitaBotaoIncluiAtividades) {
		this.habilitaBotaoIncluiAtividades = habilitaBotaoIncluiAtividades;
	}

	public boolean isHabilitaBotaoDetalhesFicha() {
		return habilitaBotaoDetalhesFicha;
	}

	public void setHabilitaBotaoDetalhesFicha(boolean habilitaBotaoDetalhesFicha) {
		this.habilitaBotaoDetalhesFicha = habilitaBotaoDetalhesFicha;
	}

	//habilita ou desabilita os botões disponívis na tabela da pagina montagemFicha.xhtml
	public void onRowSelectEcc(SelectEvent selectEvent) {
		this.ficha = (Ficha) selectEvent.getObject();
		if (this.ficha.isAtivo()) {
			this.habilitaTodosBotoesFicha();
		} else {
			this.desabilitaTodosBotoesFicha();
		}
	}

}
