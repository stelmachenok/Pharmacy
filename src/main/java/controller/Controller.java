package controller;

import DAO.impl.MedicamentDAO;
import entity.Medicament;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class Controller {
    MedicamentDAO medicamentDAO;
    public Controller(){
        medicamentDAO = new MedicamentDAO();
        Medicament medicament = new Medicament("Мезим", "панкреатин", 125, "Таблетка", "Мезим форте 10000", "Без рецепта", 1773);
        medicamentDAO.create(medicament);
    }

    public Medicament getMedicamentById(int ID){
        return medicamentDAO.getEntityById(1773);
    }
}
