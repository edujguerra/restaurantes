package br.com.fiap.restaurantes.dto;

public record CadastroClienteDTO(
        Long id,
        String nome,
        String email,
        Long telefone
) {}
