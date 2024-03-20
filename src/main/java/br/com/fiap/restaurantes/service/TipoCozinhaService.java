package br.com.fiap.restaurantes.service;

import java.util.Collection;

import br.com.fiap.restaurantes.dto.TipoCozinhaDTO;
import br.com.fiap.restaurantes.entities.TipoCozinha;

public interface TipoCozinhaService {

    public Collection<TipoCozinhaDTO> findAll();

    public TipoCozinhaDTO findById(Long id);

    public TipoCozinhaDTO save(TipoCozinhaDTO tipoDTO);

    public TipoCozinhaDTO update(Long id, TipoCozinhaDTO tipoDTO);

    public void delete(Long id);

    public TipoCozinhaDTO toTipoCozinhaDTO(TipoCozinha tipo);

    public TipoCozinha toTipoCozinha(TipoCozinhaDTO tipoDTO);
}
