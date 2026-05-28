package com.franquicia.prueba.repository;

import com.franquicia.prueba.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository
        extends JpaRepository<Producto, Long> {
}
