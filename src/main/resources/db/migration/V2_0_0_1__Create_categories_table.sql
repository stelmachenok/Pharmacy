create table Category  (
    id BIGINT not null AUTO_INCREMENT PRIMARY KEY,
    categoryName VARCHAR(64),
    description VARCHAR(64),
    guid VARCHAR(64),
    INDEX(categoryName)
);

INSERT INTO Category (categoryName, description) VALUES ('Категория A', 'Описание 1');
INSERT INTO Category (categoryName, description) VALUES ('Категория B', 'Описание 2');
INSERT INTO Category (categoryName, description) VALUES ('Категория C', 'Описание 3');
INSERT INTO Category (categoryName, description) VALUES ('Категория D', 'Описание 4');
INSERT INTO Category (categoryName, description) VALUES ('Категория X', 'Описание 5');
INSERT INTO Category (categoryName, description) VALUES ('Категория N', 'Описание 6');