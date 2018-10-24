/**
 * 
 */
package com.fsd.springboot.skillTracker.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.springboot.skillTracker.dao.IAssociateDAO;
import com.fsd.springboot.skillTracker.dto.Associate;



/**
 * @author AP
 *
 */

@Service
public class IAssociateServiceImpl implements IAssociateService {
	
	@Autowired
	private IAssociateDAO associateDAO;

	@Override
	public List<Associate> getAllAssociates() {
		// TODO Auto-generated method stub
		return associateDAO.findAll();
	}
	
	@Override
	public Associate getAssociateByID(Long assocId) {
		// TODO Auto-generated method stub
		Optional<Associate> ot = associateDAO.findById(assocId);
		return ot.isPresent() ? ot.get() : null;
	}

	@Transactional
	@Override
	public boolean saveAssociate(Associate associate) {
		boolean transFlag = false;
		// TODO Auto-generated method stub
		associateDAO.save(associate);
		transFlag = true;
		return transFlag;
	}

	@Transactional
	@Override
	public boolean updateAssociate(Associate associate) {
		boolean transFlag = false;
		// TODO Auto-generated method stub
		associateDAO.saveAndFlush(associate);
		transFlag = true;
		return transFlag;
	}

	@Transactional
	@Override
	public boolean deleteAssociate(Associate associate) {
		boolean transFlag = false;
		// TODO Auto-generated method stub
		//associateDAO.deleteById(AssociateId);
		associateDAO.delete(associate);
		transFlag = true;
		return transFlag;
	}

	public IAssociateDAO getAssociateDAO() {
		return associateDAO;
	}

	public void setAssociateDAO(IAssociateDAO associateDAO) {
		this.associateDAO = associateDAO;
	}

	@Override
	public Associate getAssociateByEmail(String email) {
		// TODO Auto-generated method stub
		Associate associate = null;
		List<Associate> associateList = associateDAO.findByEmail(email);
		if(associateList != null && !associateList.isEmpty()) {
			associate = (Associate)associateList.get(0);
		}
		return associate;
	}

	@Override
	public List<Object[]> getAssocCountByGender() {
		List<Object[]> genderResult = null;
		
		genderResult = associateDAO.getCountByGender();
		
		return genderResult;
	}
	
	@Override
	public List<Object[]> getAssocCountByLevel() {
		List<Object[]> levelResult = null;
		
		levelResult = associateDAO.getCountByLevel();
		
		return levelResult;
	}

}
