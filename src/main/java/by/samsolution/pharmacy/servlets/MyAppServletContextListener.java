package by.samsolution.pharmacy.servlets;

import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

public class MyAppServletContextListener implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("ServletContextListener destroyed");
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContextListener started");
        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament("L-ОПТИК", "Левофлоксацин", "5 мг", PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", "50 мкг", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("5-НОК", "Нитроксолин", "50 мг", PackingForm.TABLET, "Нитроксолин", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АВАМИС", "Флутиказон", "27.5 мг/доза", PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АВАМИС", "Флутиказон", "27.5 мг/доза", PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АВЕЛОКС", "Моксифлоксацин", "п/о 400 мг", PackingForm.TABLET, "Моксифлоксацин", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АВОДАРТ", "Дутастерид", "0.5 мг", PackingForm.CAPSULE, "Дутастерид", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АВОДАРТ", "Дутастерид", "0.5 мг", PackingForm.CAPSULE, "Дутастерид", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АДАПТОЛ", "Мебикар", "500 мг", PackingForm.TABLET, "Мебикар", ReleaseForm.WITHOUT_RECIPE));
        arg0.getServletContext().setAttribute("Medicaments", medicaments);
    }
}