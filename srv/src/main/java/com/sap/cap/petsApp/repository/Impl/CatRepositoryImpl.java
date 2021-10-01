package com.sap.cap.petsApp.repository.Impl;

import cds.gen.petsservice.CatsView;
import cds.gen.petsservice.CatsView_;
import cds.gen.sap.cap.petsapp.Cats;
import cds.gen.sap.cap.petsapp.Cats_;
import com.sap.cap.petsApp.repository.CatRepository;
import com.sap.cds.ql.Insert;
import com.sap.cds.ql.Select;
import com.sap.cds.ql.Update;
import com.sap.cds.ql.cqn.CqnInsert;
import com.sap.cds.services.persistence.PersistenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CatRepositoryImpl implements CatRepository {

    private final PersistenceService db;

    @Override
    public Optional<CatsView> findById(String id) {
        Select<CatsView_> findById = Select.from(CatsView_.class)
                .where(cat -> cat.ID().eq(id))
                .limit(1);

        return db.run(findById)
                .first(CatsView.class);
    }

    @Override
    public Cats save(Cats cat) {
        CqnInsert insertCat = Insert.into(Cats_.class)
                .entry(cat);

        cat.setId(db.run(insertCat)
                .single(Cats.class)
                .getId());

        return cat;
    }

    @Override
    public Cats update(Cats cat) {
        Update<Cats_> updateCat = Update.entity(Cats_.class)
                .data(cat);

        return db.run(updateCat)
                .single(Cats.class);
    }
}
