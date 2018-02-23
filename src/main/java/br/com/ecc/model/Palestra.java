package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa as Palestras do sistema.
 * @author Cleber Seixas
 * @since 09/10/2017
 */

@Audited
@Entity
@Table(name = "PALESTRAS", uniqueConstraints=@UniqueConstraint(columnNames={"descricao"}, name="uk_descPalestra"))
@SequenceGenerator(name = "seq_palestras", sequenceName = "seq_palestras", allocationSize=1)
public class Palestra implements Serializable {
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_palestras")
    @Column(name = "PALESTRA", nullable = false)
    private Long id;

    @Column(name="DESCRICAO", length=100, nullable = false)
    private String descricao;

    @Column(name="CASAL", nullable = false)
    private boolean casal = true;

    @Column(name="ATIVO", nullable = false)
    private boolean ativo = true;

    @Column(name="ORDEM", length=4)
    private Long ordemImpressao;

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

    public boolean isCasal() {
        return casal;
    }

    public void setCasal(boolean casal) {
        this.casal = casal;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Long getOrdemImpressao() {
        return ordemImpressao;
    }

    public void setOrdemImpressao(Long ordemImpressao) {
        this.ordemImpressao = ordemImpressao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Palestra palestra = (Palestra) o;

        return id != null ? id.equals(palestra.id) : palestra.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
