package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.dto.CategoryDto;
import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.dto.PharmacyDto;
import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OldService {
    private MedicamentDAO medicamentDAO;
    private MedicamentCategoryDAO categoryDAO;
    private PharmacyDAO pharmacyDAO;
    private MedicineConverter medicineConverter;

    private static Logger logger = LoggerFactory.getLogger(OldService.class);

    public OldService() {
    }

    public OldService(MedicamentDAO medicamentDAO, MedicamentCategoryDAO categoryDAO, PharmacyDAO pharmacyDAO, MedicineConverter medicineConverter) {
        this.medicamentDAO = medicamentDAO;
        this.categoryDAO = categoryDAO;
        this.pharmacyDAO = pharmacyDAO;
        this.medicineConverter = medicineConverter;
    }

    public void addMedicament(MedicamentDto medicamentDto) throws EntityAlreadyExistException, ObjectValidationFailedException {
        MedicamentDto existedMedicamentDto = medicamentDAO.getEntityByName(medicamentDto.getBrandName());
        if (medicamentDto.getDosage() < 0) {
            throw new ObjectValidationFailedException("Incorrect dosage " + medicamentDto.getDosage());
        }
        if (!equalsMedicaments(existedMedicamentDto, medicamentDto)) {
            medicamentDAO.create(medicamentDto);
        } else {
            throw new EntityAlreadyExistException("MedicamentEntity " + existedMedicamentDto + " already exist!");
        }
    }

    public void updateMedicament(MedicamentDto medicamentDto) throws EntityNotFoundException, ObjectValidationFailedException {
        MedicamentDto existedMedicamentDto = medicamentDAO.getEntityById(medicamentDto.getId());
        if (medicamentDto.getDosage() < 0) {
            throw new ObjectValidationFailedException("Incorrect dosage " + medicamentDto.getDosage());
        }
        if (existedMedicamentDto != null) {
            medicamentDAO.update(medicamentDto);
        } else {
            throw new EntityNotFoundException("MedicamentEntity " + medicamentDto + " doesn't exist");
        }
    }

    public void deleteMedicament(MedicamentDto medicamentDto) throws EntityNotFoundException {
        MedicamentDto existedMedicamentDto = medicamentDAO.getEntityById(medicamentDto.getId());
        if (existedMedicamentDto != null) {
            medicamentDAO.delete(medicamentDto.getId());
        } else {
            throw new EntityNotFoundException("MedicamentEntity " + medicamentDto + " doesn't exist");
        }
    }

    public void addMedicamentCategory(CategoryDto category) throws EntityAlreadyExistException {
        CategoryDto existedCategoryDto = categoryDAO.getEntityByName(category.getCategoryName());
        if (!equalsCategory(category, existedCategoryDto)) {
            categoryDAO.create(category);
        } else {
            throw new EntityAlreadyExistException("Category " + existedCategoryDto + " already exist!");
        }
    }

    public void updateMedicamentCategory(CategoryDto category) throws EntityNotFoundException {
        CategoryDto existedCategoryDto = categoryDAO.getEntityById(category.getId());
        if (existedCategoryDto != null) {
            categoryDAO.update(category);
        } else {
            throw new EntityNotFoundException("Category " + category + " doesn't exist");
        }
    }

    public void deleteMedicamentCategory(CategoryDto category) throws EntityNotFoundException {
        CategoryDto existedCategoryDto = categoryDAO.getEntityById(category.getId());
        if (existedCategoryDto != null) {
            categoryDAO.delete(category.getId());
        } else {
            throw new EntityNotFoundException("Category " + category + " doesn't exist");
        }
    }

    public void addPharmacy(PharmacyDto pharmacy) throws EntityAlreadyExistException, ObjectValidationFailedException {
        PharmacyDto existedPharmacyDto = pharmacyDAO.getEntityByName(pharmacy.getPharmacyName());
        Pattern mobilePhonePattern = Pattern.compile("\\+375-(\\d){2}-((\\d){3})-(\\d){2}-(\\d){2}");
        Pattern homePhonePattern = Pattern.compile("8\\s(\\d){3}\\s((\\d){3})-(\\d){2}-(\\d){2}");
        Matcher mobilePhoneMatcher = mobilePhonePattern.matcher(pharmacy.getContactNumber());
        Matcher homePhoneMatcher = homePhonePattern.matcher(pharmacy.getContactNumber());
        if (!mobilePhoneMatcher.matches() && !homePhoneMatcher.matches()) {
            throw new ObjectValidationFailedException("Phone number " + pharmacy.getContactNumber() + "is incorrect");
        }
        if (!equalsPharmacies(pharmacy, existedPharmacyDto)) {
            pharmacyDAO.create(pharmacy);
        } else {
            throw new EntityAlreadyExistException("Pharmacy " + existedPharmacyDto + " already exist!");
        }
    }

    public void updatePharmacy(PharmacyDto pharmacy) throws EntityNotFoundException, ObjectValidationFailedException {
        PharmacyDto existedPharmacyDto = pharmacyDAO.getEntityById(pharmacy.getId());
        Pattern mobilePhonePattern = Pattern.compile("\\+375-(\\d){2}-((\\d){3})-(\\d){2}-(\\d){2}");
        Pattern homePhonePattern = Pattern.compile("8\\s(\\d){3}\\s((\\d){3})-(\\d){2}-(\\d){2}");
        Matcher mobilePhoneMatcher = mobilePhonePattern.matcher(pharmacy.getContactNumber());
        Matcher homePhoneMatcher = homePhonePattern.matcher(pharmacy.getContactNumber());
        if (!mobilePhoneMatcher.matches() && !homePhoneMatcher.matches()) {
            throw new ObjectValidationFailedException("Phone number " + pharmacy.getContactNumber() + "is incorrect");
        }
        if (existedPharmacyDto != null) {
            pharmacyDAO.update(pharmacy);
        } else {
            throw new EntityNotFoundException("Pharmacy " + pharmacy + " doesn't exist");
        }
    }

    public void deletePharmacy(PharmacyDto pharmacy) throws EntityNotFoundException {
        PharmacyDto existedPharmacyDto = pharmacyDAO.getEntityById(pharmacy.getId());
        if (existedPharmacyDto != null) {
            pharmacyDAO.delete(pharmacy.getId());
        } else {
            throw new EntityNotFoundException("Pharmacy " + pharmacy + " doesn't exist");
        }
    }

    public List<MedicamentDto> getAllMedicaments() {
        return medicamentDAO.getAll();
    }

    private boolean equalsMedicaments(MedicamentDto medicamentEntity, MedicamentDto medicamentEntity2) {
        return medicamentEntity != null && medicamentEntity2 != null &&
                medicamentEntity.getBrandName().equals(medicamentEntity2.getBrandName()) &&
                medicamentEntity.getActiveIngredient().equals(medicamentEntity2.getActiveIngredient()) &&
                medicamentEntity.getDosage().equals(medicamentEntity2.getDosage()) &&
                medicamentEntity.getPackingForm().equals(medicamentEntity2.getPackingForm()) &&
                medicamentEntity.getInternationalNonproprietaryName().equals(medicamentEntity2.getInternationalNonproprietaryName()) &&
                medicamentEntity.getReleaseForm().equals(medicamentEntity2.getReleaseForm());
    }

    private boolean equalsPharmacies(PharmacyDto pharmacy, PharmacyDto pharmacy2) {
        return pharmacy != null && pharmacy2 != null &&
                pharmacy.getPharmacyName().equals(pharmacy2.getPharmacyName()) &&
                pharmacy.getAddress().equals(pharmacy2.getAddress());
    }

    private boolean equalsCategory(CategoryDto category, CategoryDto category2) {
        return category != null && category2 != null &&
                category.getCategoryName().equals(category2.getCategoryName()) &&
                category.getDescription().equals(category2.getDescription());
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
}

