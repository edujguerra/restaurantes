package br.com.fiap.restaurantes.dto;

import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.entities.Restaurante;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public record AvaliacaoDTO(
        private Long id;

        private Cliente cliente;

        private Restaurante restaurante;

        private String descricao = "";

        private int nota = 0;

        private LocalDateTime dataAvaliacao;
       )
{

}