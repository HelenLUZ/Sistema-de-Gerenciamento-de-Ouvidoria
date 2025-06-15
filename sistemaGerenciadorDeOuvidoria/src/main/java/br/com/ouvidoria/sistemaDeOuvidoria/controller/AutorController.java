package br.com.ouvidoria.sistemaDeOuvidoria.controller;

import br.com.ouvidoria.sistemaDeOuvidoria.entity.Autor;
import br.com.ouvidoria.sistemaDeOuvidoria.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autores")
public class AutorController {
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listar() {
        return ResponseEntity.ok().body(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscar(@PathVariable Integer id) {
        try{
            Optional<Autor> autor = service.buscarPorId(id);
            if(autor.isPresent()) {
                return ResponseEntity.ok().body(autor.get());
            }else {
                return ResponseEntity.notFound().build();
            }
        }
        catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Autor> criar(@RequestBody Autor autor) {
        return ResponseEntity.ok().body(service.salvar(autor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable Integer id, @RequestBody Autor autorAtualizado) {
        Autor autor = service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        autor.setNome(autorAtualizado.getNome());
        return ResponseEntity.ok().body(service.salvar(autor));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}