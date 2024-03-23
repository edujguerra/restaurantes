package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.entities.Restaurante;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder
public record ReservaDTO(
        Long id,
        Cliente cliente,
        Restaurante restaurante,
        LocalDate dataReserva,
        int numeroPessoas,
        String horaInicio,
        String horaFinal
) {}
