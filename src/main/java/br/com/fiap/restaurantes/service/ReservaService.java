package br.com.fiap.restaurantes.service;

import br.com.fiap.restaurantes.dto.ReservaDTO;
import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Reserva;
import br.com.fiap.restaurantes.exception.AvaliacaoNotFoundException;
import br.com.fiap.restaurantes.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestauranteService restauranteService;

    public ReservaService(ReservaRepository reservaRepository, RestauranteService restauranteService) {
        this.reservaRepository = reservaRepository;
        this.restauranteService = restauranteService;
    }

    public Collection<ReservaDTO> findAll() {
        var reservas = reservaRepository.findAll();

        return reservas
                .stream()
                .map(this::toReservaDTO)
                .collect(Collectors.toList());
    }

    public Reserva findById(Long id) {
        return reservaRepository.findById(id).orElseThrow(
                () -> new AvaliacaoNotFoundException.ControllerNotFoundException("Reserva não encontrada!!!"));
    }

    public ReservaDTO save(ReservaDTO reservaDTO) {
        Reserva reserva = toReserva(reservaDTO);
        RestauranteDTO restauranteDaReserva = restauranteService.findById(reservaDTO.restaurante().getId());
        String horarioEntrada = reservaDTO.horaInicio();
        if (Integer.valueOf(reserva.getHoraInicio()) < Integer.valueOf(restauranteDaReserva.horaInicio())
                || Integer.valueOf(reserva.getHoraFinal()) > Integer.valueOf(restauranteDaReserva.horaFinal())) {
            throw new AvaliacaoNotFoundException.ControllerNotFoundException("Horario da reserva incompativel com restaurante!!!");
        }
        if (restauranteDaReserva.mesasDisponiveis() < (double) reserva.getNumeroPessoas()/4) {
            throw new AvaliacaoNotFoundException.ControllerNotFoundException("Nao ha mesas disponiveis para este restaurante na data de: " + reservaDTO.dataReserva());
        } else {
            restauranteService.atualizaMesasDisponiveis(restauranteDaReserva, reservaDTO);
        }

        reserva = reservaRepository.save(reserva);
        return toReservaDTO(reserva);
    }

    public ReservaDTO update(Long id, ReservaDTO reservaDTO) {
        try {
            Reserva reserva = reservaRepository.getReferenceById(id);
            if (reserva == null || !reservaDTO.id().equals(reserva.getId())) {
                throw new AvaliacaoNotFoundException.ControllerNotFoundException("Reserva não encontrada!!!");
            }
            reserva.setCliente(reservaDTO.cliente());
            reserva.setRestaurante(reservaDTO.restaurante());
            reserva.setDataReserva(reservaDTO.dataReserva());
            reserva.setNumeroPessoas(reservaDTO.numeroPessoas());
            reserva.setHoraInicio(reservaDTO.horaInicio());
            reserva.setHoraFinal(reservaDTO.horaFinal());
            reserva = reservaRepository.save(reserva);

            return toReservaDTO(reserva);
        } catch (EntityNotFoundException e) {
            throw new AvaliacaoNotFoundException.ControllerNotFoundException("Reserva não encontrada!!!");
        }
    }

    public  void delete(Long id) {
        reservaRepository.deleteById(id);
    }

    private ReservaDTO toReservaDTO(Reserva reserva) {
        return new ReservaDTO(
                reserva.getId(),
                reserva.getCliente(),
                reserva.getRestaurante(),
                reserva.getDataReserva(),
                reserva.getNumeroPessoas(),
                reserva.getHoraInicio(),
                reserva.getHoraFinal()
        );
    }

    private Reserva toReserva(ReservaDTO reservaDTO) {
        return new Reserva(
                reservaDTO.id(),
                reservaDTO.cliente(),
                reservaDTO.restaurante(),
                reservaDTO.dataReserva(),
                reservaDTO.numeroPessoas(),
                reservaDTO.horaInicio(),
                reservaDTO.horaFinal()
        );
    }


}