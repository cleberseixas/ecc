package br.com.ecc.controller;

import br.com.ecc.model.CirculoEcc;
import br.com.ecc.model.Ecc;
import br.com.ecc.model.EquipeEcc;
import br.com.ecc.model.util.Aptidao;
import br.com.ecc.model.util.Atividade;
import br.com.ecc.service.*;
import br.com.ecc.util.Constantes;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class EstatisticaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String eccFiltro;

	private String equipeFiltro;

	private String circuloFiltro;

	private String aptidaoFiltro;

	private String atividadeFiltro;

	private List<Ecc> listaDosEccs;

	private List<Ecc> listaDosEccsAuxiliar;

	private List<Aptidao> listaDasAptidoes;

	private List<Aptidao> listaDasAptidoesAuxiliar;

	private List<Atividade> listaDasAtividades;

	private List<Atividade> listaDasAtividadesAuxiliar;

	private List<CirculoEcc> listaDosCirculos;

	private List<CirculoEcc> listaDosCirculosAuxiliar;

	private List<EquipeEcc> listaDasEquipes;

	private List<EquipeEcc> listaDasEquipesAuxiliar;

	private int numeroEncontristas;

	private int numeroEncontreiros;

	private String urlRelatorio;

	@Inject
	private EccService eccService;

	@Inject
	private FichaService fichaService;

	@Inject
	private AptidaoService aptidaoService;

	@Inject
	private AtividadeService atividadeService;

	@Inject
	private CirculoEccService circuloEccService;

	@Inject
	private EquipeEccService equipeEccService;

	public String getEccFiltro() {
		return eccFiltro;
	}

	public void setEccFiltro(String eccFiltro) {
		this.eccFiltro = eccFiltro;
	}

	public String getEquipeFiltro() {
		return equipeFiltro;
	}

	public void setEquipeFiltro(String equipeFiltro) {
		this.equipeFiltro = equipeFiltro;
	}

	public String getCirculoFiltro() {
		return circuloFiltro;
	}

	public void setCirculoFiltro(String circuloFiltro) {
		this.circuloFiltro = circuloFiltro;
	}

	public String getAptidaoFiltro() {
		return aptidaoFiltro;
	}

	public void setAptidaoFiltro(String aptidaoFiltro) {
		this.aptidaoFiltro = aptidaoFiltro;
	}

	public String getAtividadeFiltro() {
		return atividadeFiltro;
	}

	public void setAtividadeFiltro(String atividadeFiltro) {
		this.atividadeFiltro = atividadeFiltro;
	}

	public List<Ecc> getListaDosEccs() {
		return listaDosEccs;
	}

	public void setListaDosEccs(List<Ecc> listaDosEccs) {
		this.listaDosEccs = listaDosEccs;
	}

	public List<Ecc> getListaDosEccsAuxiliar() {
		return listaDosEccsAuxiliar;
	}

	public void setListaDosEccsAuxiliar(List<Ecc> listaDosEccsAuxiliar) {
		this.listaDosEccsAuxiliar = listaDosEccsAuxiliar;
	}

	public List<Aptidao> getListaDasAptidoes() {
		return listaDasAptidoes;
	}

	public void setListaDasAptidoes(List<Aptidao> listaDasAptidoes) {
		this.listaDasAptidoes = listaDasAptidoes;
	}

	public List<Aptidao> getListaDasAptidoesAuxiliar() {
		return listaDasAptidoesAuxiliar;
	}

	public void setListaDasAptidoesAuxiliar(List<Aptidao> listaDasAptidoesAuxiliar) {
		this.listaDasAptidoesAuxiliar = listaDasAptidoesAuxiliar;
	}

	public List<CirculoEcc> getListaDosCirculos() {
		return listaDosCirculos;
	}

	public void setListaDosCirculos(List<CirculoEcc> listaDosCirculos) {
		this.listaDosCirculos = listaDosCirculos;
	}

	public List<CirculoEcc> getListaDosCirculosAuxiliar() {
		return listaDosCirculosAuxiliar;
	}

	public void setListaDosCirculosAuxiliar(List<CirculoEcc> listaDosCirculosAuxiliar) {
		this.listaDosCirculosAuxiliar = listaDosCirculosAuxiliar;
	}

	public List<EquipeEcc> getListaDasEquipes() {
		return listaDasEquipes;
	}

	public void setListaDasEquipes(List<EquipeEcc> listaDasEquipes) {
		this.listaDasEquipes = listaDasEquipes;
	}

	public List<EquipeEcc> getListaDasEquipesAuxiliar() {
		return listaDasEquipesAuxiliar;
	}

	public void setListaDasEquipesAuxiliar(List<EquipeEcc> listaDasEquipesAuxiliar) {
		this.listaDasEquipesAuxiliar = listaDasEquipesAuxiliar;
	}

	public List<Atividade> getListaDasAtividades() {
		return listaDasAtividades;
	}

	public void setListaDasAtividades(List<Atividade> listaDasAtividades) {
		this.listaDasAtividades = listaDasAtividades;
	}

	public List<Atividade> getListaDasAtividadesAuxiliar() {
		return listaDasAtividadesAuxiliar;
	}

	public void setListaDasAtividadesAuxiliar(List<Atividade> listaDasAtividadesAuxiliar) {
		this.listaDasAtividadesAuxiliar = listaDasAtividadesAuxiliar;
	}

	public int getNumeroEncontristas() {
		return numeroEncontristas;
	}

	public void setNumeroEncontristas(int numeroEncontristas) {
		this.numeroEncontristas = numeroEncontristas;
	}

	public int getNumeroEncontreiros() {
		return numeroEncontreiros;
	}

	public void setNumeroEncontreiros(int numeroEncontreiros) {
		this.numeroEncontreiros = numeroEncontreiros;
	}

	public String getUrlRelatorio() {
		return urlRelatorio;
	}

	public void setUrlRelatorio(String urlRelatorio) {
		this.urlRelatorio = urlRelatorio;
	}

	public void indicadoresIniciais() {
		listaDosEccs = eccService.listar();
		listaDosEccsAuxiliar = listaDosEccs;

		numeroEncontreiros = fichaService.listarEncontreiroEncontrista(Constantes.ENCONTREIRO).size();
		numeroEncontristas = fichaService.listarEncontreiroEncontrista(Constantes.ENCONTRISTA).size();

		listaDasAptidoes = aptidaoService.totalPorAptidao();
		listaDasAptidoesAuxiliar = listaDasAptidoes;

		listaDasAtividades = atividadeService.totalPorAtividade();
		listaDasAtividadesAuxiliar = listaDasAtividades;

		listaDosCirculos = circuloEccService.listar();
		listaDosCirculosAuxiliar = listaDosCirculos;

		listaDasEquipes = equipeEccService.listar();
		listaDasEquipesAuxiliar = listaDasEquipes;
	}

	public void filtraPorEcc() {
		List<Ecc> aux = new ArrayList<Ecc>();
		listaDosEccs = listaDosEccsAuxiliar;
		if (null != eccFiltro) {
			if (!eccFiltro.equals("")) {
				for (Ecc ecc : listaDosEccs) {
					if (ecc.getNumero().equals(eccFiltro)) {
						aux.add(ecc);
					}
				}
				listaDosEccs = aux;
			}
		}

	}

	public void filtraPorAptidao() {
		List<Aptidao> aux = new ArrayList<Aptidao>();
		listaDasAptidoes = listaDasAptidoesAuxiliar;
		if (null != aptidaoFiltro) {
			if (!aptidaoFiltro.equals("")) {
				for (Aptidao aptidao : listaDasAptidoes) {
					//ID da aptidao
					if (aptidao.getAptidao().equals(aptidaoFiltro)) {
						aux.add(aptidao);
					}
				}
				listaDasAptidoes = aux;
			}
		}

	}

	public void filtraPorAtividade() {
		List<Atividade> aux = new ArrayList<Atividade>();
		listaDasAtividades = listaDasAtividadesAuxiliar;
		if (null != atividadeFiltro) {
			if (!atividadeFiltro.equals("")) {
				for (Atividade atividade : listaDasAtividades) {
					//ID da atividade
					if (atividade.getAtividade().equals(atividadeFiltro)) {
						aux.add(atividade);
					}
				}
				listaDasAtividades = aux;
			}
		}

	}

	public void filtraPorEncontristasCirculos() {
		List<CirculoEcc> aux = new ArrayList<CirculoEcc>();
		listaDosCirculos = listaDosCirculosAuxiliar;
		if (null != eccFiltro) {
			if (!eccFiltro.equals("")) {
				for (CirculoEcc circuloEcc : listaDosCirculos) {
					if (circuloEcc.getEcc().getNumero().equals(eccFiltro)) {
						aux.add(circuloEcc);
					}
				}
				listaDosCirculos = aux;
			}
		}

		if (null != circuloFiltro) {
			if (!circuloFiltro.equals("")) {
				for (CirculoEcc circuloEcc : listaDosCirculos) {
					if (circuloEcc.getCirculo().getDescricao().equals(circuloFiltro)) {
						aux.add(circuloEcc);
					}
				}
				listaDosCirculos = aux;
			}
		}

	}

	public void filtraPorEquipes() {
		List<EquipeEcc> aux = new ArrayList<EquipeEcc>();
		listaDasEquipes = listaDasEquipesAuxiliar;
		if (null != eccFiltro) {
			if (!eccFiltro.equals("")) {
				for (EquipeEcc equipeEcc : listaDasEquipes) {
					if (equipeEcc.getEcc().getNumero().equals(eccFiltro)) {
						aux.add(equipeEcc);
					}
				}
				listaDasEquipes = aux;
			}
		}

		if (null != equipeFiltro) {
			if (!equipeFiltro.equals("")) {
				for (EquipeEcc equipeEcc : listaDasEquipes) {
					if (equipeEcc.getEquipe().getDescricao().equals(equipeFiltro)) {
						aux.add(equipeEcc);
					}
				}
				listaDasEquipes = aux;
			}
		}
	}

	/**
	 * Imprime a tabela de produtividade.
	 * @param dI - Data inicial da pesquisa.
	 * @param dF - Data final da pesquisa.
	 * @throws Exception Possíveis erros durante o procedimento de impressão.
	 */
	/*
	public void imprimirProdutividade(Date dI, Date dF) throws Exception  {
		byte[] arquivo = new byte[0];
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		Connection conexao = ConnectionFactory.getConnection();

		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		File f = new File(ctx.getRealPath("/resources/imagens/NAT.png"));
		BufferedImage img = ImageIO.read(f);

		HashMap<String,Object> map = new HashMap<>();
		map.put("IMG", img);
		map.put("pDataInicial", dI);
		map.put("pDataFinal", dF);

		String caminho = facesContext.getExternalContext().getRealPath("relatorios");
		caminho = caminho + File.separator + "printProdutividade.jasper";

		JasperPrint print = JasperFillManager.fillReport(caminho, map, conexao);
		arquivo = JasperExportManager.exportReportToPdf(print);
		response.setContentType("application/pdf");
		response.setContentLength(arquivo.length);
		OutputStream saida = response.getOutputStream();
		saida.write(arquivo, 0, arquivo.length);
		saida.flush();
		saida.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	 */

	public void imprimirEcc() {

	}

	public void imprimirCasalPorAptidao(ActionEvent event) throws IOException {
		try {
			UIComponent link = event.getComponent();
			UIParameter param_01 = (UIParameter) link.findComponent("ID_APTIDAO");

			int aptidao = (int) param_01.getValue();

			urlRelatorio = Constantes.URL_BIRT+"/rptAptidoes.rptdesign&aptidao="+aptidao;

			//urlRelatorio = "http://10.20.12.173:8090/ecc/relatorios/100.pdf";

			//FacesContext.getCurrentInstance().getExternalContext().redirect(urlRelatorio);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}


	}

	/*
	public void imprimirPareceresConcluidosCidade(ActionEvent event) {
		try {
			UIComponent link = event.getComponent();
			UIParameter param_01 = (UIParameter) link.findComponent("DI_Cidade");
			UIParameter param_02 = (UIParameter) link.findComponent("DF_Cidade");
			Date dI = (Date) param_01.getValue();
			Date dF = (Date) param_02.getValue();
			estatisticaRN.imprimirPareceresConcluidosCidade(dI, dF);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}
	 */
}
