package br.com.fiap.restaurantes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.fiap.restaurantes.dto.ReservaDTO;
import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Reserva;
import br.com.fiap.restaurantes.entities.Restaurante;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.exception.AvaliacaoNotFoundException;
import br.com.fiap.restaurantes.repository.ReservaRepository;
import br.com.fiap.restaurantes.repository.RestauranteRepository;
import br.com.fiap.restaurantes.utils.ReservaHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Random;

public class ReservaServiceTest {

    private ReservaService reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    RestauranteService restauranteService;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        reservaService = new ReservaService(reservaRepository, restauranteService);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void deveRegistrarReserva() {
        var reserva = ReservaHelper.gerarReservaDTO();
        RestauranteDTO restauranteDTO = restauranteMockDTO();
        Restaurante restaurante = restauranteMock();

        when(restauranteService.findById(any())).thenReturn(restauranteDTO);
        when(restauranteRepository.getReferenceById(anyLong())).thenReturn(restaurante);
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(i -> i.getArgument(0));

        var reservaArmazenada = reservaService.save(reserva);

        assertThat(reservaArmazenada).isInstanceOf(ReservaDTO.class).isNotNull();
        assertThat(reservaArmazenada.dataReserva()).isEqualTo(reserva.dataReserva());
        assertThat(reservaArmazenada.id()).isEqualTo(reserva.id());
        assertThat(reservaArmazenada.cliente()).isEqualTo(reserva.cliente());
        assertThat(reservaArmazenada.restaurante()).isEqualTo(reserva.restaurante());
        assertThat(reservaArmazenada).isEqualTo(reserva);
        verify(reservaRepository, times(1)).save(any(Reserva.class));
        verify(restauranteService, times(1)).findById(restauranteDTO.id());
    }

    @Test
    void deveRecusarReservaQuandoNaoTemMesaDisponivel() {

        var reservaDTO = ReservaHelper.gerarReservaDTO();
        RestauranteDTO restauranteMockDTO = restauranteDTOMockCom1MesaDisponivel();
        Restaurante restauranteMock = restauranteMockCom1MesaDisponivel();

        when(restauranteService.findById(any())).thenReturn(restauranteMockDTO);
        when(restauranteRepository.getReferenceById(1L)).thenReturn(restauranteMock);
        when(restauranteRepository.save(any(Restaurante.class))).thenAnswer(i -> i.getArgument(0));
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(i -> i.getArgument(0));

        assertThatThrownBy(() -> reservaService.save(reservaDTO))
                .isInstanceOf(AvaliacaoNotFoundException.ControllerNotFoundException.class)
                .hasMessage("Nao ha mesas disponiveis para este restaurante na data de: " + reservaDTO.dataReserva());
        verify(restauranteService, times(1)).findById(restauranteMockDTO.id());
    }

    @Test
    void deveRecusarReservaQuandoForaHorarioRestaurante() {

        var reservaDTO = ReservaHelper.gerarReservaDTOForaHorario();
        RestauranteDTO restauranteMockDTO = restauranteMockDTO();
        Restaurante restauranteMock = restauranteMock();

        when(restauranteService.findById(any())).thenReturn(restauranteMockDTO);

        assertThatThrownBy(() -> reservaService.save(reservaDTO))
                .isInstanceOf(AvaliacaoNotFoundException.ControllerNotFoundException.class)
                .hasMessage("Horario da reserva incompativel com restaurante!!!");
        verify(restauranteService, times(1)).findById(any());
    }

    @Test
    void deveBuscarReserva() {
        var id = new Random().nextLong();
        Reserva reserva = ReservaHelper.gerarReserva();
        when(reservaRepository.findById(anyLong())).thenReturn(Optional.of(reserva));


        var reservaObtida = reservaService.findById(id);

        verify(reservaRepository, times(1)).findById(anyLong());
        assertThat(reservaObtida).isEqualTo(reserva);
    }

    @Test
    void deveGerarExcecaoAoBuscarReservaComIdInexistente() {
        var id = new Random().nextLong();

        when(reservaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reservaService.findById(id))
                .isInstanceOf(AvaliacaoNotFoundException.ControllerNotFoundException.class)
                .hasMessage("Reserva não encontrada!!!");
        verify(reservaRepository, times(1)).findById(id);
    }

    @Test
    void deveAlterarReserva() {
        var id = new Random().nextLong();
        RestauranteDTO restauranteDTO = restauranteMockDTO();

        var reservaAntiga = ReservaHelper.gerarReserva();

        var novaReserva = new ReservaDTO(reservaAntiga.getId(), reservaAntiga.getCliente(), reservaAntiga.getRestaurante(),
                reservaAntiga.getDataReserva(), 10, reservaAntiga.getHoraInicio(), reservaAntiga.getHoraFinal());

        when(reservaRepository.getReferenceById(anyLong())).thenReturn(reservaAntiga);
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(i -> i.getArgument(0));

        var reservaObtida = reservaService.update(id, novaReserva);

        assertThat(reservaObtida).isInstanceOf(ReservaDTO.class);
        assertThat(reservaObtida.id()).isEqualTo(reservaAntiga.getId());
        assertThat(reservaObtida.numeroPessoas()).isEqualTo(novaReserva.numeroPessoas());
        verify(reservaRepository, times(1)).save(any(Reserva.class));
    }

    @Test
    void deveGerarExcecaoQuandoAlterarReservaComIdNaoEncontrado() {
        var id = new Random().nextLong();
        var novaReserva = ReservaHelper.gerarReservaDTO();

        assertThatThrownBy(() -> reservaService.update(id, novaReserva))
                .isInstanceOf(AvaliacaoNotFoundException.ControllerNotFoundException.class)
                .hasMessage("Reserva não encontrada!!!");
        verify(reservaRepository, never()).save(any(Reserva.class));
    }

    @Test
    void devePermitirApagarReserva() {
        var id = 2L;
        reservaService.delete(id);

        verify(reservaRepository).deleteById(id);
    }

    private RestauranteDTO restauranteMockDTO() {
        return new RestauranteDTO(1L, "test", "test",
                new TipoCozinha(1L, "nome"), "18", "23", 10, 10);
    }
    private Restaurante restauranteMock() {
        return new Restaurante(1L, "test", "test",
                new TipoCozinha(1L, "nome"), "18", "23", 10, 10);
    }

    private RestauranteDTO restauranteDTOMockCom1MesaDisponivel() {
        return new RestauranteDTO(1L, "test", "test",
                new TipoCozinha(1L, "nome"), "18", "23", 1, 1);
    }
    private Restaurante restauranteMockCom1MesaDisponivel() {
        return new Restaurante(1L, "test", "test",
                new TipoCozinha(1L, "nome"), "18", "23", 1, 1);
    }
}
