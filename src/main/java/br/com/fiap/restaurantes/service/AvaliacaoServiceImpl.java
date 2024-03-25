package br.com.fiap.restaurantes.service;

import br.com.fiap.restaurantes.dto.AvaliacaoDTO;
import br.com.fiap.restaurantes.entities.Avaliacao;
import br.com.fiap.restaurantes.exception.AvaliacaoNotFoundException;
import br.com.fiap.restaurantes.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        avaliacao.setId(new Random().nextLong(1000));
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao buscarAvaliacao(Long id) {
        return avaliacaoRepository.findById(id)
                .orElseThrow(() -> new AvaliacaoNotFoundException("avaliacao não encontrada"));
    }

    public Avaliacao alterarAvaliacao(Long id, Avaliacao avaliacaoAtualizada) {
        var avaliacao = buscarAvaliacao(id);
        if (!avaliacao.getId().equals(avaliacaoAtualizada.getId())) {
            throw new AvaliacaoNotFoundException("avaliação não apresenta o ID correto");
        }
        avaliacao.setDataAvaliacao(avaliacaoAtualizada.getDataAvaliacao());
        avaliacao.setNota(avaliacaoAtualizada.getNota());
        avaliacao.setCliente(avaliacaoAtualizada.getCliente());
        avaliacao.setRestaurante(avaliacaoAtualizada.getRestaurante());
        avaliacao.setDescricao(avaliacaoAtualizada.getDescricao());
        return avaliacaoRepository.save(avaliacao);
    }

    public void delete(Long id) {

        avaliacaoRepository.deleteById(id);
    }

    public Collection<Avaliacao> findAll() {

        return avaliacaoRepository.listarAvaliacoes();
    }
}
