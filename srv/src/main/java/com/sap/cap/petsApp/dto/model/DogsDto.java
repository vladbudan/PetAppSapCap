package com.sap.cap.petsApp.dto.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class DogsDto extends PetsDto {

    private String colour;
}
