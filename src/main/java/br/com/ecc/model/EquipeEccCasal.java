package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Classe que representa os Casais por Equipe em um determinado ECC.
 * @author Cleber Seixas
 * @since 11/10/2017
 */

@Audited
@Entity
@Table(name = "EQUIPES_ECCS_CASAIS")
@SequenceGenerator(name = "seq_equipes_eccs_casais", sequenceName = "seq_equipes_eccs_casais", allocationSize=1)
public class EquipeEccCasal implements Serializable{
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_equipes_eccs_casais")
    @Column(name = "EQUIPE_ECC_CASAL", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipe", nullable = false, foreignKey=@ForeignKey(name = "fk_equipe_ecc_casal_equipe"))
    @NotNull(message="Selecione a Equipe")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "equipe_ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_equipe_ecc"))
    private EquipeEcc equipeEcc;

    @ManyToOne
    @JoinColumn(name = "ficha", nullable = false, foreignKey=@ForeignKey(name = "fk_equipe_ecc_casal_ficha"))
    @NotNull(message="Selecione o Nome do Casal da Equipe")
    private Ficha ficha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public EquipeEcc getEquipeEcc(EquipeEcc equipeEcc) {
        return this.equipeEcc;
    }

    public void setEquipeEcc(EquipeEcc equipeEcc) {
        this.equipeEcc = equipeEcc;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipeEccCasal that = (EquipeEccCasal) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
