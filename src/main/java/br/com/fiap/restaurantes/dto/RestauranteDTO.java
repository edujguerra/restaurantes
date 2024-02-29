package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.TipoCozinha;

public record RestauranteDTO(
        Long id,
        String nome,
        String endereco,
        TipoCozinha tipoCozinha,
    	String horaInicio,
    	String horaFinal
) {}
