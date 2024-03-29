package br.com.fiap.restaurantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.restaurantes.entities.TipoCozinha;

@Repository
public interface TipoCozinhaRepository extends JpaRepository<TipoCozinha, Long> {
}
