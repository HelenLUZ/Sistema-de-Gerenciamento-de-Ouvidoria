package br.com.ouvidoria.sistemaDeOuvidoria.repository;

import br.com.ouvidoria.sistemaDeOuvidoria.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

    Boolean existsByNome(String nome);

    Optional<Autor> findByNome(String nome);

}