package test.samsolution.pharmacy;

import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.PharmacyApplicationException;
import by.samsolution.pharmacy.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceMedicamentManipulationTest {
    Service service;

    @BeforeEach
    void serviceInit() {
        service = new Service();
    }

    @Test
    void addOneMedicament() throws PharmacyApplicationException {
        boolean statement = service.addMedicament(new Medicament("L-ОПТИК", "Левофлоксацин", "5 мг", PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
        assertTrue(statement);
    }

    @Test
    void addTwoDiffMedicament() throws PharmacyApplicationException {
        service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        boolean statement = service.addMedicament(new Medicament("L-ОПТИК", "Левофлоксацин", "5 мг", PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
        assertTrue(statement);
    }

    @Test
    void addTwoSameMedicament() throws PharmacyApplicationException {
        service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        assertThrows(PharmacyApplicationException.class, () -> {
            service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        });
    }

    @Test
    void deleteExistedMedicament() throws PharmacyApplicationException {
        Medicament medicament = new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicament);
        boolean statement = service.deleteMedicament(medicament);
        assertTrue(statement);
    }

    @Test
    void deleteNonexistentMedicament() throws PharmacyApplicationException {
        Medicament medicament = new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        Medicament medicament2 = new Medicament("АВАМИС", "Флутиказон", "27.5 мг/доза", PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicament);
        assertThrows(EntityNotFoundException.class, () -> {
            service.deleteMedicament(medicament2);
        });
    }

    @Test
    void updateExistedMedicament() throws PharmacyApplicationException {
        Medicament medicament = new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicament);
        medicament.setBrandName("ТИРОКСИН");
        boolean statement = service.updateMedicament(medicament);
        assertTrue(statement);
    }

    @Test
    void updateNonexistentMedicament() throws PharmacyApplicationException {
        Medicament medicament = new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE);
        Medicament medicament2 = new Medicament("АВАМИС", "Флутиказон", "27.5 мг/доза", PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE);
        service.addMedicament(medicament);
        assertThrows(EntityNotFoundException.class, () -> {
            service.updateMedicament(medicament2);
        });
    }
}
