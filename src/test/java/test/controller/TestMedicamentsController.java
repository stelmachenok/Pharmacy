package test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring-web-config.xml", "classpath*:service-logic.xml"})
public class TestMedicamentsController {
    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    public void when_Default_Page_Is_Requested_We_Expect_Pharmacy_In_AppName_Var() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");
        ResultActions result = mockMvc.perform(request);
        MvcResult result1 = result.andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("welcome"))
                .andExpect(MockMvcResultMatchers.model().attribute("appName", "Pharmacy"))
                /*.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html"))
                .andExpect(xpath("/html/head/title").string("Welcome"))*/
                .andReturn();
    }

    @Test
    public void when_Medicaments_Page_Is_Requested_We_Expect_To_Get_Time() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/getTime");
        ResultActions result = mockMvc.perform(request);
        MvcResult result1 = result.andDo(print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn();

        String s = result1.getResponse().getContentAsString();
        assertEquals(Long.parseLong(s), System.currentTimeMillis(), 5000);
    }

}
