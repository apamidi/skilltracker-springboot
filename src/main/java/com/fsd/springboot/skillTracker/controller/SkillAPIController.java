/**
 * 
 */
package com.fsd.springboot.skillTracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.springboot.skillTracker.dto.Skill;
import com.fsd.springboot.skillTracker.service.ISkillService;
import com.fsd.springboot.skillTracker.util.SkillTrackerEntityNotFoundException;

/**
 * @author Rishik Mishra
 *
 */

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SkillAPIController {

	
	final static Logger logger = Logger.getLogger(SkillAPIController.class.getName());

	@Autowired
	private ISkillService skillService;
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping(value="/",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Skill>> getAllSkills() {
		logger.info("Enter SkillAPIController - getAllSkills()");
		ResponseEntity<List<Skill>> result = null;
		
		List<Skill> skillList = skillService.getAllSkills();
				
		if(skillList != null) {
			logger.debug("Skill list size :: " + skillList.size());
			result = new ResponseEntity<List<Skill>>(skillList, HttpStatus.OK);
		}else {
			result = new ResponseEntity<List<Skill>>(skillList, HttpStatus.NOT_FOUND);
		}
		
		logger.info("Exit SkillAPIController - getAllSkills()");
		return result;
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@RequestMapping(value = "/{skillId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Skill> getSkill(@PathVariable("skillId") Long skillId) {
		logger.info("Enter SkillAPIController - getSkill()");
		ResponseEntity<Skill> result = null;
		Skill skill = null;
		
		try {
			skill = skillService.getSkillByID(skillId);
			
			if(skill != null) {
				result = new ResponseEntity<Skill>(skill, HttpStatus.OK);
			}else {
				result = new ResponseEntity<Skill>(skill, HttpStatus.NOT_FOUND);
				throw new SkillTrackerEntityNotFoundException("skill id-" + skillId);
			}
		}catch(Exception ex) {
			logger.info("Enter SkillAPIController - getSkill()");
			ex.printStackTrace();
		}
		
		
		logger.info("Exit SkillAPIController - getSkill()");
		return result;
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> saveSkill(@Valid @RequestBody Skill skill) {
		logger.info("Enter SkillAPIController - saveSkill()");

		boolean saveSuccessFlag = false;
		ResponseEntity<Void> result = null;
		
		if(skill != null) {
			logger.info("Incoming Skill :: " + skill);
			logger.debug("Skill ID :: " + skill.getSkillId());
			logger.debug("Skill Name :: " + skill.getSkillName());
			
			saveSuccessFlag = skillService.saveSkill(skill);
		}
		
		if(saveSuccessFlag) {
			logger.info("Skill successfully created...");
			result = new ResponseEntity<Void>(HttpStatus.CREATED);
		}else {
			logger.debug("Failed to save Skill...");
			result = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		logger.info("Exit SkillAPIController - saveSkill()");
		return result;
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@PutMapping("/{skillId}" )
	public ResponseEntity<Skill> updateSkill(@PathVariable Long skillId, @RequestBody Skill skill) {
		logger.info("Enter SkillAPIController - updateSkill()");
		boolean updateFlag = false;
		ResponseEntity<Skill> result = null;
		
		if(skill != null) {
			updateFlag = skillService.updateSkill(skill);
		}
		
		if(updateFlag) {
			result =  new ResponseEntity<Skill>(skill, HttpStatus.OK);
		}else {
			result =  new ResponseEntity<Skill>(skill, HttpStatus.NOT_FOUND);
		}
		
		logger.info("Exit SkillAPIController - updateSkill()");
		return result;
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@DeleteMapping("/{skillId}")
	public ResponseEntity<Void> deleteSkill(@PathVariable("skillId") Long skillId) {
		logger.info("Enter SkillAPIController - deleteSkill()");
		boolean deleteFlag = false;
		ResponseEntity<Void> result = null;
		
		if(skillId != null) {
			Skill skill = skillService.getSkillByID(skillId);
			
			if(skill != null) {
				deleteFlag = skillService.deleteSkill(skill);
			}			
			
		}
		
		if(deleteFlag) {
			result =  new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			result =  new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		logger.info("Exit SkillAPIController - deleteSkill()");
		return result;

	}


}
