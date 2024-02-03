package br.com.fiap.restaurantes.dto;

import java.time.LocalDate;

public record CartaoDTO(
        Long id,
        String numeroCartao,
        String nomeNoCartao,
        LocalDate dataValidade,
        int ccv
) {}
