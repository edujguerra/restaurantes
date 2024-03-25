
package br.com.fiap.restaurantes.dto;

import java.time.LocalDate;

public record AvaliacaoDTO (
  Long id,
  ClienteDTO cliente,
  RestauranteDTO restaurante,
  String descricao,
  int nota,
  LocalDate dataAvaliacao
) {}
