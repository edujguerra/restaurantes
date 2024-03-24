package br.com.fiap.restaurantes.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.ControllerNotFoundException;
import br.com.fiap.restaurantes.dto.TipoCozinhaDTO;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.repository.TipoCozinhaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TipoCozinhaImpl implements TipoCozinhaService{
     @Autowired
    private TipoCozinhaRepository repo;

    public Collection<TipoCozinhaDTO> findAll() {
        var tipos = repo.findAll();
        return tipos
                .stream()
                .map(this::toTipoCozinhaDTO)        
                .collect(Collectors.toList());  
    }

    public TipoCozinhaDTO findById(Long id) {
        var tipos =
                repo.findById(id).orElseThrow(
                        () -> new ControllerNotFoundException("Tipo de cozinha não encontrada !!!!")                );
        return toTipoCozinhaDTO(tipos);
    }

    public TipoCozinhaDTO save(TipoCozinhaDTO tipoDTO) {
        TipoCozinha tipo = toTipoCozinha(tipoDTO);
        tipo = repo.save(tipo);
        return toTipoCozinhaDTO(tipo);
    }

    public TipoCozinhaDTO update(Long id, TipoCozinhaDTO tipoDTO) {
        try {
            TipoCozinha buscaTipo = repo.getReferenceById(id);
            buscaTipo.setNome(tipoDTO.nome());

            return toTipoCozinhaDTO(buscaTipo);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Tipo de cozinha não Encontrado !!!!!!!!");
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public TipoCozinhaDTO toTipoCozinhaDTO(TipoCozinha tipo) {
        return new TipoCozinhaDTO(
                tipo.getId(),
                tipo.getNome()
        );
    }

    public TipoCozinha toTipoCozinha(TipoCozinhaDTO tipoDTO) {
        return new TipoCozinha(
                tipoDTO.id(),
                tipoDTO.nome()                
        );
    }
}
