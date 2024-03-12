package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.Reserva;

import java.util.List;

public record ClienteDTO(
        Long id,
        String nome,
        String email,
        Long telefone,
        List<Reserva> reservas
) {}
