package br.com.fiap.restaurantes.service;

import br.com.fiap.restaurantes.dto.AvaliacaoDTO;
import br.com.fiap.restaurantes.entities.Avaliacao;

import java.util.Collection;

public interface AvaliacaoService {
    Avaliacao criarAvaliacao(Avaliacao avaliacao);
    Avaliacao buscarAvaliacao(Long id);
    boolean delete(Long id);
    Avaliacao alterarAvaliacao(Long id, Avaliacao avaliacaoNova);
    Collection<Avaliacao> findAll();
}

