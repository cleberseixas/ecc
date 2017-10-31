package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que representa as Fichas (Encontristas e Encontreiros) do sistema.
 * As Fichas possuem os dados cadastrias dos ENCONTREIROS E ENCONTRISTAS
 * @author Cleber Seixas
 * @since 11/10/2017
 */

@Audited
@Entity
@Table(name = "FICHAS")
//@Table(name = "FICHAS", uniqueConstraints=@UniqueConstraint(columnNames={"nome_usual"}, name="uk_nome_usual"))
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

    @Column(name="FOTO_ELE", length=100, nullable = false)
    private String fotoEle;

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

    @Column(name="FOTO_ELA", length=100, nullable = false)
    private String fotoEla;

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

    //@Column(name="ULTIMO_TRABALHO", length=60, nullable = false)
    @ManyToOne
    @JoinColumn(name = "EQUIPE", nullable = true, foreignKey=@ForeignKey(name = "fk_ultimo_trabalho"))
    private Equipe ultimoTrabalho;

    @Column(name="ATIVO", nullable = false)
    private boolean ativo = true;

    @Column(name="MOTIVO", length=200, nullable = true)
    private String motivo;

    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "FICHAS_ATIVIDADES",
            joinColumns = @JoinColumn(name = "FICHA"),
            inverseJoinColumns = @JoinColumn(name = "ATIVIDADE"))
//    @JoinColumn(name = "ATIVIDADE", nullable = true, foreignKey=@ForeignKey(name = "fk_ultimo_trabalho"))

    private List<Atividade> atividades= new ArrayList<Atividade>();

    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "FICHAS_APTIDOES",
            joinColumns = @JoinColumn(name = "FICHA"),
            inverseJoinColumns = @JoinColumn(name = "APTIDAO"))
    private List<Aptidao> aptidaos= new ArrayList<Aptidao>();

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

    public String getFotoEle() {
        return fotoEle;
    }

    public void setFotoEle(String fotoEle) {
        this.fotoEle = fotoEle;
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

    public String getFotoEla() {
        return fotoEla;
    }

    public void setFotoEla(String fotoEla) {
        this.fotoEla = fotoEla;
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

    public Equipe getUltimoTrabalho() {
        return ultimoTrabalho;
    }

    public void setUltimoTrabalho(Equipe ultimoTrabalho) {
        this.ultimoTrabalho = ultimoTrabalho;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<Aptidao> getAptidaos() {
        return aptidaos;
    }

    public void setAptidaos(List<Aptidao> aptidaos) {
        this.aptidaos = aptidaos;
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
