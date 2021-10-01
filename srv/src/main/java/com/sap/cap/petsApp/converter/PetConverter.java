package com.sap.cap.petsApp.converter;

import cds.gen.petsservice.CatsView;
import cds.gen.petsservice.DogsView;
import cds.gen.petsservice.Pets;
import com.sap.cap.petsApp.dto.model.PetsDto;
import org.springframework.stereotype.Component;

@Component
public class PetConverter {

    public Pets toEntity(PetsDto petsDto) {

        Pets pet = Pets.create();
        pet.setId(petsDto.getId());
        pet.setName(petsDto.getName());
        pet.setPetKind(petsDto.getPetKind());
        pet.setUserId(petsDto.getUser_ID());

        return pet;
    }

    public Pets toEntity(CatsView cat) {

        Pets pet = Pets.create();
        pet.setId(cat.getPetId());
        pet.setName(cat.getName());
        pet.setPetKind(cat.getPetKind());
        pet.setUserId(cat.getUserId());

        return pet;
    }

    public Pets toEntity(DogsView dog) {

        Pets pet = Pets.create();
        pet.setId(dog.getPetId());
        pet.setName(dog.getName());
        pet.setPetKind(dog.getPetKind());
        pet.setUserId(dog.getUserId());

        return pet;
    }
}
