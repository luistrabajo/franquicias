package com.franquicia.prueba.service;


import com.franquicia.prueba.dto.ProductoDTO;
import com.franquicia.prueba.entity.Producto;
import com.franquicia.prueba.mappers.ProductoMapper;
import com.franquicia.prueba.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository repository;

    @Mock
    private ProductoMapper mapper;

    @InjectMocks
    private ProductoService service;

    @Test
    void debeActualizarParcialmenteProducto() {

        Long id = 1L;

        Producto producto = new Producto();

        producto.setId(id);
        producto.setNombre("Hamburguesa");


        ProductoDTO request = new ProductoDTO();


        Producto actualizado = new Producto();

        actualizado.setId(id);
        actualizado.setNombre("Hamburguesa de pollo");


        ProductoDTO response = new ProductoDTO();

        response.setId(id);
        response.setNombre("Hamburguesa de pollo");


        when(repository.findById(id))
                .thenReturn(Optional.of(producto));

        when(repository.save(any(Producto.class)))
                .thenReturn(actualizado);

        when(mapper.toDTO(any(Producto.class)))
                .thenReturn(response);

        ProductoDTO resultado =
                service.actualizarParcial(id, request);

        assertNotNull(resultado);

        assertEquals(
                "Hamburguesa de pollo",
                resultado.getNombre());

        verify(repository).findById(id);

        verify(repository).save(any(Producto.class));
    }
}
