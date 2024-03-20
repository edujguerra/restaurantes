package br.com.fiap.restaurantes.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public abstract class Pessoa {
    
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
