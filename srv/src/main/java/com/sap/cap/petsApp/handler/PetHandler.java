package com.sap.cap.petsApp.handler;

import cds.gen.petsservice.PetsService_;
import cds.gen.petsservice.SwapPetsContext;
import com.sap.cap.petsApp.service.PetService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ServiceName(PetsService_.CDS_NAME)
public class PetHandler implements EventHandler {

    private final PetService petService;

    @On(event = "swapPets")
    public void swapPets(SwapPetsContext context) {

        String firstPetId = context.getFirstPetId();
        String secondPetId = context.getSecondPetId();

        petService.swapPets(firstPetId, secondPetId);
        context.setCompleted();
    }
}
