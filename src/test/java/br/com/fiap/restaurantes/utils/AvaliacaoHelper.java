package br.com.fiap.restaurantes.utils;

import br.com.fiap.restaurantes.dto.AvaliacaoDTO;
import br.com.fiap.restaurantes.dto.ClienteDTO;
import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Avaliacao;
import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.entities.Restaurante;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.repository.AvaliacaoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public abstract class AvaliacaoHelper {

    public static AvaliacaoDTO gerarAvaliacaoRequest() {
        TipoCozinha tipoCozinha = new TipoCozinha(1L, "Teste");
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Teste", "email",1234L );
        RestauranteDTO restauranteDTO = new RestauranteDTO(1L, "nome teste",
                "endereço",  tipoCozinha, "01:00", "06:00",
                10,10);
        var timestamp = LocalDateTime.now();

        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(1L,clienteDTO, restauranteDTO,
                "Avaliação Boa", 7, LocalDate.from(timestamp));
        return avaliacaoDTO;
    }

    public static Avaliacao gerarAvaliacao() {
        TipoCozinha tipoCozinha = new TipoCozinha(1L, "LANCHES");
        Cliente cliente = new Cliente(1L, "Teste", "email",1234L );
        Restaurante restaurante = new Restaurante(1L, "RESTAURANTE UM",
                "AVENIDA UM",  tipoCozinha, "22:00", "06:00",
                30,20);
        var timestamp = LocalDateTime.now();

        return Avaliacao.builder()
                .cliente(cliente)
                .restaurante(restaurante)
                .dataAvaliacao(LocalDateTime.now())
                .nota(7)
                .descricao("Avaliação Boa")
                .build();
    }

    public static Avaliacao gerarAvaliacaoCompleta() {
        TipoCozinha tipoCozinha = new TipoCozinha(1L, "Teste");
        Cliente cliente = new Cliente(1L, "Teste", "email",1234L );
        Restaurante restaurante = new Restaurante(1L, "nome teste",
                "endereço",  tipoCozinha, "01:00", "06:00",
                10,10);

        return Avaliacao.builder()
                .id(new Random().nextLong(10000))
                .cliente(cliente)
                .restaurante(restaurante)
                .dataAvaliacao(LocalDateTime.now())
                .nota(7)
                .descricao("Avaliação Boa")
                .build();
    }

    public static Avaliacao registrarAvaliacao(AvaliacaoRepository repository) {
        var avaliacao = gerarAvaliacao();
        avaliacao.setId(new Random().nextLong(10000));
        return repository.save(avaliacao);
    }
}
