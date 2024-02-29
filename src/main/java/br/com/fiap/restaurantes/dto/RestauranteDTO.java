package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.Pessoa;

public record CarroDTO(
        Long id,
        String placa,
        Pessoa pessoa
) {}
