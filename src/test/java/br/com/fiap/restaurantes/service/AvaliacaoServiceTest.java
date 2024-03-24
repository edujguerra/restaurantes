
package br.com.fiap.restaurantes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;

import br.com.fiap.restaurantes.entities.Avaliacao;
import br.com.fiap.restaurantes.exception.AvaliacaoNotFoundException;
import br.com.fiap.restaurantes.repository.AvaliacaoRepository;
import br.com.fiap.restaurantes.utils.AvaliacaoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class AvaliacaoServiceTest {
  private AvaliacaoService avaliacaoService;
  @Mock
  private AvaliacaoRepository avaliacaoRepository;
  AutoCloseable openMocks;

  @BeforeEach
  void setUp() {
    openMocks = MockitoAnnotations.openMocks(this);
    avaliacaoService = new AvaliacaoServiceImpl(avaliacaoRepository);
  }

  @AfterEach
  void tearDown() throws Exception {
    openMocks.close();
  }

  @Nested
  class RegistrarAvaliacao {

    @Test
    void devePermitirRegistrarAvaliacao() {
      var avaliacao = AvaliacaoHelper.gerarAvaliacao();
      when(avaliacaoRepository.save(any(Avaliacao.class)))
          .thenAnswer(i -> i.getArgument(0));

      var avaliacaoArmazenada = avaliacaoService.criarAvaliacao(avaliacao);

      assertThat(avaliacaoArmazenada)
          .isInstanceOf(Avaliacao.class)
          .isNotNull();
      assertThat(avaliacaoArmazenada.getDataAvaliacao())
          .isEqualTo(avaliacao.getDataAvaliacao());
      assertThat(avaliacaoArmazenada.getId())
          .isNotNull();
      assertThat(avaliacaoArmazenada.getCliente())
          .isEqualTo(avaliacao.getCliente());
      verify(avaliacaoRepository, times(1)).save(avaliacao);
    }
  }

  @Nested
  class BuscarAvaliacao {

    @Test
    void devePermitirBuscarAvaliacao() {
      var id = new Random().nextLong();
      var avaliacao = AvaliacaoHelper.gerarAvaliacao();
      when(avaliacaoRepository.findById(any(Long.class)))
          .thenReturn(Optional.of(avaliacao));

      var avaliacaoObtida = avaliacaoService.buscarAvaliacao(id);

      verify(avaliacaoRepository, times(1))
          .findById(id);
      assertThat(avaliacaoObtida)
          .isEqualTo(avaliacao);
      assertThat(avaliacaoObtida.getId())
          .isEqualTo(avaliacao.getId());
      assertThat(avaliacaoObtida.getDataAvaliacao())
          .isEqualTo(avaliacao.getDataAvaliacao());
      assertThat(avaliacaoObtida.getDescricao())
          .isEqualTo(avaliacao.getDescricao());
      assertThat(avaliacaoObtida.getNota())
          .isEqualTo(avaliacao.getNota());
    }

    @Test
    void deveGerarExcecao_QuandoBuscarAvaliacao_IdNaoExistente() {
      var id = new Random().nextLong();

      when(avaliacaoRepository.findById(any(Long.class)))
          .thenReturn(Optional.empty());

      assertThatThrownBy(() -> avaliacaoService.buscarAvaliacao(id))
          .isInstanceOf(AvaliacaoNotFoundException.class)
          .hasMessage("avaliacao não encontrada");
      verify(avaliacaoRepository, times(1)).findById(id);
    }
  }

  @Nested
  class AlterarAvaliacao {

    @Test
    void devePermirirAlterarAvaliacao() {
      var id = new Random().nextLong();
      var avaliacaoAntiga = AvaliacaoHelper.gerarAvaliacao();
      avaliacaoAntiga.setId(id);
      var avaliacaoNova = avaliacaoAntiga;
      avaliacaoNova.setDescricao("abcd");

      when(avaliacaoRepository.findById(any(Long.class)))
          .thenReturn(Optional.of(avaliacaoAntiga));

      when(avaliacaoRepository.save(any(Avaliacao.class)))
          .thenAnswer(i -> i.getArgument(0));

      var avaliacaoObtida = avaliacaoService
          .alterarAvaliacao(id, avaliacaoNova);

      assertThat(avaliacaoObtida)
          .isInstanceOf(Avaliacao.class)
          .isNotNull();
      assertThat(avaliacaoObtida.getId())
          .isEqualTo(avaliacaoNova.getId());
      assertThat(avaliacaoObtida.getDataAvaliacao())
              .isEqualTo(avaliacaoNova.getDataAvaliacao());
      assertThat(avaliacaoObtida.getRestaurante())
              .isEqualTo(avaliacaoNova.getRestaurante());
      assertThat(avaliacaoObtida.getCliente())
              .isEqualTo(avaliacaoNova.getCliente());
      assertThat(avaliacaoObtida.getDescricao())
              .isEqualTo(avaliacaoNova.getDescricao());
      verify(avaliacaoRepository, times(1)).save(any(Avaliacao.class));
    }

    @Test
    void deveGerarExcecao_QuandoAlterarAvaliacao_IdNaoCoincide() {
      var id = new Random().nextLong();

      var avaliacaoAntiga = AvaliacaoHelper.gerarAvaliacao();
      avaliacaoAntiga.setId(id);
      var avaliacaoNova = avaliacaoAntiga.toBuilder().build();
      avaliacaoNova.setId(new Random().nextLong());

      assertThatThrownBy(
          () -> avaliacaoService.alterarAvaliacao(id, avaliacaoNova))
          .isInstanceOf(AvaliacaoNotFoundException.class)
          .hasMessage("avaliacao não encontrada");
      verify(avaliacaoRepository, never()).save(any(Avaliacao.class));
    }

  }

  @Nested
  class RemoverAvaliacao {

    @Test
    void devePermitirApagarAvaliacao() {
      var id = 1L;
      var avaliacao = AvaliacaoHelper.gerarAvaliacao();
      avaliacao.setId(id);
      when(avaliacaoRepository.findById(id))
          .thenReturn(Optional.of(avaliacao));
      doNothing()
          .when(avaliacaoRepository).deleteById(id);

      var resultado = avaliacaoService.apagarAvaliacao(id);

      assertThat(resultado).isTrue();
      verify(avaliacaoRepository, times(1)).findById(any(Long.class));
      verify(avaliacaoRepository, times(1)).delete(any(Avaliacao.class));
    }

  }

  @Nested
  class ListarAvaliacoes {

    @Test
    void devePermitirListarAvaliacoes() {
      Page<Avaliacao> page = new PageImpl<>(Arrays.asList(
          AvaliacaoHelper.gerarAvaliacao(),
          AvaliacaoHelper.gerarAvaliacao()
      ));

      when(avaliacaoRepository.listarAvaliacoes(any(Pageable.class)))
          .thenReturn(page);

      Page<Avaliacao> avaliacoes = avaliacaoService.listarAvaliacoes(Pageable.unpaged());

      assertThat(avaliacoes).hasSize(2);
      assertThat(avaliacoes.getContent())
          .asList()
          .allSatisfy(avaliacao -> {
            assertThat(avaliacao).isNotNull();
            assertThat(avaliacao).isInstanceOf(Avaliacao.class);
          });
      verify(avaliacaoRepository, times(1)).listarAvaliacoes(any(Pageable.class));
    }

    @Test
    void devePermitirListarAvaliacoes_QuandoNaoExisteRegistro() {
      Page<Avaliacao> page = new PageImpl<>(Collections.emptyList());

      when(avaliacaoRepository.listarAvaliacoes(any(Pageable.class)))
          .thenReturn(page);

      Page<Avaliacao> avaliacoes = avaliacaoService.listarAvaliacoes(Pageable.unpaged());

      assertThat(avaliacoes).isEmpty();
      verify(avaliacaoRepository, times(1)).listarAvaliacoes(any(Pageable.class));
    }
  }
}
