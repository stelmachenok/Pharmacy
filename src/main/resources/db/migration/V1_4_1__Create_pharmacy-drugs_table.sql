CREATE TABLE availability (
  id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pharmacyId   BIGINT NOT NULL,
  medicamentId BIGINT NOT NULL,
  COUNT        BIGINT NOT NULL,
  lastUpdate   DATE   NOT NULL,
  UNIQUE (pharmacyId, medicamentId)
);
