package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Classe que representa as aptidões dos ENCONTREIROS.
 * Será utilizado como fator para a montagem das equipes nos ECCs futuros,
 * caso o ULTIMO_TRABALHO = NENHUM
 * Terão prioridade para a composição das equipes
 * APRESENTADOR, SOM E PROJEÇÃO, CANTO, BOA VONTADE e SUPERVISOR DE CÍRCULO
 * @author Cleber Seixas
 * @since 26/10/2017
 */

@Audited
@Entity
@Table(name = "APTIDOES")
@SequenceGenerator(name = "seq_aptidoes", sequenceName = "seq_aptidoes", allocationSize=1)
public class Aptidao implements Serializable {

    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq_aptidoes")
    @Column(name = "APTIDAO", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipe", nullable = false, foreignKey=@ForeignKey(name = "fk_equipe_aptidao"))
    @NotNull(message="Selecione a Aptidao")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "ficha", nullable = false, foreignKey=@ForeignKey(name = "fk_ficha_aptidao"))
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

        Aptidao atividade = (Aptidao) o;

        return id != null ? id.equals(atividade.id) : atividade.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
