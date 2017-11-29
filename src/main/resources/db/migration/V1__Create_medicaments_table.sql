create table Medicament  (
    id BIGINT not null AUTO_INCREMENT PRIMARY KEY,
    brandName VARCHAR(64),
    activeIngredient VARCHAR(64),
    dosage DOUBLE,
    packingForm VARCHAR(20),
    internationalNonproprietaryName VARCHAR(64),
    guid VARCHAR(64),
    INDEX(brandName),
    INDEX(internationalNonproprietaryName)
);