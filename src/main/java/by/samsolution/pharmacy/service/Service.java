package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.entity.Medicament;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Service {
    private MedicamentDAO medicamentDAO;
    private MedicamentCategoryDAO categoryDAO;
    private PharmacyDAO pharmacyDAO;
    static final Logger logger = LogManager.getLogger(Service.class);

    public Service() {
        medicamentDAO = new MedicamentDAO();
        categoryDAO = new MedicamentCategoryDAO();
        pharmacyDAO = new PharmacyDAO();
    }

    public boolean addMedicament(Medicament medicament) {
        Medicament existedMedicament = medicamentDAO.getEntityByName(medicament.getBrandName());
        if (!medicament.equals(existedMedicament)){
            medicamentDAO.create(medicament);
            return true;
        }
        else{
            logger.info("Medicament " + existedMedicament.getBrandName() + " already exist!");
            return false;
        }
    }

    public List<Medicament> getAllMedicaments(){
        return medicamentDAO.getAll();
    }
}

