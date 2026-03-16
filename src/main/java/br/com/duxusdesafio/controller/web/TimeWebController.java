package br.com.duxusdesafio.controller.web;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.model.enums.InfoMessageEnum;
import br.com.duxusdesafio.service.api.ComposicaoTimeServiceApi;
import br.com.duxusdesafio.service.api.IntegranteServiceApi;
import br.com.duxusdesafio.service.api.TimeServiceApi;
import br.com.duxusdesafio.util.exception.DefaultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/web/time")
public class TimeWebController {

    private final TimeServiceApi service;
    private final IntegranteServiceApi integranteService;
    private final ComposicaoTimeServiceApi composicaoService;

    public TimeWebController(TimeServiceApi service,
                             IntegranteServiceApi integranteService,
                             ComposicaoTimeServiceApi composicaoService) {
        this.service = service;
        this.integranteService = integranteService;
        this.composicaoService = composicaoService;
    }

    @PostMapping("/cadastrar")
    public String cadastrarTime(@RequestParam String data,
                                @RequestParam(required = false) List<Long> integrantes, Model model) throws DefaultException {

        Time time = new Time();
        time.setData(LocalDate.parse(data));

        if (integrantes == null || integrantes.isEmpty()) {
            model.addAttribute("msgTime", InfoMessageEnum.MSG_REGISTRO_TIME.label);
            model.addAttribute("integrantes", integranteService.buscarIntegrantes());

            return "time";
        }

        List<Integrante> integrantesList = integranteService.buscarIntegrantes(integrantes);
        time.setIntegrantes(integrantesList);

        service.cadastrarTime(time);

        return "redirect:/web/times";
    }

    @GetMapping("/buscar")
    public ResponseEntity<Time> buscarTime(Long id) throws RuntimeException {
        Time response = service.buscarTime(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
