package br.com.ouvidoria.sistemaDeOuvidoria.service;

import br.com.ouvidoria.sistemaDeOuvidoria.entity.Autor;
import br.com.ouvidoria.sistemaDeOuvidoria.entity.Registro;
import br.com.ouvidoria.sistemaDeOuvidoria.entity.TipoEnum;
import br.com.ouvidoria.sistemaDeOuvidoria.repository.AutorRepository;
import br.com.ouvidoria.sistemaDeOuvidoria.repository.RegistroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OuvidoriaService {

    private final RegistroRepository registroRepository;
    private final AutorRepository autorRepository;

    public List<Registro> listarRegistros() {
        return registroRepository.findAll();
    }

    public Integer criarRegistro(Registro registro) throws IllegalArgumentException {
        Autor autor = registro.getAutor();

        if (autor == null || autor.getId() == null) {
            throw new IllegalArgumentException("O autor precisa ser informado com ID");
        }

        Optional<Autor> autorExistente = autorRepository.findById(autor.getId());
        if (autorExistente.isEmpty()) {
            throw new IllegalArgumentException("Autor não encontrado com o ID fornecido");
        }

        if (registro.getTipo() == null) {
            throw new IllegalArgumentException("O tipo não pode ser nulo");
        }

        if (registro.getManifestacao() == null || registro.getManifestacao().isEmpty()) {
            throw new IllegalArgumentException("O manifesto não pode ser nulo");
        }

        registro.setAutor(autorExistente.get());
        registro.setCriadoEm(Instant.now());

        Registro registroSalvo = registroRepository.save(registro);

        return registroSalvo.getId();
    }

    public Registro deletarRegistro(Integer id) {
        return registroRepository.findById(id)
                .map(registro -> {
                    registroRepository.delete(registro);
                    return registro;
                })
                .orElse(null);
    }

    public List<Registro> listaPorTipo(TipoEnum tipoEnum) {
        return registroRepository.findAllByTipo(tipoEnum);
    }
}