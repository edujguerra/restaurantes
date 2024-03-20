package br.com.fiap.restaurantes.service;

import br.com.fiap.restaurantes.dto.EnderecoDTO;
import br.com.fiap.restaurantes.entities.Endereco;

public interface EnderecoService {

    public EnderecoDTO update(Long id, EnderecoDTO enderecoDTO);

    public EnderecoDTO toEnderecoDTO(Endereco endereco);

    public Endereco toEndereco(EnderecoDTO enderecoDTO);
}
