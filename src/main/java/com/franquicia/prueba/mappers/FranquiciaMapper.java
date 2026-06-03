package com.franquicia.prueba.mappers;

import com.franquicia.prueba.dto.FranquiciaDTO;
import com.franquicia.prueba.entity.Franquicia;
import org.springframework.stereotype.Component;

@Component
public class FranquiciaMapper {

    public Franquicia toEntity(FranquiciaDTO dto) {
        Franquicia franquicia = new Franquicia();
        franquicia.setNombre(dto.getNombre());
        franquicia.setNit(dto.getNit());
        franquicia.setTelefono(dto.getTelefono());
        franquicia.setEmail(dto.getEmail());
        return franquicia;
    }

    public FranquiciaDTO toDTO(Franquicia entity) {
        return new FranquiciaDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getNit(),
                entity.getTelefono(),
                entity.getEmail()
        );
    }
}