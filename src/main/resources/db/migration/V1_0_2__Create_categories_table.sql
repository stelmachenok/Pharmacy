create table Category  (
  id BIGINT not null AUTO_INCREMENT PRIMARY KEY,
  categoryName VARCHAR(64) NOT NULL UNIQUE,
  description VARCHAR(64),
  guid VARCHAR(64) NOT NULL UNIQUE
);
