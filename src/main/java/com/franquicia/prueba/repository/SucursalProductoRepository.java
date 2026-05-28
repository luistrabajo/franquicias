package com.franquicia.prueba.repository;

import com.franquicia.prueba.dto.ProductoMayorStockDTO;
import com.franquicia.prueba.entity.SucursalProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalProductoRepository
        extends JpaRepository<SucursalProducto, Long> {

    @Query("""
                SELECT new com.franquicia.prueba.dto.ProductoMayorStockDTO(
                    sp.sucursal.nombre,sp.producto.nombre,sp.stock)
                FROM SucursalProducto sp
                WHERE sp.sucursal.franquicia.id = :franquiciaId
                AND sp.stock = (            
                    SELECT MAX(sp2.stock)
                    FROM SucursalProducto sp2
                    WHERE sp2.sucursal.id = sp.sucursal.id
                )""")
    List<ProductoMayorStockDTO>
    obtenerProductosMayorStockPorFranquicia(
            @Param("franquiciaId")
            Long franquiciaId);
}
