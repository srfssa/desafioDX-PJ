package br.com.duxusdesafio.service.api;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.util.exception.DefaultException;

import java.util.List;

public interface IntegranteServiceApi {

    Integrante cadastrarIntegrante(Integrante integrante);
    Integrante buscarIntegrante(Long id) throws DefaultException;
    List<Integrante> buscarIntegrantes();
    List<Integrante> buscarIntegrantes(List<Long> integrantes);
}
