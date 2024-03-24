package br.com.fiap.restaurantes.service;

import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Avaliacao;
import br.com.fiap.restaurantes.entities.Restaurante;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.repository.RestauranteRepository;
import br.com.fiap.restaurantes.utils.AvaliacaoHelper;
import br.com.fiap.restaurantes.utils.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RestauranteServiceTest {

    private RestauranteServiceImpl restauranteService;

    @Mock
    private RestauranteRepository restauranteRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        restauranteService = new RestauranteServiceImpl(restauranteRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveAtualizarMesasDisponiveis() {

        var id = new Random().nextLong();
        var reservaDTO = ReservaHelper.gerarReservaDTO();
        var restauranteDTO = restauranteMockDTO();
        var restauranteAtualizado = restauranteAtualizadoMock();

        when(restauranteRepository.getReferenceById(restauranteDTO.id())).thenReturn(restauranteMock());
        when(restauranteRepository.save(any(Restaurante.class))).thenAnswer(i -> i.getArgument(0));

        restauranteService.atualizaMesasDisponiveis(restauranteDTO, reservaDTO);

        verify(restauranteRepository, times(1)).save(any(Restaurante.class));
    }

    private RestauranteDTO restauranteMockDTO() {
        return new RestauranteDTO(1L, "test", "test",
                new TipoCozinha(1L, "nome"), "18", "23", 10, 10);
    }
    private Restaurante restauranteMock() {
        return new Restaurante(1L, "test", "test",
                new TipoCozinha(1L, "nome"), "18", "23", 10, 10);
    }
    private Restaurante restauranteAtualizadoMock() {
        return new Restaurante(1L, "test", "test",
                new TipoCozinha(1L, "nome"), "18", "23", 10, 8);
    }

}