/**
 * 
 */
package com.fsd.springboot.skillTracker.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.springboot.skillTracker.dto.Associate;
import com.fsd.springboot.skillTracker.dto.AssociateSkill;
import com.fsd.springboot.skillTracker.dto.AssociateSkillSummary;
import com.fsd.springboot.skillTracker.dto.DashboardSummary;
import com.fsd.springboot.skillTracker.helper.SkillTrackerHelper;
import com.fsd.springboot.skillTracker.service.IAssociateService;
import com.fsd.springboot.skillTracker.util.SkillTrackerEntityNotFoundException;

/**
 * @author AP
 *
 */

@RestController
@RequestMapping("/assocs")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AssociateAPIController {
	
	final static Logger logger = Logger.getLogger(AssociateAPIController.class.getName());

	@Autowired
	private IAssociateService associateService;
	
	@Autowired
	private SkillTrackerHelper skillTrackerHelper;
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping(value="/",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Associate>> getAllAssociates() {
		logger.info("Enter AssociateAPIController - getAllAssociates()");
		ResponseEntity<List<Associate>> result = null;
		
		try {
			List<Associate> associateList = associateService.getAllAssociates();
			
			if(associateList != null) {
				logger.debug("Associate list size :: " + associateList.size());
				
				for(Associate associate : associateList) {
					Set<AssociateSkill> aSkillSet = associate.getAssociateSkills();
				}
				
				result = new ResponseEntity<List<Associate>>(associateList, HttpStatus.OK);
			}else {
				result = new ResponseEntity<List<Associate>>(associateList, HttpStatus.NOT_FOUND);
			}
		}catch(Exception ex) {
			logger.info("Enter AssociateAPIController - getAllAssociates()");
			ex.printStackTrace();
		}
		
		logger.info("Exit AssociateAPIController - getAllAssociates()");
		return result;
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@RequestMapping(value = "/{associateId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Associate> getAssociate(@PathVariable("associateId") Long associateId) {
		logger.info("Enter AssociateAPIController - getAssociate()");
		ResponseEntity<Associate> result = null;
		
		try {
			Associate associate = associateService.getAssociateByID(associateId);
						
			if(associate != null) {
				Set<AssociateSkill> aSkillSet = associate.getAssociateSkills();
				result = new ResponseEntity<Associate>(associate, HttpStatus.OK);
			}else {
				result = new ResponseEntity<Associate>(associate, HttpStatus.NOT_FOUND);
				throw new SkillTrackerEntityNotFoundException("associate id-" + associateId);
			}
		}catch(Exception ex) {
			logger.info("Enter AssociateAPIController - getAssociate()");
			ex.printStackTrace();
		}
				
		System.out.println("AssociateAPIController - getAssociate() - response :: " + result);
		logger.info("Exit AssociateAPIController - getAssociate()");
		return result;
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> saveAssociate(@Valid @RequestBody Associate associate) {
		logger.info("Enter AssociateAPIController - saveAssociate()");

		boolean saveSuccessFlag = false;
		ResponseEntity<Void> result = null;
		Associate assocObj = null;
		AssociateSkill aSkill = null;
		
		if(associate != null) {
			logger.info("Incoming Associate :: " + associate);
			logger.debug("Associate ID :: " + associate.getAssociateId());
			logger.debug("Associate Name :: " + associate.getAssociateName());
			
			assocObj = new Associate();
			assocObj.setAssociateName(associate.getAssociateName());
			assocObj.setEmail(associate.getEmail());
			assocObj.setGender(associate.getGender());
			assocObj.setLevel(associate.getLevel());
			assocObj.setMobile(associate.getMobile());
			assocObj.setPic(associate.getPic());
			assocObj.setRemark(associate.getRemark());
			assocObj.setStatus(associate.getStatus());
			assocObj.setStrength(associate.getStrength());
			assocObj.setWeakness(associate.getWeakness());
			
			Set<AssociateSkill> skillSet = associate.getAssociateSkills();
			if(skillSet != null && !skillSet.isEmpty()) {
				Iterator<AssociateSkill> itr = skillSet.iterator();
				while(itr.hasNext()) {
					aSkill = (AssociateSkill)itr.next();
					if(aSkill != null) {
						aSkill.setAssociate(assocObj);
						assocObj.getAssociateSkills().add(aSkill);
					}
				}
			}
			
			saveSuccessFlag = associateService.saveAssociate(assocObj);
			//saveSuccessFlag = associateService.saveAssociate(associate);
		}
		
		if(saveSuccessFlag) {
			logger.info("Associate successfully created...");
			result = new ResponseEntity<Void>(HttpStatus.CREATED);
		}else {
			logger.debug("Failed to save Associate...");
			result = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		logger.info("Exit AssociateAPIController - saveAssociate()");
		return result;
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@PutMapping("/{associateId}" )
	public ResponseEntity<Associate> updateAssociate(@PathVariable Long associateId, @RequestBody Associate associate) {
		logger.info("Enter AssociateAPIController - updateAssociate()");
		boolean updateFlag = false;
		ResponseEntity<Associate> result = null;
		AssociateSkill aSkill = null;		
		Associate origAssociate = null;
		List<String> origAssocSkillList = null;
		
		if(associate != null) {
			
			origAssociate = associateService.getAssociateByID(associate.getAssociateId());
			
			if(origAssociate != null) {
				
				System.out.println("Associate found :: " + origAssociate);
				
				//newAssociate.setAssociateName(associate.getAssociateName());
				origAssociate.setEmail(associate.getEmail());
				origAssociate.setMobile(associate.getMobile());
				origAssociate.setLevel(associate.getLevel());
				origAssociate.setStatus(associate.getStatus());
				origAssociate.setRemark(associate.getRemark());
				origAssociate.setStrength(associate.getStrength());
				origAssociate.setWeakness(associate.getWeakness());
				
				Set<AssociateSkill> origSkillSet = origAssociate.getAssociateSkills();
				if(origSkillSet != null && !origSkillSet.isEmpty()) {
					origAssocSkillList = new ArrayList<String>();
					Iterator origASkillItr = origSkillSet.iterator();
					while(origASkillItr.hasNext()) {
						origAssocSkillList.add(((AssociateSkill)origASkillItr.next()).getSkillName());
					}
				}
				
				Set<AssociateSkill> skillSet = associate.getAssociateSkills();
				
				if(skillSet != null && !skillSet.isEmpty()) {
					System.out.println("Original Associate Skill size :: " + origSkillSet.size());
					System.out.println("Updated Associate Skill size :: " + skillSet.size());
					Iterator<AssociateSkill> itr = skillSet.iterator();
					while(itr.hasNext()) {
						aSkill = (AssociateSkill)itr.next();
						if(aSkill != null) {
							
							if(!origAssocSkillList.contains(aSkill.getSkillName())) {
								System.out.println("New Skill added for associate :: " + aSkill);
								origSkillSet.add(aSkill);
								aSkill.setAssociate(origAssociate);
								
							}
							
							/*aSkill.setAssociate(associate);
							associate.getAssociateSkills().add(aSkill);*/
						}
					}
				}
				updateFlag = associateService.updateAssociate(origAssociate);
			}
			
		}
		
		if(updateFlag) {
			result =  new ResponseEntity<Associate>(associate, HttpStatus.OK);
		}else {
			result =  new ResponseEntity<Associate>(associate, HttpStatus.NOT_FOUND);
		}
		
		logger.info("Exit AssociateAPIController - updateAssociate()");
		return result;
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@DeleteMapping("/{associateId}")
	public ResponseEntity deleteAssociate(@PathVariable("associateId") Long associateId) {
		logger.info("Enter AssociateAPIController - deleteAssociate()");
		boolean deleteFlag = false;
		ResponseEntity result = null;
		
		if(associateId != null) {
			Associate associate = associateService.getAssociateByID(associateId);
			
			if(associate != null) {
				deleteFlag = associateService.deleteAssociate(associate);
			}
			
		}
		
		if(deleteFlag) {
			//result = new ResponseEntity(associateId, HttpStatus.OK);
			result =  new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			result =  new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		logger.info("Exit AssociateAPIController - deleteAssociate()");
		return result;

	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@RequestMapping(value = "/searchAssoc/", params = "email", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Associate> getAssociateByEmail(@RequestParam String email) {
		logger.info("Enter AssociateAPIController - getAssociateByEmail()");
		logger.info("Enter AssociateAPIController - Input email :: " + email);
		System.out.println("Enter AssociateAPIController - Input email :: " + email);
		ResponseEntity<Associate> result = null;
		
		try {
			Associate associate = associateService.getAssociateByEmail(email);
			
			if(associate != null) {
				System.out.println("Associate found with email id :: " + email + " is :: " + associate.getAssociateId());
				logger.info("Associate found with email id :: " + email + " is :: " + associate.getAssociateId());
				associate.getAssociateSkills();
				result = new ResponseEntity<Associate>(associate, HttpStatus.OK);
			}else {
				System.out.println("Associate with email id :: " + email + " not found");
				logger.info("Associate with email id :: " + email + " not found");
				result = new ResponseEntity<Associate>(associate, HttpStatus.NOT_FOUND);
			}
		}catch(Exception ex) {
			logger.info("Enter AssociateAPIController - getAssociateByEmail()");
			logger.debug("Enter AssociateAPIController - getAssociateByEmail() error :: " + ex.getMessage());
			ex.printStackTrace();
		}		
		System.out.println("AssociateAPIController - getAssociateByEmail() - response :: " + result);
		logger.info("Exit AssociateAPIController - getAssociateByEmail()");
		return result;
		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping(value="/summary/",  produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DashboardSummary> getDashboardSummary() {
		logger.info("Enter AssociateAPIController - getDashboardSummary()");
		
		ResponseEntity<DashboardSummary> result = null;
		DashboardSummary summary = skillTrackerHelper.createDashboardSummary();
		result = new ResponseEntity<DashboardSummary>(summary, HttpStatus.OK);
		
		logger.debug("AssociateAPIController - getDashboardSummary() Result :: " + result);
		logger.info("Exit AssociateAPIController - getDashboardSummary()");
		return result;
	}
}
