package br.com.duxusdesafio.controller.web;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.model.enums.InfoMessageEnum;
import br.com.duxusdesafio.service.api.ApiService;
import br.com.duxusdesafio.service.api.TimeServiceApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/web/consulta")
public class ConsultaWebController {

    private final ApiService service;
    private final TimeServiceApi timeService;

    public ConsultaWebController(ApiService service, TimeServiceApi timeService) {
        this.service = service;
        this.timeService = timeService;
    }

    @PostMapping("/integrante/mais-usado")
    public String getIntegranteMaisComum(@RequestParam String dataInicial,
                                         @RequestParam String dataFinal, Model model) {
        LocalDate inicio = !dataInicial.isEmpty() ? LocalDate.parse(dataInicial) : LocalDate.now();
        LocalDate fim = !dataFinal.isEmpty() ? LocalDate.parse(dataFinal) : LocalDate.now().plusDays(7);

        List<Time> times = timeService.buscarTodosOsTimes();

        Integrante integrante = service.integranteMaisUsado(inicio, fim, times);

        model.addAttribute("integrante", integrante);

        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);

        String msg = integrante == null ? InfoMessageEnum.MSG_REGISTRO_PERIODO.label : null;
        model.addAttribute("msgIntegranteComum", msg);

        return "consulta";
    }

    @PostMapping("/time/data")
    public String getTimeDaData(@RequestParam String data, Model model) {
        LocalDate dataConsulta = !data.isEmpty() ? LocalDate.parse(data) : LocalDate.now();

        List<Time> times = timeService.buscarTodosOsTimes();

        Time time = service.timeDaData(dataConsulta, times);

        model.addAttribute("time", time);

        String msg = time == null ? InfoMessageEnum.MSG_REGISTRO_DATA.label : null;
        model.addAttribute("msgTimeData", msg);

        return "consulta";
    }

    @PostMapping("/time/mais-comum")
    public String getTimeMaisComum(@RequestParam String dataInicial,
                                   @RequestParam String dataFinal, Model model) {
        LocalDate inicio = !dataInicial.isEmpty() ? LocalDate.parse(dataInicial) : LocalDate.now();
        LocalDate fim = !dataFinal.isEmpty() ? LocalDate.parse(dataFinal) : LocalDate.now().plusDays(7);

        List<Time> times = timeService.buscarTodosOsTimes();

        Time time = service.timeMaisComum(inicio, fim, times);

        model.addAttribute("timeComum", time);

        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);

        String msg = time == null ? InfoMessageEnum.MSG_REGISTRO_PERIODO.label : null;
        model.addAttribute("msgTimeComum", msg);

        return "consulta";
    }

    @PostMapping("/funcao/mais-comum")
    public String getFuncaoMaisComum(@RequestParam String dataInicial,
                                     @RequestParam String dataFinal, Model model) {
        LocalDate inicio = !dataInicial.isEmpty() ? LocalDate.parse(dataInicial) : LocalDate.now();
        LocalDate fim = !dataFinal.isEmpty() ? LocalDate.parse(dataFinal) : LocalDate.now().plusDays(7);

        List<Time> times = timeService.buscarTodosOsTimes();

        String funcao = service.funcaoMaisComum(inicio, fim, times);

        model.addAttribute("funcaoComum", funcao);

        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);

        String msg = funcao == null ? InfoMessageEnum.MSG_REGISTRO_PERIODO.label : null;
        model.addAttribute("msgFuncaoComum", msg);

        return "consulta";
    }

    @PostMapping("/franquia/mais-famosa")
    public String getFranquiaMaisFamosa(@RequestParam String dataInicial,
                                        @RequestParam String dataFinal, Model model) {
        LocalDate inicio = !dataInicial.isEmpty() ? LocalDate.parse(dataInicial) : LocalDate.now();
        LocalDate fim = !dataFinal.isEmpty() ? LocalDate.parse(dataFinal) : LocalDate.now().plusDays(7);

        List<Time> times = timeService.buscarTodosOsTimes();

        String franquia = service.franquiaMaisFamosa(inicio, fim, times);

        model.addAttribute("franquiaComum", franquia);

        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);

        String msg = franquia == null ? InfoMessageEnum.MSG_REGISTRO_PERIODO.label : null;
        model.addAttribute("msgFranquiaComum", msg);

        return "consulta";
    }

    @PostMapping("/franquia/contagem")
    public String getFranquiaContagem(@RequestParam String dataInicial,
                                      @RequestParam String dataFinal, Model model) {
        LocalDate inicio = !dataInicial.isEmpty() ? LocalDate.parse(dataInicial) : LocalDate.now();
        LocalDate fim = !dataFinal.isEmpty() ? LocalDate.parse(dataFinal) : LocalDate.now().plusDays(7);

        List<Time> times = timeService.buscarTodosOsTimes();

        Map<String, Long> franquia = service.contagemPorFranquia(inicio, fim, times);

        model.addAttribute("contagemFranquia", franquia);

        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);

        String msg = franquia == null || franquia.isEmpty() ? InfoMessageEnum.MSG_REGISTRO_PERIODO.label : null;
        model.addAttribute("msgContagemFranquia", msg);

        return "consulta";
    }

    @PostMapping("/funcao/contagem")
    public String getFuncaoContagem(@RequestParam String dataInicial,
                                    @RequestParam String dataFinal, Model model) {
        LocalDate inicio = !dataInicial.isEmpty() ? LocalDate.parse(dataInicial) : LocalDate.now();
        LocalDate fim = !dataFinal.isEmpty() ? LocalDate.parse(dataFinal) : LocalDate.now().plusDays(7);

        List<Time> times = timeService.buscarTodosOsTimes();

        Map<String, Long> funcao = service.contagemPorFuncao(inicio, fim, times);

        model.addAttribute("contagemFuncao", funcao);

        model.addAttribute("dataInicial", dataInicial);
        model.addAttribute("dataFinal", dataFinal);

        String msg = funcao == null || funcao.isEmpty() ? InfoMessageEnum.MSG_REGISTRO_PERIODO.label : null;
        model.addAttribute("msgContagemFuncao", msg);

        return "consulta";
    }
}
