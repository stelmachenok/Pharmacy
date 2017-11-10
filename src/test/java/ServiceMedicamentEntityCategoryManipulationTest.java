import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceMedicamentEntityCategoryManipulationTest {
    Service service;

    @BeforeEach
    void serviceInit() {
        service = new Service(new MedicamentDAO(), new MedicamentCategoryDAO(), new PharmacyDAO(), new MedicineConverter());
    }

    @Test
    void addOneMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.addMedicamentCategory(new MedicamentCategory("Категория A", "Описание 1"));
    }

    @Test
    void addTwoDiffMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.addMedicamentCategory(new MedicamentCategory("Категория A", "Описание 1"));
        service.addMedicamentCategory(new MedicamentCategory("Категория B", "Описание 2"));
    }

    @Test
    void addTwoSameMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.addMedicamentCategory(new MedicamentCategory("Категория A", "Описание 1"));
        assertThrows(EntityAlreadyExistException.class, () -> {
            service.addMedicamentCategory(new MedicamentCategory("Категория A", "Описание 1"));
        });
    }

    @Test
    void deleteExistedMedicamentCategory() throws EntityNotFoundException, EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentCategory category = new MedicamentCategory("Категория A", "Описание 1");
        service.addMedicamentCategory(category);
        service.deleteMedicamentCategory(category);
    }

    @Test
    void deleteNonexistentMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentCategory category = new MedicamentCategory("Категория A", "Описание 1");
        MedicamentCategory category2 = new MedicamentCategory("Категория B", "Описание 2");
        service.addMedicamentCategory(category);
        assertThrows(EntityNotFoundException.class, () -> {
            service.deleteMedicamentCategory(category2);
        });
    }

    @Test
    void updateExistedMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException, EntityNotFoundException {
        MedicamentCategory category = new MedicamentCategory("Категория A", "Описание 1");
        service.addMedicamentCategory(category);
        category.setCategoryName("Категория B");
        service.updateMedicamentCategory(category);
    }

    @Test
    void updateNonexistentMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentCategory category = new MedicamentCategory("Категория A", "Описание 1");
        MedicamentCategory category2 = new MedicamentCategory("Категория B", "Описание 2");
        service.addMedicamentCategory(category);
        assertThrows(EntityNotFoundException.class, () -> {
            service.updateMedicamentCategory(category2);
        });
    }
}
