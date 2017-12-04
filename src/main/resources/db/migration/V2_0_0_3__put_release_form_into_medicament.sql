ALTER TABLE medicament ADD releaseForm CHAR(64);

UPDATE Medicament
SET releaseForm = 'WITHOUT_RECIPE';