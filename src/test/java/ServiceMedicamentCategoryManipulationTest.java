import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.exception.EntityAlreadyExistEsception;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceMedicamentCategoryManipulationTest {
    Service service;

    @BeforeEach
    void serviceInit(){
        service = new Service();
    }

    @Test
    void addOneMedicamentCategory() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        service.addMedicamentCategory(new MedicamentCategory("Категория A", "Описание 1"));
    }

    @Test
    void addTwoDiffMedicamentCategory() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        service.addMedicamentCategory(new MedicamentCategory("Категория A", "Описание 1"));
        service.addMedicamentCategory(new MedicamentCategory("Категория B", "Описание 2"));
    }

    @Test
    void addTwoSameMedicamentCategory() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        service.addMedicamentCategory(new MedicamentCategory("Категория A", "Описание 1"));
        assertThrows(EntityAlreadyExistEsception.class, () -> {
            service.addMedicamentCategory(new MedicamentCategory("Категория A", "Описание 1"));
        });
    }

    @Test
    void deleteExistedMedicamentCategory() throws EntityNotFoundException, EntityAlreadyExistEsception, ObjectValidationFailedException {
        MedicamentCategory category = new MedicamentCategory("Категория A", "Описание 1");
        service.addMedicamentCategory(category);
        service.deleteMedicamentCategory(category);
    }

    @Test
    void deleteNonexistentMedicamentCategory() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        MedicamentCategory category = new MedicamentCategory("Категория A", "Описание 1");
        MedicamentCategory category2 = new MedicamentCategory("Категория B", "Описание 2");
        service.addMedicamentCategory(category);
        assertThrows(EntityNotFoundException.class, () -> {
            service.deleteMedicamentCategory(category2);
        });
    }

    @Test
    void updateExistedMedicamentCategory() throws EntityAlreadyExistEsception, ObjectValidationFailedException, EntityNotFoundException {
        MedicamentCategory category = new MedicamentCategory("Категория A", "Описание 1");
        service.addMedicamentCategory(category);
        category.setCategoryName("Категория B");
        service.updateMedicamentCategory(category);
    }

    @Test
    void updateNonexistentMedicamentCategory() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        MedicamentCategory category = new MedicamentCategory("Категория A", "Описание 1");
        MedicamentCategory category2 = new MedicamentCategory("Категория B", "Описание 2");
        service.addMedicamentCategory(category);
        assertThrows(EntityNotFoundException.class, () -> {
            service.updateMedicamentCategory(category2);
        });
    }
}
