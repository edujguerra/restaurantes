package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_restaurante")
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_restaurante", nullable = false)
	private Long id;

	@Column(name = "nm_restaurante", nullable = false)
	private String nome;

	// private Endereco endereco;
	@Column(name = "ds_enderec", nullable = false)
	private String endereco;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_tipoCozinha", nullable = false)
	private TipoCozinha tipoCozinha;

	@Column(name = "hr_inicio", nullable = false, length = 5)
	private String horaInicio;

	@Column(name = "hr_final", nullable = false, length = 5)
	private String horaFinal;

	@Column(name = "nr_mesas", nullable = false)
	private int numMesas;
	@Transient
	private int mesasDisponiveis;

}
