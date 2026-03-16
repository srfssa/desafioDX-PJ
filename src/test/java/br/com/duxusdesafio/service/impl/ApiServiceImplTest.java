package br.com.duxusdesafio.service.impl;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.service.api.ComposicaoTimeServiceApi;
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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApiServiceImplTest {

    @Mock
    private ComposicaoTimeServiceApi composicaoService;

    @InjectMocks
    private ApiServiceImpl apiService;

    private Integrante integranteA;
    private Integrante integranteB;
    private Integrante integranteC;
    private Time time1;
    private Time time2;
    private Time time3;

    @BeforeEach
    void setUp() {
        integranteA = new Integrante();
        integranteA.setNome("Integrante A");
        integranteA.setFuncao("Desenvolvedor");
        integranteA.setFranquia("Google");

        integranteB = new Integrante();
        integranteB.setNome("Integrante B");
        integranteB.setFuncao("QA");
        integranteB.setFranquia("Google");

        integranteC = new Integrante();
        integranteC.setNome("Integrante C");
        integranteC.setFuncao("Desenvolvedor");
        integranteC.setFranquia("Facebook");

        time1 = new Time();
        time1.setId(1L);
        time1.setData(LocalDate.of(2023, 1, 10));

        time2 = new Time();
        time2.setId(2L);
        time2.setData(LocalDate.of(2023, 1, 15));

        time3 = new Time();
        time3.setId(3L);
        time3.setData(LocalDate.of(2023, 2, 20));
    }

    @Test
    @DisplayName("Deve retornar o Time com seus integrantes para uma data específica")
    void timeDaDataTest() {
        List<Time> todosOsTimes = Arrays.asList(time1, time2);
        
        ComposicaoTime comp1 = new ComposicaoTime(); comp1.setIntegrante(integranteA);
        ComposicaoTime comp2 = new ComposicaoTime(); comp2.setIntegrante(integranteB);
        
        when(composicaoService.buscarComposicoesPorTime(time1))
                .thenReturn(Arrays.asList(comp1, comp2));

        Time resultado = apiService.timeDaData(LocalDate.of(2023, 1, 10), todosOsTimes);

        assertNotNull(resultado);
        assertEquals(time1.getId(), resultado.getId());
        assertEquals(2, resultado.getIntegrantes().size());
        assertTrue(resultado.getIntegrantes().contains(integranteA));
        assertTrue(resultado.getIntegrantes().contains(integranteB));
    }

    @Test
    @DisplayName("Deve retornar null se não houver time na data")
    void timeDaDataNotFoundTest() {
        List<Time> todosOsTimes = Collections.singletonList(time1);
        
        Time resultado = apiService.timeDaData(LocalDate.of(2023, 12, 31), todosOsTimes);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Deve retornar o integrante mais usado no período")
    void integranteMaisUsadoTest() {
        configurarMockComposicao(time1, Arrays.asList(integranteA, integranteB));
        configurarMockComposicao(time2, Collections.singletonList(integranteA));

        List<Time> todosOsTimes = Arrays.asList(time1, time2);
        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate fim = LocalDate.of(2023, 1, 31);

        Integrante resultado = apiService.integranteMaisUsado(inicio, fim, todosOsTimes);

        assertEquals(integranteA, resultado);
    }

    @Test
    @DisplayName("Deve retornar os nomes dos integrantes do time mais comum (composição repetida)")
    void integrantesDoTimeMaisComumTest() {
        configurarMockComposicao(time1, Arrays.asList(integranteA, integranteB));
        configurarMockComposicao(time2, Arrays.asList(integranteA, integranteB));
        configurarMockComposicao(time3, Collections.singletonList(integranteC));

        List<Time> todosOsTimes = Arrays.asList(time1, time2, time3);

        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate fim = LocalDate.of(2023, 3, 1);

        List<String> resultado = apiService.integrantesDoTimeMaisComum(inicio, fim, todosOsTimes);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains("Integrante A"));
        assertTrue(resultado.contains("Integrante B"));
    }

    @Test
    @DisplayName("Deve retornar a função mais comum no período")
    void funcaoMaisComumTest() {
        configurarMockComposicao(time1, Arrays.asList(integranteA, integranteB));
        configurarMockComposicao(time2, Collections.singletonList(integranteA));

        List<Time> todosOsTimes = Arrays.asList(time1, time2);
        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate fim = LocalDate.of(2023, 1, 31);

        String resultado = apiService.funcaoMaisComum(inicio, fim, todosOsTimes);

        assertEquals("Desenvolvedor", resultado);
    }

    @Test
    @DisplayName("Deve retornar a franquia mais famosa no período")
    void franquiaMaisFamosaTest() {
        configurarMockComposicao(time1, Arrays.asList(integranteA, integranteB));
        configurarMockComposicao(time3, Collections.singletonList(integranteC));

        List<Time> todosOsTimes = Arrays.asList(time1, time3);
        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate fim = LocalDate.of(2023, 3, 1);

        String resultado = apiService.franquiaMaisFamosa(inicio, fim, todosOsTimes);

        assertEquals("Google", resultado);
    }

    @Test
    @DisplayName("Deve retornar o objeto Time representando a composição mais comum")
    void timeMaisComumTest() {
        configurarMockComposicao(time1, Arrays.asList(integranteA, integranteB));
        configurarMockComposicao(time2, Arrays.asList(integranteA, integranteB));
        configurarMockComposicao(time3, Collections.singletonList(integranteC));

        List<Time> todosOsTimes = Arrays.asList(time1, time2, time3);
        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate fim = LocalDate.of(2023, 3, 1);

        Time resultado = apiService.timeMaisComum(inicio, fim, todosOsTimes);

        assertNotNull(resultado);
        assertTrue(resultado.getData().equals(time1.getData()) || resultado.getData().equals(time2.getData()));
    }

    @Test
    @DisplayName("Deve retornar contagem de franquias por período")
    void contagemPorFranquiaTest() {
        configurarMockComposicao(time1, Arrays.asList(integranteA, integranteB));
        configurarMockComposicao(time3, Collections.singletonList(integranteC));

        List<Time> todosOsTimes = Arrays.asList(time1, time3);
        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate fim = LocalDate.of(2023, 3, 1);

        Map<String, Long> resultado = apiService.contagemPorFranquia(inicio, fim, todosOsTimes);

        assertEquals(2L, resultado.get("Google"));
        assertEquals(1L, resultado.get("Facebook"));
    }

    @Test
    @DisplayName("Deve retornar contagem de funções por período")
    void contagemPorFuncaoTest() {
        configurarMockComposicao(time1, Arrays.asList(integranteA, integranteB));
        configurarMockComposicao(time3, Collections.singletonList(integranteC));

        List<Time> todosOsTimes = Arrays.asList(time1, time3);
        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate fim = LocalDate.of(2023, 3, 1);

        Map<String, Long> resultado = apiService.contagemPorFuncao(inicio, fim, todosOsTimes);

        assertEquals(2L, resultado.get("Desenvolvedor"));
        assertEquals(1L, resultado.get("QA"));
    }

    @Test
    @DisplayName("Não deve considerar times fora do período")
    void ignorarTimesForaPeriodoTest() {
        configurarMockComposicao(time1, Collections.singletonList(integranteA));

        List<Time> todosOsTimes = Arrays.asList(time1, time3);
        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate fim = LocalDate.of(2023, 1, 31);

        String franquia = apiService.franquiaMaisFamosa(inicio, fim, todosOsTimes);

        assertEquals("Google", franquia);
        
        verify(composicaoService, never()).buscarComposicoesPorTime(time3);
    }

    private void configurarMockComposicao(Time time, List<Integrante> integrantes) {
        List<ComposicaoTime> composicoes = new java.util.ArrayList<>();
        for (Integrante i : integrantes) {
            ComposicaoTime c = new ComposicaoTime();
            c.setIntegrante(i);
            c.setTime(time);
            composicoes.add(c);
        }
        
        lenient().when(composicaoService.buscarComposicoesPorTime(time)).thenReturn(composicoes);
    }
}