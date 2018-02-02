package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa os Enconstristas por ECC.
 * Cada ECC possui Encontristas (Casais que irão participar do ECC)
 * @author Cleber Seixas
 * @since 06/11/2017
 */

@Audited
@Entity
@Table(name = "ENCONTRISTAS_ECCS")
@SequenceGenerator(name = "seq_encontristas_eccs", sequenceName = "seq_encontristas_eccs", allocationSize=1)
public class EncontristaEcc implements Serializable{
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_encontristas_eccs")
    @Column(name = "ENCONTRISTA_ECC", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_encontrista_ecc"))
    @NotNull(message="Selecione o ECC")
    private Ecc ecc;

    @ManyToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ENCONTRISTAS_ECCS_CASAIS_CASAIS",
            joinColumns = @JoinColumn(name = "ENCONTRISTA_ECC"),
            inverseJoinColumns = @JoinColumn(name = "ENCONTRISTA_ECC_CASAL"))
    private List<EncontristaEccCasal> encontristasEccCasais= new ArrayList<EncontristaEccCasal>();

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

    public List<EncontristaEccCasal> getEncontristasEccCasais() {
        return encontristasEccCasais;
    }

    public void setEncontristasEccCasais(List<EncontristaEccCasal> encontristasEccCasais) {
        this.encontristasEccCasais = encontristasEccCasais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EncontristaEcc that = (EncontristaEcc) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
