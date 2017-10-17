package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Classe que representa os Dirigentes por ECC.
 * Cada ECC possui 5 dirigentes (COORDENADOR GERAL, MONTAGEM, FICHA,
 * FINANÇAS, RECEPÇÃO DE PALESTRA/PALESTRA e PÓS ENCONTRO)
 * Ficou definido que também serão cadastrados como DIRIGENTES
 * (APRESENTADOR, SOM E PROJEÇÃO, CANTO, BOA VONTADE e SUPERVISOR DE CÍRCULO)
 * @author Cleber Seixas
 * @since 11/10/2017
 */

@Audited
@Entity
@Table(name = "DIRIGENTES")
@SequenceGenerator(name = "seq_dirigentes", sequenceName = "seq_dirigentes", allocationSize=1)
public class Dirigente implements Serializable{
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_dirigentes")
    @Column(name = "DIRIGENTE", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipe", nullable = false, foreignKey=@ForeignKey(name = "fk_equipe"))
    @NotNull(message="Selecione o Dirigente")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "ficha", nullable = false, foreignKey=@ForeignKey(name = "fk_ficha"))
    @NotNull(message="Selecione o Casal Dirigente")
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

        Dirigente dirigente = (Dirigente) o;

        if (id != null ? !id.equals(dirigente.id) : dirigente.id != null) return false;
        if (equipe != null ? !equipe.equals(dirigente.equipe) : dirigente.equipe != null) return false;
        return ficha != null ? ficha.equals(dirigente.ficha) : dirigente.ficha == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (equipe != null ? equipe.hashCode() : 0);
        result = 31 * result + (ficha != null ? ficha.hashCode() : 0);
        return result;
    }
}
