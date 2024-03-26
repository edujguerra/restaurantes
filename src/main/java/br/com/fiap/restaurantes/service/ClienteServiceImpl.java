package br.com.fiap.restaurantes.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.ControllerNotFoundException;
import br.com.fiap.restaurantes.dto.ClienteDTO;
import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService{
    
    @Autowired
    private ClienteRepository clienteRepository;

    public Collection<ClienteDTO> findAll() {
        var clientes = clienteRepository.findAll();

        return clientes
                .stream()
                .map(this::toClienteDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO findClientById(Long id) {
        var cliente =
                clienteRepository.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Cliente não encontrado!!!"));
        return toClienteDTO(cliente);
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = toCliente(clienteDTO);
        cliente = clienteRepository.save(cliente);
        return toClienteDTO(cliente);
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        try {
            Cliente buscaCliente = clienteRepository.getReferenceById(id);
            buscaCliente.setNome(clienteDTO.nome());
            buscaCliente.setEmail(clienteDTO.email());
            buscaCliente.setTelefone(clienteDTO.telefone());
            buscaCliente = clienteRepository.save(buscaCliente);

            return toClienteDTO(buscaCliente);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Cliente não Encontrado!!!");
        }
    }

    public void delete(Long id) {

        clienteRepository.deleteById(id);
    }

    public ClienteDTO toClienteDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone()
        );
    }

    public Cliente toCliente(ClienteDTO clienteDTO) {
        return new Cliente(
                clienteDTO.id(),
                clienteDTO.nome(),
                clienteDTO.email(),
                clienteDTO.telefone()
        );
    }
}
