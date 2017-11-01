package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa os Equipes por ECC.
 * Cada ECC possui Diversas equipes (ORDEM E LIMPEZA, LITURGIA E VIGILIA,
 * VISITAÇÃO, SECRETARIA, COMPRAS, CAFÉ E MINIMERCADO, ACOLHIDA, COZINHA, ETC)
 * @author Cleber Seixas
 * @since 01/11/2017
 */

@Audited
@Entity
@Table(name = "EQUIPES_ECCS")
@SequenceGenerator(name = "seq_equipes_eccs", sequenceName = "seq_equipes_eccs", allocationSize=1)
public class EquipeEcc implements Serializable{
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_equipes_eccs")
    @Column(name = "EQUIPE_ECC", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_equipe_ecc"))
    @NotNull(message="Selecione o ECC")
    private Ecc ecc;

    @ManyToOne
    @JoinColumn(name = "equipe", nullable = false, foreignKey=@ForeignKey(name = "fk_equipe_equipe"))
    @NotNull(message="Selecione a Equipe")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "casal_coordenador", nullable = false, foreignKey=@ForeignKey(name = "fk_equipe_ficha"))
    @NotNull(message="Selecione o Casal Coordenador")
    private Ficha casalCoordenador;

    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "EQUIPES_ECCS_CASAIS_CASAIS",
            joinColumns = @JoinColumn(name = "EQUIPE_ECC"),
            inverseJoinColumns = @JoinColumn(name = "EQUIPE_ECC_CASAL"))
    private List<EquipeEccCasal> equipesEccCasais= new ArrayList<EquipeEccCasal>();

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

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public Ficha getCasalCoordenador() {
        return casalCoordenador;
    }

    public void setCasalCoordenador(Ficha casalCoordenador) {
        this.casalCoordenador = casalCoordenador;
    }

    public List<EquipeEccCasal> getEquipesEccCasais() {
        return equipesEccCasais;
    }

    public void setEquipesEccCasais(List<EquipeEccCasal> equipesEccCasais) {
        this.equipesEccCasais = equipesEccCasais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipeEcc equipeEcc = (EquipeEcc) o;

        return id != null ? id.equals(equipeEcc.id) : equipeEcc.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
