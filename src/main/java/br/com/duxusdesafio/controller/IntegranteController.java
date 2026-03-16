package br.com.duxusdesafio.controller;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.service.api.IntegranteServiceApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integrante")
public class IntegranteController {

    private final IntegranteServiceApi service;

    public IntegranteController(IntegranteServiceApi service) { this.service = service; }

    @PostMapping("/cadastrar")
    public ResponseEntity<Integrante> cadastrarIntegrante(Integrante integrante) {
        Integrante response = service.cadastrarIntegrante(integrante);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
