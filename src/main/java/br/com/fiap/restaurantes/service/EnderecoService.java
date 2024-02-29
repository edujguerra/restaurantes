package br.com.fiap.restaurantes.service;

import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.dto.EnderecoDTO;
import br.com.fiap.restaurantes.entities.Endereco;

@Service
public class EnderecoService {

    public EnderecoDTO update(Long id, EnderecoDTO enderecoDTO) {
        Endereco buscaEndereco = new Endereco();
        buscaEndereco.setRua(enderecoDTO.rua());
        buscaEndereco.setBairro(enderecoDTO.bairro());
        buscaEndereco.setNumero(enderecoDTO.numero());
        buscaEndereco.setComplemento(enderecoDTO.complemento());
        buscaEndereco.setCep(enderecoDTO.cep());
        buscaEndereco.setCidade(enderecoDTO.cidade());                

        return toEnderecoDTO(buscaEndereco);
    }

    private EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return new EnderecoDTO(
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
        		enderecoDTO.rua(),
        		enderecoDTO.bairro(),
        		enderecoDTO.numero(),
        		enderecoDTO.complemento(),
        		enderecoDTO.cep(),
        		enderecoDTO.cidade()                                
        );
    }
}
