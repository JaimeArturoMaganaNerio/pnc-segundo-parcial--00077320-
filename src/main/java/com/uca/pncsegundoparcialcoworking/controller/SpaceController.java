package com.uca.pncsegundoparcialcoworking.controller;

import com.uca.pncsegundoparcialcoworking.dto.SpaceRequestDTO;
import com.uca.pncsegundoparcialcoworking.dto.SpaceResponseDTO;
import com.uca.pncsegundoparcialcoworking.service.SpaceService;
import com.uca.pncsegundoparcialcoworking.spaceenum.SpaceEnum;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaces")
public class SpaceController {

    private final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping
    public ResponseEntity<SpaceResponseDTO> createSpace(@Valid @RequestBody SpaceRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceService.crearSpace(dto));
    }

    @GetMapping
    public ResponseEntity<List<SpaceResponseDTO>> getAllSpaces(
            @RequestParam(required = false) SpaceEnum type,
            @RequestParam(required = false) Boolean available) {
        return ResponseEntity.ok(spaceService.getSpaces(type, available));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpaceResponseDTO> getSpaceById(@PathVariable Long id) {
        return ResponseEntity.ok(spaceService.getSpaceForId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpaceResponseDTO> updateSpace(
            @PathVariable Long id,
            @Valid @RequestBody SpaceRequestDTO dto) {
        return ResponseEntity.ok(spaceService.updateSpace(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return ResponseEntity.noContent().build();
    }
}