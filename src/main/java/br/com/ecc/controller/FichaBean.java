package br.com.ecc.controller;

import br.com.ecc.model.*;
import br.com.ecc.service.AptidaoService;
import br.com.ecc.service.AtividadeService;
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
import java.util.ArrayList;
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

	private Atividade atividade = new Atividade();

	private Ecc ecc = new Ecc();

	private Equipe equipe = new Equipe();

	private Aptidao aptidao = new Aptidao();

	private List<Ficha> listaFichas;

	private List<Ficha> listaEncontristas;

	private List<Ficha> listaEncontreiros;

	private UploadedFile uploadedFile;

	private String tipoFoto = "HOMEM";

	private boolean ehCoordenador = false;

	private String pathFoto;

	private boolean habilitaBotaoEditarFicha = true;

	private boolean habilitaBotaoFoto = true;

	private boolean habilitaBotaoExcluirFicha = true;

	private boolean habilitaBotaoIncluiAptidoes = true;

	private boolean habilitaBotaoIncluiAtividades = true;

	private boolean habilitaBotaoDetalhesFicha = true;

	private String situacaoFicha = "TODAS";

	private String nomeUsualCasalBusca = "";

	@Inject
	private FichaService fichaService;

	@Inject
	private AtividadeService atividadeService;

	@Inject
	private AptidaoService aptidaoService;

	public void novaFicha() {
		this.ficha = new Ficha();
	}

	private void novaAtividade() {
		this.ecc = new Ecc();
		this.equipe = new Equipe();
		this.atividade = new Atividade();
		this.ehCoordenador = false;
	}

	private void novaAptidao() {
		this.equipe = new Equipe();
		this.aptidao = new Aptidao();
	}

	public Equipe getUltimoTrabalho() {
		return ultimoTrabalho;
	}

	public void setUltimoTrabalho(Equipe ultimoTrabalho) {
		this.ultimoTrabalho = ultimoTrabalho;
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

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Aptidao getAptidao() {
		return aptidao;
	}

	public void setAptidao(Aptidao aptidao) {
		this.aptidao = aptidao;
	}

	public List<Ficha> getListaEncontristas() {
		if (this.listaEncontristas == null) {
			this.listaEncontristas = fichaService.listarEncontreiroEncontrista(Constantes.ENCONTREIRO);
		}
		return this.listaEncontristas;
	}

	public List<Ficha> getListaEncontreiros() {
		if (this.listaEncontreiros == null) {
			this.listaEncontreiros = fichaService.listarEncontreiroEncontrista(Constantes.ENCONTRISTA);
		}
		return this.listaEncontreiros;
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

	public boolean isEhCoordenador() {
		return ehCoordenador;
	}

	public void setEhCoordenador(boolean ehCoordenador) {
		this.ehCoordenador = ehCoordenador;
	}

	public String getPathFoto() {
		return pathFoto;
	}

	public void setPathFoto(String pathFoto) {
		this.pathFoto = pathFoto;
	}

	public List<Ficha> getListaFichas() {
		if (this.listaFichas == null) {
			this.listaFichas = fichaService.listar();
		}
		return this.listaFichas;
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

	public String getSituacaoFicha() {
		return situacaoFicha;
	}

	public void setSituacaoFicha(String situacaoFicha) {
		this.situacaoFicha = situacaoFicha;
	}

	public String getNomeUsualCasalBusca() {
		return nomeUsualCasalBusca;
	}

	public void setNomeUsualCasalBusca(String nomeUsualCasalBusca) {
		this.nomeUsualCasalBusca = nomeUsualCasalBusca;
	}

	public void verificaSO() {
		pathFoto = Util.retornaPathFotoSistemaOperacional();
	}

	private void habilitaTodosBotoesFicha() {
		if (ficha.getSituacao().equals("ENCONTREIRO")) {
			habilitaBotaoIncluiAptidoes = true;
			habilitaBotaoIncluiAtividades = false;
			if (ficha.getAtividades().size() > 0) {
				habilitaBotaoExcluirFicha = true;
			} else {
				habilitaBotaoExcluirFicha = false;
			}
		}  else {
				habilitaBotaoIncluiAptidoes = false;
				habilitaBotaoIncluiAtividades = true;
				if (ficha.getAptidaos().size() > 0) {
					habilitaBotaoExcluirFicha = true;
				} else {
						habilitaBotaoExcluirFicha = false;
				}
			}
		habilitaBotaoEditarFicha = false;
		habilitaBotaoDetalhesFicha = false;
		habilitaBotaoFoto = false;
	}

	private void desabilitaTodosBotoesFicha() {
		habilitaBotaoEditarFicha = false;
		habilitaBotaoExcluirFicha = true;
		habilitaBotaoIncluiAptidoes = true;
		habilitaBotaoIncluiAtividades = true;
		habilitaBotaoDetalhesFicha = false;
		habilitaBotaoFoto = true;
	}

	public void salvarFicha() {
		try {
			if (ficha.getSituacao().equals("AUSÊNCIA JUSTIFICADA")) {
				if (ficha.getMotivo().isEmpty()) {
				FacesMessages.error("Favor informar o motivo da ausência");
				}
			}
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

	public void excluirFicha() {
		try {
			removeAtividadeLimbo();
			removeAptidaoLimbo();
			String fotoEle = ficha.getFotoEle();
			String fotoEla = ficha.getFotoEla();
			fichaService.excluir(ficha);
			FacesMessages.info("Ficha excluída");
			excluirFoto(fotoEle, fotoEla);

			this.listaFichas = fichaService.listar();
			desabilitaTodosBotoesFicha();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
		}
	}

	private void excluirFoto(String ele, String ela) {
		if (null != ele) {
			File fotoExcluirEle = new File(pathFoto, ele);
			fotoExcluirEle.delete();
		}
		if (null != ela) {
			File fotoExcluirEla = new File(pathFoto, ela);
			fotoExcluirEla.delete();
		}

	}

	private String nomeFotoHM(String nomeFoto) {
		File fotoExcluir = null;
		Boolean temFoto = false;
		//HOMEM
		if (tipoFoto.equals("HOMEM")) {
			if (null != ficha.getFotoEle()) {
				temFoto = true;
				fotoExcluir = new File(pathFoto, ficha.getFotoEle());
			}
			nomeFoto = this.ficha.getId() + "_" + this.ficha.getNomeUsualEle() + "_" + nomeFoto;
			nomeFoto = Util.removeAcentos(nomeFoto);
			this.ficha.setFotoEle(nomeFoto);
		} else {
				if (null != ficha.getFotoEla()) {
					temFoto = true;
					fotoExcluir = new File(pathFoto, ficha.getFotoEla());
				}
			nomeFoto = this.ficha.getId() + "_" + this.ficha.getNomeUsualEla() + "_" + nomeFoto;
			nomeFoto = Util.removeAcentos(nomeFoto);
			this.ficha.setFotoEla(nomeFoto);
		}
		if (temFoto) {
			fotoExcluir.delete();
		}

		return nomeFoto;
	}

	public void salvarFotoCompleta(FileUploadEvent event) throws Exception {
		try {
			UploadedFile uploadedFile = event.getFile();
			String nomeFoto = uploadedFile.getFileName();
			//id_eleUsual_nomefile;
			nomeFoto = Util.removeAcentos(nomeFoto);

			nomeFotoHM(nomeFoto);
			File file = new File(pathFoto, nomeFoto);
			OutputStream out = new FileOutputStream(file);
			out.write(uploadedFile.getContents());
			out.close();
			fichaService.atualiza(ficha);
		} catch (IOException e) {
		}
	}

	public void salvarFotoSimples() {
		try {
			//UploadedFile uploadedFile = event.getFile();

			String nomeFoto = uploadedFile.getFileName();

			if (nomeFoto.trim().length() > 0) {
				//id_eleUsual_nomefile;
				nomeFoto = Util.removeAcentos(nomeFoto);


				File file = new File(pathFoto, nomeFotoHM(nomeFoto));
				OutputStream out = new FileOutputStream(file);
				out.write(uploadedFile.getContents());
				out.close();
				fichaService.atualiza(ficha);
			} else {
				FacesMessages.error("Favor escolher a foto");
			}
		} catch (IOException e) {
		}

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

	public void salvaAtividade() {
		boolean existeEcc = false;
		for(Atividade ativ : ficha.getAtividades()) {
			if (ativ.getEcc().getNumero().equals(ecc.getNumero())) {
				existeEcc = true;
			}
		}

		if (!existeEcc) {
			List<Atividade> listAux = new ArrayList<Atividade>();
			listAux.addAll(ficha.getAtividades());
			ficha.getAtividades().clear();

			atividade.setFicha(ficha);
			atividade.setEcc(ecc);
			atividade.setEquipe(equipe);
			atividade.setCoordenador(isEhCoordenador());
			listAux.add(atividade);
			this.ficha.setAtividades(listAux);
			fichaService.atualiza(ficha);
			this.novaAtividade();
			removeAtividadeLimbo();

		} else {
				FacesMessages.error("Já existe uma atividade cadastrada para o ECC selecionado");
			}
	}

	public void removeAtividade() {
		this.ficha.getAtividades().remove(atividade);
		fichaService.atualiza(ficha);
	}

	public void removeAtividadeLimbo() {
		atividadeService.removeAtividadeLimbo();
	}

	public void salvaAptidao() {
		boolean existeAptidao = false;
		for(Aptidao apti : ficha.getAptidaos()) {
			if (apti.getEquipe().getId().equals(equipe.getId())) {
				existeAptidao = true;
			}
		}

		if (!existeAptidao) {
			List<Aptidao> listAux = new ArrayList<Aptidao>();
			listAux.addAll(ficha.getAptidaos());
			ficha.getAptidaos().clear();

			aptidao.setFicha(ficha);
			aptidao.setEquipe(equipe);
			listAux.add(aptidao);
			this.ficha.setAptidaos(listAux);
			fichaService.atualiza(ficha);
			this.novaAptidao();
			removeAptidaoLimbo();

		} else {
			FacesMessages.error("Já existe uma aptidão cadastrada para o casal selecionado");
		}
	}

	public void removeAptidao() {
		this.ficha.getAptidaos().remove(aptidao);
		fichaService.atualiza(ficha);
	}

	public void removeAptidaoLimbo() {
		aptidaoService.removeAptidaoLimbo();
	}

	public void filtraFicha() {
		listaFichas = fichaService.filtraFichaSituacaoeNomeUsual(situacaoFicha, nomeUsualCasalBusca);
	}

}
