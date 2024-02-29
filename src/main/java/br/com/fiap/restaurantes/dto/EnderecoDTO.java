package br.com.fiap.restaurantes.dto;

public record EnderecoDTO(
	    String rua,
	    String bairro,
	    String numero,
	    String complemento,
	    String cep,
	    String cidade) 
{
	
}
