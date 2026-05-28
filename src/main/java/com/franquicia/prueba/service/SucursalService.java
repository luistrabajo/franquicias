package com.franquicia.prueba.service;

import com.franquicia.prueba.dto.SucursalDTO;
import com.franquicia.prueba.entity.Sucursal;
import com.franquicia.prueba.mappers.FranquiciaMapper;
import com.franquicia.prueba.mappers.SucursalMapper;
import com.franquicia.prueba.repository.SucursalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SucursalService {

    private final SucursalRepository repository;
    private final SucursalMapper mapper;
    private final FranquiciaMapper fMapper;

    // LISTAR
    public List<SucursalDTO> listar() {

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    // OBTENER
    public SucursalDTO obtener(Long id) {

        Sucursal sucursal = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sucursal no encontrada"));

        return mapper.toDTO(sucursal);
    }

    // CREAR
    public SucursalDTO guardar(SucursalDTO dto) {

        Sucursal entity = mapper.toEntity(dto);

        entity = repository.save(entity);

        return mapper.toDTO(entity);
    }

    // ELIMINAR
    public void eliminar(Long id) {

        repository.deleteById(id);
    }

    // PATCH PARCIAL
    public SucursalDTO actualizarParcial(
            Long id,
            SucursalDTO dto) {

        Sucursal sucursal = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sucursal no encontrada"));

        if (dto.getNombre() != null) {
            sucursal.setNombre(dto.getNombre());
        }

        if (dto.getDireccion() != null) {
            sucursal.setDireccion(dto.getDireccion());
        }

        if (dto.getCiudad() != null) {
            sucursal.setCiudad(dto.getCiudad());
        }

        if (dto.getTelefono() != null) {
            sucursal.setTelefono(dto.getTelefono());
        }

        if (dto.getFranquiciaDTO() != null) {
            sucursal.setFranquicia(fMapper.toEntity(dto.getFranquiciaDTO()));
        }

        Sucursal actualizada = repository.save(sucursal);

        return mapper.toDTO(actualizada);
    }
}
