package test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestPropertySource("/db.properties")
@ContextConfiguration(locations = {"classpath*:spring-web-config.xml", "classpath*:service-logic.xml"})
public class ControllerTests {
    @Autowired
    WebApplicationContext wac;
    @Autowired
    MockHttpSession session;

    MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();
    }

    @Test
    public void test_return_login_page_when_requested_url_is_login() throws Exception {
        MockHttpServletRequestBuilder request = get("/login");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print())
                .andExpect(view().name("login"))
                .andReturn();
    }

    @Test
    public void test_return_medicaments_page_when_requested_url_is_medicaments() throws Exception {
        MockHttpServletRequestBuilder request = get("/medicaments");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print())
                .andExpect(view().name("medicaments"))
                .andReturn();
    }

    @Test
    public void test_return_categories_page_when_requested_url_is_categories() throws Exception {
        MockHttpServletRequestBuilder request = get("/categories");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print())
                .andExpect(view().name("categories"))
                .andReturn();
    }

    @Test
    public void test_return_pharmacies_page_when_requested_url_is_pharmacies() throws Exception {
        MockHttpServletRequestBuilder request = get("/pharmacies");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print())
                .andExpect(view().name("pharmacies"))
                .andReturn();
    }

    @Test
    public void test_return_search_page_when_requested_url_is_searchPage() throws Exception {
        MockHttpServletRequestBuilder request = get("/searchPage");
        ResultActions result = mockMvc.perform(request);
        result.andDo(print())
                .andExpect(view().name("searchPage"))
                .andReturn();
    }

    @Test
    public void when_correct_medicament_post_form_is_submited_expect_get_medicament_page() throws Exception {
        MockHttpServletRequestBuilder request = post("/formExecute")
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
    public void when_correct_post_category_form_is_submited_expect_get_category_page() throws Exception {
        MockHttpServletRequestBuilder request = post("/categoryFormExecute")
                .param("id", "")
                .param("categoryName", "Category C")
                .param("description", "descr")
                .param("pageNum", "1")
                .param("pageSize", "10")
                .param("sortField", "CATEGORY_NAME")
                .param("sortDir", "true")
                .param("action", "");
        ResultActions result = mockMvc.perform(request);
        MvcResult result1 = result.andDo(print())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/categories?page-num=1&page-size=10&sort-field=CATEGORY_NAME&sort-dir=true"))
                .andReturn();
    }
}
