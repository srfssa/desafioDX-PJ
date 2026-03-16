package br.com.duxusdesafio.entity;

import br.com.duxusdesafio.model.Integrante;

import javax.persistence.*;

@Entity
@Table(name = "composicao_time")
public class ComposicaoTimeEntity {

    /**
     * Identificador da composição de integrante em um time
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Time da composição
     */
    @ManyToOne @JoinColumn(name = "id_time")
    private TimeEntity time;

    /**
     * Integrante da composição
     */
    @ManyToOne @JoinColumn(name = "id_integrante")
    private IntegranteEntity integrante;

    public ComposicaoTimeEntity() {}

    public ComposicaoTimeEntity(TimeEntity time, IntegranteEntity integrante) {
        this.time = time;
        this.integrante = integrante;
    }

    public Long getId() {
        return id;
    }

    public TimeEntity getTime() {
        return time;
    }

    public void setTime(TimeEntity time) {
        this.time = time;
    }

    public IntegranteEntity getIntegrante() {
        return integrante;
    }

    public void setIntegrante(IntegranteEntity integrante) {
        this.integrante = integrante;
    }
}
