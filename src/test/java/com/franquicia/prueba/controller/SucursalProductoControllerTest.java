package com.franquicia.prueba.controller;


import com.franquicia.prueba.dto.ProductoDTO;
import com.franquicia.prueba.dto.SucursalDTO;
import com.franquicia.prueba.dto.SucursalProductoDTO;
import com.franquicia.prueba.entity.Producto;
import com.franquicia.prueba.entity.Sucursal;
import com.franquicia.prueba.service.SucursalProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SucursalProductoController.class)
class SucursalProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SucursalProductoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeListar() throws Exception {

        SucursalDTO sucursal = new SucursalDTO();
        sucursal.setId(1L);
        sucursal.setNombre("Sucursal Norte");

        ProductoDTO producto = new ProductoDTO();
        producto.setId(1L);
        producto.setNombre("Hamburguesa");

        SucursalProductoDTO dto =
                new SucursalProductoDTO();

        dto.setId(1L);
        dto.setSucursal(sucursal);
        dto.setProducto(producto);
        dto.setStock(100);

        when(service.listar())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/sucursal-productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stock")
                        .value(100));
    }

    @Test
    void debeObtenerPorId() throws Exception {

        Long id = 1L;

        SucursalDTO sucursal = new SucursalDTO();
        sucursal.setId(1L);

        ProductoDTO producto = new ProductoDTO();
        producto.setId(1L);

        SucursalProductoDTO dto =
                new SucursalProductoDTO();

        dto.setId(id);
        dto.setSucursal(sucursal);
        dto.setProducto(producto);
        dto.setStock(100);

        when(service.obtener(id))
                .thenReturn(dto);

        mockMvc.perform(
                        get("/sucursal-productos/{id}", id)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock")
                        .value(100));
    }

    @Test
    void debeCrear() throws Exception {

        SucursalDTO sucursal = new SucursalDTO();
        sucursal.setId(1L);

        ProductoDTO producto = new ProductoDTO();
        producto.setId(1L);

        SucursalProductoDTO request =
                new SucursalProductoDTO();

        request.setSucursal(sucursal);
        request.setProducto(producto);
        request.setStock(100);

        SucursalProductoDTO response =
                new SucursalProductoDTO();

        response.setId(1L);
        response.setSucursal(sucursal);
        response.setProducto(producto);
        response.setStock(100);

        when(service.guardar(any(SucursalProductoDTO.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/sucursal-productos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(1));
    }

    @Test
    void debeActualizarParcialmente() throws Exception {

        Long id = 1L;

        SucursalDTO sucursal = new SucursalDTO();
        sucursal.setId(1L);

        ProductoDTO producto = new ProductoDTO();
        producto.setId(1L);

        SucursalProductoDTO response =
                new SucursalProductoDTO();

        response.setId(id);
        response.setSucursal(sucursal);
        response.setProducto(producto);
        response.setStock(300);

        when(service.actualizarParcial(
                eq(id),
                any(SucursalProductoDTO.class)))
                .thenReturn(response);

        SucursalProductoDTO request =
                new SucursalProductoDTO();

        request.setStock(300);

        mockMvc.perform(
                        patch("/sucursal-productos/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock")
                        .value(300));
    }

    @Test
    void debeEliminar() throws Exception {

        mockMvc.perform(
                        delete("/sucursal-productos/{id}", 1L)
                )
                .andExpect(status().isOk());
    }
}