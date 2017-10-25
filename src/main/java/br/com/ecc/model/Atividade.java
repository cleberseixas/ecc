package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Classe que representa as atividades dos ENCONTREIROS por ECC.
 * Mostra os trabalhos realizados pelos ENCONTREIROS em um determinado ECC
 * FINANÇAS, RECEPÇÃO DE PALESTRA/PALESTRA e PÓS ENCONTRO,
 * APRESENTADOR, SOM E PROJEÇÃO, CANTO, BOA VONTADE e SUPERVISOR DE CÍRCULO
 * @author Cleber Seixas
 * @since 25/10/2017
 */

@Audited
@Entity
@Table(name = "ATIVIDADES")
@SequenceGenerator(name = "seq_atividades", sequenceName = "seq_atividades", allocationSize=1)
public class Atividade implements Serializable {

    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq_atividades")
    @Column(name = "ATIVIDADE", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipe", nullable = false, foreignKey=@ForeignKey(name = "fk_equipe_atividade"))
    @NotNull(message="Selecione a Atividade")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_ecc_atividade"))
    @NotNull(message="Selecione o ECC")
    private Ecc ecc;

    @ManyToOne
    @JoinColumn(name = "ficha", nullable = false, foreignKey=@ForeignKey(name = "fk_ficha_atividade"))
    private Ficha ficha;

    @Column(name="COORDENADOR", nullable = false)
    private boolean coordenador = false;

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

    public Ecc getEcc() {
        return ecc;
    }

    public void setEcc(Ecc ecc) {
        this.ecc = ecc;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atividade atividade = (Atividade) o;

        return id != null ? id.equals(atividade.id) : atividade.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
