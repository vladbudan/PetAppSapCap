package com.sap.cap.petsApp.service.Impl;

import cds.gen.petsservice.CatsView;
import cds.gen.petsservice.Pets;
import cds.gen.sap.cap.petsapp.Cats;
import com.sap.cap.petsApp.converter.CatConverter;
import com.sap.cap.petsApp.dto.model.CatsDto;
import com.sap.cap.petsApp.repository.CatRepository;
import com.sap.cap.petsApp.service.CatService;
import com.sap.cap.petsApp.service.PetService;
import com.sap.cds.services.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sap.cds.services.ErrorStatuses.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final PetService petService;
    private final CatConverter catConverter;

    @Override
    public CatsView findById(String id) {

        Optional<CatsView> optionalCatById = catRepository.findById(id);

        return optionalCatById.orElseThrow(() -> new ServiceException(NOT_FOUND,
                "Cat with such id not exist!"));
    }

    @Override
    public CatsView create(CatsDto catsDto) {

        Pets newPet = petService.create(catsDto);
        Cats cat = catConverter.toEntity(catsDto, newPet);
        Cats newCat = catRepository.save(cat);

        return catConverter.toCatsView(newPet, newCat);
    }

    @Override
    public CatsView update(CatsDto catsDto) {

        CatsView oldCat = findById(catsDto.getId());
        CatsView cat = catConverter.update(catsDto, oldCat);
        Cats newCat = catConverter.toEntity(cat);

        Pets updatedPet = petService.update(cat);
        Cats updatedCat = catRepository.update(newCat);

        return catConverter.toCatsView(updatedPet, updatedCat);
    }
}
