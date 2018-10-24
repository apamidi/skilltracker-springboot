/**
 * 
 */
package com.fsd.springboot.skillTracker.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Aravinda Pamidi
 *
 */

@Entity
@Table(name = "ASSOCIATE_SKILL")
public class AssociateSkill implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "ASSOC_SKILL_ID")
	private long assocSkillId;

	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associate_id", nullable = false)
    @JsonBackReference
	private Associate associate;
	
	
    //@ManyToOne(cascade=CascadeType.ALL)
    //@JoinColumn(name = "SKILL_ID")
	//private Skill skill;
    
    @Column(name = "SKILL_ID")
    private long skillId;
    
    @Column(name = "SKILL_NAME")
    private String skillName;
	
	@Column(name = "POINTS")
	private int points;
	
	@Column(name = "SKILL_STRENGTH")
	private String skillStrength;

	public Associate getAssociate() {
		return associate;
	}

	public void setAssociate(Associate associate) {
		this.associate = associate;
	}

	/*public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}*/

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public long getAssocSkillId() {
		return assocSkillId;
	}

	public void setAssocSkillId(long assocSkillId) {
		this.assocSkillId = assocSkillId;
	}

	public String getSkillStrength() {
		return skillStrength;
	}

	public void setSkillStrength(String skillStrength) {
		this.skillStrength = skillStrength;
	}

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

	public AssociateSkill(String skillName) {
		super();
		this.skillName = skillName;
	}
	
	public AssociateSkill() {
		
	}
	
}
