package by.samsolution.pharmacy.servlets;

import by.samsolution.pharmacy.controller.Controller;
import by.samsolution.pharmacy.entity.Medicament;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by y50-70 on 20.10.2017.
 */
@WebServlet("/s")
public class MyServlet extends HttpServlet {

    Controller controller = new Controller();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        response.setContentType("text/html");
        String varTextA = "Hello World!";
        request.setAttribute("textA", varTextA);
        String varTextB = "Its JSP";
        request.setAttribute("textB", varTextB);

        /***********************by.samsolution.pharmacy.dao Test*************************/

//        Medicament medicament = controller.getMedicamentByName(1773);
//        int number;
//        String text = medicament.getBrandName();
//
//        request.setAttribute("brandName", text);
//        text = medicament.getActiveIngredient();
//        request.setAttribute("activeIngredient", text);
//        number = medicament.getDosage();
//        request.setAttribute("dosage", number);
//        text = medicament.getPackingForm();
//        request.setAttribute("packingForm", text);
//        text = medicament.getInternationalNonproprietaryName();
//        request.setAttribute("internationalNonproprietaryName", text);
//        text = medicament.getReleaseMedicament();
//        request.setAttribute("releaseMedicament", text);
//        number = medicament.getGUID();
//        request.setAttribute("GUID", number);


        /***********************End by.samsolution.pharmacy.dao Test*************************/

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
