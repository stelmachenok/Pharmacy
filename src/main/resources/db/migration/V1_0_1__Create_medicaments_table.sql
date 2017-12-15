CREATE TABLE Medicament (
    id                              BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    brandName                       VARCHAR(64),
    activeIngredient                VARCHAR(64),
    dosage                          DOUBLE,
    packingForm                     VARCHAR(20),
    internationalNonproprietaryName VARCHAR(64),
    releaseForm                     CHAR(64),
    medicamentCategory              BIGINT,
    guid                            VARCHAR(64)
);

CREATE INDEX index1 ON Medicament (brandName);
CREATE INDEX index2 ON Medicament (internationalNonproprietaryName);