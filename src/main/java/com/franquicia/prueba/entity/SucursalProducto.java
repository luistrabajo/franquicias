package com.franquicia.prueba.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sucursal_productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;


    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;


}
