package com.franquicia.prueba.mappers;

import com.franquicia.prueba.dto.SucursalProductoDTO;
import com.franquicia.prueba.entity.SucursalProducto;
import org.springframework.stereotype.Component;

@Component
public class SucursalProductoMapper {

    public SucursalProductoDTO toDTO(
            SucursalProducto entity) {

        return new SucursalProductoDTO(
                entity.getId(),
                entity.getSucursal(),
                entity.getProducto(),
                entity.getStock()
        );
    }

    public SucursalProducto toEntity(
            SucursalProductoDTO dto) {

        SucursalProducto entity =
                new SucursalProducto();

        entity.setId(dto.getId());
        entity.setSucursal(dto.getSucursal());
        entity.setProducto(dto.getProducto());
        entity.setStock(dto.getStock());

        return entity;
    }
}
