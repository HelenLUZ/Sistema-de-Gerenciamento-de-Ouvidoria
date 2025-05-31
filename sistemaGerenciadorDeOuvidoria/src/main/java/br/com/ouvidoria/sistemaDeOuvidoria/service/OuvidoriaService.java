package br.com.ouvidoria.sistemaDeOuvidoria.service;

import br.com.ouvidoria.sistemaDeOuvidoria.entity.Registro;
import br.com.ouvidoria.sistemaDeOuvidoria.entity.TipoEnum;
import br.com.ouvidoria.sistemaDeOuvidoria.repository.RegistroRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class OuvidoriaService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<Registro> listarRegistros() {
        return registroRepository.findAll();
    }

    public Integer criarRegistro(Registro registro) throws IllegalArgumentException {
        if (registro.getAutor() == null || registro.getAutor().isEmpty()) {
            throw new IllegalArgumentException("O autor não pode ser nulo");
        }
        if (registro.getTipo() == null) {
            throw new IllegalArgumentException("O tipo não pode ser nulo");
        }
        if (registro.getManifestacao() == null || registro.getManifestacao().isEmpty()) {
            throw new IllegalArgumentException("O manifesto não pode ser nulo");
        }
        registro.setCriadoEm(Instant.now());
        return registroRepository.save(registro).getCodigo();

    }

    public Registro deletarRegistro(Integer id) {

        if(registroRepository.existsById(id)) {
            Registro registro = registroRepository.findById(id).get();
            registroRepository.delete(registro);
            return registro;
        } else {
            return null;
        }

    }

    public List<Registro> listaPorTipo(TipoEnum tipoEnum) {
        return registroRepository.findAllByTipo(tipoEnum);
    }
}
