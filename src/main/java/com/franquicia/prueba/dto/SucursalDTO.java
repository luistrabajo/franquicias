package com.franquicia.prueba.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {
    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String telefono;
    private FranquiciaDTO franquiciaDTO;
}
