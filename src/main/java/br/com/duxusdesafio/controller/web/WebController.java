package br.com.duxusdesafio.controller.web;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.api.IntegranteServiceApi;
import br.com.duxusdesafio.service.api.TimeServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/web")
public class WebController {

    private final IntegranteServiceApi integranteService;
    private final TimeServiceApi timeService;

    public WebController(IntegranteServiceApi integranteService,
                         TimeServiceApi timeService) {
        this.integranteService = integranteService;
        this.timeService = timeService;
    }

    @GetMapping({"/", ""})
    public String home() {
        return "index";
    }

    @GetMapping("/integrantes")
    public String telaIntegrantes(Model model) {

        model.addAttribute("integrantes", integranteService.buscarIntegrantes());
        model.addAttribute("novoIntegrante", new Integrante());

        return "integrantes";
    }

    @GetMapping("/times")
    public String telaTimes(Model model) {

        model.addAttribute("integrantes", integranteService.buscarIntegrantes());

        return "time";
    }

    @GetMapping("/consulta")
    public String telaConsulta(Model model) {
        model.addAttribute("integrante", new Integrante());
        model.addAttribute("time", new Time());
        model.addAttribute("data", LocalDate.now());
        return "consulta";
    }
}