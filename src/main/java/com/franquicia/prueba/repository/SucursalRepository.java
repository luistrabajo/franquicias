package com.franquicia.prueba.repository;


import com.franquicia.prueba.entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository
        extends JpaRepository<Sucursal, Long> {
}