/**
 * 
 */
package com.fsd.springboot.skillTracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fsd.springboot.skillTracker.dto.AssociateSkill;


/**
 * @author pamidi
 *
 */

@Repository
public interface IAssociateSkillDAO extends JpaRepository<AssociateSkill, Long> {
	
	@Query("SELECT a.skillName, count(a) FROM AssociateSkill a group by a.skillName")
	List<Object[]> getCountBySkill();
	
	List<AssociateSkill> findBySkillId(long skillId);
}
