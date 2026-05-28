package com.franquicia.prueba.controller;

import com.franquicia.prueba.dto.ProductoDTO;
import com.franquicia.prueba.service.ProductoService;
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

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeListarProductos() throws Exception {

        ProductoDTO dto = new ProductoDTO();

        dto.setId(1L);
        dto.setNombre("Hamburguesa");

        when(service.listar())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre")
                        .value("Hamburguesa"));
    }

    @Test
    void debeObtenerProductoPorId() throws Exception {

        Long id = 1L;

        ProductoDTO dto = new ProductoDTO();

        dto.setId(id);
        dto.setNombre("Hamburguesa");

        when(service.obtener(id))
                .thenReturn(dto);

        mockMvc.perform(get("/productos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre")
                        .value("Hamburguesa"));
    }

    @Test
    void debeCrearProducto() throws Exception {

        ProductoDTO request = new ProductoDTO();

        request.setNombre("Hamburguesa");
        request.setSku("HAM-001");
        request.setDescripcion("Descripcion actual");

        ProductoDTO response = new ProductoDTO();

        response.setId(1L);
        response.setNombre("Hamburguesa de pollo");
        response.setSku("HAM-001");
        response.setDescripcion("Nueva descripcion");

        when(service.guardar(any(ProductoDTO.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/productos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(1))
                .andExpect(jsonPath("$.nombre")
                        .value("Hamburguesa de pollo"));
    }

    @Test
    void debeActualizarParcialmente() throws Exception {

        Long id = 1L;

        ProductoDTO existente = new ProductoDTO();

        existente.setId(id);
        existente.setNombre("Hamburguesa");
        existente.setDescripcion("Descripcion actual");

        ProductoDTO response = new ProductoDTO();

        response.setId(id);
        response.setNombre("Hamburguesa");
        response.setDescripcion("Nueva descripcion");

        when(service.obtener(id))
                .thenReturn(existente);

        when(service.actualizarParcial(
                eq(id),
                any(ProductoDTO.class)))
                .thenReturn(response);

        ProductoDTO request = new ProductoDTO();

        request.setDescripcion("Descripcion actualizada");

        mockMvc.perform(
                        patch("/productos/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descripcion")
                        .value("Nueva descripcion"));
    }

    @Test
    void debeEliminarProducto() throws Exception {

        mockMvc.perform(delete("/productos/{id}", 1L))
                .andExpect(status().isOk());
    }
}
