package by.samsolution.pharmacy.servlet;
import by.samsolution.pharmacy.service.Service;
import org.slf4j.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppServletContextListener implements ServletContextListener{

    private static Logger logger = LoggerFactory.getLogger(MyAppServletContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        logger.debug("ServletContextListener destroyed");
    }

    //Run this before web application is started
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        logger.debug("ServletContextListener started");
        Service service = new Service();
        arg0.getServletContext().setAttribute("Service", service);
    }
}