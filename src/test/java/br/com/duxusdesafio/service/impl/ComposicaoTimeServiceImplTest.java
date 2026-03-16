package br.com.duxusdesafio.service.impl;

import br.com.duxusdesafio.entity.ComposicaoTimeEntity;
import br.com.duxusdesafio.entity.IntegranteEntity;
import br.com.duxusdesafio.entity.TimeEntity;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.util.exception.DefaultException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComposicaoTimeServiceImplTest {

    @Mock
    private ComposicaoTimeRepository repository;

    @Mock
    private TimeRepository timeRepository;

    @Mock
    private IntegranteRepository integranteRepository;

    @InjectMocks
    private ComposicaoTimeServiceImpl service;

    @Test
    @DisplayName("Deve cadastrar composição de time com sucesso")
    void cadastrarComposicaoTimeTest() {
        Long timeId = 1L;
        Long integranteId = 10L;

        Time time = new Time(); time.setId(timeId);
        Integrante integrante = new Integrante(); integrante.setId(integranteId);

        ComposicaoTime composicaoInput = new ComposicaoTime();
        composicaoInput.setTime(time);
        composicaoInput.setIntegrante(integrante);

        TimeEntity timeEntity = new TimeEntity();
        when(timeRepository.findById(timeId)).thenReturn(Optional.of(timeEntity));

        IntegranteEntity integranteEntity = new IntegranteEntity();
        integranteEntity.setId(integranteId);
        when(integranteRepository.findById(integranteId)).thenReturn(Optional.of(integranteEntity));

        ComposicaoTimeEntity savedEntity = new ComposicaoTimeEntity();
        savedEntity.setTime(timeEntity);
        savedEntity.setIntegrante(integranteEntity);

        when(repository.save(any(ComposicaoTimeEntity.class))).thenReturn(savedEntity);

        ComposicaoTime resultado = service.cadastrarComposicaoTime(composicaoInput);

        assertNotNull(resultado);
        verify(repository, times(1)).save(any(ComposicaoTimeEntity.class));
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar se Time não existir")
    void cadastrarComposicaoTimeTimeNotFoundTest() {
        ComposicaoTime composicaoInput = new ComposicaoTime();
        composicaoInput.setTime(new Time());
        composicaoInput.getTime().setId(1L);

        when(timeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DefaultException.class, () -> service.cadastrarComposicaoTime(composicaoInput));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao cadastrar se Integrante não existir")
    void cadastrarComposicaoTimeIntegranteNotFoundTest() {
        ComposicaoTime composicaoInput = new ComposicaoTime();
        composicaoInput.setTime(new Time());
        composicaoInput.getTime().setId(1L);
        composicaoInput.setIntegrante(new Integrante());
        composicaoInput.getIntegrante().setId(2L);

        when(timeRepository.findById(1L)).thenReturn(Optional.of(new TimeEntity()));
        when(integranteRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(DefaultException.class, () -> service.cadastrarComposicaoTime(composicaoInput));
        verify(repository, never()).save(any());
    }

    @Test
    @DisplayName("Deve buscar composição por ID com sucesso")
    void buscarComposicaoTimeTest() {
        Long id = 1L;
        ComposicaoTimeEntity entity = new ComposicaoTimeEntity();

        entity.setTime(new TimeEntity());
        entity.setIntegrante(new IntegranteEntity());

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        ComposicaoTime resultado = service.buscarComposicaoTime(id);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção se composição não encontrada")
    void buscarComposicaoTimeNotFoundTest() {
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.buscarComposicaoTime(id));
    }

    @Test
    @DisplayName("Deve buscar composições por time")
    void buscarComposicoesPorTimeTest() {
        Time time = new Time();
        time.setId(10L);
        time.setData(LocalDate.now());

        ComposicaoTimeEntity entity = new ComposicaoTimeEntity();
        entity.setTime(new TimeEntity());
        entity.setIntegrante(new IntegranteEntity());

        when(repository.findAllByTime(any(TimeEntity.class))).thenReturn(Collections.singletonList(entity));

        List<ComposicaoTime> resultado = service.buscarComposicoesPorTime(time);

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
    }
}