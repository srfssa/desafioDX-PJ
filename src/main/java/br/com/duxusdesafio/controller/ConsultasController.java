package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.api.ApiService;
import br.com.duxusdesafio.service.api.TimeServiceApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consultar")
public class ConsultasController {

    private final ApiService service;
    private final TimeServiceApi timeService;

    public ConsultasController(ApiService service, TimeServiceApi timeService) {
        this.service = service;
        this.timeService = timeService;
    }

    @GetMapping("/time/data")
    public ResponseEntity<Time> getTimeDataData(@RequestParam String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate dataLocal = LocalDate.parse(data, formatter);
        List<Time> times = timeService.buscarTimesDaData(dataLocal);
        Time time = service.timeDaData(dataLocal, times);

        return ResponseEntity.status(HttpStatus.OK).body(time);
    }

    @GetMapping("/time/mais-comum")
    public ResponseEntity<Time> getTimeMaisComum(@RequestParam LocalDate dataInicial,
                                                       @RequestParam LocalDate dataFinal) {

        List<Time> times = timeService.buscarTodosOsTimes();
        Time time = service.timeMaisComum(dataInicial, dataFinal, times);

        return ResponseEntity.status(HttpStatus.OK).body(time);
    }

    @GetMapping("/integrante/mais-usado")
    public ResponseEntity<Integrante> getIntegranteMaisUsado(@RequestParam LocalDate dataInicial,
                                                             @RequestParam LocalDate dataFinal) {

        List<Time> times = timeService.buscarTodosOsTimes();
        Integrante integrante = service.integranteMaisUsado(dataInicial, dataFinal, times);

        return ResponseEntity.status(HttpStatus.OK).body(integrante);
    }

    @GetMapping("/funcao/mais-comum")
    public ResponseEntity<String> getFuncaoMaisComum(@RequestParam LocalDate dataInicial,
                                                     @RequestParam LocalDate dataFinal) {
        List<Time> times = timeService.buscarTodosOsTimes();
        String funcao = service.funcaoMaisComum(dataInicial, dataFinal, times);

        return ResponseEntity.status(HttpStatus.OK).body(funcao);
    }

    @GetMapping("/franquia/mais-famosa")
    public ResponseEntity<String> getFranquiaMaisFamosa(@RequestParam LocalDate dataInicial,
                                                        @RequestParam LocalDate dataFinal) {
        List<Time> times = timeService.buscarTodosOsTimes();
        String response = service.franquiaMaisFamosa(dataInicial, dataFinal, times);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/franquia/contagem")
    public ResponseEntity<Map<String, Long>> getContagemPorFranquia(@RequestParam LocalDate dataInicial,
                                                                    @RequestParam LocalDate dataFinal) {
        List<Time> times = timeService.buscarTodosOsTimes();
        Map<String, Long> response = service.contagemPorFranquia(dataInicial, dataFinal, times);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/funcao/contagem")
    public ResponseEntity<Map<String, Long>> getContagemPorFuncao(@RequestParam LocalDate dataInicial,
                                                                  @RequestParam LocalDate dataFinal) {
        List<Time> times = timeService.buscarTodosOsTimes();
        Map<String, Long> response = service.contagemPorFuncao(dataInicial, dataFinal, times);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
