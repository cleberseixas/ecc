package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe que representa as Fichas (Encontristas e Encontreiros) do sistema.
 * As Fichas possuem os dados cadastrias dos ENCONTREIROS E ENCONTRISTAS
 * @author Cleber Seixas
 * @since 11/10/2017
 */

@Audited
@Entity
@Table(name = "FICHAS", uniqueConstraints=@UniqueConstraint(columnNames={"nome_usual"}, name="uk_nome_usual"))
@SequenceGenerator(name = "seq_fichas", sequenceName = "seq_fichas", allocationSize=1)
public class Ficha implements Serializable {
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_fichas")
    @Column(name = "FICHA", nullable = false)
    private Long id;

    @Column(name="NOME_USUAL", length=100, nullable = false)
    private String nomeUsual;

    @Column(name="SITUACAO", length=60, nullable = false)
    private String situacao="ENCONTREIRO";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsual() {
        return nomeUsual;
    }

    public void setNomeUsual(String nomeUsual) {
        this.nomeUsual = nomeUsual;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ficha ficha = (Ficha) o;

        if (id != null ? !id.equals(ficha.id) : ficha.id != null) return false;
        if (nomeUsual != null ? !nomeUsual.equals(ficha.nomeUsual) : ficha.nomeUsual != null) return false;
        return situacao != null ? situacao.equals(ficha.situacao) : ficha.situacao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nomeUsual != null ? nomeUsual.hashCode() : 0);
        result = 31 * result + (situacao != null ? situacao.hashCode() : 0);
        return result;
    }
}
