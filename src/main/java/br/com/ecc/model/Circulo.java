package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa os Círculos do sistema.
        * @author Cleber Seixas
        * @since 09/10/2017
        */

@Audited
@Entity
@Table(name = "CIRCULOS", uniqueConstraints=@UniqueConstraint(columnNames={"descricao"}, name="uk_descricao"))
@SequenceGenerator(name = "seq_circulos", sequenceName = "seq_circulos", allocationSize=1)
public class Circulo implements Serializable {
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_circulos")
    @Column(name = "CIRCULO", nullable = false)
    private Long id;

    @Column(name="DESCRICAO", length=50, nullable = false)
    private String descricao;

    @Column(name="TEMPO_CASADO_MINIMO", length=5, nullable = false)
    private Long tempoCasadoMinimo;

    @Column(name="TEMPO_CASADO_MAXIMO", length=5, nullable = false)
    private Long tempoCasadoMaximo;

    @Column(name="TOTAL_CASAL", length=5, nullable = false)
    private Long totalCasais;

    @Column(name="ATIVO", nullable = false)
    private boolean ativo = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getTempoCasadoMinimo() {
        return tempoCasadoMinimo;
    }

    public void setTempoCasadoMinimo(Long tempoCasadoMinimo) {
        this.tempoCasadoMinimo = tempoCasadoMinimo;
    }

    public Long getTempoCasadoMaximo() {
        return tempoCasadoMaximo;
    }

    public void setTempoCasadoMaximo(Long tempoCasadoMaximo) {
        this.tempoCasadoMaximo = tempoCasadoMaximo;
    }

    public Long getTotalCasais() {
        return totalCasais;
    }

    public void setTotalCasais(Long totalCasais) {
        this.totalCasais = totalCasais;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circulo circulo = (Circulo) o;

        return id != null ? id.equals(circulo.id) : circulo.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
