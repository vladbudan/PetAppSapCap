package com.sap.cap.petsApp.converter;

import cds.gen.petsservice.DogsView;
import cds.gen.petsservice.Pets;
import cds.gen.sap.cap.petsapp.Dogs;
import com.sap.cap.petsApp.dto.model.DogsDto;
import org.springframework.stereotype.Component;

@Component
public class DogConverter {

    public Dogs toEntity(DogsDto dogsDto, Pets pet) {

        Dogs dog = Dogs.create();
        dog.setId(dogsDto.getId());
        dog.setColour(dogsDto.getColour());
        dog.setPetId(pet.getId());

        return dog;
    }

    public Dogs toEntity(DogsView dogsView) {

        Dogs dog = Dogs.create();
        dog.setId(dogsView.getId());
        dog.setPetId(dogsView.getPetId());
        dog.setColour(dogsView.getColour());

        return dog;
    }

    public DogsView toDogsVIew(Pets pet, Dogs dog) {

        DogsView dogView = DogsView.create();
        dogView.setId(dog.getId());
        dogView.setColour(dog.getColour());
        dogView.setPetId(pet.getId());

        return dogView;
    }

    public DogsView update(DogsDto dogsDto, DogsView dogView) {

        dogView.setName(dogsDto.getName());
        dogView.setColour(dogsDto.getColour());
        dogView.setUserId(dogsDto.getUser_ID());

        return dogView;
    }
}
