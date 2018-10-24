/**
 * 
 */
package com.fsd.springboot.skillTracker.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsd.springboot.skillTracker.dto.Associate;


/**
 * @author AP
 *
 */

@Repository
public interface IAssociateDAO extends JpaRepository<Associate, Long> {
	
	List<Associate> findByEmail(@Param("email") String email);
	
	@Query("SELECT a.level, count(a) FROM Associate a group by a.level order by a.level")
	List<Object[]> getCountByLevel();
	
	@Query("SELECT a.gender, count(a) FROM Associate a group by a.gender")
	List<Object[]> getCountByGender();
	
}
