//import by.samsolution.pharmacy.converter.impl.MedicineConverter;
//import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
//import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
//import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
//import by.samsolution.pharmacy.dto.CategoryDto;
//import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
//import by.samsolution.pharmacy.exception.EntityNotFoundException;
//import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
//import by.samsolution.pharmacy.service.impl.CategoryServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class OldServiceMedicamentEntityCategoryManipulationTest {
//    CategoryServiceImpl service;
//
//    @BeforeEach
//    void serviceInit() {
//        service = new OldService(new MedicamentDAO(), new MedicamentCategoryDAO(), new PharmacyDAO(), new MedicineConverter());
//    }
//
//    @Test
//    void addOneMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        service.addMedicamentCategory(new CategoryDto("Категория A", "Описание 1"));
//    }
//
//    @Test
//    void addTwoDiffMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        service.addMedicamentCategory(new CategoryDto("Категория A", "Описание 1"));
//        service.addMedicamentCategory(new CategoryDto("Категория B", "Описание 2"));
//    }
//
//    @Test
//    void addTwoSameMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        service.addMedicamentCategory(new CategoryDto("Категория A", "Описание 1"));
//        assertThrows(EntityAlreadyExistException.class, () -> {
//            service.addMedicamentCategory(new CategoryDto("Категория A", "Описание 1"));
//        });
//    }
//
//    @Test
//    void deleteExistedMedicamentCategory() throws EntityNotFoundException, EntityAlreadyExistException, ObjectValidationFailedException {
//        CategoryDto category = new CategoryDto("Категория A", "Описание 1");
//        service.addMedicamentCategory(category);
//        service.deleteMedicamentCategory(category);
//    }
//
//    @Test
//    void deleteNonexistentMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        CategoryDto category = new CategoryDto("Категория A", "Описание 1");
//        CategoryDto category2 = new CategoryDto("Категория B", "Описание 2");
//        service.addMedicamentCategory(category);
//        assertThrows(EntityNotFoundException.class, () -> {
//            service.deleteMedicamentCategory(category2);
//        });
//    }
//
//    @Test
//    void updateExistedMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException, EntityNotFoundException {
//        CategoryDto category = new CategoryDto("Категория A", "Описание 1");
//        service.addMedicamentCategory(category);
//        category.setCategoryName("Категория B");
//        service.updateMedicamentCategory(category);
//    }
//
//    @Test
//    void updateNonexistentMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        CategoryDto category = new CategoryDto("Категория A", "Описание 1");
//        CategoryDto category2 = new CategoryDto("Категория B", "Описание 2");
//        service.addMedicamentCategory(category);
//        assertThrows(EntityNotFoundException.class, () -> {
//            service.updateMedicamentCategory(category2);
//        });
//    }
//}
