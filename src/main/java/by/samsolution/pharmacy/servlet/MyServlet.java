package by.samsolution.pharmacy.servlet;

import by.samsolution.pharmacy.entity.Medicament;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MyServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(MyServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    public void init() throws ServletException {
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

        Service service = (Service) getServletContext().getAttribute("Service");
        medicaments.forEach(service::addMedicament);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Service service = (Service) getServletContext().getAttribute("Service");
        service.getAllMedicaments().forEach(logger::info);

    }
}
