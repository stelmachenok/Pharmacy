CREATE TABLE pharmacy (
  id             BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pharmacyName   VARCHAR(64),
  address        VARCHAR(64),
  pharmacistName VARCHAR(64),
  contactNumber  VARCHAR(64),
  login          VARCHAR(64),
  password       VARCHAR(64),
  category       VARCHAR(64),
  uuid           VARCHAR(64)
);