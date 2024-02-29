package br.com.fiap.restaurantes.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.ControllerNotFoundException;
import br.com.fiap.restaurantes.dto.EnderecoDTO;
import br.com.fiap.restaurantes.dto.TipoCozinhaDTO;
import br.com.fiap.restaurantes.entities.Endereco;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repo;

    public Collection<EnderecoDTO> findAll() {
        var enderecos = repo.findAll();
        return enderecos
                .stream()
                .map(this::toEnderecoDTO)        
                .collect(Collectors.toList());  
    }

    public EnderecoDTO findById(Long id) {
        var enderecos =
                repo.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Endereço não encontrado !!!!"));
        return toEnderecoDTO(enderecos);
    }

    public EnderecoDTO save(EnderecoDTO enderecoDTO) {
        Endereco endereco = toEndereco(enderecoDTO);
        endereco = repo.save(endereco);
        return toEnderecoDTO(endereco);
    }

    public EnderecoDTO update(Long id, EnderecoDTO enderecoDTO) {
        try {
            Endereco buscaEndereco = repo.getReferenceById(id);
            buscaEndereco.setRua(enderecoDTO.rua());
            buscaEndereco.setBairro(enderecoDTO.bairro());
            buscaEndereco.setNumero(enderecoDTO.numero());
            buscaEndereco.setComplemento(enderecoDTO.complemento());
            buscaEndereco.setCep(enderecoDTO.cep());
            buscaEndereco.setCidade(enderecoDTO.cidade());                

            return toEnderecoDTO(buscaEndereco);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Endereco não Encontrado !!!!!!!!");
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getRua(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCep(),
                endereco.getCidade()                
                
        );
    }

    private Endereco toEndereco(EnderecoDTO enderecoDTO) {
        return new Endereco(
        		enderecoDTO.id(),
        		enderecoDTO.rua(),
        		enderecoDTO.bairro(),
        		enderecoDTO.numero(),
        		enderecoDTO.complemento(),
        		enderecoDTO.cep(),
        		enderecoDTO.cidade()                                
        );
    }
}
