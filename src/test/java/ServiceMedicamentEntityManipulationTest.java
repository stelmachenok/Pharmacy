import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceMedicamentEntityManipulationTest {
    Service service;

    @BeforeEach
    void serviceInit(){
        service = new Service(new MedicamentDAO(), new MedicamentCategoryDAO(), new PharmacyDAO(), new MedicineConverter());
    }

    @Test
    void addOneMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.addMedicament(new MedicamentDto("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
    }

    @Test
    void addTwoDiffMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.addMedicament(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        service.addMedicament(new MedicamentDto("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
    }

    @Test
    void addTwoSameMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.addMedicament(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        assertThrows(EntityAlreadyExistException.class, () -> {
            service.addMedicament(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        });
    }

    @Test
    void addNegativeDosageMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.addMedicament(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", -50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        });
    }

    @Test
    void deleteExistedMedicament() throws EntityNotFoundException, EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicamentDto);
        service.deleteMedicament(medicamentDto);
    }

    @Test
    void deleteNonexistentMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        MedicamentDto medicamentDto2 = new MedicamentDto("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicamentDto);
        assertThrows(EntityNotFoundException.class, () -> {
            service.deleteMedicament(medicamentDto2);
        });
    }

    @Test
    void updateExistedMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException, EntityNotFoundException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicamentDto);
        medicamentDto.setBrandName("ТИРОКСИН");
        service.updateMedicament(medicamentDto);
    }

    @Test
    void updateNonexistentMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        MedicamentDto medicamentDto2 = new MedicamentDto("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicamentDto);
        assertThrows(EntityNotFoundException.class, () -> {
            service.updateMedicament(medicamentDto2);
        });
    }

    @Test
    void updateNegativeDosageMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicamentDto);
        medicamentDto.setDosage(-50.0);
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.updateMedicament(medicamentDto);
        });
    }
}
