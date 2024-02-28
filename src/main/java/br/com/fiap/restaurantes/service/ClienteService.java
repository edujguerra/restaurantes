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
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    public Collection<ClienteDTO> findAll() {
        var clientes = repo.findAll();
        return clientes
                .stream()
                .map(this::toClienteDTO)        //Transforma cada cliente em ClienteDTO
                .collect(Collectors.toList());  //Devolve a Lista
    }

    public ClienteDTO findById(Long id) {
        var cliente =
                repo.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Cliente não Encontrada !!!!")                );
        return toClienteDTO(cliente);
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {
        Cliente cliente = toCliente(clienteDTO);
        cliente = repo.save(cliente);
        return toClienteDTO(cliente);
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        try {
            Cliente buscaCliente = repo.getReferenceById(id);
            buscaCliente.setNome(clienteDTO.nome());
            buscaCliente.setEmail(clienteDTO.email());
            buscaCliente = repo.save(buscaCliente);

            return toClienteDTO(buscaCliente);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Cliente não Encontrada !!!!!!!!");
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private ClienteDTO toClienteDTO(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail()
        );
    }

    private Cliente toCliente(ClienteDTO clienteDTO) {
        return new Cliente(
                clienteDTO.id(),
                clienteDTO.nome(),
                clienteDTO.email()
        );
    }
}
