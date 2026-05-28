package com.franquicia.prueba.dto;

import com.franquicia.prueba.entity.Producto;
import com.franquicia.prueba.entity.Sucursal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalProductoDTO {

    private Long id;

    private Sucursal sucursal;

    private Producto producto;

    private Integer stock;
}
