package com.sap.cap.petsApp.service;

import cds.gen.petsservice.CatsView;
import com.sap.cap.petsApp.dto.model.CatsDto;

public interface CatService {

    CatsView findById(String id);

    CatsView create(CatsDto catsDto);

    CatsView update(CatsDto catsDto);
}
