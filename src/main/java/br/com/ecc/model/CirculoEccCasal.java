package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Classe que representa os Casais por Círculos em um determinado ECC.
 * @author Cleber Seixas
 * @since 11/11/2017
 */

@Audited
@Entity
@Table(name = "CIRCULOS_ECCS_ENCONTRISTAS")
@SequenceGenerator(name = "seq_circulos_eccs_encontristas", sequenceName = "seq_circulos_eccs_encontristas", allocationSize=1)
public class CirculoEccCasal implements Serializable{
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_circulos_eccs_encontristas")
    @Column(name = "CIRCULO_ECC_ENCONTRISTA", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "circulo", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_ecc_encontrista_circulo"))
    @NotNull(message="Selecione o Circulo")
    private Circulo circulo;

    @ManyToOne
    @JoinColumn(name = "circulo_ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_ecc"))
    private CirculoEcc circuloEcc;

    @ManyToOne
    @JoinColumn(name = "ficha", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_ecc_encontrista_ficha"))
    @NotNull(message="Selecione o Nome do Encontrista")
    private Ficha ficha;

    @ManyToOne
    @JoinColumn(name = "ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_ecc_encontrista_ecc"))
    @NotNull(message="Selecione o ecc")
    private Ecc ecc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Circulo getCirculo() {
        return circulo;
    }

    public void setCirculo(Circulo circulo) {
        this.circulo = circulo;
    }

    public CirculoEcc getCirculoEcc() {
        return circuloEcc;
    }

    public void setCirculoEcc(CirculoEcc circuloEcc) {
        this.circuloEcc = circuloEcc;
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

        CirculoEccCasal that = (CirculoEccCasal) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
