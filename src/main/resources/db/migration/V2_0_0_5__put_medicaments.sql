DROP TABLE medicament;
create table Medicament  (
  id BIGINT not null AUTO_INCREMENT PRIMARY KEY,
  brandName VARCHAR(64),
  activeIngredient VARCHAR(64),
  dosage DOUBLE,
  packingForm VARCHAR(20),
  internationalNonproprietaryName VARCHAR(64),
  releaseForm CHAR(64),
  categoryId BIGINT,
  guid VARCHAR(64),
  INDEX(brandName),
  INDEX(internationalNonproprietaryName)
);

INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES ('L-ОПТИК', 'Левофлоксацин', 5.0, 'DROP', 'Левофлоксацин', 'WITHOUT_RECIPE', 1,
        '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES
  ('L-ОПТИК', 'Левотироксин натрия', 50.0, 'TABLET', 'Левотироксин натрия', 'WITHOUT_RECIPE', 1,
   '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES
  ('5-НОК', 'Нитроксолин', 50.0, 'TABLET', 'Нитроксолин', 'WITHOUT_RECIPE', 1, '7dc53df5-703e-49b3-8670-b1c468f47f1f');

INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES
  ('АВАМИС', 'Флутиказон', 27.5, 'TABLET', 'Флутиказон', 'WITHOUT_RECIPE', 1, '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES ('АВЕЛОКС', 'Моксифлоксацин', 400.0, 'TABLET', 'Моксифлоксацин', 'WITHOUT_RECIPE', 1,
        '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES
  ('АВОДАРТ', 'Дутастерид', 0.5, 'CAPSULE', 'Дутастерид', 'WITHOUT_RECIPE', 2, '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES ('АДАПТОЛ', 'Мебикар', 500.0, 'TABLET', 'Мебикар', 'WITHOUT_RECIPE', 1, '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES ('АЕВИТ', 'Ретинол', 1.19, 'CAPSULE', 'Ретинол', 'WITHOUT_RECIPE', 1, '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES
  ('АЕКОЛ', 'Токоферол', 100.0, 'AEROSOL', 'Токоферол', 'WITHOUT_RECIPE', 1, '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES ('АДВАНТАН', 'Метилпреднизолона ацепонат', 20.0, 'EMULSION', 'Метилпреднизолона ацепонат', 'WITHOUT_RECIPE', 1,
        '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES
  ('АДЕНИК', 'Тамсулозин', 0.4, 'CAPSULE', 'Тамсулозин', 'WITHOUT_RECIPE', 1, '7dc53df5-703e-49b3-8670-b1c468f47f1f');
INSERT INTO Medicament (brandName, activeIngredient, dosage, packingForm, internationalNonproprietaryName, releaseForm, categoryId, guid)
VALUES ('АДИЦЕФ', 'Цефдинир', 125.0, 'POWDER', 'Цефдинир', 'WITHOUT_RECIPE', 1, '7dc53df5-703e-49b3-8670-b1c468f47f1f');

