//import by.samsolution.pharmacy.converter.impl.MedicineConverter;
//import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
//import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
//import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
//import by.samsolution.pharmacy.dto.MedicamentDto;
//import by.samsolution.pharmacy.entity.PackingForm;
//import by.samsolution.pharmacy.entity.ReleaseForm;
//import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
//import by.samsolution.pharmacy.exception.EntityNotFoundException;
//import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
//import by.samsolution.pharmacy.service.OldService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class OldServiceMedicamentEntityManipulationTest {
//    OldService oldService;
//
//    @BeforeEach
//    void serviceInit(){
//        oldService = new OldService(new MedicamentDAO(), new MedicamentCategoryDAO(), new PharmacyDAO(), new MedicineConverter());
//    }
//
//    @Test
//    void addOneMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        oldService.addMedicament(new MedicamentDto("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
//    }
//
//    @Test
//    void addTwoDiffMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        oldService.addMedicament(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
//        oldService.addMedicament(new MedicamentDto("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
//    }
//
//    @Test
//    void addTwoSameMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        oldService.addMedicament(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
//        assertThrows(EntityAlreadyExistException.class, () -> {
//            oldService.addMedicament(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
//        });
//    }
//
//    @Test
//    void addNegativeDosageMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        assertThrows(ObjectValidationFailedException.class, ()->{
//            oldService.addMedicament(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", -50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
//        });
//    }
//
//    @Test
//    void deleteExistedMedicament() throws EntityNotFoundException, EntityAlreadyExistException, ObjectValidationFailedException {
//        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
//        oldService.addMedicament(medicamentDto);
//        oldService.deleteMedicament(medicamentDto);
//    }
//
//    @Test
//    void deleteNonexistentMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
//        MedicamentDto medicamentDto2 = new MedicamentDto("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
//        oldService.addMedicament(medicamentDto);
//        assertThrows(EntityNotFoundException.class, () -> {
//            oldService.deleteMedicament(medicamentDto2);
//        });
//    }
//
//    @Test
//    void updateExistedMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException, EntityNotFoundException {
//        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
//        oldService.addMedicament(medicamentDto);
//        medicamentDto.setBrandName("ТИРОКСИН");
//        oldService.updateMedicament(medicamentDto);
//    }
//
//    @Test
//    void updateNonexistentMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
//        MedicamentDto medicamentDto2 = new MedicamentDto("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
//        oldService.addMedicament(medicamentDto);
//        assertThrows(EntityNotFoundException.class, () -> {
//            oldService.updateMedicament(medicamentDto2);
//        });
//    }
//
//    @Test
//    void updateNegativeDosageMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
//        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
//        oldService.addMedicament(medicamentDto);
//        medicamentDto.setDosage(-50.0);
//        assertThrows(ObjectValidationFailedException.class, ()->{
//            oldService.updateMedicament(medicamentDto);
//        });
//    }
//}
