package by.samsolution.pharmacy.servlet;

import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.PharmacyApplicationException;
import by.samsolution.pharmacy.service.Service;
import org.slf4j.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

public class MyAppServletContextListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(MyAppServletContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        logger.debug("ServletContextListener destroyed");
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        logger.debug("ServletContextListener started");

        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("5-НОК", "Нитроксолин", 50.0, PackingForm.TABLET, "Нитроксолин", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE));
//        medicaments.add(new Medicament("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АВЕЛОКС", "Моксифлоксацин", 400.0, PackingForm.TABLET, "Моксифлоксацин", ReleaseForm.WITHOUT_RECIPE));
//        medicaments.add(new Medicament("АВОДАРТ", "Дутастерид", 0.5, PackingForm.CAPSULE, "Дутастерид", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АВОДАРТ", "Дутастерид", 0.5, PackingForm.CAPSULE, "Дутастерид", ReleaseForm.WITHOUT_RECIPE));
        medicaments.add(new Medicament("АДАПТОЛ", "Мебикар", 500.0, PackingForm.TABLET, "Мебикар", ReleaseForm.WITHOUT_RECIPE));


        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
        arg0.getServletContext().setAttribute("context", context);
        Service service = (Service) context.getBean("Service");
        for (Medicament medicament : medicaments) {
            try {
                service.addMedicament(medicament);
            } catch (PharmacyApplicationException e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }
}