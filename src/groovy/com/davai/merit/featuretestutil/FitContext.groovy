package com.davai.merit.featuretestutil

import com.davai.merit.*
import com.davai.merit.criteria.*

def class FitContext {

	static singleton
	static results
	static incrementer = 0

	def appCtx
	def user
	def objectService
	def userPassword = "inputPassword"
	
	public static initialize(appCtx) {
		singleton = new FitContext(
			appCtx: appCtx, 
			objectService:appCtx.getBean("objectService"))
	}
	
	static giveUser() {
		if (!singleton.user) {
			singleton.user = FitContext.givePerson()
		}
		return singleton.user        			
	}
	
	static giveUserPassword() {
		return singleton.userPassword
	}
	
	static givePerson() {
		def person = singleton.objectService.save(new Person(
			username: "cucumberUser" + incrementer++,
			password: singleton.userPassword, 
			name: "inputName" + incrementer++,
			accountLocked:"false",
			accountExpired:"false",
			enabled:"true"))
		assert person != null	
		return person
	}
	
	static giveUnlockedAchievement(String achievementName, Person person, categories, SkillLevel level, Date unlockedDate) {
		def achievement = singleton.objectService.find(new UnlockedAchievementCriteria(
			arguments:[person:person,skillLevel:level,name:achievementName]
		))
		
		if (!achievement) {
			achievement = singleton.objectService.save(new UnlockedAchievement(
				person: person,
				categories: categories,
				skillLevel: level,
				name: achievementName,
				unlockedDate: unlockedDate?:new java.util.Date()    		
			))
		} else {
			categories.each() {cat ->
				achievement.each() {achv ->
					System.out.println("achv: " + achv.getClass()) 
					achv.addToCategories(cat)
				}
			}
		}
		
		assert achievement != null    
		
		return achievement
	}	
	
	static giveSkillLevel(String skillLevelMultiplier) {
		def multiplierInt = Integer.parseInt(skillLevelMultiplier)
	
		def level = singleton.objectService.find(new SkillLevelCriteria(
			arguments:[multiplier: multiplierInt]))
		
		if (level) {
			return level[0]
		}
		
		level = singleton.objectService.save(new SkillLevel(
			name:"cukeskilllevel" + skillLevelMultiplier,
			description:"cukedesc:" + skillLevelMultiplier,
			rank:multiplierInt,
			multiplier:multiplierInt,
		))
		
		assert level != null
		return level
	}
	
	static giveCategory(String categoryName) {
	
		def exists = singleton.objectService.find(new CategoryCriteria(
			arguments: [name:categoryName]
		))
		
		if (exists.size() > 0) {
			return exists[0]
		}
	
		exists = singleton.objectService.save(new Category(name:categoryName))
		
		return exists
	}	
}