package br.com.ecc.model;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Classe que representa os Circulos de Estudo por ECC.
 * Representa os círculos de estudo do ECC, abrange o stemas HARMONIA CONJUGAL e PARÁBOLA DO FILHO PRÓDIGO
 * @author Cleber Seixas
 * @since 26/02/2018
 */

@Audited
@Entity
@Table(name = "CIRCULOS_ESTUDOS_ECCS")
@SequenceGenerator(name = "seq_circulos_estudos_eccs", sequenceName = "seq_circulos_estudos_eccs", allocationSize=1)
public class CirculoEstudoEcc implements Serializable{
    private static final long serialVersionUID = 5783412212859462833L;

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_circulos_estudos_eccs")
    @Column(name = "CIRCULO_ESTUDO_ECC", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ecc", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_estudo_ecc"))
    @NotNull(message="Selecione o ECC")
    private Ecc ecc;

    @ManyToOne
    @JoinColumn(name = "circulo", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_estudo_circulo"))
    @NotNull(message="Selecione o Círculo")
    private Circulo circulo;

    @ManyToOne
    @JoinColumn(name = "casal_relator", nullable = false, foreignKey=@ForeignKey(name = "fk_circulo_estudo_ficha"))
    @NotNull(message="Selecione o Casal Relator")
    private Ficha casalRelator;

    @Column(name="TEMA", length=200)
    private String tema;

    @Column(name="PERGUNTA1", length=500)
    private String pergunta1;

    @Column(columnDefinition="TEXT")
    private String resposta1;

    @Column(name="PERGUNTA2", length=500)
    private String pergunta2;

    @Column(columnDefinition="TEXT")
    private String resposta2;

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

    public Ficha getCasalRelator() {
        return casalRelator;
    }

    public void setCasalRelator(Ficha casalRelator) {
        this.casalRelator = casalRelator;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getPergunta1() {
        return pergunta1;
    }

    public void setPergunta1(String pergunta1) {
        this.pergunta1 = pergunta1;
    }

    public String getResposta1() {
        return resposta1;
    }

    public void setResposta1(String resposta1) {
        this.resposta1 = resposta1;
    }

    public String getPergunta2() {
        return pergunta2;
    }

    public void setPergunta2(String pergunta2) {
        this.pergunta2 = pergunta2;
    }

    public String getResposta2() {
        return resposta2;
    }

    public void setResposta2(String resposta2) {
        this.resposta2 = resposta2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CirculoEstudoEcc that = (CirculoEstudoEcc) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
