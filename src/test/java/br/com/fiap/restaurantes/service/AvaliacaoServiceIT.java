
package br.com.fiap.restaurantes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.fiap.restaurantes.entities.Avaliacao;
import br.com.fiap.restaurantes.exception.AvaliacaoNotFoundException;
import br.com.fiap.restaurantes.repository.AvaliacaoRepository;
import br.com.fiap.restaurantes.utils.AvaliacaoHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AvaliacaoServiceIT {
  @Mock
  private AvaliacaoRepository avaliacaoRepository;

  private AvaliacaoServiceImpl avaliacaoService;

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
    var id = 1666L;

    assertThatThrownBy(() -> avaliacaoService.buscarAvaliacao(id))
            .isInstanceOf(AvaliacaoNotFoundException.class)
            .hasMessage("avaliacao não encontrada");
  }

  @Test
  void devePermirirAlterarAvaliacao() {
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

  @Test
  void devePermitirListarAvaliacoes() {
    Collection<Avaliacao> listaAvaliacoes = new ArrayList<>();
    var avaliacao1 = AvaliacaoHelper.gerarAvaliacao();
    var avaliacao2 = AvaliacaoHelper.gerarAvaliacaoCompleta();
    listaAvaliacoes.add(avaliacao1);
    listaAvaliacoes.add(avaliacao2);

    when(avaliacaoRepository.listarAvaliacoes()).thenReturn(listaAvaliacoes);
    Collection<Avaliacao> avaliacoes = avaliacaoService.findAll();

    assertThat(avaliacoes).hasSize(2);
  }

  @Test
  void devePermitirListarAvaliacoes_QuandoNaoExisteRegistro() {
    Collection<Avaliacao> page = new ArrayList<>();

    when(avaliacaoRepository.listarAvaliacoes())
            .thenReturn(page);

    Collection<Avaliacao> avaliacoes = avaliacaoService.findAll();

    assertThat(avaliacoes).isEmpty();
    verify(avaliacaoRepository, times(1)).listarAvaliacoes();
  }
}
