package com.franquicia.prueba.controller;


import com.franquicia.prueba.dto.SucursalDTO;
import com.franquicia.prueba.service.SucursalService;
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

@WebMvcTest(SucursalController.class)
class SucursalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SucursalService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeListarSucursales() throws Exception {

        SucursalDTO dto = new SucursalDTO();

        dto.setId(1L);
        dto.setNombre("Sucursal Norte");

        when(service.listar())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/sucursales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre")
                        .value("Sucursal Norte"));
    }

    @Test
    void debeObtenerSucursalPorId() throws Exception {

        Long id = 1L;

        SucursalDTO dto = new SucursalDTO();

        dto.setId(id);
        dto.setNombre("Sucursal Norte");

        when(service.obtener(id))
                .thenReturn(dto);

        mockMvc.perform(get("/sucursales/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre")
                        .value("Sucursal Norte"));
    }

    @Test
    void debeCrearSucursal() throws Exception {

        SucursalDTO request = new SucursalDTO();

        request.setNombre("Sucursal Norte");

        SucursalDTO response = new SucursalDTO();

        response.setId(1L);
        response.setNombre("Sucursal Norte");

        when(service.guardar(any(SucursalDTO.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/sucursales")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(1))
                .andExpect(jsonPath("$.nombre")
                        .value("Sucursal Norte"));
    }

    @Test
    void debeActualizarParcialmente() throws Exception {

        Long id = 1L;

        SucursalDTO existente = new SucursalDTO();

        existente.setId(id);
        existente.setNombre("Sucursal Antigua");

        SucursalDTO response = new SucursalDTO();

        response.setId(id);
        response.setNombre("Sucursal Nueva");

        when(service.obtener(id))
                .thenReturn(existente);

        when(service.actualizarParcial(
                eq(id),
                any(SucursalDTO.class)))
                .thenReturn(response);

        SucursalDTO request = new SucursalDTO();

        request.setNombre("Sucursal Nueva");

        mockMvc.perform(
                        patch("/sucursales/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre")
                        .value("Sucursal Nueva"));
    }

    @Test
    void debeEliminarSucursal() throws Exception {

        mockMvc.perform(delete("/sucursales/{id}", 1L))
                .andExpect(status().isOk());
    }
}
