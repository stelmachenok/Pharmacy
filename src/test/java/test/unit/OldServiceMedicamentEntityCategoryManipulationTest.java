//import by.samsolution.pharmacy.converter.impl.CategoryConverter;
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
//        service = new CategoryServiceImpl(new MedicamentCategoryDAO(), new CategoryConverter());
//
//    }
//
//    @Test
//    void addOneMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        service.add(new CategoryDto("Категория A", "Описание 1"));
//    }
//
//    @Test
//    void addTwoDiffMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        service.add(new CategoryDto("Категория A", "Описание 1"));
//        service.add(new CategoryDto("Категория B", "Описание 2"));
//    }
//
//    @Test
//    void addTwoSameMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        service.add(new CategoryDto("Категория A", "Описание 1"));
//        assertThrows(EntityAlreadyExistException.class, () -> {
//            service.add(new CategoryDto("Категория A", "Описание 1"));
//        });
//    }
//
//    @Test
//    void deleteExistedMedicamentCategory() throws EntityNotFoundException, EntityAlreadyExistException, ObjectValidationFailedException {
//        CategoryDto category = new CategoryDto("Категория A", "Описание 1");
//        service.add(category);
//        service.delete(category.getId());
//    }
//
//    @Test
//    void deleteNonexistentMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        CategoryDto category = new CategoryDto("Категория A", "Описание 1");
//        CategoryDto category2 = new CategoryDto("Категория B", "Описание 2");
//        service.add(category);
//        assertThrows(EntityNotFoundException.class, () -> {
//            service.delete(category2.getId());
//        });
//    }
//
//    @Test
//    void updateExistedMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException, EntityNotFoundException {
//        CategoryDto category = new CategoryDto("Категория A", "Описание 1");
//        service.add(category);
//        category.setCategoryName("Категория B");
//        service.update(category);
//    }
//
//    @Test
//    void updateNonexistentMedicamentCategory() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        CategoryDto category = new CategoryDto("Категория A", "Описание 1");
//        CategoryDto category2 = new CategoryDto("Категория B", "Описание 2");
//        service.add(category);
//        assertThrows(EntityNotFoundException.class, () -> {
//            service.update(category2);
//        });
//    }
//}
