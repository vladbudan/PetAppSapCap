package com.sap.cap.petsApp.repository;

import cds.gen.petsservice.Pets;

import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Optional<Pets> findById(String id);

    Pets save(Pets pet);

    Pets update(Pets pet);

    List<Pets> update(List<Pets> pets);
}
