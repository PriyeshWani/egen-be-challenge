import io.egen.weighttracker.Metrics;
import io.egen.weighttracker.MetricsService;
import io.egen.weighttracker.WeightTracker;
import org.apache.catalina.connector.Response;
import java.util.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by priyeshkumarwani on 5/26/16.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(WeightTracker.class)
//@WebIntegrationTest
//@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")
public class MetricsServiceTests {

//    @Autowired
//    MetricsService metricsService;

    @Mock
    MetricsService metricsService;

    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {

       // mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(MetricsService.class).build();
    }


    @Test
    @Ignore
    public void testRetrieveAllMetrics() throws Exception{


       // assertNotNull(metricsService);
        //metricsService = Mockito.mock(MetricsService.class);
        Metrics metrics = new Metrics();
        metrics.setValue(150);
        metrics.setTimestamp("1464302192040");
        List<Metrics>  mockResponse = new ArrayList<Metrics>();
        mockResponse.add(metrics);
        ResponseEntity<List<Metrics>> response = new ResponseEntity<List<Metrics>>(mockResponse, HttpStatus.OK);
        when(metricsService.getAllMetrics()).thenReturn(null);
        this.mockMvc.perform(get("/retrieveAllMetrics")).andExpect(status().isOk());
        //assertEquals(response, metricsService.getAllMetrics());



        //when(metricsService.getAllMetrics()).thenReturn(new ResponseEntity<List<Metrics>>(mockResponse, HttpStatus.OK));
//
//        this.mockMvc.perform(get("/retrieveAllMetrics"))
//            .andExpect(status().isOk());



    }


}
