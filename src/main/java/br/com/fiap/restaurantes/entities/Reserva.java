package br.com.fiap.restaurantes.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
@Table(name = "tb_reserva")
public class Reserva {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id_reserva", nullable = false)
   private Long id;

   @ManyToOne
   private Cliente cliente;

   @ManyToOne
   private Restaurante restaurante;

   @Column(name = "data_reserva", nullable = false)
   private String dataReserva;

   @Column(name = "numero_pessoas", nullable = false)
   private int numeroPessoas;

   @Column(name = "hr_inicio", nullable = false, length = 5)
   private String horaInicio;

   @Column(name = "hr_final", nullable = false, length = 5)
   private String horaFinal;

   public Reserva() {}

   public Reserva(Long id, Cliente cliente, Restaurante restaurante, String dataReserva, int numeroPessoas,
                  String horaInicio, String horaFinal) {
      this.id = id;
      this.cliente = cliente;
      this.restaurante = restaurante;
      this.dataReserva = dataReserva;
      this.numeroPessoas = numeroPessoas;
      this.horaInicio = horaInicio;
      this.horaFinal = horaFinal;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Cliente getCliente() {
      return cliente;
   }

   public void setCliente(Cliente cliente) {
      this.cliente = cliente;
   }

   public Restaurante getRestaurante() {
      return restaurante;
   }

   public void setRestaurante(Restaurante restaurante) {
      this.restaurante = restaurante;
   }

   public String getDataReserva() {
      return dataReserva;
   }

   public void setDataReserva(String dataReserva) {
      this.dataReserva = dataReserva;
   }

   public int getNumeroPessoas() {
      return numeroPessoas;
   }

   public void setNumeroPessoas(int numeroPessoas) {
      this.numeroPessoas = numeroPessoas;
   }

   public String getHoraInicio() {
      return horaInicio;
   }

   public void setHoraInicio(String horaInicio) {
      this.horaInicio = horaInicio;
   }

   public String getHoraFinal() {
      return horaFinal;
   }

   public void setHoraFinal(String horaFinal) {
      this.horaFinal = horaFinal;
   }

}
