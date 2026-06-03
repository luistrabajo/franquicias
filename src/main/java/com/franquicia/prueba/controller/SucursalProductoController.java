package com.franquicia.prueba.controller;

import com.franquicia.prueba.dto.ProductoMayorStockDTO;
import com.franquicia.prueba.dto.SucursalProductoDTO;
import com.franquicia.prueba.service.SucursalProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursal-productos")
@RequiredArgsConstructor
public class SucursalProductoController {

    private final SucursalProductoService service;

    @GetMapping
    public List<SucursalProductoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public SucursalProductoDTO obtener( @PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public SucursalProductoDTO crear(@RequestBody SucursalProductoDTO dto) {
        return service.guardar(dto);
    }

    @PatchMapping("/{id}")
    public SucursalProductoDTO actualizarParcial(@PathVariable Long id,
                                                 @RequestBody SucursalProductoDTO dto) {
        return service.actualizarParcial(id, dto);
    }

    @PatchMapping("/stock/{idPrpducto}/{nuevo_stock}")
    public SucursalProductoDTO actualizarStock(@PathVariable Long idPrpducto,
                                                @PathVariable Integer nuevo_stock) {
        return service.actualizarStock(idPrpducto, nuevo_stock);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/franquicia/mayor-stock/{franquiciaId}")
    public List<ProductoMayorStockDTO> obtenerProductosMayorStock( @PathVariable Long franquiciaId) {
        return service.obtenerProductosMayorStockPorFranquicia(franquiciaId);
    }
}
