package br.com.fiap.restaurantes.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Table(name = "tb_avaliacao")
public class Avaliacao {
    @Id
    @Column(name = "id_avaliacao", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    @NotEmpty(message = "Cliente não pode estar vazio.")
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_restaurante", nullable = false)
    @NotEmpty(message = "Restaurante não pode estar vazio.")
    private Restaurante restaurante;

    @Builder.Default
    @Column(name = "ds_descricao")
    private String descricao = "";

    @Builder.Default
    @Column(name = "nr_nota")
    private int nota = 0;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSS")
    @Column(name = "dt_avaliacao")
    private LocalDateTime dataAvaliacao;

    @PrePersist
    public void prePersist() {
        var timestamp = LocalDateTime.now();
        dataAvaliacao = timestamp;
    }
}
