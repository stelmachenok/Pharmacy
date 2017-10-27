package by.samsolution.pharmacy.service;

import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.dao.impl.PharmacyDAO;
import by.samsolution.pharmacy.entity.Medicament;

import java.util.List;

public class Service {
    private MedicamentDAO medicamentDAO;
    private MedicamentCategoryDAO categoryDAO;
    private PharmacyDAO pharmacyDAO;

    public Service() {
        medicamentDAO = new MedicamentDAO();
        categoryDAO = new MedicamentCategoryDAO();
        pharmacyDAO = new PharmacyDAO();
    }

    public void addMedicament(Medicament medicament) {
        Medicament existedMedicament = medicamentDAO.getEntityByName(medicament.getBrandName());
        if (existedMedicament == null || !existedMedicament.equals(medicament)){
            medicamentDAO.create(medicament);
        }
        else{
            System.out.println("Medicament already exist!");
        }
    }

    public List<Medicament> getAllMedicaments(){
        return medicamentDAO.getAll();
    }
}

