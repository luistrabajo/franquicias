package com.franquicia.prueba.controller;

import com.franquicia.prueba.dto.SucursalDTO;
import com.franquicia.prueba.service.SucursalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sucursales")
@RequiredArgsConstructor
public class SucursalController {

    private final SucursalService service;

    @GetMapping
    public List<SucursalDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public SucursalDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public SucursalDTO crear(@RequestBody SucursalDTO dto) {
        return service.guardar(dto);
    }

    @PatchMapping("/{id}")
    public SucursalDTO actualizarParcial(
                @PathVariable Long id,
                @RequestBody SucursalDTO dto) {
        return service.actualizarParcial(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}