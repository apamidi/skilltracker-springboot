/**
 * 
 */
package com.fsd.springboot.skillTracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsd.springboot.skillTracker.dto.Skill;

/**
 * @author Aravinda Pamidi
 *
 */

@Repository
public interface ISkillDAO extends JpaRepository<Skill, Long> {

}
