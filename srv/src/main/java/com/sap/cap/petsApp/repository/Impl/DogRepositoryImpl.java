package com.sap.cap.petsApp.repository.Impl;

import cds.gen.petsservice.DogsView;
import cds.gen.petsservice.DogsView_;
import cds.gen.sap.cap.petsapp.Dogs;
import cds.gen.sap.cap.petsapp.Dogs_;
import com.sap.cap.petsApp.repository.DogRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.ql.cqn.CqnUpdate;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DogRepositoryImpl implements DogRepository {

    private final PersistenceService db;

    @Override
    public Optional<DogsView> findById(String id) {
        Select<DogsView_> findById = Select.from(DogsView_.class)
                .where(dog -> dog.ID().eq(id));

        return db.run(findById)
                .first(DogsView.class);
    }

    @Override
    public Dogs save(Dogs dog) {
        CqnInsert insertDog = Insert.into(Dogs_.class)
                .entry(dog);

        return db.run(insertDog)
                .single(Dogs.class);
    }

    @Override
    public Dogs update(Dogs dog) {
        CqnUpdate updateDog = Update.entity(Dogs_.class)
                .data(dog);

        return db.run(updateDog)
                .single(Dogs.class);
    }
}
