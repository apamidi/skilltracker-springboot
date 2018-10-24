package com.fsd.springboot.skillTracker.service;

import java.util.List;

import com.fsd.springboot.skillTracker.dto.Skill;

public interface ISkillService {
	
	List<Skill> getAllSkills();

	Skill getSkillByID(Long id);
		
	boolean saveSkill(Skill skill);
	
	boolean updateSkill(Skill skill);
	
	boolean deleteSkill(Skill skill);
	
}
