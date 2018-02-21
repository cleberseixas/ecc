package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que representa os ECCs do sistema.
 * Tudo funciona em função do ECC cadastrado, é ele que irá definir as equipes, circulos, encontristas
 * Quando um ECC está em uso (no ano em questão) sua situação é ANDAMENTO, após o término do ECC
 * O mesmo é encerrado mudando sua situação para ENCERRADO, não podendo mais alterar nenhuma informação
 * de todos os dados envolvidos no ECC
 * @author Cleber Seixas
 * @since 10/10/2017
 */

@Audited
@Entity
@Table(name = "ECCS", uniqueConstraints=@UniqueConstraint(columnNames={"numero"}, name="uk_numeroecc"))
@SequenceGenerator(name = "seq_eccs", sequenceName = "seq_eccs", allocationSize=1)
public class Ecc implements Serializable {

    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_eccs")
    @Column(name = "ECC", nullable = false)
    private Long id;

    @Column(name="NUMERO", length=10, nullable = false)
    private String numero;

    @Column(name="LOCAL", length=60, nullable = false)
    private String local;

    @Column(name="TOTAL", length=5, nullable = false)
    private Long total;

    @Column(name="TEMA", length=100, nullable = false)
    private String tema;

    @Column(name="AUTOMATICO", nullable = false)
    private boolean automatico = true;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_INICIO", nullable = false)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_FIM", nullable = false)
    private Date dataFim;

    @Column(name="SITUACAO", nullable = false)
    private String situacao = "ANDAMENTO";

    @Column(name="ATIVO", nullable = false)
    private boolean ativo = true;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_ENCERRAMENTO", nullable = true)
    private Date dataEncerramento;

    @Column(name="USUARIO_ENCERROU", length=20, nullable = true)
    private String usuarioEncerrou;

    @Column(name="DIRETOR_ESPIRITUAL", length=60, nullable = true)
    private String diretorEspiritual;

    @Column(name="ENDERECO_DIRETOR", length=60, nullable = true)
    private String enderecoDiretor;

    @Column(name="TELEFONE_DIRETOR", length=40, nullable = true)
    private String telefoneDiretor;


    //@OneToMany(mappedBy="ecc", fetch=FetchType.EAGER, cascade = CascadeType.ALL)

    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "ECCS_DIRIGENTES",
            joinColumns = @JoinColumn(name = "ECC"),
            inverseJoinColumns = @JoinColumn(name = "DIRIGENTE"))
    private List<DirigenteEcc> dirigentes= new ArrayList<DirigenteEcc>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public boolean isAutomatico() {
        return automatico;
    }

    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public String getUsuarioEncerrou() {
        return usuarioEncerrou;
    }

    public void setUsuarioEncerrou(String usuarioEncerrou) {
        this.usuarioEncerrou = usuarioEncerrou;
    }

    public String getDiretorEspiritual() {
        return diretorEspiritual;
    }

    public void setDiretorEspiritual(String diretorEspiritual) {
        this.diretorEspiritual = diretorEspiritual;
    }

    public String getEnderecoDiretor() {
        return enderecoDiretor;
    }

    public void setEnderecoDiretor(String enderecoDiretor) {
        this.enderecoDiretor = enderecoDiretor;
    }

    public String getTelefoneDiretor() {
        return telefoneDiretor;
    }

    public void setTelefoneDiretor(String telefoneDiretor) {
        this.telefoneDiretor = telefoneDiretor;
    }

    public List<DirigenteEcc> getDirigentes() {
        return dirigentes;
    }

    public void setDirigentes(List<DirigenteEcc> dirigentes) {
        this.dirigentes = dirigentes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ecc ecc = (Ecc) o;

        return id != null ? id.equals(ecc.id) : ecc.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
