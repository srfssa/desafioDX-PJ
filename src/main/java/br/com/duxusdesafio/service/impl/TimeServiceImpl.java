package br.com.duxusdesafio.service.impl;

import br.com.duxusdesafio.entity.TimeEntity;
import br.com.duxusdesafio.mapper.TimeConverter;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.api.ComposicaoTimeServiceApi;
import br.com.duxusdesafio.service.api.IntegranteServiceApi;
import br.com.duxusdesafio.service.api.TimeServiceApi;
import br.com.duxusdesafio.util.exception.DefaultException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeServiceImpl implements TimeServiceApi {

    private final ComposicaoTimeServiceApi composicaoTimeService;
    private final IntegranteServiceApi integranteService;
    private final TimeRepository repository;

    private TimeConverter converter = new TimeConverter();

    public TimeServiceImpl(ComposicaoTimeServiceApi composicaoTimeService,
                           IntegranteServiceApi integranteService, TimeRepository repository) {
        this.composicaoTimeService = composicaoTimeService;
        this.integranteService = integranteService;
        this.repository = repository;
    }

    /**
     * @param time
     * @return Time, cadastra um time e seus integrantes
     */
    @Transactional
    @Override
    public Time cadastrarTime(Time time) throws RuntimeException {
        TimeEntity entity = converter.toEntity(time);
        TimeEntity timeSaved = repository.save(entity);

        for (Integrante i : time.getIntegrantes()) {
            Integrante integrante = integranteService.buscarIntegrante(i.getId());

            time.setId(timeSaved.getId());
            ComposicaoTime composicao = new ComposicaoTime(time, integrante);

            composicaoTimeService.cadastrarComposicaoTime(composicao);
        }

        return converter.toDto(timeSaved);
    }

    /**
     * @param id
     * @return Time, busca um time por ID
     */
    @Override
    public Time buscarTime(Long id) throws RuntimeException {
        TimeEntity entity = repository.findById(id)
                .orElseThrow(() -> new DefaultException(404, "Time não encontrado"));

        return TimeConverter.toDto(entity);
    }

    /**
     * @param data
     * @return List<Time>, busca todos os times da data
     */
    @Override
    public List<Time> buscarTimesDaData(LocalDate data) {
        return new TimeConverter().toDtoList(repository.findAllByData(data));
    }

    /**
     * @return List<Time>, busca todos os times
     */
    @Override
    public List<Time> buscarTodosOsTimes() {
        return new TimeConverter().toDtoList(repository.findAll());
    }
}
