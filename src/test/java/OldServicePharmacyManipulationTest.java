import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.dto.PharmacyDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.OldService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OldServicePharmacyManipulationTest {
    OldService oldService;

    @BeforeEach
    void serviceInit(){
        oldService = new OldService(new MedicamentDAO(), new MedicamentCategoryDAO(), new PharmacyDAO(), new MedicineConverter());
    }

    @Test
    void addOnePharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        oldService.addPharmacy(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1"));
    }

    @Test
    void addTwoDiffPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        oldService.addPharmacy(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1"));
        oldService.addPharmacy(new PharmacyDto("Аптека № 17", "улица Филимонова 119, Минск", "Атрошенко", "8 017 210-07-72", "atroshenko", "12345", "2"));
    }

    @Test
    void addTwoSamePharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        oldService.addPharmacy(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1"));
        assertThrows(EntityAlreadyExistException.class, () -> {
            oldService.addPharmacy(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1"));
        });
    }

    @Test
    void addIncorrectNumberPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        assertThrows(ObjectValidationFailedException.class, ()->{
            oldService.addPharmacy(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "eight zero one seven 210-07-72", "tabina", "qwerty", "1"));
        });
    }

    @Test
    void deleteExistedPharmacy() throws EntityNotFoundException, EntityAlreadyExistException, ObjectValidationFailedException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        oldService.addPharmacy(pharmacy);
        oldService.deletePharmacy(pharmacy);
    }

    @Test
    void deleteNonexistentPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        PharmacyDto pharmacy2 = new PharmacyDto("Аптека № 17", "улица Филимонова 119, Минск", "Атрошенко", "8 017 210-07-72", "atroshenko", "12345", "2");
        oldService.addPharmacy(pharmacy);
        assertThrows(EntityNotFoundException.class, () -> {
            oldService.deletePharmacy(pharmacy2);
        });
    }

    @Test
    void updateExistedPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException, EntityNotFoundException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        oldService.addPharmacy(pharmacy);
        pharmacy.setPharmacyName("Аптека №2");
        oldService.updatePharmacy(pharmacy);
    }

    @Test
    void updateNonexistentPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        PharmacyDto pharmacy2 = new PharmacyDto("Аптека № 17", "улица Филимонова 119, Минск", "Атрошенко", "8 017 210-07-72", "atroshenko", "12345", "2");
        oldService.addPharmacy(pharmacy);
        assertThrows(EntityNotFoundException.class, () -> {
            oldService.updatePharmacy(pharmacy2);
        });
    }

    @Test
    void updateIncorrectNumberPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        oldService.addPharmacy(pharmacy);
        pharmacy.setContactNumber("eight zero one seven 210-07-72");
        assertThrows(ObjectValidationFailedException.class, ()->{
            oldService.updatePharmacy(pharmacy);
        });
    }
    
}
