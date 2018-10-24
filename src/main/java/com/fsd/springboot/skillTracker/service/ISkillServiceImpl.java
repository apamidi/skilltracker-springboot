/**
 * 
 */
package com.fsd.springboot.skillTracker.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.springboot.skillTracker.dao.ISkillDAO;
import com.fsd.springboot.skillTracker.dto.Skill;


/**
 * @author AP
 *
 */

@Service
public class ISkillServiceImpl implements ISkillService {
	
	@Autowired
	private ISkillDAO skillDAO;

	@Override
	public List<Skill> getAllSkills() {
		// TODO Auto-generated method stub
		return skillDAO.findAll();
	}
	
	@Override
	public Skill getSkillByID(Long skillId) {
		// TODO Auto-generated method stub
		Optional<Skill> ot = skillDAO.findById(skillId);
		return ot.isPresent() ? ot.get() : null;
	}

	@Transactional
	@Override
	public boolean saveSkill(Skill skill) {
		boolean transFlag = false;
		// TODO Auto-generated method stub
		skillDAO.save(skill);
		transFlag = true;
		return transFlag;
	}

	@Transactional
	@Override
	public boolean updateSkill(Skill skill) {
		boolean transFlag = false;
		// TODO Auto-generated method stub
		skillDAO.save(skill);
		transFlag = true;
		return transFlag;
	}

	@Transactional
	@Override
	public boolean deleteSkill(Skill skill) {
		boolean transFlag = false;
		// TODO Auto-generated method stub
		//skillDAO.deleteById(SkillId);
		skillDAO.delete(skill);
		transFlag = true;
		return transFlag;
	}

	public ISkillDAO getSkillDAO() {
		return skillDAO;
	}

	public void setSkillDAO(ISkillDAO skillDAO) {
		this.skillDAO = skillDAO;
	}


}
