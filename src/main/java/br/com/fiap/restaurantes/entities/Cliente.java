package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_cliente")
public class Cliente extends Pessoa {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_cliente", nullable = false)
   private Long id;

   public Cliente(Long id, String nome, String email, Long telefone) {
      super(nome,email,telefone);
      this.id = id;
   }
}
