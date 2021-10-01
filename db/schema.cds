namespace sap.cap.petsApp;

using { cuid } from '@sap/cds/common';

type PetKind : String enum { Cats; Dogs; }

entity Users : cuid {
    firstname : String(50) not null;
    lastname  : String(50) not null;
    pets      : Association to many Pets on pets.user = $self;
}

entity Pets : cuid {
    name    : String(50) not null;
    petKind : PetKind;
    user    : Association to Users;
}

entity Cats : cuid {
   @assert.unique pet : Composition of Pets;
   gender             : String not null;
}

entity Dogs : cuid {
   @assert.unique pet : Composition of Pets;
   colour             : String not null;
}