package br.com.fiap.restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fiap.restaurantes.entities.Restaurante;

import java.time.LocalDate;
import java.util.Date;

@Repository	
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

//    @Query("SELECT count(*) FROM reserva INNER JOIN tb_restaurante WHERE tb_restaurante.id_restaurante = (:idRestaurante)" +
//            " AND reserva.data_reserva = (:dataReserva) AND reserva.hr_inicio = (:horaReserva);")


    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.restaurante.id = :idRestaurante AND r.dataReserva = :dataReserva AND r.horaInicio = :horaReserva")
    public int mesasOcupadasNoHorario(Long idRestaurante, LocalDate dataReserva, String horaReserva);
}
