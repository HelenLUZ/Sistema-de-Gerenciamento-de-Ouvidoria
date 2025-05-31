package br.com.ouvidoria.sistemaDeOuvidoria.repository;

import br.com.ouvidoria.sistemaDeOuvidoria.entity.Registro;
import br.com.ouvidoria.sistemaDeOuvidoria.entity.TipoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Integer> {
    List<Registro> findAllByTipo(TipoEnum tipoEnum);
}
