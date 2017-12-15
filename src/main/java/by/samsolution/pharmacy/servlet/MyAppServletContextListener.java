//package by.samsolution.pharmacy.servlet;
//
//import by.samsolution.pharmacy.dto.CategoryDto;
//import by.samsolution.pharmacy.dto.MedicamentDto;
//import by.samsolution.pharmacy.entity.PackingForm;
//import by.samsolution.pharmacy.entity.ReleaseForm;
//import by.samsolution.pharmacy.exception.EntityAlreadyExistException;
//import by.samsolution.pharmacy.exception.ObjectValidationFailedException;
//import by.samsolution.pharmacy.exception.PharmacyApplicationException;
//import by.samsolution.pharmacy.service.CategoryService;
//import by.samsolution.pharmacy.service.MedicamentService;
//import org.slf4j.*;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyAppServletContextListener implements ServletContextListener {
//
//    private static Logger logger = LoggerFactory.getLogger(MyAppServletContextListener.class);
//
//    @Override
//    public void contextDestroyed(ServletContextEvent arg0) {
//        logger.debug("ServletContextListener destroyed");
//    }
//
//    //Run this before web application is started
//    @Override
//    public void contextInitialized(ServletContextEvent arg0) {
//        logger.debug("ServletContextListener started");
//
//        List<CategoryDto> categoryDtos = new ArrayList<>();
//        categoryDtos.add(new CategoryDto("Категория A", "Описание 1"));
//        categoryDtos.add(new CategoryDto("Категория B", "Описание 2"));
//        categoryDtos.add(new CategoryDto("Категория C", "Описание 3"));
//        categoryDtos.add(new CategoryDto("Категория D", "Описание 4"));
//        categoryDtos.add(new CategoryDto("Категория X", "Описание 5"));
//        categoryDtos.add(new CategoryDto("Категория N", "Описание 6"));
//        List<MedicamentDto> medicamentDtos = new ArrayList<>();
//        CategoryDto categoryDto = categoryDtos.get(0);
//        medicamentDtos.add(new MedicamentDto("L-ОПТИК", "Левофлоксацин", 5.0, PackingForm.DROP, "Левофлоксацин", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//        medicamentDtos.add(new MedicamentDto("L-ТИРОКСИН", "Левотироксин натрия", 50.0, PackingForm.TABLET, "Левотироксин натрия", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//        medicamentDtos.add(new MedicamentDto("5-НОК", "Нитроксолин", 50.0, PackingForm.TABLET, "Нитроксолин", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//        medicamentDtos.add(new MedicamentDto("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE, categoryDto));
////        medicamentEntities.add(new MedicamentEntity("АВАМИС", "Флутиказон", 27.5, PackingForm.AEROSOL, "Флутиказон", ReleaseForm.WITHOUT_RECIPE));
//        medicamentDtos.add(new MedicamentDto("АВЕЛОКС", "Моксифлоксацин", 400.0, PackingForm.TABLET, "Моксифлоксацин", ReleaseForm.WITHOUT_RECIPE, categoryDto));
////        medicamentEntities.add(new MedicamentEntity("АВОДАРТ", "Дутастерид", 0.5, PackingForm.CAPSULE, "Дутастерид", ReleaseForm.WITHOUT_RECIPE));
//        medicamentDtos.add(new MedicamentDto("АВОДАРТ", "Дутастерид", 0.5, PackingForm.CAPSULE, "Дутастерид", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//        medicamentDtos.add(new MedicamentDto("АДАПТОЛ", "Мебикар", 500.0, PackingForm.TABLET, "Мебикар", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//
//        medicamentDtos.add(new MedicamentDto("АЕВИТ", "Ретинол", 1.19, PackingForm.CAPSULE, "Ретинол", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//        medicamentDtos.add(new MedicamentDto("АЕКОЛ", "Токоферол", 100.0, PackingForm.AEROSOL, "Токоферол", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//        medicamentDtos.add(new MedicamentDto("АДВАНТАН", "Метилпреднизолона ацепонат", 20.0, PackingForm.EMULSION, "Метилпреднизолона ацепонат", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//        medicamentDtos.add(new MedicamentDto("АДЕНИК", "Тамсулозин", 0.4, PackingForm.CAPSULE, "Тамсулозин", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//        medicamentDtos.add(new MedicamentDto("АДИЦЕФ", "Цефдинир", 125.0, PackingForm.POWDER, "Цефдинир", ReleaseForm.WITHOUT_RECIPE, categoryDto));
//
//        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
//        MedicamentService medicamentService = context.getBean(MedicamentService.class);
//        for (MedicamentDto medicamentDto : medicamentDtos) {
//            try {
//                medicamentService.add(medicamentDto);
//            } catch (PharmacyApplicationException e) {
//                logger.error(e.getMessage(), e);
//                throw new RuntimeException(e);
//            }
//        }
//
//        CategoryService categoryService = context.getBean(CategoryService.class);
//        categoryDtos.forEach((c) -> {
//            try {
//                categoryService.add(c);
//            } catch (ObjectValidationFailedException | EntityAlreadyExistException e) {
//                logger.error(e.getMessage(), e);
//                throw new RuntimeException(e);
//            }
//        });
//    }
//}