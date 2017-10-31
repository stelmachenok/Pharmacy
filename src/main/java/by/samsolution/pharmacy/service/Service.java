package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.entity.MedicamentCategory;
import by.samsolution.pharmacy.entity.Pharmacy;
import by.samsolution.pharmacy.exception.EntityNotFoundException;
import by.samsolution.pharmacy.exception.PharmacyApplicationException;
import org.slf4j.*;

import java.util.List;

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

    public boolean addMedicament(Medicament medicament) throws PharmacyApplicationException {
        Medicament existedMedicament = medicamentDAO.getEntityByName(medicament.getBrandName());
        if (!medicament.equals(existedMedicament)) {
            medicamentDAO.create(medicament);
            return true;
        } else {
            throw new PharmacyApplicationException("Medicament " + existedMedicament + " already exist!");
        }
    }

    public boolean updateMedicament(Medicament medicament) throws EntityNotFoundException {
        Medicament existedMedicament = medicamentDAO.getEntityById(medicament.getId());
        if (existedMedicament != null) {
            medicamentDAO.update(medicament);
            return true;
        } else {
            throw new EntityNotFoundException("Medicament " + medicament + " doesn't exist");
        }
    }

    public boolean deleteMedicament(Medicament medicament) throws EntityNotFoundException {
        Medicament existedMedicament = medicamentDAO.getEntityById(medicament.getId());
        if (existedMedicament != null) {
            medicamentDAO.delete(medicament.getId());
            return true;
        } else {
            throw new EntityNotFoundException("Medicament " + medicament + " doesn't exist");
        }
    }

    public boolean addMedicamentCategory(MedicamentCategory category) throws PharmacyApplicationException {
        MedicamentCategory existedCategory = categoryDAO.getEntityByName(category.getCategoryName());
        if (!category.equals(existedCategory)) {
            categoryDAO.create(category);
            return true;
        } else {
            throw new PharmacyApplicationException("Category " + existedCategory + " already exist!");
        }
    }

    public boolean updateMedicamentCategory(MedicamentCategory category) throws EntityNotFoundException {
        MedicamentCategory existedCategory = categoryDAO.getEntityById(category.getId());
        if (existedCategory != null) {
            categoryDAO.update(category);
            return true;
        } else {
            throw new EntityNotFoundException("Category " + category + " doesn't exist");
        }
    }

    public boolean deleteMedicamentCategory(Medicament category) throws EntityNotFoundException {
        MedicamentCategory existedCategory = categoryDAO.getEntityById(category.getId());
        if (existedCategory != null) {
            categoryDAO.delete(category.getId());
            return true;
        } else {
            throw new EntityNotFoundException("Category " + category + " doesn't exist");
        }
    }


    public boolean addPharmacy(Pharmacy pharmacy) throws PharmacyApplicationException {
        Pharmacy existedPharmacy = pharmacyDAO.getEntityByName(pharmacy.getPharmacyName());
        if (!pharmacy.equals(existedPharmacy)) {
            pharmacyDAO.create(pharmacy);
            return true;
        } else {
            throw new PharmacyApplicationException("Category " + existedPharmacy + " already exist!");
        }
    }

    public boolean updatePharmacy(Pharmacy pharmacy) throws EntityNotFoundException {
        Pharmacy existedPharmacy = pharmacyDAO.getEntityById(pharmacy.getId());
        if (existedPharmacy != null) {
            pharmacyDAO.update(pharmacy);
            return true;
        } else {
            throw new EntityNotFoundException("Category " + pharmacy + " doesn't exist");
        }
    }

    public boolean deletePharmacy(Pharmacy pharmacy) throws EntityNotFoundException {
        Pharmacy existedPharmacy = pharmacyDAO.getEntityById(pharmacy.getId());
        if (existedPharmacy != null) {
            pharmacyDAO.delete(pharmacy.getId());
            return true;
        } else {
            throw new EntityNotFoundException("Category " + pharmacy + " doesn't exist");
        }
    }

    public List<Medicament> getAllMedicaments() {
        return medicamentDAO.getAll();
    }
}

