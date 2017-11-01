import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.EntityAlreadyExistEsception;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceMedicamentManipulationTest {
    Service service;

    @BeforeEach
    void serviceInit(){
        service = new Service();
    }

    @Test
    void addOneMedicament() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        service.addMedicament(new Medicament("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
    }

    @Test
    void addTwoDiffMedicament() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        service.addMedicament(new Medicament("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
    }

    @Test
    void addTwoSameMedicament() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        assertThrows(EntityAlreadyExistEsception.class, () -> {
            service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        });
    }

    @Test
    void addNegativeDosageMedicament() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", -50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        });
    }

    @Test
    void deleteExistedMedicament() throws EntityNotFoundException, EntityAlreadyExistEsception, ObjectValidationFailedException {
        Medicament medicament = new Medicament("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicament);
        service.deleteMedicament(medicament);
    }

    @Test
    void deleteNonexistentMedicament() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        Medicament medicament = new Medicament("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        Medicament medicament2 = new Medicament("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicament);
        assertThrows(EntityNotFoundException.class, () -> {
            service.deleteMedicament(medicament2);
        });
    }

    @Test
    void updateExistedMedicament() throws EntityAlreadyExistEsception, ObjectValidationFailedException, EntityNotFoundException {
        Medicament medicament = new Medicament("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicament);
        medicament.setBrandName("ТИРОКСИН");
        service.updateMedicament(medicament);
    }

    @Test
    void updateNonexistentMedicament() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        Medicament medicament = new Medicament("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        Medicament medicament2 = new Medicament("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicament);
        assertThrows(EntityNotFoundException.class, () -> {
            service.updateMedicament(medicament2);
        });
    }

    @Test
    void updateNegativeDosageMedicament() throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        Medicament medicament = new Medicament("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicament);
        medicament.setDosage(-50.0);
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.updateMedicament(medicament);
        });
    }
}