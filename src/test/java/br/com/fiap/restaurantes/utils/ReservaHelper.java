package br.com.fiap.restaurantes.utils;

import br.com.fiap.restaurantes.dto.ReservaDTO;
import br.com.fiap.restaurantes.entities.Cliente;
import br.com.fiap.restaurantes.entities.Reserva;
import br.com.fiap.restaurantes.entities.Restaurante;
import br.com.fiap.restaurantes.entities.TipoCozinha;
import br.com.fiap.restaurantes.repository.ReservaRepository;

import java.time.LocalDate;

public abstract class ReservaHelper {

    public static Reserva gerarReserva() {
        TipoCozinha tipoCozinha = new TipoCozinha(1L, "Teste");
        Cliente cliente = new Cliente(1L, "Teste", "email",1234L);
        Restaurante restaurante = new Restaurante(1L, "nome teste",
                "endereço",  tipoCozinha, "01:00", "06:00",
                10,10);
        var timestamp = LocalDate.now();

        return Reserva.builder()
                .id(1L)
                .cliente(cliente)
                .restaurante(restaurante)
                .dataReserva(LocalDate.from(timestamp).toString())
                .numeroPessoas(6)
                .horaInicio("20")
                .horaFinal("21")
                .build();
    }

    public static ReservaDTO gerarReservaDTO() {
        TipoCozinha tipoCozinha = new TipoCozinha(1L, "Teste");
        Cliente cliente = new Cliente(1L, "Teste", "email",1234L);
        Restaurante restaurante = new Restaurante(1L, "nome teste",
                "endereço",  tipoCozinha, "01:00", "06:00",
                10,10);
        var timestamp = LocalDate.now();

        return ReservaDTO.builder()
                .id(1L)
                .cliente(cliente)
                .restaurante(restaurante)
                .dataReserva(LocalDate.from(timestamp).toString())
                .numeroPessoas(6)
                .horaInicio("20")
                .horaFinal("21")
                .build();
    }

    public static ReservaDTO gerarReservaDTOForaHorario() {
        TipoCozinha tipoCozinha = new TipoCozinha(1L, "Teste");
        Cliente cliente = new Cliente(1L, "Teste", "email",1234L);
        Restaurante restaurante = new Restaurante();
        var timestamp = LocalDate.now();

        return ReservaDTO.builder()
                .id(1L)
                .cliente(cliente)
                .restaurante(restaurante)
                .dataReserva(LocalDate.from(timestamp).toString())
                .numeroPessoas(6)
                .horaInicio("22")
                .horaFinal("24")
                .build();
    }

    public static Reserva registrarReserva(ReservaRepository reservaRepository) {
        var reserva = gerarReserva();
        reserva.setId(25L);
        return reservaRepository.save(reserva);
    }
}
