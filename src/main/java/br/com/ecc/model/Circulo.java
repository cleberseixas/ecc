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

    @Column(name="DESCRICAO", length=50)
    private String descricao;

    @Column(name="TEMPO_CASADO", length=5)
    private Long tempoCasado;

    @Column(name="TOTAL_CASAL", length=50)
    private Long totalCasais;

    @Column(name="ATIVO", length=50)
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

    public Long getTempoCasado() {
        return tempoCasado;
    }

    public void setTempoCasado(Long tempoCasado) {
        this.tempoCasado = tempoCasado;
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

        if (ativo != circulo.ativo) return false;
        if (id != null ? !id.equals(circulo.id) : circulo.id != null) return false;
        if (descricao != null ? !descricao.equals(circulo.descricao) : circulo.descricao != null) return false;
        if (tempoCasado != null ? !tempoCasado.equals(circulo.tempoCasado) : circulo.tempoCasado != null) return false;
        return totalCasais != null ? totalCasais.equals(circulo.totalCasais) : circulo.totalCasais == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (tempoCasado != null ? tempoCasado.hashCode() : 0);
        result = 31 * result + (totalCasais != null ? totalCasais.hashCode() : 0);
        result = 31 * result + (ativo ? 1 : 0);
        return result;
    }
}
