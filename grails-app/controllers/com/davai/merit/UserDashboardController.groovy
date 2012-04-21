package com.davai.merit

import com.davai.merit.criteria.*

class UserDashboardController {
	def objectService
	def springSecurityService

    def index() { 
    	def person = springSecurityService.currentUser
    	
    	def unlockedAchievements = objectService.find(new UnlockedAchievementCriteria(person:person))
    
    	render view:"index", model: [unlockedAchievements: unlockedAchievements]	
    }
}
