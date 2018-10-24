package com.fsd.springboot.skillTracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.springboot.skillTracker.dao.IAssociateSkillDAO;
import com.fsd.springboot.skillTracker.dto.AssociateSkill;

@Service
public class IAssociateSkillServiceImpl implements IAssociateSkillService{

	@Autowired
	private IAssociateSkillDAO associateSkillDAO;
	
	public List<Object[]> getCountBySkill() {
		// TODO Auto-generated method stub
		return associateSkillDAO.getCountBySkill();
	}
	
	public List<AssociateSkill> getAssociateSkillsBySkillId(Long skillId){
		return associateSkillDAO.findBySkillId(skillId);
	}
	
	
}
