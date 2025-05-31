package br.com.ouvidoria.sistemaDeOuvidoria.controller;

import br.com.ouvidoria.sistemaDeOuvidoria.entity.Registro;
import br.com.ouvidoria.sistemaDeOuvidoria.entity.TipoEnum;
import br.com.ouvidoria.sistemaDeOuvidoria.service.OuvidoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class OuvidoriaController {

    @Autowired
    private OuvidoriaService ouvidoriaService;

    @GetMapping("/listar")
    public ResponseEntity<List<Registro>> listar() {
        try {
            List<Registro> lista = ouvidoriaService.listarRegistros();
            return ResponseEntity.ok().body(lista);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping("/criar")
    public ResponseEntity<String>criarManifestacao(@RequestBody Registro registro){
        try {
            Integer codigo = ouvidoriaService.criarRegistro(registro);
            return ResponseEntity.ok().body(codigo.toString());
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Registro>deletarManifestacao(@PathVariable Integer id){
        try {
            Registro registro = ouvidoriaService.deletarRegistro(id);
            if (registro != null) {
                return ResponseEntity.ok().body(registro);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/listarPorTipo/{tipo}")
    public ResponseEntity<List<Registro>> listarPorTipo(@PathVariable String tipo){
        try {
            TipoEnum tipoEnum = TipoEnum.valueOf(tipo);
            List<Registro> lista = ouvidoriaService.listaPorTipo(tipoEnum);
            return ResponseEntity.ok().body(lista);
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }
}
