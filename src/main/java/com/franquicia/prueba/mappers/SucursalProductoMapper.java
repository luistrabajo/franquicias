package com.franquicia.prueba.mappers;


import com.franquicia.prueba.dto.SucursalProductoDTO;
import com.franquicia.prueba.entity.SucursalProducto;
import org.springframework.stereotype.Component;

@Component
public class SucursalProductoMapper {

    SucursalMapper sucursalMapper = new SucursalMapper();
    ProductoMapper productoMapper = new ProductoMapper();

    public SucursalProductoDTO toDTO( SucursalProducto entity) {
        SucursalProductoDTO dto =  new SucursalProductoDTO();
        dto.setId(entity.getId());
        dto.setSucursal(sucursalMapper.toDTO(entity.getSucursal()));
        dto.setProducto(productoMapper.toDTO(entity.getProducto()));
        dto.setStock(entity.getStock());
        return dto;
    }

    public SucursalProducto toEntity(SucursalProductoDTO dto) {
        SucursalProducto entity = new SucursalProducto();
        entity.setId(dto.getId());
        entity.setSucursal(sucursalMapper.toEntity(dto.getSucursal()));
        entity.setProducto(productoMapper.toEntity(dto.getProducto()));
        entity.setStock(dto.getStock());
        return entity;
    }
}
