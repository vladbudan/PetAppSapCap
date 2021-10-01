package com.sap.cap.petsApp.repository.Impl;

import cds.gen.petsservice.Pets;
import cds.gen.petsservice.Pets_;
import com.sap.cap.petsApp.repository.PetRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PetRepositoryImpl implements PetRepository {

    private final PersistenceService db;

    @Override
    public Optional<Pets> findById(String id) {
        CqnSelect findById  = Select.from(Pets_.class)
                .where(p -> p.ID().eq(id));

        return db.run(findById)
                .first(Pets.class);
    }

    @Override
    public Pets save(Pets pet) {
        CqnInsert insert = Insert.into(Pets_.class)
                .entry(pet);

        pet.setId(db.run(insert)
                .single(Pets.class)
                .getId());

        return pet;
    }

    @Override
    public Pets update(Pets pet) {
        CqnUpdate update = Update.entity(Pets_.class)
                .data(pet);

        return db.run(update)
                .single(Pets.class);
    }

    @Override
    public List<Pets> update(List<Pets> pets) {
        CqnUpdate update = Update.entity(Pets_.class)
                .entries(pets);

        return db.run(update)
                .listOf(Pets.class);
    }
}
