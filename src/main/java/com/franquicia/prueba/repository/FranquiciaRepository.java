package com.franquicia.prueba.repository;

import com.franquicia.prueba.entity.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {
}
