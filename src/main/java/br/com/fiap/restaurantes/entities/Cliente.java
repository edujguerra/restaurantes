package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;

import java.util.List;

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

   @OneToMany
   private List<Reserva> reservas;

   public Cliente(){}

   public Cliente(Long id, String nome, String email, Long telefone, List<Reserva> reservas) {
      this.id = id;
      this.nome = nome;
      this.email = email;
      this.telefone = telefone;
      this.reservas = reservas;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Long getTelefone() {
      return telefone;
   }

   public void setTelefone(Long telefone) {
      this.telefone = telefone;
   }

   public List<Reserva> getReservas() {
      return reservas;
   }

   public void setReservas(List<Reserva> reservas) {
      this.reservas = reservas;
   }
}
