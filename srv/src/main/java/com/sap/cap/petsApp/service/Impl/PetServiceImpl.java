package com.sap.cap.petsApp.service.Impl;

import cds.gen.petsservice.CatsView;
import cds.gen.petsservice.DogsView;
import cds.gen.petsservice.Pets;
import com.sap.cap.petsApp.converter.PetConverter;
import com.sap.cap.petsApp.dto.model.PetsDto;
import com.sap.cap.petsApp.repository.PetRepository;
import com.sap.cap.petsApp.service.PetService;
import com.sap.cds.services.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.sap.cds.services.ErrorStatuses.BAD_REQUEST;
import static com.sap.cds.services.ErrorStatuses.NOT_FOUND;
import static java.util.Arrays.asList;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final PetConverter petConverter;

    @Override
    public Pets findById(String id) {
        return  petRepository.findById(id)
                .orElseThrow(() -> new ServiceException(NOT_FOUND, "Pet with such id not exist!"));
    }

    @Override
    public Pets create(PetsDto petsDto) {
        Pets newPet = petConverter.toEntity(petsDto);

        return petRepository.save(newPet);
    }

    @Override
    public Pets update(CatsView catsView) {
        Pets newPet = petConverter.toEntity(catsView);

        return petRepository.update(newPet);
    }

    @Override
    public Pets update(DogsView dogsView) {
        Pets newPet = petConverter.toEntity(dogsView);

        return petRepository.update(newPet);
    }

    @Override
    @Transactional
    public void swapPets(String firstPetId, String secondPetId) {

        if(Objects.equals(firstPetId, secondPetId)) {
            throw new ServiceException(BAD_REQUEST, "To swap pets there are should be different pets!");
        }

        Pets firstPet = findById(firstPetId);
        Pets secondPet = findById(secondPetId);

        if(!Objects.equals(firstPet.getUserId(), secondPet.getUserId())) {
            throw new ServiceException(BAD_REQUEST, "To swap pets there are should be two different users!");
        }

        String firstUserId = firstPet.getUserId();
        String secondUserId = secondPet.getUserId();

        firstPet.setUserId(secondUserId);
        secondPet.setUserId(firstUserId);

        petRepository.update(asList(firstPet, secondPet));
    }
}
