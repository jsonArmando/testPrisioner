package com.prisoner.test;

import com.prisoner.test.controllers.PrisionerController;
import com.prisoner.test.entities.Person;
import com.prisoner.test.repositories.PersonRepository;
import com.prisoner.test.services.PrisionerService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PrisionerControllerTest {
    private MockMvc mockMvc;
    @Mock
    private PrisionerService prisionerService;
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PrisionerController prisionerController;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(prisionerController).build();
        personRepository.deleteAll();
        personRepository.save(new Person("A", false));
    }
    @Test
    public void controllerInitializedCorrectly() {
        assertThat(prisionerController).isNotNull();
    }
    @Test
    public void getStatsStatusOK() throws Exception {
        mockMvc.perform(get("/stats")).andExpect(status().isOk());
    }

    @Test
    public void postPrisionerForbiddenIsHuman() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/prosioner/")
                .content("{\"dna\":[\"|||||||S||\",\"|P ||   |\",\"||  | | |\",\"|v| | < |\"]}").
                contentType(MediaType.APPLICATION_JSON);
        this.mockMvc.perform(builder).andExpect(status().isForbidden());

    }

}
