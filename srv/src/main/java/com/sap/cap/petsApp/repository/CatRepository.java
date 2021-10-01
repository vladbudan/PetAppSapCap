package com.sap.cap.petsApp.repository;

import cds.gen.petsservice.CatsView;
import cds.gen.sap.cap.petsapp.Cats;

import java.util.Optional;

public interface CatRepository {

    Optional<CatsView> findById(String id);

    Cats save(Cats cat);

    Cats update(Cats cat);
}
