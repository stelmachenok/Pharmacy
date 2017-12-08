CREATE TABLE UserTable (
  id         BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login      VARCHAR(64) NOT NULL UNIQUE,
  password   VARCHAR(64) NOT NULL,
  role       VARCHAR(64) NOT NULL,
  pharmacyId BIGINT
);