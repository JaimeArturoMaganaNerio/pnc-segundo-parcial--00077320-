package com.uca.pncsegundoparcialcoworking.service;

import com.uca.pncsegundoparcialcoworking.dto.SpaceRequestDTO;
import com.uca.pncsegundoparcialcoworking.dto.SpaceResponseDTO;
import com.uca.pncsegundoparcialcoworking.entity.SpaceEntity;
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

    public SpaceResponseDTO  crearSpace(SpaceRequestDTO spaceRequestDTO){
        SpaceEntity space = new SpaceEntity();
        space.setName(spaceRequestDTO.getName());
        space.setDescription(spaceRequestDTO.getDescription());
        space.setType(spaceRequestDTO.getType());
        space.setCapacity(spaceRequestDTO.getCapacity());
        space.setPricePerHour(spaceRequestDTO.getPricePerHour());
        space.setAvailable(spaceRequestDTO.getAvailable());
        space.setAmenities(spaceRequestDTO.getAmenities());
        space.setFloor(spaceRequestDTO.getFloor());

        SpaceEntity spaceSave = spaceRepository.save(space);

        return SpaceResponseDTO.fromEntity(spaceSave);

    }


    public List<SpaceResponseDTO> getSpace(){
        return spaceRepository.findAll()
                .stream()
                .map(SpaceResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }


    public SpaceResponseDTO getSpaceForId (Long id){
        SpaceEntity space = spaceRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("spacio no escontrado" + id));
        return SpaceResponseDTO.fromEntity(space);

    }




}
