
package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.entities.Restaurante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
public class AvaliacaoRequest {
  private ClienteDTO cliente;
  private RestauranteDTO restaurante;
  private String descricao;
  private int nota;
  private LocalDate dataAvaliacao;
}
