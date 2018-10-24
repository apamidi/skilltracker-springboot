/**
 * 
 */
package com.fsd.springboot.skillTracker;

import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsd.springboot.skillTracker.dao.IAssociateDAO;
import com.fsd.springboot.skillTracker.dto.Associate;
import com.fsd.springboot.skillTracker.service.IAssociateServiceImpl;


/**
 * @author AP
 *
 */

@RunWith(SpringRunner.class)
public class ISkillTrackerAssociateServiceImplTest {
	
	@TestConfiguration
    static class SkillTrackerServiceImplTestContextConfiguration {
  
        @Bean
        public IAssociateServiceImpl associateServiceImpl() {
            return new IAssociateServiceImpl();
        }
	}
	
	@Autowired
    private IAssociateServiceImpl associateService;
 
    @MockBean
    private IAssociateDAO associateDAO;
    
    Properties appProps = null;
    
    @Before
    public void setUp() {
    	loadProperties();
        Associate associate = new Associate();
        associate.setAssociateId(Long.valueOf(111));
        associate.setAssociateName("Arav Pami");
                
        Optional<Associate> opt = Optional.of(associate);
        Mockito.when(associateDAO.findById(associate.getAssociateId()))
          .thenReturn(opt);
        associateService.setAssociateDAO(associateDAO);
        
    }
    
    @Test
    public void whenValidName_thenTaskShouldBeFound() {
        Long assocId = new Long(111);
        String associatename = "Arav Pami";
        Associate foundAssoc = associateService.getAssociateByID(assocId);
      
         assertThat(foundAssoc.getAssociateName())
          .isEqualTo(associatename);
     }
    
    @Test
    public void whenValidName_thenTaskShouldNotBeFound() {
        Long assocId = new Long(111);
        String assocName = "A 2";
        Associate foundAssoc = associateService.getAssociateByID(assocId);
      
         assertThat(foundAssoc.getAssociateName())
          .isNotEqualTo(assocName);
     }

    private void loadProperties() {
    	appProps = new Properties();
    	try {
			appProps.load(ISkillTrackerAssociateServiceImplTest.class.getClassLoader().getResourceAsStream("skillTracker-integrationtest.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
