package br.com.duxusdesafio.model;

public class ComposicaoTime {

    private Long id;
    private Time time;
    private Integrante integrante;

    public ComposicaoTime() {}

    public ComposicaoTime(Time time, Integrante integrante) {
        this.time = time;
        this.integrante = integrante;
    }

    public ComposicaoTime(Long id, Time time, Integrante integrante) {
        this.id = id;
        this.time = time;
        this.integrante = integrante;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }

    public Long getId() {
        return id;
    }

    public Time getTime() {
        return time;
    }


    public Integrante getIntegrante() {
        return integrante;
    }

}
