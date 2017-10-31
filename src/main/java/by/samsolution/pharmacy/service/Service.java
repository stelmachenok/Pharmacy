package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.entity.Medicament;
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
        if (!medicament.equals(existedMedicament)){
            medicamentDAO.create(medicament);
            return true;
        }
        else{
            throw new PharmacyApplicationException("Medicament " + existedMedicament.getBrandName() + " already exist!");
        }
    }

    public List<Medicament> getAllMedicaments(){
        return medicamentDAO.getAll();
    }
}

