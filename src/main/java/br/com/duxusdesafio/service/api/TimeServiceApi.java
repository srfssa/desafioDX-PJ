package br.com.duxusdesafio.service.api;

import br.com.duxusdesafio.model.Time;

import java.time.LocalDate;
import java.util.List;

public interface TimeServiceApi {

    Time cadastrarTime(Time time) throws RuntimeException;
    Time buscarTime(Long id) throws RuntimeException;
    List<Time> buscarTimesDaData(LocalDate data) throws RuntimeException;
    List<Time> buscarTodosOsTimes();
}
