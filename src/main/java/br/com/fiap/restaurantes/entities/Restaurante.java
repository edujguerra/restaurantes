package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tb_restaurante")
public class Restaurante {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    //private Endereco endereco;
    private String endereco;
    
	@ManyToOne(optional = false)
    @JoinColumn(name = "id_TipoCozinha", nullable = false)
    private TipoCozinha tipoCozinha;
	
	private String horaInicio;
	private String horaFinal;
	
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
	
    public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public TipoCozinha getTipoCozinha() {
		return tipoCozinha;
	}

	public void setTipoCozinha(TipoCozinha tipoCozinha) {
		this.tipoCozinha = tipoCozinha;
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

	public Restaurante(Long id, String nome, String endereco, TipoCozinha tipoCozinha, String horaInicio,
			String horaFinal) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.tipoCozinha = tipoCozinha;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
	}

	public Restaurante() {
		
	}
	
	
}
