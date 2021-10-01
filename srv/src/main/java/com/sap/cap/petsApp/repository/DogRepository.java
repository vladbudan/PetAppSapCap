package com.sap.cap.petsApp.repository;

import cds.gen.petsservice.DogsView;
import cds.gen.sap.cap.petsapp.Dogs;

import java.util.Optional;

public interface DogRepository {

    Optional<DogsView> findById(String id);

    Dogs save(Dogs dog);

    Dogs update(Dogs dog);
}
