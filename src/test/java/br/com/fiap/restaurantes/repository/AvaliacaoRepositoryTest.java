package br.com.fiap.restaurantes.repository;

import br.com.fiap.restaurantes.entities.Avaliacao;
import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.entities.Restaurante;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.repository.AvaliacaoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class AvaliacaoRepositoryTest {
    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Test
    void devePermitirRegistrarAvaliacao() {
        // Arrange
        var avaliacao = gerarAvaliacao();
        when(avaliacaoRepository.save(any(Avaliacao.class))).thenReturn(avaliacao);
        // Act
        var avaliacaoArmazenada = avaliacaoRepository.save(avaliacao);
        // Assert
        verify(avaliacaoRepository, times(1)).save(avaliacao);
        assertThat(avaliacaoArmazenada)
                .isInstanceOf(Avaliacao.class)
                .isNotNull()
                .isEqualTo(avaliacao);
        assertThat(avaliacaoArmazenada)
                .extracting(Avaliacao::getId)
                .isEqualTo(avaliacao.getId());
        assertThat(avaliacaoArmazenada)
                .extracting(Avaliacao::getCliente)
                .isEqualTo(avaliacao.getCliente());
        assertThat(avaliacaoArmazenada)
                .extracting(Avaliacao::getRestaurante)
                .isEqualTo(avaliacao.getRestaurante());
        assertThat(avaliacaoArmazenada)
                .extracting(Avaliacao::getDescricao)
                .isEqualTo(avaliacao.getDescricao());
        assertThat(avaliacaoArmazenada)
                .extracting(Avaliacao::getDataAvaliacao)
                .isEqualTo(avaliacao.getDataAvaliacao());
    }

    @Test
    void devePermitirConsultarAvaliacao() {
        // Arrange
        var id = new Random().nextLong();
        var avaliacao = gerarAvaliacao();
        avaliacao.setId(id);

        when(avaliacaoRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(avaliacao));
        // Act
        var avaliacaoOptional = avaliacaoRepository.findById(id);
        // Assert
        verify(avaliacaoRepository, times(1)).findById(id);
        assertThat(avaliacaoOptional)
                .isPresent()
                .containsSame(avaliacao);
        avaliacaoOptional.ifPresent(avaliacaoArmazenada -> {
            assertThat(avaliacaoArmazenada.getId())
                    .isEqualTo(avaliacao.getId());
            assertThat(avaliacaoArmazenada.getCliente())
                    .isEqualTo(avaliacao.getCliente());
            assertThat(avaliacaoArmazenada.getRestaurante())
                    .isEqualTo(avaliacao.getRestaurante());
            assertThat(avaliacaoArmazenada)
                    .extracting(Avaliacao::getDescricao)
                    .isEqualTo(avaliacao.getDescricao());
            assertThat(avaliacaoArmazenada)
                    .extracting(Avaliacao::getDataAvaliacao)
                    .isEqualTo(avaliacao.getDataAvaliacao());


        });
    }

    @Test
    void devePermitirApagarAvaliacao() {
        // Arrange
        var id = new Random().nextLong();
        doNothing().when(avaliacaoRepository).deleteById(id);
        // Act
        avaliacaoRepository.deleteById(id);
        // Assert
        verify(avaliacaoRepository, times(1)).deleteById(id);
    }

    @Test
    void devePermitirListarAvaliacoes() {
        // Arrange
        var avaliacao1 = gerarAvaliacao();
        var avaliacao2 = gerarAvaliacao();
        var avaliacaoList = Arrays.asList(avaliacao1, avaliacao2);

        when(avaliacaoRepository.findAll()).thenReturn(avaliacaoList);

        // Act
        var resultado = avaliacaoRepository.findAll();

        // Assert
        verify(avaliacaoRepository, times(1)).findAll();
        assertThat(resultado)
                .hasSize(2)
                .containsExactlyInAnyOrder(avaliacao1, avaliacao2);
    }

    private Avaliacao gerarAvaliacao() {
        TipoCozinha tipoCozinhaDTO = new TipoCozinha(1L, "tipo cozinha");
        Cliente clienteDTO = new Cliente(1L,"teste","email",213213L);
        Restaurante restauranteDTO = new Restaurante(1L, "teste","endereço",tipoCozinhaDTO,
                "00:00", "09:00",10,6);

        return Avaliacao.builder()
                .cliente(clienteDTO)
                .restaurante(restauranteDTO)
                .descricao("Restaurante legal")
                .build();
    }
}
