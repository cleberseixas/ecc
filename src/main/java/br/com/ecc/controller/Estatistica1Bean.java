package br.com.ecc.controller;

import br.com.dti.astec.model.util.AnalistaTrabalhando;
import br.com.dti.astec.model.util.AnalistaViagem;
import br.com.dti.astec.model.util.Estatistica;
import br.com.dti.astec.model.util.ProdutividadeModel;
import br.com.dti.astec.service.EstatisticaRN;
import br.com.dti.astec.util.Util;
import org.joda.time.DateTime;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class Estatistica1Bean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String numeroTramitacao;
	private String numeroAguardando;
	private String numeroConcluido;
	private String numeroCancelado;
	private String numeroArquivado;
	private String numeroNovosMovimentos;
	private String numeroMovimentosParados;
	private String numeroNovasAtividades;
	
	private String numeroTramitacaoAtividades;
	private String numeroAguardandoAtividades;
	private String numeroConcluidoAtividades;
	private String numeroCanceladoAtividades;
	
	private Long numeroPareceresDevolvidosAoChefe;
	private Long numeroMensagenAoChefe;
	
	private List<AnalistaTrabalhando> listaAtividadesDosAnalistas;
	private List<AnalistaTrabalhando> listaAtividadesDosAnalistasAuxiliar;
	private List<ProdutividadeModel> listaProdutividade;
	
	List<Estatistica> listaEstatisticaMovimentacoesAbertas;
	List<Estatistica> listaEstatisticaMovimentacoesConcluidas;
	List<Estatistica> listaEstatisticaMovimentacoesConcluidasRelatorio;
	List<Estatistica> listaPareceresConcluidosPorArea;
	List<Estatistica> listaPareceresConcluidosPorCidade;
	
	List<AnalistaViagem> listaViagens; //mostra os analistas que estão viajando e que irão viajar. 
	
	private String tipoPesquisa = "Área";
	private String areaAtuacao;
	private String analistaFiltro;
	
	private Date dataInicial, dataFinal;
	private Date dataInicialGraficos, dataFinalGraficos;
	private Date dataInicialArea, dataFinalArea, dataInicialCidade, dataFinalCidade;

	private PieChartModel pizzaMovimentacaoPorNatureza, pizzaMovimentacaoPorNaturaAbertos;
	
	@Inject
	private EstatisticaRN estatisticaRN;
	
	@PostConstruct
	public void init() {
		dataInicial = ((new DateTime(System.currentTimeMillis())).minusDays(30)).toDate();
		dataFinal =  (new DateTime(System.currentTimeMillis())).toDate();
		dataInicialArea = dataInicial;
		dataFinalArea = dataFinal;
		dataInicialCidade = dataInicial;
		dataFinalCidade = dataFinal;
	}
	
	public void indicadoresIniciais() {
		dataInicialGraficos = dataInicial;
		dataFinalGraficos = dataFinal;

		Long pareceresTramitacao = estatisticaRN.numeroPareceres("Encaminhado para Análise");
		Long pareceresAguardando = estatisticaRN.numeroPareceres("Aguardando Análise");
		Long pareceresConcluido = estatisticaRN.numeroPareceres("Parecer concluído");
		Long pareceresCancelados = estatisticaRN.numeroPareceres("Parecer cancelado");
		Long pareceresArquivados = estatisticaRN.numeroPareceres("Parecer arquivado");

		Long atividadesTramitacao = estatisticaRN.numeroAtividades("Encaminhado para Análise");
		Long atividadesAguardando = estatisticaRN.numeroAtividades("Aguardando Análise");
		Long atividadesConcluido = estatisticaRN.numeroAtividades("Concluida");
		Long atividadeCancelados = estatisticaRN.numeroAtividades("Cancelada");

		numeroPareceresDevolvidosAoChefe = estatisticaRN.numeroParecerDevolvidosAochefe();
		numeroMensagenAoChefe = estatisticaRN.numeroMensagenAoChefe();
		
		numeroTramitacao = Util.getQuantidadePareceresFormatados(pareceresTramitacao);
		numeroAguardando = Util.getQuantidadePareceresFormatados(pareceresAguardando);
		numeroConcluido = Util.getQuantidadePareceresFormatados(pareceresConcluido);
		numeroCancelado = Util.getQuantidadePareceresFormatados(pareceresCancelados);
		numeroArquivado = Util.getQuantidadePareceresFormatados(pareceresArquivados);
		
		numeroTramitacaoAtividades = Util.getQuantidadePareceresFormatados(atividadesTramitacao);
		numeroAguardandoAtividades = Util.getQuantidadePareceresFormatados(atividadesAguardando);
		numeroConcluidoAtividades = Util.getQuantidadePareceresFormatados(atividadesConcluido);
		numeroCanceladoAtividades = Util.getQuantidadePareceresFormatados(atividadeCancelados);
				
		Long numeroMovimentos = estatisticaRN.numeroNovosMovimentos();
		numeroNovosMovimentos = Util.getQuantidadePareceresFormatados(numeroMovimentos);
				
		Integer movimentosParados = estatisticaRN.numeroMovimentacoesParadas();
		numeroMovimentosParados = Util.getQuantidadePareceresFormatados(movimentosParados.longValue());
		
		Long numeroAtividades = estatisticaRN.numeroNovasAtividades();
		numeroNovasAtividades = Util.getQuantidadePareceresFormatados(numeroAtividades);
		
		listaAtividadesDosAnalistas = estatisticaRN.listaAtividadeDosAnalistas();
		listaAtividadesDosAnalistasAuxiliar = listaAtividadesDosAnalistas; //fica sempre cheia
		listaProdutividade = estatisticaRN.listaProdutividade(dataInicial, dataFinal); //armazena a produtividade de cada analista
		listaViagens = estatisticaRN.listaViagemDosAnalista();
		listaPareceresConcluidosPorArea = estatisticaRN.listaPareceresConcluidosPorArea(dataInicialArea, dataFinalArea);
		listaPareceresConcluidosPorCidade = estatisticaRN.listaPareceresConcluidosPorCidade(dataInicialCidade, dataFinalCidade);
	}

	public void geraProdutividade() {
		listaProdutividade = estatisticaRN.listaProdutividade(dataInicial, dataFinal);
	}
	
	public String getLogoAnalista(int contador) {
		return contador % 2 == 0 ? "logoAnalista_1.png" : "logoAnalista_2.png";
	}
	
	public String getLogoAreaConhecimento(int contador) {
		return contador % 2 == 0 ? "logoAreaConhecimento_1.png" : "logoAreaConhecimento_2.png";
	}

	public String getVolumeTrabalho(int qtd) {
		if (qtd == 0) {
			return "--";
		} else if (qtd == 1) {
			return "01";
		} else {
			return Util.getFormataNumerosZeroNaFrente(qtd);
		}
	}

	public String getVolumeTrabalhoAtividades(int qtd) {
		if (qtd == 0) {
			return "--";
		} else if (qtd == 1) {
			return "01";
		} else {
			return Util.getFormataNumerosZeroNaFrente(qtd);
		}
	}	
	
	public String getAvisoVolumeTrabalho(int qtd) {
		return qtd == 0 ? "divTrabalhoAnalista_03_01_branco" : qtd <= 5  ? "divTrabalhoAnalista_03_01_verde" : qtd <= 10 ? "divTrabalhoAnalista_03_01_laranja" : "divTrabalhoAnalista_03_01_vermelha"; 
	}
	
	public void filtraPorArea() {
		List<AnalistaTrabalhando> aux = new ArrayList<AnalistaTrabalhando>();
		listaAtividadesDosAnalistas = listaAtividadesDosAnalistasAuxiliar;
		if (!areaAtuacao.equals("")) {
			for (AnalistaTrabalhando analista : listaAtividadesDosAnalistas) {
				if (analista.getArea().equals(areaAtuacao)) {
					aux.add(analista);
				}
			}
			listaAtividadesDosAnalistas = aux;
		}
	}
	
	public void filtraPorAnalista() {
		List<AnalistaTrabalhando> aux = new ArrayList<AnalistaTrabalhando>();
		listaAtividadesDosAnalistas = listaAtividadesDosAnalistasAuxiliar;
		if (!analistaFiltro.equals("")) {
			for (AnalistaTrabalhando analista : listaAtividadesDosAnalistas) {
				if (analista.getNome().equals(analistaFiltro)) {
					aux.add(analista);
				}
			}
			listaAtividadesDosAnalistas = aux;
		}
	}
	
	public void imprimir(ActionEvent event) {
		try {
			UIComponent link = event.getComponent();
			UIParameter param_01 = (UIParameter) link.findComponent("dataInicial");
			UIParameter param_02 = (UIParameter) link.findComponent("dataFinal");
			Date dI = (Date) param_01.getValue();
			Date dF = (Date) param_02.getValue();
			estatisticaRN.imprimirProdutividade(dI, dF);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!"+ex.getMessage());
		}
	}
	
	public void imprimirPareceresConcluidosArea(ActionEvent event) {
		try {
			UIComponent link = event.getComponent();
			UIParameter param_01 = (UIParameter) link.findComponent("DI_Area");
			UIParameter param_02 = (UIParameter) link.findComponent("DF_Area");
			Date dI = (Date) param_01.getValue();
			Date dF = (Date) param_02.getValue();
			estatisticaRN.imprimirPareceresConcluidosArea(dI, dF);
		} catch (Exception ex) {
			System.err.println("O arquivo não foi gerado corretamente!");
		}
	}
	
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
	
	public void carregaGraficos() {
		System.out.println("Data Inicial quando clico no botão de pesquisa: " + Util.getDataFormatada(dataInicialGraficos));
		System.out.println("Data Final quando clico no botão de pesquisa: " + Util.getDataFormatada(dataFinalGraficos));
		
		System.out.println("========================================================================");
		
		this.carregaGraficoMovimentacoesPorNatureza();
		this.carregaGraficoMovimentacoesPorNaturezaAbertos();
	}
	
	public void geraTabelaMovimentacoesPorNatureza() {
		listaEstatisticaMovimentacoesConcluidas = estatisticaRN.listaEstatisticaNaturezaMovimentacaoConcluidas(dataInicialGraficos, dataFinalGraficos);
	}

	public void geraTabelaMovimentacoesPorNaturezaRelatorio() {
		System.out.println("Data Inicial: " + Util.getDataFormatada(dataInicialGraficos));
		System.out.println("Data Final: " + Util.getDataFormatada(dataFinalGraficos));
		System.out.println("========================================================================");
		listaEstatisticaMovimentacoesConcluidasRelatorio = 
				estatisticaRN.listaEstatisticaNaturezaMovimentacaoConcluidas(dataInicialGraficos, dataFinalGraficos);
	}
	
	public String getDataFormatada() {
		return Util.getDataExtenso(new Date());
	}

	public Integer somaMovimentacoesConcluidasPorNatureza() {
		Integer result = 0;
		for (Estatistica aux : listaEstatisticaMovimentacoesConcluidasRelatorio) {
			result += aux.getValor();
		}
		return result;
	}

	public String getPeriodoPesquisa() {
		System.out.println("Data Inicial quando gera a pagina: " + Util.getDataFormatada(dataInicialGraficos));
		System.out.println("Data Final quando gera a pagina: " + Util.getDataFormatada(dataFinalGraficos));
		System.out.println("========================================================================");
		
		return "Período da Pesquisa: De " + Util.getDataFormatada(dataInicialGraficos) + " até " + Util.getDataFormatada(dataFinalGraficos);
	}
	
	public void carregaGraficoMovimentacoesPorNatureza() {
		listaEstatisticaMovimentacoesConcluidas = estatisticaRN.listaEstatisticaNaturezaMovimentacaoConcluidas(dataInicialGraficos, dataFinalGraficos);
		pizzaMovimentacaoPorNatureza = new PieChartModel();
		for (Estatistica e : listaEstatisticaMovimentacoesConcluidas) {
			pizzaMovimentacaoPorNatureza.set(e.getDescricao() + " - "	+ e.getValor(), e.getValor());
		}
		pizzaMovimentacaoPorNatureza.setLegendPosition("e");
		pizzaMovimentacaoPorNatureza.setShowDataLabels(true);
		pizzaMovimentacaoPorNatureza.setShowDatatip(false);
		pizzaMovimentacaoPorNatureza.setTitle("Concluídas no período");
	}

	public void carregaGraficoMovimentacoesPorNaturezaAbertos() {
		listaEstatisticaMovimentacoesAbertas = estatisticaRN.listaEstatisticaNaturezaMovimentacaoAbertas(dataInicialGraficos, dataFinalGraficos);
		pizzaMovimentacaoPorNaturaAbertos = new PieChartModel();
		for (Estatistica e : listaEstatisticaMovimentacoesAbertas) {
			pizzaMovimentacaoPorNaturaAbertos.set(e.getDescricao() + " - "	+ e.getValor(), e.getValor());
		}
		pizzaMovimentacaoPorNaturaAbertos.setLegendPosition("e");
		pizzaMovimentacaoPorNaturaAbertos.setShowDataLabels(true);
		pizzaMovimentacaoPorNaturaAbertos.setShowDatatip(false);
		pizzaMovimentacaoPorNaturaAbertos.setTitle("Abertas no período");
	}
	
	public void carregaViagensDosAnalistas() {
		this.listaViagens = estatisticaRN.listaViagemDosAnalista();
	}
	
	public void atualizaParecesConcluidosPorArea() {
		listaPareceresConcluidosPorArea = estatisticaRN.listaPareceresConcluidosPorArea(dataInicialArea, dataFinalArea);
	}
	
	public void atualizaParecerConcluidosPorCidade() {
		listaPareceresConcluidosPorCidade = estatisticaRN.listaPareceresConcluidosPorCidade(dataInicialCidade, dataFinalCidade);
	}
	
	public String relatorioMovimentacoesPorNatureza() {
		return "/chefe/relatorioMovimentacoesPorNatureza?faces-redirect=true";
	}
	
	//---------------------------------------------------------------------------
	//---------------------------- Getters and Setters --------------------------
	//---------------------------------------------------------------------------
	public String getNumeroTramitacao() {
		return numeroTramitacao;
	}

	public String getNumeroAguardando() {
		return numeroAguardando;
	}

	public String getNumeroConcluido() {
		return numeroConcluido;
	}

	public String getNumeroCancelado() {
		return numeroCancelado;
	}

	public String getNumeroArquivado() {
		return numeroArquivado;
	}

	public String getNumeroNovosMovimentos() {
		return numeroNovosMovimentos;
	}

	public String getNumeroMovimentosParados() {
		return numeroMovimentosParados;
	}

	public List<AnalistaTrabalhando> getListaAtividadesDosAnalistas() {
		return listaAtividadesDosAnalistas;
	}

	public List<AnalistaTrabalhando> getListaAtividadesDosAnalistasAuxiliar() {
		return listaAtividadesDosAnalistasAuxiliar;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public String getAnalistaFiltro() {
		return analistaFiltro;
	}

	public void setAnalistaFiltro(String analistaFiltro) {
		this.analistaFiltro = analistaFiltro;
	}

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public List<ProdutividadeModel> getListaProdutividade() {
		return listaProdutividade;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public PieChartModel getPizzaMovimentacaoPorNatureza() {
		return pizzaMovimentacaoPorNatureza;
	}

	public void setPizzaMovimentacaoPorNatureza(PieChartModel pizzaMovimentacaoPorNatureza) {
		this.pizzaMovimentacaoPorNatureza = pizzaMovimentacaoPorNatureza;
	}

	public PieChartModel getPizzaMovimentacaoPorNaturaAbertos() {
		return pizzaMovimentacaoPorNaturaAbertos;
	}

	public void setPizzaMovimentacaoPorNaturaAbertos(PieChartModel pizzaMovimentacaoPorNaturaAbertos) {
		this.pizzaMovimentacaoPorNaturaAbertos = pizzaMovimentacaoPorNaturaAbertos;
	}

	public Date getDataInicialGraficos() {
		return dataInicialGraficos;
	}

	public void setDataInicialGraficos(Date dataInicialGraficos) {
		this.dataInicialGraficos = dataInicialGraficos;
	}

	public Date getDataFinalGraficos() {
		return dataFinalGraficos;
	}

	public void setDataFinalGraficos(Date dataFinalGraficos) {
		this.dataFinalGraficos = dataFinalGraficos;
	}

	public List<Estatistica> getListaEstatisticaMovimentacoesAbertas() {
		return listaEstatisticaMovimentacoesAbertas;
	}

	public void setListaEstatisticaMovimentacoesAbertas(List<Estatistica> listaEstatisticaMovimentacoesAbertas) {
		this.listaEstatisticaMovimentacoesAbertas = listaEstatisticaMovimentacoesAbertas;
	}

	public List<Estatistica> getListaEstatisticaMovimentacoesConcluidas() {
		return listaEstatisticaMovimentacoesConcluidas;
	}

	public void setListaEstatisticaMovimentacoesConcluidas(List<Estatistica> listaEstatisticaMovimentacoesConcluidas) {
		this.listaEstatisticaMovimentacoesConcluidas = listaEstatisticaMovimentacoesConcluidas;
	}

	public List<AnalistaViagem> getListaViagens() {
		return listaViagens;
	}

	public List<Estatistica> getListaPareceresConcluidosPorArea() {
		return listaPareceresConcluidosPorArea;
	}
	
	public List<Estatistica> getListaPareceresConcluidosPorCidade() {
		return listaPareceresConcluidosPorCidade;
	}

	public Date getDataInicialArea() {
		return dataInicialArea;
	}

	public void setDataInicialArea(Date dataInicialArea) {
		this.dataInicialArea = dataInicialArea;
	}

	public Date getDataFinalArea() {
		return dataFinalArea;
	}

	public void setDataFinalArea(Date dataFinalArea) {
		this.dataFinalArea = dataFinalArea;
	}

	public Date getDataInicialCidade() {
		return dataInicialCidade;
	}

	public void setDataInicialCidade(Date dataInicialCidade) {
		this.dataInicialCidade = dataInicialCidade;
	}

	public Date getDataFinalCidade() {
		return dataFinalCidade;
	}

	public void setDataFinalCidade(Date dataFinalCidade) {
		this.dataFinalCidade = dataFinalCidade;
	}

	public List<Estatistica> getListaEstatisticaMovimentacoesConcluidasRelatorio() {
		return listaEstatisticaMovimentacoesConcluidasRelatorio;
	}

	public String getNumeroTramitacaoAtividades() {
		return numeroTramitacaoAtividades;
	}

	public void setNumeroTramitacaoAtividades(String numeroTramitacaoAtividades) {
		this.numeroTramitacaoAtividades = numeroTramitacaoAtividades;
	}

	public String getNumeroAguardandoAtividades() {
		return numeroAguardandoAtividades;
	}

	public void setNumeroAguardandoAtividades(String numeroAguardandoAtividades) {
		this.numeroAguardandoAtividades = numeroAguardandoAtividades;
	}

	public String getNumeroConcluidoAtividades() {
		return numeroConcluidoAtividades;
	}

	public void setNumeroConcluidoAtividades(String numeroConcluidoAtividades) {
		this.numeroConcluidoAtividades = numeroConcluidoAtividades;
	}

	public String getNumeroCanceladoAtividades() {
		return numeroCanceladoAtividades;
	}

	public void setNumeroCanceladoAtividades(String numeroCanceladoAtividades) {
		this.numeroCanceladoAtividades = numeroCanceladoAtividades;
	}

	public String getNumeroNovasAtividades() {
		return numeroNovasAtividades;
	}

	public void setNumeroNovasAtividades(String numeroNovasAtividades) {
		this.numeroNovasAtividades = numeroNovasAtividades;
	}

	public Long getNumeroPareceresDevolvidosAoChefe() {
		return numeroPareceresDevolvidosAoChefe;
	}

	public void setNumeroPareceresDevolvidosAoChefe(Long numeroPareceresDevolvidosAoChefe) {
		this.numeroPareceresDevolvidosAoChefe = numeroPareceresDevolvidosAoChefe;
	}

	public Long getNumeroMensagenAoChefe() {
		return numeroMensagenAoChefe;
	}

	public void setNumeroMensagenAoChefe(Long numeroMensagenAoChefe) {
		this.numeroMensagenAoChefe = numeroMensagenAoChefe;
	}

}
