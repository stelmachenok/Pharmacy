package test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
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
@TestPropertySource("/db.properties")
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

    //    "АВОДАРТ", "Дутастерид", 0.5, CAPSULE, "Дутастерид", WITHOUT_RECIPE, new CategoryDto("Категоря 1", "Описание 1")))
    @Test
    public void when_Correct_post_form_is_submited_expect_get_medicament_page() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/formExecute")
                .param("id", "")
                .param("brandName", "АВОДАРТ")
                .param("activeIngredient", "Дутастерид")
                .param("dosage", "0.5")
                .param("packingForm", "CAPSULE")
                .param("internationalNonproprietaryName", "Дутастерид")
                .param("releaseForm", "WITHOUT_RECIPE")
                .param("categoryMedicament", "1")
                .param("pageNum", "1")
                .param("pageSize", "10")
                .param("sortField", "BRAND_NAME")
                .param("sortDir", "true")
                .param("action", "");
        ResultActions result = mockMvc.perform(request);
        MvcResult result1 = result.andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/medicaments?page-num=1&page-size=10&sort-field=BRAND_NAME&sort-dir=true"))
                .andReturn();
    }

    @Test
    public void when_record_already_exist_and_post_form_is_submited_expect_form_execute_page() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/formExecute")
                .param("id", "")
                .param("brandName", "5-НОК")
                .param("activeIngredient", "Нитроксолин")
                .param("dosage", "50.01")
                .param("packingForm", "CAPSULE")
                .param("internationalNonproprietaryName", "Нитроксолин")
                .param("releaseForm", "WITHOUT_RECIPE")
                .param("categoryMedicament", "1")
                .param("pageNum", "1")
                .param("pageSize", "10")
                .param("sortField", "BRAND_NAME")
                .param("sortDir", "true")
                .param("action", "");
        //  5-НОК	Нитроксолин	50.01	CAPSULE	Нитроксолин	WITHOUT_RECIPE	1

        ResultActions result = mockMvc.perform(request);
        MvcResult result1 = result.andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/formExecute"))
                .andReturn();
    }
}
