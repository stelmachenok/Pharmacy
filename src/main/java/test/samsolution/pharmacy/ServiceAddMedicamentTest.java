package test.samsolution.pharmacy;

import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceAddMedicamentTest {
    Service service;

    @BeforeEach
    void serviceInit(){
        service = new Service();
    }
    @Test
    void addOneMedicament(){
        boolean statement = service.addMedicament(new Medicament("L-ОПТИК", "Левофлоксацин", "5 мг", PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
        assertTrue(statement);
    }

    @Test
    void addTwoDiffMedicament(){
        service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        boolean statement = service.addMedicament(new Medicament("L-ОПТИК", "Левофлоксацин", "5 мг", PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
        assertTrue(statement);
    }

    @Test
    void addTwoSameMedicament(){
        service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        boolean statement = service.addMedicament(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        assertFalse(statement);
    }
}
