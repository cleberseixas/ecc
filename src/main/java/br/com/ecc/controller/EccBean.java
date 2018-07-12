package br.com.ecc.controller;

import br.com.ecc.model.*;
import br.com.ecc.service.*;
import br.com.ecc.util.FacesMessages;
import br.com.ecc.util.Util;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

	private DirigenteEcc dirigenteEcc = new DirigenteEcc();

	private List<Ecc> listaEcc;

	private List<Ecc> listaUltimoEcc;

	private List<Equipe> listaEquipe;

	private List<Ecc> listaEccAtividades;

	private List<Ecc> listarEccAtivoEncerradoNao;

	private boolean habilitaBotaoEditarEcc = true;

	private boolean habilitaBotaoExcluirEcc = true;

	private boolean habilitaBotaoIncluiDirigentes = true;

	private boolean habilitaBotaoDetalhesEcc = true;

	private boolean habilitaBotaoEncerrarEcc = true;

	private boolean habilitaBotaoImprimirCapa = true;

	private boolean habilitaBotaoImprimirConvites = true;

	private boolean habilitaBotaoImprimirPreces = true;

	private boolean habilitaBotaoImprimirCasaisEncontristas = true;

	private boolean habilitaBotaoImprimirDirigentes = true;

	private boolean  habilitaBotaoImprimirEquipes = true;

	private boolean  habilitaBotaoImprimirPalestrantes = true;

	private boolean  habilitaBotaoImprimirDesenhos = true;

	private boolean  habilitaBotaoImprimirHarmoniaEParabola = true;

	private boolean  habilitaBotaoImprimirSemeador = true;


	private Ecc idEcc;

	private String statusEcc = "ENCERRADO";

	private String urlRelatorio;

	@Inject
	private ContextoBean contextoBean;

	@Inject
	private EccService eccService;

	@Inject
	private DirigenteEccService dirigenteEccService;


	@Inject
	private EquipeService equipeService;

	@Inject
	private EquipeEccService equipeEccService;

	@Inject
	private CirculoEccService circuloEccService;

	@Inject
	private AtividadeService atividadeService;

	@Inject
	private EquipeEccCasalService equipeEccCasalService;

	@Inject
	private PalestranteService palestranteService;

	@Inject
	private FichaService fichaService;

	@Inject
	private EncontristaEccCasalService encontristaEccCasalService;

	public void setListaEcc(List<Ecc> listaEcc) {
		this.listaEcc = listaEcc;
	}

	public void setListaUltimoEcc(List<Ecc> listaUltimoEcc) {
		this.listaUltimoEcc = listaUltimoEcc;
	}

	public List<Ecc> getListaEcc() {
		if (this.listaEcc == null) {
			this.listaEcc = eccService.listar();
		}
		return this.listaEcc;
	}
	public List<Ecc> getListaUltimoEcc() {
		if (this.listaUltimoEcc == null) {
			this.listaUltimoEcc = eccService.listarUltimoEcc();
		}
		return this.listaUltimoEcc;
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

	public List<Ecc> getListaEccAtividades() {
		return eccService.listarEccAtividades();
	}

	public void setListaEccAtividades(List<Ecc> listaEccAtividades) {
		this.listaEccAtividades = listaEccAtividades;
	}

	public List<Ecc> getListarEccAtivoEncerradoNao() {
		return eccService.listarEccAtivoEncerradoNao();
	}

	public void setListarEccAtivoEncerradoNao(List<Ecc> listarEccAtivoEncerradoNao) {
		this.listarEccAtivoEncerradoNao = listarEccAtivoEncerradoNao;
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

	public DirigenteEcc getDirigenteEcc() {
		return dirigenteEcc;
	}

	public void setDirigenteEcc(DirigenteEcc dirigenteEcc) {
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

	public boolean isHabilitaBotaoEncerrarEcc() {
		return habilitaBotaoEncerrarEcc;
	}

	public void setHabilitaBotaoEncerrarEcc(boolean habilitaBotaoEncerrarEcc) {
		this.habilitaBotaoEncerrarEcc = habilitaBotaoEncerrarEcc;
	}

	public boolean isHabilitaBotaoImprimirCapa() {
		return habilitaBotaoImprimirCapa;
	}

	public void setHabilitaBotaoImprimirCapa(boolean habilitaBotaoImprimirCapa) {
		this.habilitaBotaoImprimirCapa = habilitaBotaoImprimirCapa;
	}

	public boolean isHabilitaBotaoImprimirConvites() {
		return habilitaBotaoImprimirConvites;
	}

	public void setHabilitaBotaoImprimirConvites(boolean habilitaBotaoImprimirConvites) {
		this.habilitaBotaoImprimirConvites = habilitaBotaoImprimirConvites;
	}

	public boolean isHabilitaBotaoImprimirPreces() {
		return habilitaBotaoImprimirPreces;
	}

	public void setHabilitaBotaoImprimirPreces(boolean habilitaBotaoImprimirPreces) {
		this.habilitaBotaoImprimirPreces = habilitaBotaoImprimirPreces;
	}

	public boolean isHabilitaBotaoImprimirCasaisEncontristas() {
		return habilitaBotaoImprimirCasaisEncontristas;
	}

	public void setHabilitaBotaoImprimirCasaisEncontristas(boolean habilitaBotaoImprimirCasaisEncontristas) {
		this.habilitaBotaoImprimirCasaisEncontristas = habilitaBotaoImprimirCasaisEncontristas;
	}

	public boolean isHabilitaBotaoImprimirDirigentes() {
		return habilitaBotaoImprimirDirigentes;
	}

	public void setHabilitaBotaoImprimirDirigentes(boolean habilitaBotaoImprimirDirigentes) {
		this.habilitaBotaoImprimirDirigentes = habilitaBotaoImprimirDirigentes;
	}

	public boolean isHabilitaBotaoImprimirEquipes() {
		return habilitaBotaoImprimirEquipes;
	}

	public void setHabilitaBotaoImprimirEquipes(boolean habilitaBotaoImprimirEquipes) {
		this.habilitaBotaoImprimirEquipes = habilitaBotaoImprimirEquipes;
	}

	public boolean isHabilitaBotaoImprimirPalestrantes() {
		return habilitaBotaoImprimirPalestrantes;
	}

	public void setHabilitaBotaoImprimirPalestrantes(boolean habilitaBotaoImprimirPalestrantes) {
		this.habilitaBotaoImprimirPalestrantes = habilitaBotaoImprimirPalestrantes;
	}

	public boolean isHabilitaBotaoImprimirDesenhos() {
		return habilitaBotaoImprimirDesenhos;
	}

	public void setHabilitaBotaoImprimirDesenhos(boolean habilitaBotaoImprimirDesenhos) {
		this.habilitaBotaoImprimirDesenhos = habilitaBotaoImprimirDesenhos;
	}

	public boolean isHabilitaBotaoImprimirHarmoniaEParabola() {
		return habilitaBotaoImprimirHarmoniaEParabola;
	}

	public void setHabilitaBotaoImprimirHarmoniaEParabola(boolean habilitaBotaoImprimirHarmoniaEParabola) {
		this.habilitaBotaoImprimirHarmoniaEParabola = habilitaBotaoImprimirHarmoniaEParabola;
	}

	public boolean isHabilitaBotaoImprimirSemeador() {
		return habilitaBotaoImprimirSemeador;
	}

	public void setHabilitaBotaoImprimirSemeador(boolean habilitaBotaoImprimirSemeador) {
		this.habilitaBotaoImprimirSemeador = habilitaBotaoImprimirSemeador;
	}

	public Ecc getIdEcc() {
		return idEcc;
	}

	public void setIdEcc(Ecc idEcc) {
		this.idEcc = idEcc;
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

	private void novoDirigente() {
		this.casal = new Ficha();
		this.equipe = new Equipe();
		this.dirigenteEcc = new DirigenteEcc();
	}

	public void salvaDirigente() {
		if (dirigenteEccService.casalJaExisteEccDirigente(ecc.getId(), casal.getId(), equipe.getId()) && equipe.getId() != 12L) {
			FacesMessages.error("Casal/Equipe já faz parte da Equipe de Dirigentes neste ECC.");
			RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
			this.novoDirigente();
			return;
		} else if (equipeEccService.casalJaExisteEccCoordenadorOuEquipe(ecc.getId(), casal.getId())) {
					FacesMessages.error("Casal já faz parte de outra equipe neste ECC.");
					RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
					this.novoDirigente();
					return;
				} else if (circuloEccService.casalJaExisteEccCoordenadorCirculo(ecc.getId(), casal.getId())) {
							FacesMessages.error("Casal já faz parte de um Círculo(Coordenador) neste ECC.");
							RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
							this.novoDirigente();
							return;
						} else {
							List<DirigenteEcc> listAux = new ArrayList<DirigenteEcc>();
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
	}
	public void removeDirigente() {
		this.ecc.getDirigentes().remove(dirigenteEcc);
		eccService.atualiza(ecc);
		removeDirigenteLimbo();
	}

	public void removeDirigenteLimbo() {
		dirigenteEccService.removeDirigenteLimbo();
	}

	private void habilitaTodosBotoesEcc() {
		habilitaBotaoEditarEcc = false;
		if (this.ecc.getDirigentes().size() > 0) {
			habilitaBotaoExcluirEcc = true;
			habilitaBotaoEncerrarEcc = false;
		}
		else {
			habilitaBotaoExcluirEcc = false;
			habilitaBotaoEncerrarEcc = true;
		}

		habilitaBotaoIncluiDirigentes = false;
		habilitaBotaoDetalhesEcc = false;
		habilitaBotaoImprimirCapa = false;
		habilitaBotaoImprimirConvites = false;
		habilitaBotaoImprimirPreces = false;
		habilitaBotaoImprimirCasaisEncontristas = false;
		habilitaBotaoImprimirDirigentes = false;
		habilitaBotaoImprimirEquipes = false;
		habilitaBotaoImprimirPalestrantes = false;
		habilitaBotaoImprimirDesenhos = false;
		habilitaBotaoImprimirHarmoniaEParabola = false;
		habilitaBotaoImprimirSemeador = false;
	}

	private void desabilitaTodosBotoesEcc() {
		if (contextoBean.getUsuarioLogado().getPerfil().equals("Administrador")) {
			habilitaBotaoEditarEcc = false;
		} else habilitaBotaoEditarEcc = true;

		habilitaBotaoExcluirEcc = true;
		habilitaBotaoIncluiDirigentes = false;
		habilitaBotaoImprimirCapa = false;
		habilitaBotaoDetalhesEcc = false;
		habilitaBotaoEncerrarEcc = true;
		habilitaBotaoImprimirConvites = false;
		habilitaBotaoImprimirPreces = false;
		habilitaBotaoImprimirCasaisEncontristas = false;
		habilitaBotaoImprimirDirigentes = false;
		habilitaBotaoImprimirEquipes = false;
		habilitaBotaoImprimirPalestrantes = false;
		habilitaBotaoImprimirDesenhos = false;
		habilitaBotaoImprimirHarmoniaEParabola = false;
		habilitaBotaoImprimirSemeador = false;
	}

	//Incluir as regras para salvar o Ecc (Equipes)
	public void salvarEcc() {
		eccService.salvar(ecc);
		this.listaUltimoEcc = eccService.listarUltimoEcc();
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
			if (this.ecc.getSituacao().equals("ENCERRADO")) {
				this.ecc.setUsuarioEncerrou(contextoBean.getUsuarioLogado().getLogin());
				this.ecc.setDataEncerramento(new Date());
			} else {
				this.ecc.setUsuarioEncerrou(null);
				this.ecc.setDataEncerramento(null);
			}
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
			this.listaUltimoEcc = eccService.listarUltimoEcc();
			desabilitaTodosBotoesEcc();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
		}
	}

	public void encerrarEcc() {
		try {
			atividadeService.removeAtividadeLimbo();
			atualizaDirigentes();
			atualizaEquipesCoordenadores();
			atualizaEquipesCasais();
			atualizaCirculos();
			atualizaPalestrantes();
			atualizaEncontristas();
			//eccService.atualizaSituacaoEcc(ecc.getId(), "cleber");
			eccService.encerrar(ecc);
			//FacesMessages.info("ECC encerrado com sucesso");
			this.listaUltimoEcc = eccService.listarUltimoEcc();
			desabilitaTodosBotoesEcc();
		} catch (Exception e) {
			FacesMessages.error(e.getMessage());
		}
	}

	private void atualizaDirigentes() {
		List<DirigenteEcc> dirigentes = dirigenteEccService.dirigentesPorEcc(ecc.getId());
		for (DirigenteEcc dirigente : dirigentes) {
            Long ecc = dirigente.getEcc().getId();
            Long ficha = dirigente.getFicha().getId();
            Long equipe = dirigente.getEquipe().getId();
            Boolean coordenador = dirigente.getEquipe().isCoordenador();
            System.out.println("ECC "+ecc+" FICHA "+ficha+" EQUIPE "+equipe+ " COORDENADOR "+coordenador);
            atividadeService.insereAtividade(ecc, ficha, equipe, coordenador, true);
        }
	}

	private void atualizaEquipesCoordenadores() {
		List<EquipeEcc> equipes = equipeEccService.equipesPorEcc(ecc.getId());
		for (EquipeEcc equipe : equipes) {
			Long ecc = equipe.getEcc().getId();
			Long coordenador = equipe.getCasalCoordenador().getId();
			Long idEquipe = equipe.getEquipe().getId();
			System.out.println("ECC "+ecc+" COORDENADOR "+coordenador+" EQUIPE "+idEquipe+ " COORDENADOR "+true);
			atividadeService.insereAtividade(ecc, coordenador, idEquipe, true, true);
		}
	}

	private void atualizaEquipesCasais() {
		List<EquipeEccCasal> casais = equipeEccCasalService.casaisMembrosPorEcc(ecc.getId());
		for (EquipeEccCasal casal : casais) {
			Long ecc = casal.getEcc().getId();
			Long ficha = casal.getFicha().getId();
			Long equipe = casal.getEquipe().getId();
			System.out.println("ECC "+ecc+" CASAL "+ficha+" EQUIPE "+equipe+ " COORDENADOR "+false);
			atividadeService.insereAtividade(ecc, ficha, equipe, false, true);
		}
	}

	private void atualizaCirculos() {
		List<CirculoEcc> circulos = circuloEccService.circulosPorEcc(ecc.getId());
		for (CirculoEcc circulo : circulos) {
			Long ecc = circulo.getEcc().getId();
			Long coordenador = circulo.getCasalCoordenador().getId();
			Long equipe = 21L;
			System.out.println("ECC "+ecc+" FICHA "+coordenador+" EQUIPE "+equipe+ " COORDENADOR "+true);
			atividadeService.insereAtividade(ecc, coordenador, equipe, true, true);
		}
	}

	private void atualizaPalestrantes() {
		List<Palestrante> palestrantes = palestranteService.palestrantesPorEcc(ecc.getId());
		for (Palestrante palestrante : palestrantes) {
			Long ecc = palestrante.getEcc().getId();
			Long ficha = palestrante.getFicha().getId();
			Long equipe = 8L;
			System.out.println("ECC "+ecc+" FICHA "+ficha+" EQUIPE "+equipe+ " COORDENADOR "+false);
			atividadeService.insereAtividade(ecc, ficha, equipe, false, false);
		}
	}

	private void atualizaEncontristas() {
		List<EncontristaEccCasal> encontristas = encontristaEccCasalService.encontristasPorEcc(ecc.getId());
		String numeroEcc = ecc.getNumero();
		String fichas = "";
		int total = 0;
		for (EncontristaEccCasal encontrista : encontristas) {
			fichaService.atualizaSituacaoEPrimeiraEtapa(encontrista.getFicha().getId(), numeroEcc);
//			fichas = fichas +"'"+encontrista.getFicha().getId()+"',";
//			total++;
		}
		if (fichas.contains(",") && total > 0) {
			fichas = fichas.substring(0, fichas.length()-1);
			System.out.println("FICHAS "+fichas);
//		fichaService.atualizaSituacaoEPrimeiraEtapa(fichas, numeroEcc);
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
		//urlRelatorio = Util.retornaURLRelatorio();
		dirigenteEccService.removeDirigenteLimbo();
	}

	public void filtraEcc() {

		if (null != idEcc) {
			listaUltimoEcc = eccService.filtraEccPorEccStatus(idEcc.getId(), statusEcc);
		} else {
			listaUltimoEcc = eccService.filtraEccPorEccStatus(0L, statusEcc);
		}
	}

	public void imprimirCapa() throws IOException {
		try {

			urlRelatorio = Util.retornaURLRelatorio()+"/rptCapa.rptdesign&ecc=" + ecc.getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}


	public void imprimirDirigentes() throws IOException {
		try {
			urlRelatorio = Util.retornaURLRelatorio()+"/rptDirigentes.rptdesign&ecc=" + ecc.getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}

	public void imprimirEquipes() throws IOException {
		try {
			urlRelatorio = Util.retornaURLRelatorio()+"/rptEquipes.rptdesign&ecc=" + ecc.getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}

	public void imprimirPalestrantes() throws IOException {
		try {
			urlRelatorio = Util.retornaURLRelatorio()+"/rptPalestrantes.rptdesign&ecc=" + ecc.getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}

	public void imprimirConvites() throws IOException {
		try {

			urlRelatorio = Util.retornaURLRelatorio()+"/rptConvites.rptdesign&ecc=" + ecc.getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}

	public void imprimirPreces() throws IOException {
		try {

			urlRelatorio = Util.retornaURLRelatorio()+"/rptPrece.rptdesign&ecc=" + ecc.getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}

	public void imprimirEncontristas() throws IOException {
		try {

			urlRelatorio = Util.retornaURLRelatorio()+"/rptEncontristas.rptdesign&ecc=" + ecc.getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}

	public void imprimirHarmoniaEParabola() throws IOException {
		try {

			urlRelatorio = Util.retornaURLRelatorio()+"/rptCirculosEstudos.rptdesign&ecc=" + ecc.getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}

	public void imprimirDesenhos() throws IOException {
		try {

			urlRelatorio = Util.retornaURLRelatorio()+"/rptDesenhos.rptdesign&ecc=" + ecc.getId();
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}

	public void imprimirSemeador() throws IOException {
		try {

			urlRelatorio = Util.retornaURLRelatorio()+"/rptSemeador.rptdesign";
			System.out.println(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}


}
