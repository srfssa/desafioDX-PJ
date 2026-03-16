package br.com.duxusdesafio.service.impl;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.api.ApiService;
import br.com.duxusdesafio.service.api.ComposicaoTimeServiceApi;
import br.com.duxusdesafio.service.api.TimeServiceApi;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados solicitados no desafio!
 */
@Service
public class ApiServiceImpl implements ApiService {

    private final ComposicaoTimeServiceApi composicaoService;
    private final TimeServiceApi timeService;

    public ApiServiceImpl(ComposicaoTimeServiceApi composicaoService, TimeServiceApi timeService) {
        this.composicaoService = composicaoService;
        this.timeService = timeService;
    }

    /**
     * @param data
     * @param todosOsTimes
     * @return Time, com a composição do time daquela data
     */
    @Override
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        Time time = todosOsTimes.stream()
                .filter(t -> t.getData().equals(data))
                .findFirst().orElse(null);

        if (time == null) return null;

        List<Integrante> integrantes = composicaoService.buscarComposicoesPorTime(time)
                .stream().map(ComposicaoTime::getIntegrante).collect(Collectors.toList());

        return new Time(time.getId(), data, integrantes);
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @param todosOsTimes
     * @return integrante que estiver presente na maior quantidade de times
     */
    @Override
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<Integrante, Integer> contagem = new HashMap<>();
        for (Time time : todosOsTimes) {
            if (!dentroDoPeriodo(time.getData(), dataInicial, dataFinal))
                continue;

//            Time timeRecorded = timeService.buscarTime(time.getId());
            List<ComposicaoTime> composicoes = composicaoService.buscarComposicoesPorTime(time);

            for (ComposicaoTime c : composicoes) {
                Integrante integrante = c.getIntegrante();
                contagem.put(integrante, contagem.getOrDefault(integrante, 0) + 1);
            }
        }

        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @param todosOsTimes
     * @return List com os nomes dos integrantes do time mais comum
     */
    @Override
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<String, Integer> contagem = new HashMap<>();

        for (Time time : todosOsTimes) {
            if (!dentroDoPeriodo(time.getData(), dataInicial, dataFinal)) {
                continue;
            }

            List<String> integrantes = composicaoService.buscarComposicoesPorTime(time).stream()
                    .map(c -> c.getIntegrante().getNome())
                    .sorted().collect(Collectors.toList());

            String chave = String.join("|", integrantes);

            contagem.put(chave, contagem.getOrDefault(chave, 0) + 1);
        }

        String timeMaisComum = contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return timeMaisComum == null
                ? Collections.emptyList()
                : Arrays.asList(timeMaisComum.split("\\|"));
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @param todosOsTimes
     * @return String com função mais comum nos times dentro do período
     */
    @Override
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<String, Integer> contagem = new HashMap<>();

        for (Time time : todosOsTimes) {
            if (!dentroDoPeriodo(time.getData(), dataInicial, dataFinal))
                continue;

            List<ComposicaoTime> composicaoTimes = composicaoService.buscarComposicoesPorTime(time);

            for (ComposicaoTime composicaoTime : composicaoTimes) {
                String funcao = composicaoTime.getIntegrante().getFuncao();

                contagem.put(funcao, contagem.getOrDefault(funcao, 0) + 1);
            }
        }

        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @param todosOsTimes
     * @return String com time mais comum nos times dentro do período
     */
    @Override
    public Time timeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        Map<String, Long> contagemTimes = new HashMap<>();
        Map<String, Time> referenciaTimes = new HashMap<>();

        for (Time time : todosOsTimes) {

            if (!dentroDoPeriodo(time.getData(), dataInicial, dataFinal)) {
                continue;
            }

            List<ComposicaoTime> composicoes =
                    composicaoService.buscarComposicoesPorTime(time);

            List<String> integrantes = composicoes.stream()
                    .map(c -> c.getIntegrante().getNome())
                    .sorted()
                    .collect(Collectors.toList());

            String chaveTime = String.join("-", integrantes);

            contagemTimes.put(
                    chaveTime,
                    contagemTimes.getOrDefault(chaveTime, 0L) + 1
            );

            referenciaTimes.putIfAbsent(chaveTime, time);
        }

        String chaveMaisComum = contagemTimes.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (chaveMaisComum == null) {
            return null;
        }

        return referenciaTimes.get(chaveMaisComum);
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @param todosOsTimes
     * @return String com o nome da Franquia mais comum nos times dentro do período
     */
    @Override
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        Map<String, Integer> contagem = new HashMap<>();

        for (Time time : todosOsTimes) {
            if (!dentroDoPeriodo(time.getData(), dataInicial, dataFinal))
                continue;

            List<ComposicaoTime> composicaoTimes = composicaoService.buscarComposicoesPorTime(time);

            for (ComposicaoTime composicaoTime : composicaoTimes) {
                String franquia = composicaoTime.getIntegrante().getFranquia();

                contagem.put(franquia, contagem.getOrDefault(franquia, 0) + 1);
            }
        }

        return contagem.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @param todosOsTimes
     * @return Map com o número (quantidade) de Franquias dentro do período
     */
    @Override
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<String, Long> contagem = new HashMap<>();

        for (Time time : todosOsTimes) {
            if (!dentroDoPeriodo(time.getData(), dataInicial, dataFinal))
                continue;

            List<ComposicaoTime> composicaoTimes = composicaoService.buscarComposicoesPorTime(time);

            for (ComposicaoTime composicaoTime : composicaoTimes) {
                String franquia = composicaoTime.getIntegrante().getFranquia();

                contagem.put(franquia, contagem.getOrDefault(franquia, 0L) + 1);
            }
        }

        return contagem;
    }

    /**
     * @param dataInicial
     * @param dataFinal
     * @param todosOsTimes
     * @return Map com o número (quantidade) de Funções dentro do período
     */
    @Override
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        Map<String, Long> contagem = new HashMap<>();

        for (Time time : todosOsTimes) {
            if (!dentroDoPeriodo(time.getData(), dataInicial, dataFinal))
                continue;

            List<ComposicaoTime> composicaoTimes = composicaoService.buscarComposicoesPorTime(time);

            for (ComposicaoTime composicaoTime : composicaoTimes) {
                String funcao = composicaoTime.getIntegrante().getFuncao();

                contagem.put(funcao, contagem.getOrDefault(funcao, 0L) + 1);
            }
        }

        return contagem;
    }

    private boolean dentroDoPeriodo(LocalDate data, LocalDate inicio, LocalDate fim) {
        if (inicio != null && data.isBefore(inicio)) return false;

        if (fim != null && data.isAfter(fim)) return false;

        return true;
    }

}
