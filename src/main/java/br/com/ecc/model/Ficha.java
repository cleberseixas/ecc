package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe que representa as Fichas (Encontristas e Encontreiros) do sistema.
 * As Fichas possuem os dados cadastrias dos ENCONTREIROS E ENCONTRISTAS
 * @author Cleber Seixas
 * @since 11/10/2017
 */

@Audited
@Entity
@Table(name = "FICHAS", uniqueConstraints=@UniqueConstraint(columnNames={"nome_usual"}, name="uk_nome_usual"))
@SequenceGenerator(name = "seq_fichas", sequenceName = "seq_fichas", allocationSize=1)
public class Ficha implements Serializable {
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_fichas")
    @Column(name = "FICHA", nullable = false)
    private Long id;

    @Column(name="NOME_USUAL", length=100, nullable = false)
    private String nomeUsual;

    @Column(name="SITUACAO", length=60, nullable = false)
    private String situacao="ENCONTRISTA";

    @Column(name="NOME_ELE", length=100, nullable = false)
    private String nomeEle;

    @Column(name="NOME_USUAL_ELE", length=50, nullable = false)
    private String nomeUsualEle;

    @Temporal(TemporalType.DATE)
    @Column(name="NASCIMENTO_ELE", nullable = false)
    private Date nascimentoEle;

    @Column(name="PROFISSAO_ELE", length=100, nullable = false)
    private String profissaoEle;

    @Column(name="RELIGIAO_ELE", length=100, nullable = false)
    private String religiaoEle;

    @Column(name="TELEFONE_ELE", length=40, nullable = false)
    private String telefoneEle;

    @Column(name="NOME_ELA", length=100, nullable = false)
    private String nomeEla;

    @Column(name="NOME_USUAL_ELA", length=50, nullable = false)
    private String nomeUsualEla;

    @Temporal(TemporalType.DATE)
    @Column(name="NASCIMENTO_ELA", nullable = false)
    private Date nascimentoEla;

    @Column(name="PROFISSAO_ELA", length=100, nullable = false)
    private String profissaoEla;

    @Column(name="RELIGIAO_ELA", length=100, nullable = false)
    private String religiaoEla;

    @Column(name="TELEFONE_ELA", length=40, nullable = false)
    private String telefoneEla;

    @Column(name="RUA", length=100, nullable = false)
    private String rua;

    @Column(name="NUMERO", length=10, nullable = false)
    private String numero;

    @Column(name="COMPLEMENTO", length=60, nullable = true)
    private String complemento;

    @Column(name="CEP", length=9, nullable = false)
    private String cep;

    @Column(name="BAIRRO", length=60, nullable = false)
    private String bairro;

    @Column(name="CIDADE", length=60, nullable = false)
    private String cidade="PORTO VELHO";

    @Column(name="ESTADO", length=2, nullable = false)
    private String estado="RO";

    @Column(name="DATA_CASAMENTO", length=10, nullable = true)
    private Date dataCasamento;

    @Column(name="CASAMENTO_RELIGIOSO", nullable = false)
    private boolean casamentoReligioso = false;

    @Column(name="PAROQUIA", length=100, nullable = true)
    private String paroquia;

    @Column(name="QTD_FILHOS", length=5, nullable = true)
    private Long qtdFilhos;

    @Column(name="PRIMEIRA_ETAPA", length=5, nullable = true)
    private String primeiraEtapa;

    @Column(name="SEGUNDA_ETAPA", length=5, nullable = true)
    private String segundaEtapa;

    @Column(name="TERCEIRA_ETAPA", length=5, nullable = true)
    private String terceiraEtapa;

    @Column(name="ULTIMO_TRABALHO", length=60, nullable = false)
    private String ultimoTrabalho="NENHUM";

    @Column(name="ATIVO", nullable = false)
    private boolean ativo = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsual() {
        return nomeUsual;
    }

    public void setNomeUsual(String nomeUsual) {
        this.nomeUsual = nomeUsual;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNomeEle() {
        return nomeEle;
    }

    public void setNomeEle(String nomeEle) {
        this.nomeEle = nomeEle;
    }

    public String getNomeUsualEle() {
        return nomeUsualEle;
    }

    public void setNomeUsualEle(String nomeUsualEle) {
        this.nomeUsualEle = nomeUsualEle;
    }

    public Date getNascimentoEle() {
        return nascimentoEle;
    }

    public void setNascimentoEle(Date nascimentoEle) {
        this.nascimentoEle = nascimentoEle;
    }

    public String getProfissaoEle() {
        return profissaoEle;
    }

    public void setProfissaoEle(String profissaoEle) {
        this.profissaoEle = profissaoEle;
    }

    public String getReligiaoEle() {
        return religiaoEle;
    }

    public void setReligiaoEle(String religiaoEle) {
        this.religiaoEle = religiaoEle;
    }

    public String getTelefoneEle() {
        return telefoneEle;
    }

    public void setTelefoneEle(String telefoneEle) {
        this.telefoneEle = telefoneEle;
    }

    public String getNomeEla() {
        return nomeEla;
    }

    public void setNomeEla(String nomeEla) {
        this.nomeEla = nomeEla;
    }

    public String getNomeUsualEla() {
        return nomeUsualEla;
    }

    public void setNomeUsualEla(String nomeUsualEla) {
        this.nomeUsualEla = nomeUsualEla;
    }

    public Date getNascimentoEla() {
        return nascimentoEla;
    }

    public void setNascimentoEla(Date nascimentoEla) {
        this.nascimentoEla = nascimentoEla;
    }

    public String getProfissaoEla() {
        return profissaoEla;
    }

    public void setProfissaoEla(String profissaoEla) {
        this.profissaoEla = profissaoEla;
    }

    public String getReligiaoEla() {
        return religiaoEla;
    }

    public void setReligiaoEla(String religiaoEla) {
        this.religiaoEla = religiaoEla;
    }

    public String getTelefoneEla() {
        return telefoneEla;
    }

    public void setTelefoneEla(String telefoneEla) {
        this.telefoneEla = telefoneEla;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getDataCasamento() {
        return dataCasamento;
    }

    public void setDataCasamento(Date dataCasamento) {
        this.dataCasamento = dataCasamento;
    }

    public boolean isCasamentoReligioso() {
        return casamentoReligioso;
    }

    public void setCasamentoReligioso(boolean casamentoReligioso) {
        this.casamentoReligioso = casamentoReligioso;
    }

    public String getParoquia() {
        return paroquia;
    }

    public void setParoquia(String paroquia) {
        this.paroquia = paroquia;
    }

    public Long getQtdFilhos() {
        return qtdFilhos;
    }

    public void setQtdFilhos(Long qtdFilhos) {
        this.qtdFilhos = qtdFilhos;
    }

    public String getPrimeiraEtapa() {
        return primeiraEtapa;
    }

    public void setPrimeiraEtapa(String primeiraEtapa) {
        this.primeiraEtapa = primeiraEtapa;
    }

    public String getSegundaEtapa() {
        return segundaEtapa;
    }

    public void setSegundaEtapa(String segundaEtapa) {
        this.segundaEtapa = segundaEtapa;
    }

    public String getTerceiraEtapa() {
        return terceiraEtapa;
    }

    public void setTerceiraEtapa(String terceiraEtapa) {
        this.terceiraEtapa = terceiraEtapa;
    }

    public String getUltimoTrabalho() {
        return ultimoTrabalho;
    }

    public void setUltimoTrabalho(String ultimoTrabalho) {
        this.ultimoTrabalho = ultimoTrabalho;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ficha ficha = (Ficha) o;

        return id != null ? id.equals(ficha.id) : ficha.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
