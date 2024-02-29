package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.Endereco;
import br.com.fiap.restaurantes.entities.TipoCozinha;

public record RestauranteDTO(
        Long id,
        String nome,
        Endereco endereço,
        TipoCozinha tipoCozinha,
    	String horaInicio,
    	String horaFinal
) {}
