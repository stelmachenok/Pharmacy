package by.samsolution.pharmacy.controller;

import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.entity.Medicament;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class Controller {
    MedicamentDAO medicamentDAO;
    public Controller(){
        medicamentDAO = new MedicamentDAO();
        Medicament medicament = new Medicament("Мезим", "панкреатин", 125, "Таблетка", "Мезим форте 10000", "Без рецепта");
        medicamentDAO.create(medicament);
    }

//    public Medicament getMedicamentByName(int ID){
//        return medicamentDAO.getEntityById();
//    }
}
