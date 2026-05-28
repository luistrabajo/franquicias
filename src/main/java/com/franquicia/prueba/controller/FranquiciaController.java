package com.franquicia.prueba.controller;


import com.franquicia.prueba.dto.FranquiciaDTO;
import com.franquicia.prueba.service.FranquiciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/franquicias")
@RequiredArgsConstructor
public class FranquiciaController {

    private final FranquiciaService service;

    @GetMapping("/hola")
    public String hola() {
        return "Hola mundo";
    }

    @GetMapping
    public List<FranquiciaDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public FranquiciaDTO obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public FranquiciaDTO crear(@RequestBody FranquiciaDTO franquicia) {
        return service.guardar(franquicia);
    }

    @PutMapping("/{id}")
    public FranquiciaDTO actualizar(
            @PathVariable Long id,
            @RequestBody FranquiciaDTO franquicia) {

        franquicia.setId(id);
        return service.guardar(franquicia);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PatchMapping("/{id}")
    public FranquiciaDTO actualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos) {

        FranquiciaDTO franquiciaDto = service.obtener(id);

        if (campos.containsKey("nombre")) {
            franquiciaDto.setNombre((String) campos.get("nombre"));
        }

        if (campos.containsKey("telefono")) {
            franquiciaDto.setTelefono((String) campos.get("telefono"));
        }

        return service.actualizarParcial(id, franquiciaDto);
    }
}