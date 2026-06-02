package com.franquicia.prueba.service;


import com.franquicia.prueba.dto.ProductoDTO;
import com.franquicia.prueba.dto.SucursalDTO;
import com.franquicia.prueba.dto.SucursalProductoDTO;
import com.franquicia.prueba.entity.Producto;
import com.franquicia.prueba.entity.Sucursal;
import com.franquicia.prueba.entity.SucursalProducto;
import com.franquicia.prueba.mappers.ProductoMapper;
import com.franquicia.prueba.mappers.SucursalMapper;
import com.franquicia.prueba.mappers.SucursalProductoMapper;
import com.franquicia.prueba.repository.SucursalProductoRepository;
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
class SucursalProductoServiceTest {

    @Mock
    private SucursalProductoRepository repository;

    @Mock
    private SucursalProductoMapper mapper;

    @InjectMocks
    private SucursalProductoService service;

    @Test
    void debeActualizarParcialmente() {

        Long id = 1L;

        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);
        sucursal.setNombre("Sucursal Norte");

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Hamburguesa");

        SucursalProducto entity =
                new SucursalProducto();

        entity.setId(id);
        entity.setSucursal(sucursal);
        entity.setProducto(producto);
        entity.setStock(100);

        SucursalProductoDTO request =
                new SucursalProductoDTO();

        request.setStock(200);

        SucursalProducto actualizado =
                new SucursalProducto();

        actualizado.setId(id);
        actualizado.setSucursal(sucursal);
        actualizado.setProducto(producto);
        actualizado.setStock(200);

        SucursalDTO sucursalDTO = new SucursalDTO();
        sucursalDTO.setId(1L);
        sucursalDTO.setNombre("Sucursal Norte");

        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(1L);
        productoDTO.setNombre("Hamburguesa");

        SucursalProductoDTO response =  new SucursalProductoDTO();


        response.setId(id);
        response.setSucursal(sucursalDTO);
        response.setProducto(productoDTO);
        response.setStock(200);

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        when(repository.save(any(SucursalProducto.class)))
                .thenReturn(actualizado);

        when(mapper.toDTO(any(SucursalProducto.class)))
                .thenReturn(response);

        SucursalProductoDTO resultado =
                service.actualizarParcial(id, request);

        assertNotNull(resultado);

        assertEquals(
                200,
                resultado.getStock());

        verify(repository).findById(id);

        verify(repository)
                .save(any(SucursalProducto.class));
    }
}