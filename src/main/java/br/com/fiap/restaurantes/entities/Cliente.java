package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_cliente")
public class Cliente extends Pessoa {

   public Cliente(Long id, String nome, String email, Long telefone) {
      super(id,nome,email,telefone);
   }
}
