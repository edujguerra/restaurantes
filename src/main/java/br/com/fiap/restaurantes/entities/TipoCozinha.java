package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tb_tipocozinha")
public class TipoCozinha {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

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

	public TipoCozinha(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}    
	
	
}
