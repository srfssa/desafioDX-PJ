package br.com.duxusdesafio.controller.web;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.api.ApiService;
import br.com.duxusdesafio.service.api.IntegranteServiceApi;
import br.com.duxusdesafio.service.api.TimeServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/web/integrante")
public class IntegranteWebController {

    private final IntegranteServiceApi service;
    private final ApiService apiService;
    private final TimeServiceApi timeService;

    public IntegranteWebController(IntegranteServiceApi service, ApiService apiService, TimeServiceApi timeService) {
        this.service = service;
        this.apiService = apiService;
        this.timeService = timeService;
    }

    @PostMapping("/cadastrar")
    public String cadastrarIntegrante(Integrante integrante) {
        service.cadastrarIntegrante(integrante);
        return "redirect:/web/integrantes";
    }
}
