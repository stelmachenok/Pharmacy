package by.samsolution.pharmacy.servlets;

import by.samsolution.pharmacy.controller.Controller;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.entity.Medicament;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MyServlet extends HttpServlet {

    Controller controller = new Controller();
    MedicamentDAO medicamentDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }


    @Override
    public void init() throws ServletException {
        List<Medicament> medicaments = (List<Medicament>) getServletContext().getAttribute("Medicaments");
        medicamentDAO = new MedicamentDAO();
        medicaments.forEach((medicament) -> {
            Medicament existedMedicament = medicamentDAO.getEntityByName(medicament.getBrandName());
            if (existedMedicament == null || !existedMedicament.equals(medicament)) {
                medicamentDAO.create(medicament);
            } else {
                System.out.println("Duplicate medicament");
            }
        });
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Medicament> medicaments = medicamentDAO.getAll();
        medicaments.forEach(System.out::println);
    }
}
