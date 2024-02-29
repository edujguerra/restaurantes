package br.com.fiap.restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.restaurantes.entities.Restaurante;

@Repository	
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
