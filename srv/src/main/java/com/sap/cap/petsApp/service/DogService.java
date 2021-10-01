package com.sap.cap.petsApp.service;

import cds.gen.petsservice.DogsView;
import com.sap.cap.petsApp.dto.model.DogsDto;

public interface DogService {

    DogsView findById(String id);

    DogsView create(DogsDto dogsDto);

    DogsView update(DogsDto dogsDto);
}
