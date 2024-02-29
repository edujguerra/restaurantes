package br.com.fiap.restaurantes.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.ControllerNotFoundException;
import br.com.fiap.restaurantes.dto.PessoaDTO;
import br.com.fiap.restaurantes.entities.Pessoa;
import br.com.fiap.restaurantes.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repo;

    public Collection<PessoaDTO> findAll() {
        var pessoas = repo.findAll();
        return pessoas
                .stream()
                .map(this::toPessoaDTO)        //Transforma cada pessoa em PessoaDTO
                .collect(Collectors.toList());  //Devolve a Lista
    }

    public PessoaDTO findById(Long id) {
        var pessoa =
                repo.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Pessoa não Encontrada !!!!")                );
        return toPessoaDTO(pessoa);
    }

    public PessoaDTO save(PessoaDTO pessoaDTO) {
        Pessoa pessoa = toPessoa(pessoaDTO);
        pessoa = repo.save(pessoa);
        return toPessoaDTO(pessoa);
    }

    public PessoaDTO update(Long id, PessoaDTO pessoaDTO) {
        try {
            Pessoa buscaPessoa = repo.getReferenceById(id);
            buscaPessoa.setNome(pessoaDTO.nome());
            buscaPessoa.setCpf(pessoaDTO.cpf());
            buscaPessoa.setEmail(pessoaDTO.email());
            buscaPessoa = repo.save(buscaPessoa);

            return toPessoaDTO(buscaPessoa);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Pessoa não Encontrada !!!!!!!!");
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private PessoaDTO toPessoaDTO(Pessoa pessoa) {
        return new PessoaDTO(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getEmail()
        );
    }

    private Pessoa toPessoa(PessoaDTO pessoaDTO) {
        return new Pessoa(
                pessoaDTO.id(),
                pessoaDTO.nome(),
                pessoaDTO.cpf(),
                pessoaDTO.email()
        );
    }
}
