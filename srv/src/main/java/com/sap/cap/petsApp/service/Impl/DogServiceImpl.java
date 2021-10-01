package com.sap.cap.petsApp.service.Impl;

import cds.gen.petsservice.DogsView;
import cds.gen.petsservice.Pets;
import cds.gen.sap.cap.petsapp.Dogs;
import com.sap.cap.petsApp.converter.DogConverter;
import com.sap.cap.petsApp.dto.model.DogsDto;
import com.sap.cap.petsApp.repository.DogRepository;
import com.sap.cap.petsApp.service.DogService;
import com.sap.cap.petsApp.service.PetService;
import com.sap.cds.services.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.sap.cds.services.ErrorStatuses.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;
    private final PetService petService;
    private final DogConverter dogConverter;

    @Override
    public DogsView findById(String id) {

        Optional<DogsView> optionalDogId = dogRepository.findById(id);

        return optionalDogId.orElseThrow(() -> new ServiceException(NOT_FOUND,
                "Dog with such id not found!"));
    }

    @Override
    public DogsView create(DogsDto dogsDto) {

        Pets pet = petService.create(dogsDto);
        Dogs dog = dogConverter.toEntity(dogsDto, pet);
        Dogs newDog = dogRepository.save(dog);

        return dogConverter.toDogsVIew(pet, newDog);
    }

    @Override
    public DogsView update(DogsDto dogsDto) {

        DogsView dog = findById(dogsDto.getId());
        dogConverter.update(dogsDto, dog);
        Dogs newDog = dogConverter.toEntity(dog);

        Pets updatedPet = petService.update(dog);
        Dogs updatedDog = dogRepository.update(newDog);

        return dogConverter.toDogsVIew(updatedPet, updatedDog);
    }
}
