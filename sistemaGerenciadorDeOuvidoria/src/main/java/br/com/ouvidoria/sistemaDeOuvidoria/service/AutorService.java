package br.com.ouvidoria.sistemaDeOuvidoria.service;

import br.com.ouvidoria.sistemaDeOuvidoria.entity.Autor;
import br.com.ouvidoria.sistemaDeOuvidoria.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    public List<Autor> listarTodos() {
        return repository.findAll();
    }

    public Optional<Autor> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Autor salvar(Autor autor) {
        Optional<Autor> velho = repository.findByNome(autor.getNome());
        return velho.orElseGet(() -> repository.save(autor));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}