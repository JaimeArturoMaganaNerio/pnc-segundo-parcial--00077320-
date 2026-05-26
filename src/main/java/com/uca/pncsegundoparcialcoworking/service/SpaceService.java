package com.uca.pncsegundoparcialcoworking.service;

import com.uca.pncsegundoparcialcoworking.dto.SpaceRequestDTO;
import com.uca.pncsegundoparcialcoworking.dto.SpaceResponseDTO;
import com.uca.pncsegundoparcialcoworking.entity.SpaceEntity;
import com.uca.pncsegundoparcialcoworking.spaceenum.SpaceEnum;
import com.uca.pncsegundoparcialcoworking.exception.BusinessRuleException;
import com.uca.pncsegundoparcialcoworking.exception.ResourceNotFoundException;
import com.uca.pncsegundoparcialcoworking.repository.ISpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceService {

    private final ISpaceRepository spaceRepository;

    public SpaceService(ISpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public SpaceResponseDTO crearSpace(SpaceRequestDTO dto) {
        if (spaceRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new BusinessRuleException("Ya existe un espacio registrado con el nombre: " + dto.getName());
        }

        SpaceEntity space = new SpaceEntity();
        space.setName(dto.getName());
        space.setDescription(dto.getDescription());
        space.setType(dto.getType());
        space.setCapacity(dto.getCapacity());
        space.setPricePerHour(dto.getPricePerHour());
        space.setAvailable(dto.getAvailable());
        space.setAmenities(dto.getAmenities());
        space.setFloor(dto.getFloor());

        return SpaceResponseDTO.fromEntity(spaceRepository.save(space));
    }

    public List<SpaceResponseDTO> getSpaces(SpaceEnum type, Boolean available) {
        List<SpaceEntity> entities;

        if (type != null && available != null) {
            entities = spaceRepository.findByTypeAndAvailable(type, available);
        } else if (type != null) {
            entities = spaceRepository.findByType(type);
        } else if (available != null) {
            entities = spaceRepository.findByAvailable(available);
        } else {
            entities = spaceRepository.findAll();
        }

        return entities.stream().map(SpaceResponseDTO::fromEntity).collect(Collectors.toList());
    }

    public SpaceResponseDTO getSpaceForId(Long id) {
        SpaceEntity space = spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Espacio no encontrado con el ID: " + id));
        return SpaceResponseDTO.fromEntity(space);
    }

    public SpaceResponseDTO updateSpace(Long id, SpaceRequestDTO dto) {
        SpaceEntity space = spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Espacio no encontrado con el ID: " + id));


        if (!space.getName().equalsIgnoreCase(dto.getName()) && spaceRepository.existsByNameIgnoreCase(dto.getName())) {
            throw new BusinessRuleException("Ya existe otro espacio registrado con el nombre: " + dto.getName());
        }

        space.setName(dto.getName());
        space.setDescription(dto.getDescription());
        space.setType(dto.getType());
        space.setCapacity(dto.getCapacity());
        space.setPricePerHour(dto.getPricePerHour());
        space.setAvailable(dto.getAvailable());
        space.setFloor(dto.getFloor());
        space.setAmenities(dto.getAmenities());

        return SpaceResponseDTO.fromEntity(spaceRepository.save(space));
    }

    public void deleteSpace(Long id) {
        SpaceEntity space = spaceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Espacio no encontrado con el ID: " + id));

        if (space.getAvailable() != null && !space.getAvailable()) {
            throw new BusinessRuleException("No se puede eliminar un espacio que se encuentra en uso (available = false)");
        }

        spaceRepository.delete(space);
    }
}