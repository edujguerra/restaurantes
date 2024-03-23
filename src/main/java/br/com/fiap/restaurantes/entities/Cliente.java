package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Entity
@Table(name = "tb_cliente")
public class Cliente {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_cliente", nullable = false)
   private Long id;

   @Column(nullable = false)
   private String nome;

   @Column(nullable = false)
   private String email;

   @Column(nullable = false, length = 5)
   private Long telefone;

}
