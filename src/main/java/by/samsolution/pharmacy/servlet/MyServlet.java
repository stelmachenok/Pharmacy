package by.samsolution.pharmacy.servlet;

import by.samsolution.pharmacy.service.Service;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.*;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MyServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(MyServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ApplicationContext context = (ApplicationContext) getServletContext().getAttribute("context");
        Service service = (Service) context.getBean("Service");


//        Service service = (Service) getServletContext().getAttribute("Service");
        PrintWriter pw = response.getWriter();
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        VelocityContext vc = new VelocityContext(); // создание контекста Velocity
        vc.put("medicaments", service.getAllMedicaments()); // атрибут "name" связывается с именем переменной $foo в шаблоне и помещается в контекст

        Template template = ve.getTemplate("/template.vm", "utf-8"); // загрузка шаблона с именем template.vm
        template.merge(vc, pw); // метод merge() принимает набор данных в виде объекта "vc" и объект потока "bw"
//        service.getAllMedicaments().forEach(m -> {
//
//            pw.write(m.toString());
//            logger.info(String.valueOf(m));
//        });

    }
}
