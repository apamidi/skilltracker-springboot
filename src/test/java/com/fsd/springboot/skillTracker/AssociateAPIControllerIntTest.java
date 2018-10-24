/**
 * 
 */
package com.fsd.springboot.skillTracker;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fsd.springboot.skillTracker.SpringBootFsdSkillTrackerApplication;

/**
 * @author AP
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootFsdSkillTrackerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:skillTracker-integrationtest.properties")
public class AssociateAPIControllerIntTest {


	@LocalServerPort
	private int port;
	
	@Autowired
    private MockMvc mvc;
	
	Properties appProps = null;
	          
    
    @Test
	public void testGetTask() {
			 
	    try {
	    	loadProperties();
	    	
			mvc.perform(get("/assocs/")
			  .contentType(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk())
			  .andExpect(jsonPath("$", hasSize(Integer.valueOf(appProps.getProperty("test.associate.size")))))
			  .andExpect(jsonPath("$[0].associateName", is(appProps.getProperty("test.associate.1.name"))))
			  .andExpect(jsonPath("$[0].email", is(appProps.getProperty("test.associate.1.email"))));
			  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
    private void loadProperties() {
    	appProps = new Properties();
    	try {
			appProps.load(AssociateAPIControllerIntTest.class.getClassLoader().getResourceAsStream("skillTracker-integrationtest.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
