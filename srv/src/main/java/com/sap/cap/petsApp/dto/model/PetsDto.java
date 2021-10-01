package com.sap.cap.petsApp.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public abstract class PetsDto {

    @JsonProperty("ID")
    private String id;

    private String name;

    private String petKind;

    private String user_ID;
}
