package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.*;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import org.slf4j.*;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {
    private MedicamentDAO medicamentDAO;
    private MedicamentCategoryDAO categoryDAO;
    private PharmacyDAO pharmacyDAO;
    private MedicineConverter medicineConverter;

    private static Logger logger = LoggerFactory.getLogger(Service.class);

    public Service() {
        medicamentDAO = new MedicamentDAO();
        categoryDAO = new MedicamentCategoryDAO();
        pharmacyDAO = new PharmacyDAO();
        medicineConverter = new MedicineConverter();
    }

    public void setMedicamentDAO(MedicamentDAO medicamentDAO) {
        this.medicamentDAO = medicamentDAO;
    }

    public void setCategoryDAO(MedicamentCategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public void setPharmacyDAO(PharmacyDAO pharmacyDAO) {
        this.pharmacyDAO = pharmacyDAO;
    }

    public void setMedicineConverter(MedicineConverter medicineConverter) {
        this.medicineConverter = medicineConverter;
    }

    public void addMedicament(MedicamentDto medicamentDto) throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentEntity existedMedicamentEntity = medicamentDAO.getEntityByName(medicamentDto.getBrandName());
        Pattern dosagePattern = Pattern.compile("(\\d)+.(\\d)+");
        Matcher dosagePatternMatcher = dosagePattern.matcher(medicamentDto.getDosage());
        if (!dosagePatternMatcher.matches()) {
            throw new ObjectValidationFailedException("Incorrect dosage " + medicamentDto.getDosage());
        }
        MedicamentEntity medicamentEntity = medicineConverter.dtoToEntity(medicamentDto);
        if (!equalsMedicaments(existedMedicamentEntity, medicamentEntity)) {
            medicamentDAO.create(medicamentEntity);
            medicamentDto.setId(medicamentEntity.getId());
        } else {
            throw new EntityAlreadyExistException("MedicamentEntity " + existedMedicamentEntity + " already exist!");
        }
    }

    public void updateMedicament(MedicamentDto medicamentDto) throws EntityNotFoundException, ObjectValidationFailedException {
        MedicamentEntity existedMedicamentEntity = medicamentDAO.getEntityById(medicamentDto.getId());
        Pattern dosagePattern = Pattern.compile("(\\d)+.(\\d)+");
        Matcher dosagePatternMatcher = dosagePattern.matcher(medicamentDto.getDosage());
        if (!dosagePatternMatcher.matches()) {
            throw new ObjectValidationFailedException("Incorrect dosage " + medicamentDto.getDosage());
        }
        MedicamentEntity medicamentEntity = medicineConverter.dtoToEntity(medicamentDto);
        if (existedMedicamentEntity != null) {
            medicamentDAO.update(medicamentEntity);
        } else {
            throw new EntityNotFoundException("MedicamentEntity " + medicamentDto + " doesn't exist");
        }
    }

    public void deleteMedicament(MedicamentDto medicamentDto) throws EntityNotFoundException {
        MedicamentEntity existedMedicamentEntity = medicamentDAO.getEntityById(medicamentDto.getId());
        MedicamentEntity medicamentEntity = medicineConverter.dtoToEntity(medicamentDto);
        if (existedMedicamentEntity != null) {
            medicamentDAO.delete(medicamentEntity.getId());
        } else {
            throw new EntityNotFoundException("MedicamentEntity " + medicamentEntity + " doesn't exist");
        }
    }

    public void addMedicamentCategory(MedicamentCategory category) throws EntityAlreadyExistException {
        MedicamentCategory existedCategory = categoryDAO.getEntityByName(category.getCategoryName());
        if (!equalsCategory(category, existedCategory)) {
            categoryDAO.create(category);
        } else {
            throw new EntityAlreadyExistException("Category " + existedCategory + " already exist!");
        }
    }

    public void updateMedicamentCategory(MedicamentCategory category) throws EntityNotFoundException {
        MedicamentCategory existedCategory = categoryDAO.getEntityById(category.getId());
        if (existedCategory != null) {
            categoryDAO.update(category);
        } else {
            throw new EntityNotFoundException("Category " + category + " doesn't exist");
        }
    }

    public void deleteMedicamentCategory(MedicamentCategory category) throws EntityNotFoundException {
        MedicamentCategory existedCategory = categoryDAO.getEntityById(category.getId());
        if (existedCategory != null) {
            categoryDAO.delete(category.getId());
        } else {
            throw new EntityNotFoundException("Category " + category + " doesn't exist");
        }
    }

    public void addPharmacy(Pharmacy pharmacy) throws EntityAlreadyExistException, ObjectValidationFailedException {
        Pharmacy existedPharmacy = pharmacyDAO.getEntityByName(pharmacy.getPharmacyName());
        Pattern mobilePhonePattern = Pattern.compile("\\+375-(\\d){2}-((\\d){3})-(\\d){2}-(\\d){2}");
        Pattern homePhonePattern = Pattern.compile("8\\s(\\d){3}\\s((\\d){3})-(\\d){2}-(\\d){2}");
        Matcher mobilePhoneMatcher = mobilePhonePattern.matcher(pharmacy.getContactNumber());
        Matcher homePhoneMatcher = homePhonePattern.matcher(pharmacy.getContactNumber());
        if (!mobilePhoneMatcher.matches() && !homePhoneMatcher.matches()) {
            throw new ObjectValidationFailedException("Phone number " + pharmacy.getContactNumber() + "is incorrect");
        }
        if (!equalsPharmacies(pharmacy, existedPharmacy)) {
            pharmacyDAO.create(pharmacy);
        } else {
            throw new EntityAlreadyExistException("Pharmacy " + existedPharmacy + " already exist!");
        }
    }

    public void updatePharmacy(Pharmacy pharmacy) throws EntityNotFoundException, ObjectValidationFailedException {
        Pharmacy existedPharmacy = pharmacyDAO.getEntityById(pharmacy.getId());
        Pattern mobilePhonePattern = Pattern.compile("\\+375-(\\d){2}-((\\d){3})-(\\d){2}-(\\d){2}");
        Pattern homePhonePattern = Pattern.compile("8\\s(\\d){3}\\s((\\d){3})-(\\d){2}-(\\d){2}");
        Matcher mobilePhoneMatcher = mobilePhonePattern.matcher(pharmacy.getContactNumber());
        Matcher homePhoneMatcher = homePhonePattern.matcher(pharmacy.getContactNumber());
        if (!mobilePhoneMatcher.matches() && !homePhoneMatcher.matches()) {
            throw new ObjectValidationFailedException("Phone number " + pharmacy.getContactNumber() + "is incorrect");
        }
        if (existedPharmacy != null) {
            pharmacyDAO.update(pharmacy);
        } else {
            throw new EntityNotFoundException("Pharmacy " + pharmacy + " doesn't exist");
        }
    }

    public void deletePharmacy(Pharmacy pharmacy) throws EntityNotFoundException {
        Pharmacy existedPharmacy = pharmacyDAO.getEntityById(pharmacy.getId());
        if (existedPharmacy != null) {
            pharmacyDAO.delete(pharmacy.getId());
        } else {
            throw new EntityNotFoundException("Pharmacy " + pharmacy + " doesn't exist");
        }
    }

    public List<MedicamentEntity> getAllMedicaments() {
        return medicamentDAO.getAll();
    }

    private boolean equalsMedicaments(MedicamentEntity medicamentEntity, MedicamentEntity medicamentEntity2) {
        return medicamentEntity != null && medicamentEntity2 != null &&
                medicamentEntity.getBrandName().equals(medicamentEntity2.getBrandName()) &&
                medicamentEntity.getActiveIngredient().equals(medicamentEntity2.getActiveIngredient()) &&
                medicamentEntity.getDosage().equals(medicamentEntity2.getDosage()) &&
                medicamentEntity.getPackingForm().equals(medicamentEntity2.getPackingForm()) &&
                medicamentEntity.getInternationalNonproprietaryName().equals(medicamentEntity2.getInternationalNonproprietaryName()) &&
                medicamentEntity.getReleaseForm().equals(medicamentEntity2.getReleaseForm());
    }

    private boolean equalsPharmacies(Pharmacy pharmacy, Pharmacy pharmacy2) {
        return pharmacy != null && pharmacy2 != null &&
                pharmacy.getPharmacyName().equals(pharmacy2.getPharmacyName()) &&
                pharmacy.getAddress().equals(pharmacy2.getAddress());
    }

    private boolean equalsCategory(MedicamentCategory category, MedicamentCategory category2) {
        return category != null && category2 != null &&
                category.getCategoryName().equals(category2.getCategoryName()) &&
                category.getDescription().equals(category2.getDescription());
    }
}

