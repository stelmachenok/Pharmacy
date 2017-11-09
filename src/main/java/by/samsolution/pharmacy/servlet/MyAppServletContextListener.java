package by.samsolution.pharmacy.servlet;

import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.exception.PharmacyApplicationException;
import by.samsolution.pharmacy.service.Service;
import org.slf4j.*;
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

        List<MedicamentDto> medicamentDtos = new ArrayList<>();
        medicamentDtos.add(new MedicamentDto("L-ОПТИК", "Левофлоксацин", "5.0", PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE));
        medicamentDtos.add(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", "50.0", PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE));
        medicamentDtos.add(new MedicamentDto("5-НОК", "Нитроксолин", "50.0", PackingForm.TABLET, "Нитроксолин", ReleaseForm.WITHOUT_RECIPE));
        medicamentDtos.add(new MedicamentDto("АВАМИС", "Флутиказон", "27.5", PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE));
//        medicamentEntities.add(new MedicamentEntity("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE));
        medicamentDtos.add(new MedicamentDto("АВЕЛОКС", "Моксифлоксацин", "400.0", PackingForm.TABLET, "Моксифлоксацин", ReleaseForm.WITHOUT_RECIPE));
//        medicamentEntities.add(new MedicamentEntity("АВОДАРТ", "Дутастерид", 0.5, PackingForm.CAPSULE, "Дутастерид", ReleaseForm.WITHOUT_RECIPE));
        medicamentDtos.add(new MedicamentDto("АВОДАРТ", "Дутастерид", "0.5", PackingForm.CAPSULE, "Дутастерид", ReleaseForm.WITHOUT_RECIPE));
        medicamentDtos.add(new MedicamentDto("АДАПТОЛ", "Мебикар", "500.0", PackingForm.TABLET, "Мебикар", ReleaseForm.WITHOUT_RECIPE));


        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
        arg0.getServletContext().setAttribute("context", context);
        Service service = (Service) context.getBean("Service");
        for (MedicamentDto medicamentDto : medicamentDtos) {
            try {
                service.addMedicament(medicamentDto);
            } catch (PharmacyApplicationException e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }
}