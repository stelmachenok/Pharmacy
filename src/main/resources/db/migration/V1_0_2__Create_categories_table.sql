CREATE TABLE Category (
  id           BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  categoryName VARCHAR(64) NOT NULL UNIQUE,
  description  VARCHAR(256),
  guid         VARCHAR(64) NOT NULL UNIQUE
);
