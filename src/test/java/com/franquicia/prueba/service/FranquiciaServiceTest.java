package com.franquicia.prueba.service;

import com.franquicia.prueba.dto.FranquiciaDTO;
import com.franquicia.prueba.entity.Franquicia;
import com.franquicia.prueba.mappers.FranquiciaMapper;
import com.franquicia.prueba.repository.FranquiciaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FranquiciaServiceTest {

    @Mock
    private FranquiciaRepository repository;

    @Mock
    private FranquiciaMapper mapper;

    @InjectMocks
    private FranquiciaService service;

    @Test
    void debeActualizarParcialmenteFranquicia() {

        Long id = 1L;

        Franquicia franquicia = new Franquicia();
        franquicia.setId(id);
        franquicia.setNombre("Burger House");

        FranquiciaDTO request = new FranquiciaDTO();
        request.setNombre("Burger World");

        FranquiciaDTO response = new FranquiciaDTO();

        response.setId(id);
        response.setNombre("Burger World");

        when(repository.findById(id)).thenReturn(Optional.of(franquicia));

        when(repository.save(any(Franquicia.class))).thenReturn(franquicia);

        when(mapper.toDTO(any(Franquicia.class))).thenReturn(response);

        FranquiciaDTO resultado = service.actualizarParcial(id, request);

        assertNotNull(resultado);

        assertEquals("Burger World", resultado.getNombre());

        verify(repository).findById(id);

        verify(repository).save(any(Franquicia.class));
    }
}
