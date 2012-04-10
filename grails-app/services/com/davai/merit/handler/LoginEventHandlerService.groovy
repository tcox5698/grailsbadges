package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.criteria.*
import com.davai.merit.*

class LoginEventHandlerService implements EventHandler {
	def objectService

	void handleEvent(Event event) {
		def personCriteria = new PersonCriteria(username: event.username)
		def person = objectService.find(personCriteria)
		
		def LoginCountCriteria criteria = new LoginCountCriteria(personId: person.id)
		def loginCounts = objectService.find(criteria)
		def count 
		
		if (loginCounts instanceof LoginCount) {
			count = loginCounts
			count.countValue++
		} else {
			count = new LoginCount(personId: person.id, countValue:1)		
		}
		objectService.save(count)		
	}
}