package com.uca.pncsegundoparcialcoworking.repository;

import com.uca.pncsegundoparcialcoworking.entity.SpaceEntity;
import com.uca.pncsegundoparcialcoworking.spaceenum.SpaceEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISpaceRepository extends JpaRepository<SpaceEntity, Long> {
    boolean existsByNameIgnoreCase(String name);


    List<SpaceEntity> findByType(SpaceEnum type);
    List<SpaceEntity> findByAvailable(Boolean available);
    List<SpaceEntity> findByTypeAndAvailable(SpaceEnum type, Boolean available);
}