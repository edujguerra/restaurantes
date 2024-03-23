package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_tipocozinha")
public class TipoCozinha {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipoCozinha", nullable = false)
    private Long id;

	@Column(name = "ds_tipo", nullable = false)
    private String nome; 
}
