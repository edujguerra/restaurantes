package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.entities.Restaurante;


public record ReservaDTO(
        Long id,
        Cliente cliente,
        Restaurante restaurante,
        String dataReserva,
        int numeroPessoas,
        String horaInicio,
        String horaFinal
) {}
