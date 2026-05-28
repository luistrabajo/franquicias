package com.franquicia.prueba.controller;

import com.franquicia.prueba.dto.ProductoDTO;
import com.franquicia.prueba.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @GetMapping
    public List<ProductoDTO> listar() {

        return service.listar();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtener(
            @PathVariable Long id) {

        return service.obtener(id);
    }

    @PostMapping
    public ProductoDTO crear(
            @RequestBody ProductoDTO dto) {

        return service.guardar(dto);
    }

    @PatchMapping("/{id}")
    public ProductoDTO actualizarParcial(
            @PathVariable Long id,
            @RequestBody ProductoDTO dto) {

        return service.actualizarParcial(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id) {

        service.eliminar(id);
    }
}
