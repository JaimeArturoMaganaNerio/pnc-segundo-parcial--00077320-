package com.uca.pncsegundoparcialcoworking.dto;

import com.uca.pncsegundoparcialcoworking.spaceenum.SpaceEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceRequestDTO {

 @NotBlank(message = "El nombre es requerido y no puede estar vacío")
 private String name;

 private String description;

 @NotNull(message = "El tipo de espacio es requerido")
 private SpaceEnum type;

 @NotNull(message = "La capacidad es obligatoria")
 @Min(value = 1, message = "La capacidad mínima es de 1 persona")
 private Integer capacity;

 @NotNull(message = "El precio por hora es obligatorio")
 @DecimalMin(value = "0.01", message = "El precio por hora debe ser mayor a 0")
 private BigDecimal pricePerHour;

 private Boolean available = true;

 @NotNull(message = "El piso es obligatorio")
 @Min(value = 0, message = "El número de piso no puede ser negativo")
 private Integer floor;

 private String amenities;
}