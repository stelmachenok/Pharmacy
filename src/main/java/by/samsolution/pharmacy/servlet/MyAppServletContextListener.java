package by.samsolution.pharmacy.servlet;
import by.samsolution.pharmacy.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyAppServletContextListener implements ServletContextListener{

    static final Logger logger = LogManager.getLogger(MyAppServletContextListener.class);

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