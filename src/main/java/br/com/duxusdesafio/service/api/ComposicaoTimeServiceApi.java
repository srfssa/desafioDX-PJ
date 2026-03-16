package br.com.duxusdesafio.service.api;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Time;

import java.util.List;

public interface ComposicaoTimeServiceApi {

    ComposicaoTime cadastrarComposicaoTime(ComposicaoTime composicaoTime);
    ComposicaoTime buscarComposicaoTime(Long composicaoId);
    List<ComposicaoTime> buscarComposicoesPorTime(Time time);
}
