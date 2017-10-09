package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa as Equipes do Sistema (Muito importante pois possui alguns
 * parâmetros para a montagem do ECC, Circulos, Equipes, ETC.
 * @author Cleber Seixas
 * @since 09/10/2017
 */

@Audited
@Entity
@Table(name = "EQUIPES", uniqueConstraints=@UniqueConstraint(columnNames={"descricao"}, name="uk_descequipe"))
@SequenceGenerator(name = "seq_equipes", sequenceName = "seq_equipes", allocationSize=1)
public class Equipe implements Serializable{

    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_equipes")
    @Column(name = "EQUIPE", nullable = false)
    private Long id;

    @Column(name="DESCRICAO", length=60, nullable = false)
    private String descricao;

    @Column(name="SIGLA", length=2, nullable = false)
    private String sigla;

    @Column(name="MINIMO_CASAL", length=2, nullable = false)
    private Long minimoCasal;

    @Column(name="MAXIMO_CASAL", length=2, nullable = false)
    private Long maximoCasal;

    @Column(name="AUTOMATICA", nullable = false)
    private boolean automatica = true;

    @Column(name="COORDENADOR", nullable = false)
    private boolean coordenador = false;

    @Column(name="PALESTRANTE", nullable = false)
    private boolean palestrante = false;

    @Column(name="APTIDAO", nullable = false)
    private boolean aptidao = false;

    @Column(name="ATIVO", nullable = false)
    private boolean ativo = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getMinimoCasal() {
        return minimoCasal;
    }

    public void setMinimoCasal(Long minimoCasal) {
        this.minimoCasal = minimoCasal;
    }

    public Long getMaximoCasal() {
        return maximoCasal;
    }

    public void setMaximoCasal(Long maximoCasal) {
        this.maximoCasal = maximoCasal;
    }

    public boolean isAutomatica() {
        return automatica;
    }

    public void setAutomatica(boolean automatica) {
        this.automatica = automatica;
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }

    public boolean isPalestrante() {
        return palestrante;
    }

    public void setPalestrante(boolean palestrante) {
        this.palestrante = palestrante;
    }

    public boolean isAptidao() {
        return aptidao;
    }

    public void setAptidao(boolean aptidao) {
        this.aptidao = aptidao;
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

        Equipe equipe = (Equipe) o;

        if (id != equipe.id) return false;
        if (automatica != equipe.automatica) return false;
        if (coordenador != equipe.coordenador) return false;
        if (palestrante != equipe.palestrante) return false;
        if (aptidao != equipe.aptidao) return false;
        if (ativo != equipe.ativo) return false;
        if (descricao != null ? !descricao.equals(equipe.descricao) : equipe.descricao != null) return false;
        if (sigla != null ? !sigla.equals(equipe.sigla) : equipe.sigla != null) return false;
        if (minimoCasal != null ? !minimoCasal.equals(equipe.minimoCasal) : equipe.minimoCasal != null) return false;
        return maximoCasal != null ? maximoCasal.equals(equipe.maximoCasal) : equipe.maximoCasal == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (sigla != null ? sigla.hashCode() : 0);
        result = 31 * result + (minimoCasal != null ? minimoCasal.hashCode() : 0);
        result = 31 * result + (maximoCasal != null ? maximoCasal.hashCode() : 0);
        result = 31 * result + (automatica ? 1 : 0);
        result = 31 * result + (coordenador ? 1 : 0);
        result = 31 * result + (palestrante ? 1 : 0);
        result = 31 * result + (aptidao ? 1 : 0);
        result = 31 * result + (ativo ? 1 : 0);
        return result;
    }
}
