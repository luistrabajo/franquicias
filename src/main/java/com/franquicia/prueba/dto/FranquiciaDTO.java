package com.franquicia.prueba.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FranquiciaDTO {
    private Long id;
    private String nombre;
    private String nit;
    private String telefono;
    private String email;
}
