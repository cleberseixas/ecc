package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Classe que representa os PALESTRANTES do sistema.
 * Palestrantes são ENCONTREIROS e ministram as palestras em um determinado ECC
 * @author Cleber Seixas
 * @since 30/10/2017
 */

@Audited
@Entity
@Table(name = "PALESTRANTES_ECCS")
@SequenceGenerator(name = "seq_palestrantes_eccs", sequenceName = "seq_palestrantes_eccs", allocationSize=1)
public class Palestrante implements Serializable {

    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_palestrantes_eccs")
    @Column(name = "PALESTRANTE_ECC", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ECC", nullable = false, foreignKey=@ForeignKey(name = "fk_palestrante_ecc"))
    private Ecc ecc;

    @ManyToOne
    @JoinColumn(name = "PALESTRA", nullable = false, foreignKey=@ForeignKey(name = "fk_palestrante_palestra"))
    private Palestra palestra;

    @ManyToOne
    @JoinColumn(name = "FICHA", nullable = true, foreignKey=@ForeignKey(name = "fk_palestrante_ficha"))
    private Ficha ficha;

    @Column(name="PALESTRANTE", length=100, nullable = true)
    private String palestrante;

    @Temporal(TemporalType.DATE)
    @Column(name="DATA_PALESTRA", nullable = false)
    private Date dataPalestra;

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

    public Palestra getPalestra() {
        return palestra;
    }

    public void setPalestra(Palestra palestra) {
        this.palestra = palestra;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public String getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(String palestrante) {
        this.palestrante = palestrante;
    }

    public Date getDataPalestra() {
        return dataPalestra;
    }

    public void setDataPalestra(Date dataPalestra) {
        this.dataPalestra = dataPalestra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Palestrante that = (Palestrante) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}


