package com.franquicia.prueba.controller;

import com.franquicia.prueba.dto.FranquiciaDTO;
import com.franquicia.prueba.service.FranquiciaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FranquiciaController.class)
class FranquiciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FranquiciaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void debeActualizarParcialmente() throws Exception {

        Long id = 1L;

        FranquiciaDTO request = new FranquiciaDTO();
        request.setNombre("Burger World");

        FranquiciaDTO franquiciaExistente = new FranquiciaDTO();
        franquiciaExistente.setId(id);
        franquiciaExistente.setNombre("Burger House");

        FranquiciaDTO response = new FranquiciaDTO();
        response.setId(id);
        response.setNombre("Burger World");

        when(service.obtener(id))
                .thenReturn(franquiciaExistente);

        when(service.actualizarParcial(
                eq(id),
                any(FranquiciaDTO.class)))
                .thenReturn(response);

        mockMvc.perform(
                        patch("/franquicias/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre")
                        .value("Burger World"));
    }
}
