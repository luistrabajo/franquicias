package com.franquicia.prueba.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalProductoDTO {
    private Long id;
    private SucursalDTO sucursal;
    private ProductoDTO producto;
    private Integer stock;
}
