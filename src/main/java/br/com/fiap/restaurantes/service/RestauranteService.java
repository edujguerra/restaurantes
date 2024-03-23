package br.com.fiap.restaurantes.service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import br.com.fiap.restaurantes.dto.ReservaDTO;
import br.com.fiap.restaurantes.exception.AvaliacaoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Restaurante;

public interface RestauranteService {

    public Collection<RestauranteDTO> findAll();

    public RestauranteDTO findById(Long id);

    public RestauranteDTO save(RestauranteDTO restauranteDTO);

    public RestauranteDTO update(Long id, RestauranteDTO restauranteDTO);

    public void delete(Long id);

    public void atualizaMesasDisponiveis(RestauranteDTO restauranteDTO, ReservaDTO reservaDTO);

    public boolean isMesaDisponivelNoHorario(Long idRestaurante, LocalDate dataReserva, String horaReserva, int totalDeMesas);

    public RestauranteDTO toRestauranteDTO(Restaurante restaurante);

    public Restaurante toRestaurante(RestauranteDTO restauranteDTO);
}