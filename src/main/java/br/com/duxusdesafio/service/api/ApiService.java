package br.com.duxusdesafio.service.api;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;

import java.time.LocalDate;
import java.util.*;

public interface ApiService {

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    Time timeDaData(LocalDate data, List<Time> todosOsTimes);

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes);

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes);

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes);

    /**
     * Vai retornar o time mais comum nos times dentro do período
     */
    Time timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes);

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes);


    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes);

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes);
}
