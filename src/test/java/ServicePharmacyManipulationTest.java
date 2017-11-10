import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServicePharmacyManipulationTest {
    Service service;

    @BeforeEach
    void serviceInit(){
        service = new Service(new MedicamentDAO(), new MedicamentCategoryDAO(), new PharmacyDAO(), new MedicineConverter());
    }

    @Test
    void addOnePharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.addPharmacy(new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1"));
    }

    @Test
    void addTwoDiffPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.addPharmacy(new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1"));
        service.addPharmacy(new Pharmacy("Аптека № 17", "улица Филимонова 119, Минск", "Атрошенко", "8 017 210-07-72", "atroshenko", "12345", "2"));
    }

    @Test
    void addTwoSamePharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.addPharmacy(new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1"));
        assertThrows(EntityAlreadyExistException.class, () -> {
            service.addPharmacy(new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1"));
        });
    }

    @Test
    void addIncorrectNumberPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.addPharmacy(new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "eight zero one seven 210-07-72", "tabina", "qwerty", "1"));
        });
    }

    @Test
    void deleteExistedPharmacy() throws EntityNotFoundException, EntityAlreadyExistException, ObjectValidationFailedException {
        Pharmacy pharmacy = new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        service.addPharmacy(pharmacy);
        service.deletePharmacy(pharmacy);
    }

    @Test
    void deleteNonexistentPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        Pharmacy pharmacy = new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        Pharmacy pharmacy2 = new Pharmacy("Аптека № 17", "улица Филимонова 119, Минск", "Атрошенко", "8 017 210-07-72", "atroshenko", "12345", "2");
        service.addPharmacy(pharmacy);
        assertThrows(EntityNotFoundException.class, () -> {
            service.deletePharmacy(pharmacy2);
        });
    }

    @Test
    void updateExistedPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException, EntityNotFoundException {
        Pharmacy pharmacy = new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        service.addPharmacy(pharmacy);
        pharmacy.setPharmacyName("Аптека №2");
        service.updatePharmacy(pharmacy);
    }

    @Test
    void updateNonexistentPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        Pharmacy pharmacy = new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        Pharmacy pharmacy2 = new Pharmacy("Аптека № 17", "улица Филимонова 119, Минск", "Атрошенко", "8 017 210-07-72", "atroshenko", "12345", "2");
        service.addPharmacy(pharmacy);
        assertThrows(EntityNotFoundException.class, () -> {
            service.updatePharmacy(pharmacy2);
        });
    }

    @Test
    void updateIncorrectNumberPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        Pharmacy pharmacy = new Pharmacy("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", "1");
        service.addPharmacy(pharmacy);
        pharmacy.setContactNumber("eight zero one seven 210-07-72");
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.updatePharmacy(pharmacy);
        });
    }
    
}
