import by.samsolution.pharmacy.converter.impl.PharmacyConverter;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.dto.PharmacyDto;
import by.samsolution.pharmacy.entity.PharmacyCategory;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import by.samsolution.pharmacy.service.impl.PharmacyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static by.samsolution.pharmacy.entity.PharmacyCategory.FIRST;
import static by.samsolution.pharmacy.entity.PharmacyCategory.SECOND;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OldServicePharmacyManipulationTest {
    PharmacyServiceImpl service;

    @BeforeEach
    void serviceInit(){
        service = new PharmacyServiceImpl(new PharmacyDAO(), new PharmacyConverter());
    }

    @Test
    void addOnePharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.add(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", FIRST));
    }

    @Test
    void addTwoDiffPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.add(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", FIRST));
        service.add(new PharmacyDto("Аптека № 17", "улица Филимонова 119, Минск", "Атрошенко", "8 017 210-07-72", "atroshenko", "12345", SECOND));
    }

    @Test
    void addTwoSamePharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        service.add(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", FIRST));
        assertThrows(EntityAlreadyExistException.class, () -> {
            service.add(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", FIRST));
        });
    }

    @Test
    void addIncorrectNumberPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.add(new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "eight zero one seven 210-07-72", "tabina", "qwerty", FIRST));
        });
    }

    @Test
    void deleteExistedPharmacy() throws EntityNotFoundException, EntityAlreadyExistException, ObjectValidationFailedException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", FIRST);
        service.add(pharmacy);
        service.delete(pharmacy.getId());
    }

    @Test
    void deleteNonexistentPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", FIRST);
        PharmacyDto pharmacy2 = new PharmacyDto("Аптека № 17", "улица Филимонова 119, Минск", "Атрошенко", "8 017 210-07-72", "atroshenko", "12345", SECOND);
        service.add(pharmacy);
        assertThrows(EntityNotFoundException.class, () -> {
            service.delete(pharmacy2.getId());
        });
    }

    @Test
    void updateExistedPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException, EntityNotFoundException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", FIRST);
        service.add(pharmacy);
        pharmacy.setPharmacyName("Аптека №2");
        service.update(pharmacy);
    }

    @Test
    void updateNonexistentPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", FIRST);
        PharmacyDto pharmacy2 = new PharmacyDto("Аптека № 17", "улица Филимонова 119, Минск", "Атрошенко", "8 017 210-07-72", "atroshenko", "12345", SECOND);
        service.add(pharmacy);
        assertThrows(EntityNotFoundException.class, () -> {
            service.update(pharmacy2);
        });
    }

    @Test
    void updateIncorrectNumberPharmacy() throws EntityAlreadyExistException, ObjectValidationFailedException {
        PharmacyDto pharmacy = new PharmacyDto("Доктор До Аптека", "улица Филимонова 3/2, Минск", "Табина", "8 017 210-07-72", "tabina", "qwerty", FIRST);
        service.add(pharmacy);
        pharmacy.setContactNumber("eight zero one seven 210-07-72");
        assertThrows(ObjectValidationFailedException.class, ()->{
            service.update(pharmacy);
        });
    }

}
