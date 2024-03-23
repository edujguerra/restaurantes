package br.com.fiap.restaurantes.service;

import br.com.fiap.restaurantes.dto.ReservaDTO;
import br.com.fiap.restaurantes.entities.Reserva;

import java.util.Collection;

public interface ReservaService {

    public Collection<ReservaDTO> findAll();

    public ReservaDTO findById(Long id);

    public ReservaDTO save(ReservaDTO reservaDTO);

    public ReservaDTO update(Long id, ReservaDTO reservaDTO);

    public  void delete(Long id);

    public ReservaDTO toReservaDTO(Reserva reserva);

    public Reserva toReserva(ReservaDTO reservaDTO);


}