package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.exception.EntityAlreadyExistEsception;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
import org.slf4j.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Service {
    private MedicamentDAO medicamentDAO;
    private MedicamentCategoryDAO categoryDAO;
    private PharmacyDAO pharmacyDAO;

    private static Logger logger = LoggerFactory.getLogger(Service.class);

    public Service() {
        medicamentDAO = new MedicamentDAO();
        categoryDAO = new MedicamentCategoryDAO();
        pharmacyDAO = new PharmacyDAO();
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

    public void addMedicament(Medicament medicament) throws EntityAlreadyExistEsception, ObjectValidationFailedException {
        Medicament existedMedicament = medicamentDAO.getEntityByName(medicament.getBrandName());
        if (medicament.getDosage() < 0)
            throw new ObjectValidationFailedException("Incorrect dosage " + medicament.getDosage());
        if (!equalsMedicaments(existedMedicament, medicament)) {
            medicamentDAO.create(medicament);
        } else {
            throw new EntityAlreadyExistEsception("Medicament " + existedMedicament + " already exist!");
        }
    }

    public void updateMedicament(Medicament medicament) throws EntityNotFoundException, ObjectValidationFailedException {
        Medicament existedMedicament = medicamentDAO.getEntityById(medicament.getId());
        if (medicament.getDosage() < 0)
            throw new ObjectValidationFailedException("Incorrect dosage " + medicament.getDosage());
        if (existedMedicament != null) {
            medicamentDAO.update(medicament);
        } else {
            throw new EntityNotFoundException("Medicament " + medicament + " doesn't exist");
        }
    }

    public void deleteMedicament(Medicament medicament) throws EntityNotFoundException {
        Medicament existedMedicament = medicamentDAO.getEntityById(medicament.getId());
        if (existedMedicament != null) {
            medicamentDAO.delete(medicament.getId());
        } else {
            throw new EntityNotFoundException("Medicament " + medicament + " doesn't exist");
        }
    }

    public void addMedicamentCategory(MedicamentCategory category) throws EntityAlreadyExistEsception {
        MedicamentCategory existedCategory = categoryDAO.getEntityByName(category.getCategoryName());
        if (!equalsCategory(category, existedCategory)) {
            categoryDAO.create(category);
        } else {
            throw new EntityAlreadyExistEsception("Category " + existedCategory + " already exist!");
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


    public void addPharmacy(Pharmacy pharmacy) throws EntityAlreadyExistEsception, ObjectValidationFailedException {
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
            throw new EntityAlreadyExistEsception("Pharmacy " + existedPharmacy + " already exist!");
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

    public List<Medicament> getAllMedicaments() {
        return medicamentDAO.getAll();
    }

    private boolean equalsMedicaments(Medicament medicament, Medicament medicament2) {
        return medicament != null && medicament2 != null &&
                medicament.getBrandName().equals(medicament2.getBrandName()) &&
                medicament.getActiveIngredient().equals(medicament2.getActiveIngredient()) &&
                medicament.getDosage().equals(medicament2.getDosage()) &&
                medicament.getPackingForm().equals(medicament2.getPackingForm()) &&
                medicament.getInternationalNonproprietaryName().equals(medicament2.getInternationalNonproprietaryName()) &&
                medicament.getReleaseForm().equals(medicament2.getReleaseForm());
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

