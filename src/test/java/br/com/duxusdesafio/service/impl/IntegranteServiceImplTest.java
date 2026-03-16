package br.com.duxusdesafio.service.impl;

import br.com.duxusdesafio.entity.IntegranteEntity;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.util.exception.DefaultException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IntegranteServiceImplTest {

    @Mock
    private IntegranteRepository repository;

    @InjectMocks
    private IntegranteServiceImpl service;

    @Test
    @DisplayName("Deve cadastrar um integrante com sucesso")
    void cadastrarIntegranteTest() {
        Integrante integranteInput = new Integrante("Carlos", "Google", "Desenvolvedor");

        IntegranteEntity integranteEntitySaved = new IntegranteEntity();
        integranteEntitySaved.setId(1L);
        integranteEntitySaved.setNome("Carlos");
        integranteEntitySaved.setFranquia("Google");
        integranteEntitySaved.setFuncao("Desenvolvedor");

        when(repository.save(any(IntegranteEntity.class))).thenReturn(integranteEntitySaved);

        Integrante resultado = service.cadastrarIntegrante(integranteInput);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Carlos", resultado.getNome());
        verify(repository, times(1)).save(any(IntegranteEntity.class));
    }

    @Test
    @DisplayName("Deve buscar integrante por ID com sucesso")
    void buscarIntegranteTest() throws DefaultException {
        Long id = 1L;
        IntegranteEntity entity = new IntegranteEntity();
        entity.setId(id);
        entity.setNome("Ana");
        entity.setFranquia("Facebook");
        entity.setFuncao("QA");

        when(repository.findById(id)).thenReturn(Optional.of(entity));

        Integrante resultado = service.buscarIntegrante(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Ana", resultado.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção ao buscar integrante inexistente")
    void buscarIntegranteNotFoundTest() {
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DefaultException.class, () -> service.buscarIntegrante(id));
    }

    @Test
    @DisplayName("Deve buscar todos os integrantes")
    void buscarIntegrantesTest() {
        IntegranteEntity i1 = new IntegranteEntity(); i1.setId(1L);
        IntegranteEntity i2 = new IntegranteEntity(); i2.setId(2L);

        when(repository.findAll()).thenReturn(Arrays.asList(i1, i2));

        List<Integrante> resultado = service.buscarIntegrantes();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar integrantes por lista de IDs")
    void buscarIntegrantesPorIdsTest() {
        List<Long> ids = Arrays.asList(1L, 2L);
        IntegranteEntity i1 = new IntegranteEntity(); i1.setId(1L);
        IntegranteEntity i2 = new IntegranteEntity(); i2.setId(2L);

        when(repository.findAllById(ids)).thenReturn(Arrays.asList(i1, i2));

        List<Integrante> resultado = service.buscarIntegrantes(ids);

        assertEquals(2, resultado.size());
        verify(repository, times(1)).findAllById(ids);
    }
}