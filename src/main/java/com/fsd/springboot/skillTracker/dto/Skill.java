/**
 * 
 */
package com.fsd.springboot.skillTracker.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Aravinda Pamidi
 *
 */

@Entity
@Table(name = "SKILL")
public class Skill {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SKILL_ID")
	private long skillId;
	
	@NotNull
	@Column(name="SKILL_NAME",unique=true)
	private String skillName;
	
	private boolean isSelected = false;
	private boolean isSaved = false;
	private boolean skillEditable = false;
	
	//@OneToMany(mappedBy = "skill", fetch = FetchType.EAGER)
	//private Set<AssociateSkill> associateSkills = new HashSet<>();

	public long getSkillId() {
		return skillId;
	}

	public void setSkillId(long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}


	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	public boolean isSkillEditable() {
		return skillEditable;
	}

	public void setSkillEditable(boolean skillEditable) {
		this.skillEditable = skillEditable;
	}

	/*public Set<AssociateSkill> getAssociateSkills() {
		return associateSkills;
	}

	public void setAssociateSkills(Set<AssociateSkill> associateSkills) {
		this.associateSkills = associateSkills;
	}*/
		
	
}
