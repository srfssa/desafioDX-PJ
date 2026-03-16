package br.com.duxusdesafio.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class Time {

    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
    private List<Integrante> integrantes;

    public Time() {}

    public Time(LocalDate data) {
        this.data = data;
    }

    public Time(LocalDate data, List<Integrante> integrantes) {
        this.data = data;
        this.integrantes = integrantes;
    }

    public Time(Long id, LocalDate data, List<Integrante> integrantes) {
        this.id = id;
        this.data = data;
        this.integrantes = integrantes;
    }

    public Time(Long id, LocalDate data) {
        this.id = id;
        this.data = data;
    }

    public void setData(LocalDate localDate) {
        this.data = localDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return this.data;
    }

    public List<Integrante> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<Integrante> integrantes) {
        this.integrantes = integrantes;
    }
}
