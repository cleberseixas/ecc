package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Classe que representa os Casais (ENCONTRISTAS) por ECC.
 * @author Cleber Seixas
 * @since 11/10/2017
 */

@Audited
@Entity
@Table(name = "ENCONTRISTAS_ECCS_CASAIS")
@SequenceGenerator(name = "seq_encontristas_eccs_casais", sequenceName = "seq_encontristas_eccs_casais", allocationSize=1)
public class EncontristaEccCasal implements Serializable{
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_encontristas_eccs_casais")
    @Column(name = "ENCONTRISTA_ECC_CASAL", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "encontrista_ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_encontrista_ecc"))
    private EncontristaEcc encontristaEcc;

    @ManyToOne
    @JoinColumn(name = "ficha", nullable = false, foreignKey=@ForeignKey(name = "fk_encontrista_ecc_casal_ficha"))
    @NotNull(message="Selecione o Nome do Casal Encontrista")
    private Ficha ficha;

    @ManyToOne
    @JoinColumn(name = "ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_encontrista_ecc_casal_ecc"))
    @NotNull(message="Selecione o ecc")
    private Ecc ecc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EncontristaEcc getEncontristaEcc() {
        return encontristaEcc;
    }

    public void setEncontristaEcc(EncontristaEcc encontristaEcc) {
        this.encontristaEcc = encontristaEcc;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Ecc getEcc() {
        return ecc;
    }

    public void setEcc(Ecc ecc) {
        this.ecc = ecc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EncontristaEccCasal that = (EncontristaEccCasal) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
