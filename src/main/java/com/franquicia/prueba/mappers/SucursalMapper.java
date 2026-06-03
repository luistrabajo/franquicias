package com.franquicia.prueba.mappers;

import com.franquicia.prueba.dto.SucursalDTO;
import com.franquicia.prueba.entity.Sucursal;
import org.springframework.stereotype.Component;

@Component
public class SucursalMapper {

    FranquiciaMapper fMapper = new FranquiciaMapper();

    public SucursalDTO toDTO(Sucursal entity) {
        return new SucursalDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getDireccion(),
                entity.getCiudad(),
                entity.getTelefono(),
                fMapper.toDTO(entity.getFranquicia()));
    }

    public Sucursal toEntity(SucursalDTO dto) {
        Sucursal entity = new Sucursal();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setDireccion(dto.getDireccion());
        entity.setCiudad(dto.getCiudad());
        entity.setTelefono(dto.getTelefono());
        entity.setFranquicia(fMapper.toEntity(dto.getFranquiciaDTO()));
        return entity;
    }
}