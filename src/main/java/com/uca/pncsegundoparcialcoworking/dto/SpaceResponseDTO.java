package com.uca.pncsegundoparcialcoworking.dto;


import com.uca.pncsegundoparcialcoworking.entity.SpaceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceResponseDTO {


    private String name;
    private String description;
    private Enum type;
    private Integer capacity;
    private BigDecimal pricePerHour;
    private Boolean available;
    private Integer floor;
    private String amenities;


    public static SpaceResponseDTO fromEntity(SpaceEntity space){
        return new SpaceResponseDTO(
                space.getName(),
                space.getDescription(),
                space.getType(),
                space.getCapacity(),
                space.getPricePerHour(),
                space.getAvailable(),
                space.getFloor(),
                space.getAmenities()
        );


    }

}
