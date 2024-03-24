package br.com.fiap.restaurantes.service;

import br.com.fiap.restaurantes.dto.ClienteDTO;
import br.com.fiap.restaurantes.entities.Cliente;

import java.util.Collection;

public interface ClienteService {


    public Collection<ClienteDTO> findAll();

    public ClienteDTO findClientById(Long id);

    public ClienteDTO save(ClienteDTO clienteDTO);

    public ClienteDTO update(Long id, ClienteDTO clienteDTO);

    public void delete(Long id);

    public ClienteDTO toClienteDTO(Cliente cliente);

    public Cliente toCliente(ClienteDTO clienteDTO);
}
