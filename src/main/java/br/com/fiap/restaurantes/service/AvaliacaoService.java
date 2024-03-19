package br.com.fiap.restaurantes.service;

import br.com.fiap.restaurantes.entities.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AvaliacaoService {
    Avaliacao criarAvaliacao(Avaliacao avaliacao);
    Avaliacao buscarAvaliacao(Long id);
    Avaliacao alterarAvaliacao(Long id, Avaliacao avaliacaoNova);
    Page<Avaliacao> listarAvaliacoes(Pageable pageable);
}

