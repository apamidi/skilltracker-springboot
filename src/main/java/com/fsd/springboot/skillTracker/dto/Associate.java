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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;



/**
 * @author Aravinda Pamidi
 *
 */

@Entity
@NamedQuery(name = "Associate.findByEmail", query = "SELECT a FROM Associate a WHERE a.email = :email")
@Table(name = "ASSOCIATE")
public class Associate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name = "ASSOCIATE_ID")
	private long associateId;
	
	@NotNull
	@Column(name="ASSOCIATE_NAME")
	private String associateName;
	
	@NotNull
	@Column(name="EMAIL")//,unique=true
	private String email;
	
	@Column(name = "MOBILE")
	private long mobile;
	
	//@Lob
	//@Column(name="PIC",columnDefinition="mediumblob")
	//private byte[] pic;
	@Lob
	@Column(name="PIC")
	private String pic;
	
	@Column(name = "LEVEL")
	private int level;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "REMARK")
	private String remark;
	
	@Column(name = "STRENGTH")
	private String strength;
	
	@Column(name = "WEAKNESS")
	private String weakness;
	
	@Column(name = "GENDER")
	private String gender;
	
	//@OneToMany(mappedBy = "associate",cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@OneToMany(mappedBy="associate", cascade= { CascadeType.MERGE,CascadeType.ALL}, fetch = FetchType.LAZY)
	private Set<AssociateSkill> associateSkills = new HashSet<>();

	public long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(long associateId) {
		this.associateId = associateId;
	}

	public String getAssociateName() {
		return associateName;
	}

	public void setAssociateName(String associateName) {
		this.associateName = associateName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	/*public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}*/
	
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getWeakness() {
		return weakness;
	}

	public void setWeakness(String weakness) {
		this.weakness = weakness;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<AssociateSkill> getAssociateSkills() {
		return associateSkills;
	}

	public void setAssociateSkills(Set<AssociateSkill> associateSkills) {
		this.associateSkills = associateSkills;
	}
		
	

}
