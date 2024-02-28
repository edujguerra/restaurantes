package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.Cliente;

public record CarroDTO(
        Long id,
        String placa,
        Cliente cliente
) {}
