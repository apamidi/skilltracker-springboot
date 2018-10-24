/**
 * 
 */
package com.fsd.springboot.skillTracker.helper;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fsd.springboot.skillTracker.controller.AssociateAPIController;
import com.fsd.springboot.skillTracker.dto.Associate;
import com.fsd.springboot.skillTracker.dto.DashboardSummary;
import com.fsd.springboot.skillTracker.service.IAssociateService;

/**
 * @author AP
 *
 */

@Component("skillTrackerHelper")
public class SkillTrackerHelper {

	@Autowired
	private IAssociateService associateService;
	
	final static Logger logger = Logger.getLogger(SkillTrackerHelper.class.getName());
	
	public DashboardSummary createDashboardSummary() {	
		logger.info("Enter SkillTrackerHelper-createDashboardSummary");
		List<Associate> associateList = null;
		List<Object[]> genderData = null;
		List<Object[]> levelData = null;
		DashboardSummary dashboardSummary = null;
		Long maleCount = new Long(0);
		Long femaleCount = new Long(0);
		Long levelOneCount = new Long(0);
		Long levelTwoCount = new Long(0);
		float malePercentage = 0;
		float femalePercentage = 0;
		float levelOnePercentage = 0;
		float levelTwoPercentage = 0;
		float levelThreePercentage = 0;
		Object[] levelObj = null;
		
		
		try {
			dashboardSummary = new DashboardSummary();
			associateList = associateService.getAllAssociates();
			
			if(associateList != null &&!associateList.isEmpty()) {
				int assocSize = associateList.size();
				logger.debug("Total Associate Count :: " + assocSize);
								
				genderData = (List<Object[]>) associateService.getAssocCountByGender();
				
				if(genderData != null && !genderData.isEmpty()) {
					Object[] genderObj = (Object[])genderData.get(0);
					if(genderObj != null) {
						String gender = (String)genderObj[0];
						logger.debug("Gender :: " + gender);
						if(!"".equals(gender)) {
							if(gender.equalsIgnoreCase("M")) {
								maleCount = (Long)genderObj[1];
								logger.debug("Male Count :: " + maleCount);
								malePercentage = (float)(maleCount.doubleValue()/new Long(assocSize).doubleValue());
								malePercentage *= 100d;
								
								femalePercentage = 100 - malePercentage;
								
							}else {
								femaleCount = (Long)genderObj[1];
								logger.debug("Female Count :: " + femaleCount);
								femalePercentage = (float)(femaleCount.doubleValue()/new Long(assocSize).doubleValue());
								femalePercentage *= 100d;
								
								malePercentage = 100 - femalePercentage;
							}
							 
						}
					}
					dashboardSummary.setFemalePercentage(femalePercentage);
					dashboardSummary.setMalePercentage(malePercentage);
				}
				
				levelData = (List<Object[]>) associateService.getAssocCountByLevel();
				if(levelData != null && !levelData.isEmpty()) {
					if(levelData.size() >= 1) {
						levelObj = (Object[])levelData.get(0);
						levelOneCount = (Long)levelObj[1];
						logger.debug("Level 1 Count :: " + levelOneCount);
						levelOnePercentage = (float)(levelOneCount.doubleValue()/new Long(assocSize).doubleValue());
						levelOnePercentage *= 100d;
						
						logger.debug("levelOnePercentage :: " + levelOnePercentage);
					}
					
					if(levelData.size() >= 2) {
						levelObj = (Object[])levelData.get(1);
						levelTwoCount = (Long)levelObj[1];
						logger.debug("Level 2 Count :: " + levelTwoCount);
						levelTwoPercentage = (float)(levelTwoCount.doubleValue()/new Long(assocSize).doubleValue());
						levelTwoPercentage *= 100d;
						
						logger.debug("levelTwoPercentage :: " + levelTwoPercentage);
					}
					
					levelThreePercentage = 100 - (levelOnePercentage + levelTwoPercentage);
					logger.debug("levelThreePercentage :: " + levelThreePercentage);
				}
				dashboardSummary.setLevelOnePercentage(levelOnePercentage);
				dashboardSummary.setLevelTwoPercentage(levelTwoPercentage);
				dashboardSummary.setLevelThreePercentage(levelThreePercentage);
				
				logger.debug("malePercentage set :: " + dashboardSummary.getMalePercentage());
				logger.debug("femalePercentage set :: " + dashboardSummary.getFemalePercentage());
				logger.debug("levelOnePercentage set :: " + dashboardSummary.getLevelOnePercentage());
				logger.debug("levelTwoPercentage set :: " + dashboardSummary.getLevelTwoPercentage());
				logger.debug("levelThreePercentage set :: " + dashboardSummary.getLevelThreePercentage());
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Error in SkillTrackerHelper-createDashboardSummary :: " + e.getMessage());
		}
		
		logger.info("Exit SkillTrackerHelper-createDashboardSummary");
		return dashboardSummary;
	}
	
}
