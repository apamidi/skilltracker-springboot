/**
 * 
 */
package com.fsd.springboot.skillTracker.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.springboot.skillTracker.dto.AssociateSkill;
import com.fsd.springboot.skillTracker.dto.AssociateSkillSummary;
import com.fsd.springboot.skillTracker.service.IAssociateSkillService;


/**
 * @author AP
 *
 */

@RestController
@RequestMapping("/asSummary")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AssociateSkillAPIController {

	final static Logger logger = Logger.getLogger(AssociateSkillAPIController.class.getName());

	@Autowired
	private IAssociateSkillService asService;
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping(value="/",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AssociateSkillSummary>> getSkillSummary() {
		logger.info("Enter AssociateSkillAPIController - getSkillSummary()");
		ResponseEntity<List<AssociateSkillSummary>> result = null;
		List<AssociateSkillSummary> skillSummaryList = null;
		AssociateSkillSummary asSummary = null;
		
		List<Object[]> assocSkillList = (List<Object[]>)asService.getCountBySkill();
				
		if(assocSkillList != null) {
			skillSummaryList = new ArrayList<AssociateSkillSummary>();
			logger.debug("AssociateSkill list size :: " + assocSkillList.size());
			
			for(int i = 0 ; i < assocSkillList.size() ; i++) {
				Object[] obj = (Object[])assocSkillList.get(i);
				
				if(obj != null) {
					asSummary = new AssociateSkillSummary();
					asSummary.setSkillName((String)obj[0]);
					asSummary.setAssociateCount((Long)obj[1]);
					logger.debug("AssociateSkillAPIController - SkillName :: " + (String)obj[0] + " Count :: " + (Long)obj[1]);
					skillSummaryList.add(asSummary);
				}
			}
			
			result = new ResponseEntity<List<AssociateSkillSummary>>(skillSummaryList, HttpStatus.OK);
		}else {
			result = new ResponseEntity<List<AssociateSkillSummary>>(skillSummaryList, HttpStatus.NOT_FOUND);
		}
		
		logger.info("Exit AssociateSkillAPIController - getSkillSummary()");
		return result;
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@RequestMapping(value="/{skillId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AssociateSkill>> getAssociateSkillsBySkillId(@PathVariable("skillId") Long skillId) {
		logger.info("Enter AssociateSkillAPIController - getAssociateSkillsBySkillId()");
		ResponseEntity<List<AssociateSkill>> result = null;
				
		List<AssociateSkill> assocSkillList = (List<AssociateSkill>)asService.getAssociateSkillsBySkillId(skillId);
				
		if(assocSkillList != null) {
			result = new ResponseEntity<List<AssociateSkill>>(assocSkillList, HttpStatus.OK);
		}else {
			result = new ResponseEntity<List<AssociateSkill>>(assocSkillList, HttpStatus.NOT_FOUND);
		}
		
		logger.info("Exit AssociateSkillAPIController - getAssociateSkillsBySkillId()");
		return result;
		
	}
}
