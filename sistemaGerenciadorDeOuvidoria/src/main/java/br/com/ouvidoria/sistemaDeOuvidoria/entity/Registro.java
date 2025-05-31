package br.com.ouvidoria.sistemaDeOuvidoria.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="registro")
public class Registro implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer codigo;
    @Enumerated(EnumType.STRING)
    private TipoEnum tipo;
    private String manifestacao;
    private String autor;
    private Instant criadoEm;

}
