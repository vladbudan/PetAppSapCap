package com.sap.cap.petsApp.handler;

import cds.gen.petsservice.DogsView;
import cds.gen.petsservice.DogsView_;
import cds.gen.petsservice.PetsService_;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cap.petsApp.dto.model.DogsDto;
import com.sap.cap.petsApp.service.DogService;
import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsUpdateEventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.sap.cds.services.cds.CdsService.EVENT_CREATE;
import static com.sap.cds.services.cds.CdsService.EVENT_UPDATE;

@Slf4j
@Component
@RequiredArgsConstructor
@ServiceName(PetsService_.CDS_NAME)
public class DogHandler implements EventHandler {

    private final DogService dogService;
    private final ObjectMapper objectMapper;

    @On(event = EVENT_CREATE, entity = DogsView_.CDS_NAME)
    public void create(CdsCreateEventContext context) {
        log.info("create() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach((m) -> {
            DogsDto dogsDto = objectMapper.convertValue(m, DogsDto.class);
            DogsView dogs = dogService.create(dogsDto);
            m.put(DogsView.ID, dogs.getId());
        });

        context.setResult(context.getCqn().entries());
    }

    @On(event = EVENT_UPDATE, entity = DogsView_.CDS_NAME)
    public void update(CdsUpdateEventContext context) {
        log.info("update() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach((m) -> {
            DogsDto dogsDto = objectMapper.convertValue(m, DogsDto.class);
            dogService.update(dogsDto);
        });

        context.setResult(context.getCqn().entries());
    }
}
