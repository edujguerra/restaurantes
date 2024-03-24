package br.com.fiap.restaurantes.service;

import br.com.fiap.restaurantes.dto.ClienteDTO;
import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface AvaliacaoService {
    Avaliacao criarAvaliacao(Avaliacao avaliacao);
    Avaliacao buscarAvaliacao(Long id);
    boolean apagarAvaliacao(Long id);
    Avaliacao alterarAvaliacao(Long id, Avaliacao avaliacaoNova);
    Collection<Avaliacao> listarAvaliacoes();
}

