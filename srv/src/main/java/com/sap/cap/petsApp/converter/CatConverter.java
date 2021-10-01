package com.sap.cap.petsApp.converter;

import cds.gen.petsservice.CatsView;
import cds.gen.petsservice.Pets;
import cds.gen.sap.cap.petsapp.Cats;
import com.sap.cap.petsApp.dto.model.CatsDto;
import org.springframework.stereotype.Component;

@Component
public class CatConverter {

    public Cats toEntity(CatsDto catsDto, Pets pet) {

        Cats cat = Cats.create();
        cat.setId(catsDto.getId());
        cat.setGender(catsDto.getGender());
        cat.setPetId(pet.getId());

        return cat;
    }

    public Cats toEntity(CatsView catsView) {

        Cats cat = Cats.create();
        cat.setId(catsView.getId());
        cat.setGender(catsView.getGender());
        cat.setPetId(catsView.getPetId());

        return cat;
    }

    public CatsView toCatsView(Pets pet, Cats cat) {

        CatsView catView = CatsView.create();
        catView.setId(cat.getId());
        catView.setGender(cat.getGender());
        catView.setPetId(pet.getId());

        return catView;
    }

    public CatsView update(CatsDto catsDto, CatsView catView) {

        catView.setGender(catsDto.getGender());
        catView.setName(catsDto.getName());
        catView.setUserId(catsDto.getUser_ID());

        return catView;
    }
}
