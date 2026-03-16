package br.com.duxusdesafio.service.impl;

import br.com.duxusdesafio.mapper.IntegranteConverter;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.service.api.IntegranteServiceApi;
import br.com.duxusdesafio.util.exception.DefaultException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegranteServiceImpl implements IntegranteServiceApi {

    private final IntegranteRepository repository;

    public IntegranteServiceImpl(IntegranteRepository repository) { this.repository = repository; }

    /**
     * @param integrante
     * @return Integrante, cadastra um integrante
     */
    @Override
    public Integrante cadastrarIntegrante(Integrante integrante) {
        return new IntegranteConverter()
                .toDto(repository.save(new IntegranteConverter().toEntity(integrante)));
    }

    /**
     * @param id
     * @return Integrante, busca um integrante por ID
     */
    @Override
    public Integrante buscarIntegrante(Long id) throws DefaultException {
        return new IntegranteConverter().toDto(repository.findById(id)
                .orElseThrow(() -> new DefaultException(404, "Integrante não encontrado")));
    }

    /**
     * @return List<Integrante>, busca todos os integrantes
     */
    @Override
    public List<Integrante> buscarIntegrantes() {
        return new IntegranteConverter().toDtoList(repository.findAll());
    }

    /**
     * @param idList
     * @return List<Integrante>, busca uma lista de integrantes
     */
    @Override
    public List<Integrante> buscarIntegrantes(List<Long> idList) {
        return new IntegranteConverter().toDtoList(repository.findAllById(idList));
    }
}
