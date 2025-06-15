package br.com.ouvidoria.sistemaDeOuvidoria.entity;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "registro")
public class Registro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoEnum tipo;

    private String manifestacao;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    private Instant criadoEm;
}