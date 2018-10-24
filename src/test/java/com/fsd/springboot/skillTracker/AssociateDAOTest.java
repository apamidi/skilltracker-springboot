package com.fsd.springboot.skillTracker;
/**
 * 
 *//*
package com.rm.fsd.springboot.skillTracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.rm.fsd.springboot.skillTracker.dao.IAssociateDAO;
import com.rm.fsd.springboot.skillTracker.dto.Associate;


*//**
 * @author AP
 *
 *//*

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
@TestPropertySource(locations = "classpath:skillTracker-integrationtest.properties")
public class AssociateDAOTest {
	
		
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private IAssociateDAO associateDAO;
    
    Properties appProps = null;
        
        
    @Test
    public void whenFindById_thenReturnAssociate() {
        // given
        Associate associate = new Associate();
        loadProperties();
        
        associate.setAssociateName(appProps.getProperty("test.dao.assoc.name"));
        associate.setEmail(appProps.getProperty("test.dao.assoc.email"));
        
        entityManager.persist(associate);
        entityManager.flush();
     
        // when
        List<Associate> foundAssoc = associateDAO.findByEmail(appProps.getProperty("test.dao.assoc.email"));
     
        // then
        assertThat(foundAssoc.get(0).getAssociateName())
          .isEqualTo(associate.getAssociateName());
    }
    
    private void loadProperties() {
    	appProps = new Properties();
    	try {
			appProps.load(AssociateDAOTest.class.getClassLoader().getResourceAsStream("skillTracker-integrationtest.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
*/