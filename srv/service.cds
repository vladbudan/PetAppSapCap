using { sap.cap.petsApp as db } from '../db/schema';

service PetsService {

    entity Users as projection on db.Users;

    @readonly
    entity Pets as projection on db.Pets;

    entity CatsView as select from db.Cats {
        Cats.ID,
        Cats.gender,
        Cats.pet,
        Cats.pet.name,
        Cats.pet.petKind,
        Cats.pet.user
    };

    entity DogsView as select from db.Dogs {
        Dogs.ID,
        Dogs.colour,
        Dogs.pet,
        Dogs.pet.name,
        Dogs.pet.petKind,
        Dogs.pet.user
    };

    action swapPets(firstPetId : UUID, secondPetId : UUID);

}