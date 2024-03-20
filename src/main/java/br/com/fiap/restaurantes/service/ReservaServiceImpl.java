package br.com.fiap.restaurantes.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.restaurantes.ControllerNotFoundException;
import br.com.fiap.restaurantes.dto.ReservaDTO;
import br.com.fiap.restaurantes.dto.RestauranteDTO;
import br.com.fiap.restaurantes.entities.Reserva;
import br.com.fiap.restaurantes.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaServiceImpl implements ReservaService{
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestauranteService restauranteService;

    public Collection<ReservaDTO> findAll() {
        var reservas = reservaRepository.findAll();

        return reservas
                .stream()
                .map(this::toReservaDTO)
                .collect(Collectors.toList());
    }

    public ReservaDTO findById(Long id) {
        var reserva = reservaRepository.findById(id).orElseThrow(
                () -> new ControllerNotFoundException("Reserva não encontrada!!!"));

        return toReservaDTO(reserva);
    }

    public ReservaDTO save(ReservaDTO reservaDTO) {
        Reserva reserva = toReserva(reservaDTO);
        RestauranteDTO restauranteDaReserva = restauranteService.findById(reservaDTO.restaurante().getId());
        String horarioEntrada = reservaDTO.horaInicio();
        if (Integer.valueOf(reserva.getHoraInicio()) < Integer.valueOf(restauranteDaReserva.horaInicio())
                || Integer.valueOf(reserva.getHoraFinal()) > Integer.valueOf(restauranteDaReserva.horaFinal()) + 2) {
            throw new ControllerNotFoundException("Horario da reserva incompativel com restaurante!!!");
        }
        if (restauranteDaReserva.mesasDisponiveis() < reserva.getNumeroPessoas()/4) {
            throw new ControllerNotFoundException("Nao ha mesas disponiveis para este restaurante na data de: " + reservaDTO.dataReserva());
        } else {
            restauranteService.atualizaMesasDisponiveis(restauranteDaReserva, reservaDTO);
        }

        reserva = reservaRepository.save(reserva);
        return toReservaDTO(reserva);
    }

    public ReservaDTO update(Long id, ReservaDTO reservaDTO) {
        try {
            Reserva reserva = reservaRepository.getReferenceById(id);
            reserva.setCliente(reservaDTO.cliente());
            reserva.setRestaurante(reservaDTO.restaurante());
            reserva.setDataReserva(reservaDTO.dataReserva());
            reserva.setHoraInicio(reservaDTO.horaInicio());
            reserva.setHoraFinal(reservaDTO.horaFinal());
            reserva = reservaRepository.save(reserva);

            return toReservaDTO(reserva);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Reserva não encontrada!!!");
        }
    }

    public  void delete(Long id) {
        reservaRepository.deleteById(id);
    }

    public ReservaDTO toReservaDTO(Reserva reserva) {
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

    public Reserva toReserva(ReservaDTO reservaDTO) {
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
