package com.franquicia.prueba.service;

import com.franquicia.prueba.dto.FranquiciaDTO;
import com.franquicia.prueba.entity.Franquicia;
import com.franquicia.prueba.mappers.FranquiciaMapper;
import com.franquicia.prueba.repository.FranquiciaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class FranquiciaService {

    private final FranquiciaRepository repository;
    private final FranquiciaMapper mapper;

    public List<FranquiciaDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public FranquiciaDTO guardar(FranquiciaDTO dto) {
        Franquicia entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDTO(entity);
    }

    public FranquiciaDTO obtener(Long id) {
        Franquicia entity = repository.findById(id).orElseThrow();
        return mapper.toDTO(entity);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public FranquiciaDTO actualizarParcial(Long id,FranquiciaDTO dto) {
        Franquicia franquicia = repository.findById(id)
                                .orElseThrow(() ->new RuntimeException("Franquicia no encontrada"));
        if (dto.getNombre() != null) {
            franquicia.setNombre(dto.getNombre());
        }
        if (dto.getNit() != null) {
            franquicia.setNit(dto.getNit());
        }
        if (dto.getTelefono() != null) {
            franquicia.setTelefono(dto.getTelefono());
        }
        if (dto.getEmail() != null) {
            franquicia.setEmail(dto.getEmail());
        }
        Franquicia actualizada = repository.save(franquicia);
        return mapper.toDTO(actualizada);
    }
}
