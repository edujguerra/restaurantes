package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.entities.Restaurante;

import java.util.Date;

public record ReservaDTO(
        Long id,
        Cliente cliente,
        Restaurante restaurante,
        Date dataReserva,
        int numeroPessoas,
        String horaInicio,
        String horaFinal
) {}
