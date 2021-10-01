package com.sap.cap.petsApp.handler;

import cds.gen.petsservice.CatsView;
import cds.gen.petsservice.CatsView_;
import cds.gen.petsservice.PetsService_;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cap.petsApp.dto.model.CatsDto;
import com.sap.cap.petsApp.service.CatService;
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
public class CatHandler implements EventHandler {

    private final CatService catService;
    private final ObjectMapper objectMapper;

    @On(event = EVENT_CREATE, entity = CatsView_.CDS_NAME)
    public void create(CdsCreateEventContext context) {
        log.info("createCats() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach((m) -> {
            CatsDto catsDto = objectMapper.convertValue(m, CatsDto.class);
            CatsView cats = catService.create(catsDto);
            m.put(CatsView.ID, cats.getId());
        });

        context.setResult(context.getCqn().entries());
    }

    @On(event = EVENT_UPDATE, entity = CatsView_.CDS_NAME)
    public void update(CdsUpdateEventContext context) {
        log.info("updateCats() with context : " + context.getCqn().entries());
        context.getCqn().entries().forEach((m) -> {
            CatsDto catsDto = objectMapper.convertValue(m, CatsDto.class);
            catService.update(catsDto);
        });

        context.setResult(context.getCqn().entries());
    }
}
