package br.com.duxusdesafio.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "time")
public class TimeEntity {

    /**
     * Identificador do time
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Data do time
     */
    private LocalDate data;

    public TimeEntity() {}

    public TimeEntity(LocalDate data) {
        this.setData(data);
    }

    public TimeEntity(Long id, LocalDate data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
