package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class Controller {
    MedicamentDAO medicamentDAO;
    public Controller(){
        medicamentDAO = new MedicamentDAO();
        Medicament medicament = new Medicament("Мезим", "панкреатин", "125 мг", PackingForm.TABLET, "Мезим форте 10000", ReleaseForm.WITHOUT_RECIPE);
        medicamentDAO.create(medicament);
    }
}
