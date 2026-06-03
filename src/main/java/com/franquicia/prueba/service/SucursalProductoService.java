package com.franquicia.prueba.service;

import com.franquicia.prueba.dto.ProductoMayorStockDTO;
import com.franquicia.prueba.dto.SucursalProductoDTO;
import com.franquicia.prueba.entity.SucursalProducto;
import com.franquicia.prueba.mappers.ProductoMapper;
import com.franquicia.prueba.mappers.SucursalMapper;
import com.franquicia.prueba.mappers.SucursalProductoMapper;
import com.franquicia.prueba.repository.SucursalProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SucursalProductoService {

    private final SucursalProductoRepository repository;
    private final SucursalProductoMapper mapper;


    public List<SucursalProductoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public SucursalProductoDTO obtener(Long id) {
        SucursalProducto entity =
        repository.findById(id)
        .orElseThrow(() ->  new RuntimeException("Registro no encontrado"));
        return mapper.toDTO(entity);
    }

    public SucursalProductoDTO guardar(SucursalProductoDTO dto) {
        SucursalProducto entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDTO(entity);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public SucursalProductoDTO actualizarParcial(Long id,SucursalProductoDTO dto) {
        SucursalProducto entity =
        repository.findById(id).orElseThrow(() ->
        new RuntimeException("Registro no encontrado"));
        if (dto.getSucursal() != null) {
            SucursalMapper sucursalMapper = new SucursalMapper();
            entity.setSucursal(sucursalMapper.toEntity(dto.getSucursal()));
        }
        if (dto.getProducto() != null) {
            ProductoMapper productoMapper = new ProductoMapper();
            entity.setProducto(productoMapper.toEntity(dto.getProducto()));
        }
        if (dto.getStock() != null) {
            entity.setStock(dto.getStock());
        }
        entity = repository.save(entity);
        return mapper.toDTO(entity);
    }

    public SucursalProductoDTO actualizarStock(Long id, Integer nuevo_stock) {
        SucursalProducto entity =
        repository.findById(id).orElseThrow(() ->
        new RuntimeException("Registro no encontrado"));
        entity.setStock(nuevo_stock);
        entity = repository.save(entity);
        return mapper.toDTO(entity);
    }

    public List<ProductoMayorStockDTO>
    obtenerProductosMayorStockPorFranquicia(Long franquiciaId) {
        return repository.obtenerProductosMayorStockPorFranquicia(franquiciaId);
    }
}
