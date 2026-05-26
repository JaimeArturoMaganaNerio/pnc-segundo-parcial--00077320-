package com.uca.pncsegundoparcialcoworking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "spaceEntity")
@AllArgsConstructor
@NoArgsConstructor
public class SpaceEntity {

    @Id
    @GeneratedValue
    Long id;

    @Column(name ="Name")
    String name;

    @Column(name ="description")
    String description;

    @Column(name ="type")
    Enum type;

    @Column(name = "capacity")
    Integer capacity;

    @Column(name = "pricePerHour")
    BigDecimal pricePerHour;

    @Column(name = "available")
    Boolean available;

    @Column(name = "Floor")
    Integer floor;

    @Column(name = "amenities")
    String amenities;


}
