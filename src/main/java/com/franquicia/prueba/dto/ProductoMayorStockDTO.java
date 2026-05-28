package com.franquicia.prueba.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoMayorStockDTO {

    private String sucursal;

    private String producto;

    private Integer stock;
}

