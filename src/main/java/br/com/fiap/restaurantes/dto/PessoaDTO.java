package br.com.fiap.restaurantes.dto;

public record PessoaDTO(
        Long id,
        String nome,
        String cpf,
        String email
) {

}
