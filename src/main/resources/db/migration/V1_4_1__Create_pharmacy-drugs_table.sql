CREATE TABLE availability (
  pharmacyId   BIGINT NOT NULL,
  medicamentId BIGINT NOT NULL,
  count        BIGINT NOT NULL,
  lastUpdate   DATE
);