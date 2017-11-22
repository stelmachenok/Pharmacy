import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.impl.MedicamentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OldServiceMedicamentEntityManipulationTest {
    MedicamentServiceImpl service;

    @BeforeEach
    void serviceInit(){
        service = new MedicamentServiceImpl(new MedicamentDAO(), new MedicineConverter());
    }

    @Test
    void addOneMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.add(new MedicamentDto("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
    }

    @Test
    void addTwoDiffMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.add(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        service.add(new MedicamentDto("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
    }

    @Test
    void addTwoSameMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.add(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        assertThrows(EntityAlreadyExistException.class, () -> {
            service.add(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        });
    }

    @Test
    void addNegativeDosageMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.add(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", -50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        });
    }

    @Test
    void deleteExistedMedicament() throws EntityNotFoundException, EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.add(medicamentDto);
        MedicamentDto medicamentDto2 = service.getAll().get(0);
        service.delete(medicamentDto2.getId());
    }

    @Test
    void deleteNonexistentMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        MedicamentDto medicamentDto2 = new MedicamentDto("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
        service.add(medicamentDto);
        assertThrows(EntityNotFoundException.class, () -> {
            service.delete(medicamentDto2.getId());
        });
    }

    @Test
    void updateExistedMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException, EntityNotFoundException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.add(medicamentDto);
        MedicamentDto medicamentDto2 = service.getAll().get(0);
        medicamentDto2.setBrandName("ТИРОКСИН");
        service.update(medicamentDto2);
    }

    @Test
    void updateNonexistentMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        MedicamentDto medicamentDto2 = new MedicamentDto("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
        service.add(medicamentDto);
        assertThrows(EntityNotFoundException.class, () -> {
            service.update(medicamentDto2);
        });
    }

    @Test
    void updateNegativeDosageMedicament() throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentDto medicamentDto = new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.add(medicamentDto);
        medicamentDto.setDosage(-50.0);
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.update(medicamentDto);
        });
    }
}
