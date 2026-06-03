package com.franquicia.prueba.service;

import com.franquicia.prueba.dto.ProductoDTO;
import com.franquicia.prueba.entity.Producto;
import com.franquicia.prueba.mappers.ProductoMapper;
import com.franquicia.prueba.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoService {

    private final ProductoRepository repository;
    private final ProductoMapper mapper;


    public List<ProductoDTO> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public ProductoDTO obtener(Long id) {
        Producto producto =
        repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapper.toDTO(producto);
    }

    public ProductoDTO guardar(ProductoDTO dto) {
        Producto entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDTO(entity);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public ProductoDTO actualizarParcial(Long id,ProductoDTO dto) {

        Producto producto =
        repository.findById(id).orElseThrow(() ->new RuntimeException("Producto no encontrado"));
        if (dto.getNombre() != null) {
            producto.setNombre(dto.getNombre());
        }
        if (dto.getSku() != null) {
            producto.setSku(dto.getSku());
        }
        if (dto.getDescripcion() != null) {
            producto.setDescripcion(dto.getDescripcion());
        }
        if (dto.getCreatedAt() != null) {
            producto.setCreatedAt(dto.getCreatedAt());
        }
        Producto actualizado = repository.save(producto);
        return mapper.toDTO(actualizado);
    }
}
