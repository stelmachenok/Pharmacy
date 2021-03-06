package by.samsolution.pharmacy.util;

import org.flywaydb.core.Flyway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.sql.DataSource;

public class FlyWayInit implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
            Flyway flyway = new Flyway();
            flyway.setDataSource((DataSource) applicationContext.getBean("dataSource"));
            flyway.clean();
            flyway.migrate();
        }
    }
}
