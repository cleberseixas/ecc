package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa os Circulos por ECC.
 * Cada ECC possui Diversas Circulos (VÁRIOS ENCONTRISTAS DIVIDIDOS POR CÍRCULOS),
 * @author Cleber Seixas
 * @since 11/11/2017
 */

@Audited
@Entity
@Table(name = "CIRCULOS_ECCS")
@SequenceGenerator(name = "seq_circulos_eccs", sequenceName = "seq_circulos_eccs", allocationSize=1)
public class CirculoEcc implements Serializable{
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_circulos_eccs")
    @Column(name = "CIRCULO_ECC", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_ecc"))
    @NotNull(message="Selecione o ECC")
    private Ecc ecc;

    @ManyToOne
    @JoinColumn(name = "circulo", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_circulo"))
    @NotNull(message="Selecione o Círculo")
    private Circulo circulo;

    @ManyToOne
    @JoinColumn(name = "casal_coordenador", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_ficha"))
    @NotNull(message="Selecione o Casal Coordenador")
    private Ficha casalCoordenador;

    @Column(name="NOME_GRUPO", length=100)
    private String nomeGrupo;

    @ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "CIRCULOS_ECCS_ENCONTRISTAS_ENCONTRISTAS",
            joinColumns = @JoinColumn(name = "CIRCULO_ECC"),
            inverseJoinColumns = @JoinColumn(name = "CIRCULO_ECC_ENCONTRISTA"))
    private List<CirculoEccCasal> circulosEccCasais= new ArrayList<CirculoEccCasal>();

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

    public Circulo getCirculo() {
        return circulo;
    }

    public void setCirculo(Circulo circulo) {
        this.circulo = circulo;
    }

    public Ficha getCasalCoordenador() {
        return casalCoordenador;
    }

    public void setCasalCoordenador(Ficha casalCoordenador) {
        this.casalCoordenador = casalCoordenador;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public List<CirculoEccCasal> getCirculosEccCasais() {
        return circulosEccCasais;
    }

    public void setCirculosEccCasais(List<CirculoEccCasal> circulosEccCasais) {
        this.circulosEccCasais = circulosEccCasais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CirculoEcc that = (CirculoEcc) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
