/**
 * 
 */
package com.fsd.springboot.skillTracker.dto;

/**
 * @author pamidi
 *
 */
public class DashboardSummary {

	private float malePercentage = 0;
	private float femalePercentage = 0;
	private float levelOnePercentage = 0;
	private float levelTwoPercentage = 0;
	private float levelThreePercentage = 0;
	
	public float getMalePercentage() {
		return malePercentage;
	}
	public void setMalePercentage(float malePercentage) {
		this.malePercentage = malePercentage;
	}
	public float getFemalePercentage() {
		return femalePercentage;
	}
	public void setFemalePercentage(float femalePercentage) {
		this.femalePercentage = femalePercentage;
	}
	public float getLevelOnePercentage() {
		return levelOnePercentage;
	}
	public void setLevelOnePercentage(float levelOnePercentage) {
		this.levelOnePercentage = levelOnePercentage;
	}
	public float getLevelTwoPercentage() {
		return levelTwoPercentage;
	}
	public void setLevelTwoPercentage(float levelTwoPercentage) {
		this.levelTwoPercentage = levelTwoPercentage;
	}
	public float getLevelThreePercentage() {
		return levelThreePercentage;
	}
	public void setLevelThreePercentage(float levelThreePercentage) {
		this.levelThreePercentage = levelThreePercentage;
	}
	
	
}
