/**
 * 
 */
package com.fsd.springboot.skillTracker.dto;

/**
 * @author AP
 *
 */
public class AssociateSkillSummary {

	private String skillName;
	private Long associateCount;
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Long getAssociateCount() {
		return associateCount;
	}
	public void setAssociateCount(Long associateCount) {
		this.associateCount = associateCount;
	}
		
}
