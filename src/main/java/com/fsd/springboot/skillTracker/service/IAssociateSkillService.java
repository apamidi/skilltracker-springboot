/**
 * 
 */
package com.fsd.springboot.skillTracker.service;

import java.util.List;

import com.fsd.springboot.skillTracker.dto.AssociateSkill;


/**
 * @author pamidi
 *
 */
public interface IAssociateSkillService {
	
	List<Object[]> getCountBySkill();
	
	List<AssociateSkill> getAssociateSkillsBySkillId(Long skillId);

}
