package br.com.fiap.restaurantes.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tb_restaurante")
public class Restaurante {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_restaurante", nullable = false)
    private Long id;

	@Column(name = "nm_restaurante", nullable = false)
    private String nome;
    
    //private Endereco endereco;
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

	public int getNumMesas() {
		return numMesas;
	}

	public void setNumMesas(int numMesas) {
		this.numMesas = numMesas;
	}

	public Restaurante(Long id, String nome, String endereco, TipoCozinha tipoCozinha, String horaInicio,
			String horaFinal, int numMesas) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.tipoCozinha = tipoCozinha;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.numMesas = numMesas;
	}

	public Restaurante() {
		
	}
	
	
}
