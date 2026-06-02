package com.franquicia.prueba.mappers;

import com.franquicia.prueba.dto.SucursalProductoDTO;
import com.franquicia.prueba.entity.Producto;
import com.franquicia.prueba.entity.SucursalProducto;
import org.springframework.stereotype.Component;

@Component
public class SucursalProductoMapper {

    SucursalMapper sucursalMapper;
    ProductoMapper productoMapper;

    public SucursalProductoDTO toDTO(
            SucursalProducto entity) {



        return new SucursalProductoDTO(
                entity.getId(),
                sucursalMapper.toDTO(entity.getSucursal()),
                productoMapper.toDTO(entity.getProducto()),
                entity.getStock()
        );
    }

    public SucursalProducto toEntity(
            SucursalProductoDTO dto) {

        SucursalProducto entity =
                new SucursalProducto();

        entity.setId(dto.getId());
        entity.setSucursal(sucursalMapper.toEntity(dto.getSucursal()));
        entity.setProducto(productoMapper.toEntity(dto.getProducto()));
        entity.setStock(dto.getStock());

        return entity;
    }
}
