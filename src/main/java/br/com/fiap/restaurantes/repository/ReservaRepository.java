package br.com.fiap.restaurantes.repository;

import br.com.fiap.restaurantes.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
