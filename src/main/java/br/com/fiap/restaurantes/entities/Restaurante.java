package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tb_restaurante")
public class Restaurante {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_Endereco", nullable = false)
    private Endereco endereço;
    
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
	
    public Endereco getEndereço() {
		return endereço;
	}

	public void setEndereço(Endereco endereço) {
		this.endereço = endereço;
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

	public Restaurante(Long id, String nome, Endereco endereço, TipoCozinha tipoCozinha, String horaInicio,
			String horaFinal) {
		this.id = id;
		this.nome = nome;
		this.endereço = endereço;
		this.tipoCozinha = tipoCozinha;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
	}
	
	
}