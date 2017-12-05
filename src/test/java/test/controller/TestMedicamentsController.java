package test.controller;

import by.samsolution.pharmacy.controller.MedicamentsController;
import by.samsolution.pharmacy.converter.impl.CategoryConverter;
import by.samsolution.pharmacy.converter.impl.MedicineConverter;
import by.samsolution.pharmacy.dao.impl.MedicamentCategoryDAO;
import by.samsolution.pharmacy.dao.impl.MedicamentDAO;
import by.samsolution.pharmacy.formvalidator.MedicamentValidator;
import by.samsolution.pharmacy.service.impl.CategoryServiceImpl;
import by.samsolution.pharmacy.service.impl.MedicamentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-web-config.xml", "classpath*:service-logic.xml"})
public class TestMedicamentsController {

    @Autowired
    private ApplicationContext applicationContext;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerAdapter handlerAdapter;
    private MedicamentsController controller;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
        controller = new MedicamentsController(new MedicamentServiceImpl(new MedicamentDAO(), new MedicineConverter(new CategoryConverter())),new CategoryServiceImpl(new MedicamentCategoryDAO(), new CategoryConverter()),new MedicamentValidator());
    }

    @Test
    public void testDoSomething() throws Exception {
        request.setRequestURI("/test.html");
        final ModelAndView mav = handlerAdapter.handle(request, response, controller);
        assertViewName(mav, "view");
        // и т.д.
    }
}


//
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath*:spring-web-config.xml", "classpath*:service-logic.xml"} )
//public class TestMedicamentsController {
//    @Autowired
//    WebApplicationContext wac;
//
//    MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
//    }
//
//    @Test
//    public void home() throws Exception {
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");
//        ResultActions result = mockMvc.perform(request);
//        result.andExpect(MockMvcResultMatchers.redirectedUrl("/index"));
//    }
//}
