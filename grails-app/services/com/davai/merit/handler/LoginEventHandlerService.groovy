package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.criteria.*
import com.davai.merit.*

class LoginEventHandlerService implements EventHandler {
	def objectService

	void handleEvent(Event event) {
		def personCriteria = new PersonCriteria(username: event.username)
		def person = objectService.find(personCriteria)
		person = person[0]
		def personId = person.id
		
		def LoginCountCriteria criteria = new LoginCountCriteria("personId": personId)
		def loginCounts = objectService.find(criteria)
		def count 
			
		if (loginCounts.size() > 0) {
			count = loginCounts[0]
			count.countValue++
		} else {
			count = new LoginCount(personId: personId, countValue:1)		
		}
				
		objectService.save(count)	
		
		if (isFibonacci(count.countValue)) {
			def unlockedAchievement = new UnlockedAchievement(
				person: person,
				messageKey: "achievement.msg.login.singular",
				messageArguments: count.countValue,
				unlockedDate: new Date()
			)
				
			objectService.save(unlockedAchievement)		
		}			
	}
	
	def isFibonacci(number, candidate0=0, candidate1=1) {
		if (candidate0 > number) {		
			return false
		}
			
		return candidate0 == number || candidate1 == number || isFibonacci(number, candidate1, candidate0 + candidate1)
	}
}