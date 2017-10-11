package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ecc ecc = (Ecc) o;

        if (automatico != ecc.automatico) return false;
        if (ativo != ecc.ativo) return false;
        if (id != null ? !id.equals(ecc.id) : ecc.id != null) return false;
        if (numero != null ? !numero.equals(ecc.numero) : ecc.numero != null) return false;
        if (local != null ? !local.equals(ecc.local) : ecc.local != null) return false;
        if (total != null ? !total.equals(ecc.total) : ecc.total != null) return false;
        if (tema != null ? !tema.equals(ecc.tema) : ecc.tema != null) return false;
        if (dataInicio != null ? !dataInicio.equals(ecc.dataInicio) : ecc.dataInicio != null) return false;
        if (dataFim != null ? !dataFim.equals(ecc.dataFim) : ecc.dataFim != null) return false;
        return situacao != null ? situacao.equals(ecc.situacao) : ecc.situacao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (local != null ? local.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (tema != null ? tema.hashCode() : 0);
        result = 31 * result + (automatico ? 1 : 0);
        result = 31 * result + (dataInicio != null ? dataInicio.hashCode() : 0);
        result = 31 * result + (dataFim != null ? dataFim.hashCode() : 0);
        result = 31 * result + (situacao != null ? situacao.hashCode() : 0);
        result = 31 * result + (ativo ? 1 : 0);
        return result;
    }
}
