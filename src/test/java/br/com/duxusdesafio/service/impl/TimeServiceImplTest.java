package br.com.duxusdesafio.service.impl;

import br.com.duxusdesafio.entity.TimeEntity;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.api.ComposicaoTimeServiceApi;
import br.com.duxusdesafio.service.api.IntegranteServiceApi;
import br.com.duxusdesafio.util.exception.DefaultException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimeServiceImplTest {

    @Mock
    private ComposicaoTimeServiceApi composicaoTimeService;

    @Mock
    private IntegranteServiceApi integranteService;

    @Mock
    private TimeRepository repository;

    @InjectMocks
    private TimeServiceImpl service;

    private Time timeInput;
    private Integrante integrante;

    @BeforeEach
    void setUp() {
        integrante = new Integrante();
        integrante.setId(1L);
        integrante.setNome("Fulano");

        timeInput = new Time();
        timeInput.setData(LocalDate.now());
        timeInput.setIntegrantes(Collections.singletonList(integrante));
    }

    @Test
    @DisplayName("Deve cadastrar um time e sua composição com sucesso")
    void cadastrarTimeTest() {
        TimeEntity timeEntitySaved = new TimeEntity();
        timeEntitySaved.setData(timeInput.getData());

        when(repository.save(any(TimeEntity.class))).thenReturn(timeEntitySaved);
        when(integranteService.buscarIntegrante(integrante.getId())).thenReturn(integrante);
        when(composicaoTimeService.cadastrarComposicaoTime(any(ComposicaoTime.class))).thenReturn(new ComposicaoTime());

        Time resultado = service.cadastrarTime(timeInput);

        assertNotNull(resultado);

        verify(repository, times(1)).save(any(TimeEntity.class));
        verify(integranteService, times(1)).buscarIntegrante(1L);
        verify(composicaoTimeService, times(1)).cadastrarComposicaoTime(any(ComposicaoTime.class));
    }

    @Test
    @DisplayName("Deve buscar um time por ID com sucesso")
    void buscarTimeTest() {
        Long id = 1L;
        TimeEntity entity = new TimeEntity();
        entity.setData(LocalDate.now());

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        Time resultado = service.buscarTime(id);

        assertNotNull(resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar time por ID inexistente")
    void buscarTimeNotFoundTest() {
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DefaultException.class, () -> service.buscarTime(id));
    }

    @Test
    @DisplayName("Deve buscar todos os times de uma data específica")
    void buscarTimesDaDataTest() {
        LocalDate data = LocalDate.of(2023, 10, 26);
        TimeEntity entity1 = new TimeEntity(1L, data);
        TimeEntity entity2 = new TimeEntity(2L, data);

        when(repository.findAllByData(data)).thenReturn(Arrays.asList(entity1, entity2));

        List<Time> resultado = service.buscarTimesDaData(data);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAllByData(data);
    }

    @Test
    @DisplayName("Deve buscar todos os times cadastrados")
    void buscarTodosOsTimesTest() {
        TimeEntity entity1 = new TimeEntity(1L, LocalDate.now());
        TimeEntity entity2 = new TimeEntity(2L, LocalDate.now().minusDays(1));

        when(repository.findAll()).thenReturn(Arrays.asList(entity1, entity2));

        List<Time> resultado = service.buscarTodosOsTimes();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertEquals(2L, resultado.get(1).getId());

        verify(repository, times(1)).findAll();
    }
}