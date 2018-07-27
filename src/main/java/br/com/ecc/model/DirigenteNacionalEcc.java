package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Classe que representa os Dirigentes Nacionais por ECC.
 * Representa os dirigentes nacionais dos ECCs :
 *      ASSISTENTE ECLESIASTICO NACIONAL
        SECRETARIA NACIONAL
        DIRETOR ESPIRITUAL REGIONAL NOROESTE
        CASAL REGIONAL
        BISPO ARQUI(DIOCESANO)
        DIRETOR ESPIRITUAL ARQUI(DIOCESANO)
        CASAL ARQUIDIOCESANO
        CASAL LIGACAO SETORIAL
 * @author Cleber Seixas
 * @since 24/07/2018
 */

@Audited
@Entity
@Table(name = "DIRIGENTES_NACIONAIS_ECCS")
@SequenceGenerator(name = "seq_dirigentes_nacionais_eccs", sequenceName = "seq_dirigentes_nacionais_eccs", allocationSize=1)
public class DirigenteNacionalEcc implements Serializable {
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dirigentes_nacionais_eccs")
    @Column(name = "DIRIGENTE_NACIONAL_ECC", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ecc", nullable = false, foreignKey = @ForeignKey(name = "fk_dirigente_nacional_ecc"))
    @NotNull(message = "Selecione o ECC")
    private Ecc ecc;

    @Column(name = "TIPO", length = 100, nullable = false)
    private String tipo = "";

    @Column(name="ORDEM", length=4)
    private Long ordemExibicao;

    @Column(name = "DESCRICAO", columnDefinition = " TEXT")
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ecc getEcc() {
        return ecc;
    }

    public void setEcc(Ecc ecc) {
        this.ecc = ecc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getOrdemExibicao() {
        return ordemExibicao;
    }

    public void setOrdemExibicao(Long ordemExibicao) {
        this.ordemExibicao = ordemExibicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirigenteNacionalEcc that = (DirigenteNacionalEcc) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
