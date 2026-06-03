package com.franquicia.prueba.mappers;

import com.franquicia.prueba.dto.ProductoDTO;
import com.franquicia.prueba.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto entity) {
        return new ProductoDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getSku(),
                entity.getDescripcion(),
                entity.getCreatedAt());
    }

    public Producto toEntity(ProductoDTO dto) {
        Producto entity = new Producto();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setSku(dto.getSku());
        entity.setDescripcion(dto.getDescripcion());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}
