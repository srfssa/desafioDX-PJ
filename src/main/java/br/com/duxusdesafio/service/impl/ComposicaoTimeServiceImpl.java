package br.com.duxusdesafio.service.impl;

import br.com.duxusdesafio.entity.ComposicaoTimeEntity;
import br.com.duxusdesafio.entity.IntegranteEntity;
import br.com.duxusdesafio.entity.TimeEntity;
import br.com.duxusdesafio.mapper.ComposicaoTimeConverter;
import br.com.duxusdesafio.mapper.TimeConverter;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.api.ComposicaoTimeServiceApi;
import br.com.duxusdesafio.service.api.TimeServiceApi;
import br.com.duxusdesafio.util.exception.DefaultException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComposicaoTimeServiceImpl implements ComposicaoTimeServiceApi {

    private final ComposicaoTimeRepository repository;
    private final TimeRepository timeRepository;
    private final IntegranteRepository integranteRepository;

    private ComposicaoTimeConverter converter = new ComposicaoTimeConverter();

    public ComposicaoTimeServiceImpl(ComposicaoTimeRepository repository,
                                     TimeRepository timeRepository,
                                     IntegranteRepository integranteRepository) {
        this.repository = repository;
        this.timeRepository = timeRepository;
        this.integranteRepository = integranteRepository;
    }

    /**
     * @param composicaoTime
     * @return ComposicaoTime, composição de integrante em um time
     */
    @Override
    public ComposicaoTime cadastrarComposicaoTime(ComposicaoTime composicaoTime) {
        TimeEntity timeEntity = timeRepository.findById(composicaoTime.getTime().getId())
                .orElseThrow(() -> new DefaultException(404, "Time não encontrado"));

        IntegranteEntity integranteEntity = integranteRepository.findById(composicaoTime.getIntegrante().getId())
                .orElseThrow(() -> new DefaultException(404, "Integrante não encontrado"));

        ComposicaoTimeEntity entity = new ComposicaoTimeEntity();
        entity.setTime(timeEntity);
        entity.setIntegrante(integranteEntity);
        entity = repository.save(entity);
        return converter.toDto(entity);
    }

    /**
     * @param composicaoId
     * @return ComposicaoTIme, busca uma composição de integrante em um time
     */
    @Override
    public ComposicaoTime buscarComposicaoTime(Long composicaoId) {
        return new ComposicaoTimeConverter()
                .toDto(repository.findById(composicaoId)
                        .orElseThrow(() -> new RuntimeException("Composição não encontrada")));

    }

    /**
     * @param time
     * @return List<ComposicaoTime>, busca todas as composições de um time
     */
    @Override
    public List<ComposicaoTime> buscarComposicoesPorTime(Time time) {
        TimeEntity entity = TimeConverter.toEntity(time);
        List<ComposicaoTimeEntity> composicoesTime = repository.findAllByTime(entity);

        return converter.toDtoList(composicoesTime);
    }
}
