package com.sap.cap.petsApp.service;

import cds.gen.petsservice.CatsView;
import cds.gen.petsservice.DogsView;
import cds.gen.petsservice.Pets;
import com.sap.cap.petsApp.dto.model.PetsDto;

public interface PetService {

    Pets findById(String id);

    Pets create(PetsDto petsDto);

    Pets update(CatsView catsView);

    Pets update(DogsView dogsView);

    void swapPets(String firstPetId, String secondPetId);
}
