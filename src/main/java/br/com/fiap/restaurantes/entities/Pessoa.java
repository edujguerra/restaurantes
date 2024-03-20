package br.com.fiap.restaurantes.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public abstract class Pessoa {
    
   @Column(nullable = false)
   private String nome;

   @Column(nullable = false)
   private String email;

   @Column(nullable = false, length = 5)
   private Long telefone;

}
