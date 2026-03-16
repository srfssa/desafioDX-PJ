package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.api.TimeServiceApi;
import br.com.duxusdesafio.util.exception.DefaultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/time")
public class TimeController {

    private final TimeServiceApi service;

    public TimeController(TimeServiceApi service) { this.service = service; }

    @PostMapping("/cadastrar")
    public ResponseEntity<Time> cadastrarTime(@RequestBody Time time) throws DefaultException {
        Time response = service.cadastrarTime(time);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Time> buscarTime(@RequestParam Long id) throws RuntimeException {
        Time response = service.buscarTime(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
