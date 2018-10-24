/**
 * 
 */
package com.fsd.springboot.skillTracker.service;

import java.util.List;

import com.fsd.springboot.skillTracker.dto.Associate;

/**
 * @author pamidi
 *
 */
public interface IAssociateService {
	
	List<Associate> getAllAssociates();

	Associate getAssociateByID(Long id);
		
	boolean saveAssociate(Associate associate);
	
	boolean updateAssociate(Associate associate);
	
	boolean deleteAssociate(Associate associate);
	
	Associate getAssociateByEmail(String email);
	
	List<Object[]> getAssocCountByGender();
	
	List<Object[]> getAssocCountByLevel();
}
