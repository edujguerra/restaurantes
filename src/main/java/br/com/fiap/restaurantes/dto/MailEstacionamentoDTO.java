package br.com.fiap.restaurantes.dto;

import java.time.LocalDateTime;

public record MailEstacionamentoDTO(
        String to,
        String subject,
        LocalDateTime horarioEntrada,
        LocalDateTime horarioSaida,
        double valorPagamento
) {
    
}
