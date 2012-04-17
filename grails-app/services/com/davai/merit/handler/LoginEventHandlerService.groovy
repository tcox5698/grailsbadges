package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.criteria.*
import com.davai.merit.*

class LoginEventHandlerService implements EventHandler {
	def objectService

	void handleEvent(Event event) {
		def personCriteria = new PersonCriteria(username: event.username)
		def person = objectService.find(personCriteria)
		def personId = person.id[0]
		
		log.info "handleEvent: person: " + person
		
		log.info "handleEvent: personId: " + personId
		
		def LoginCountCriteria criteria = new LoginCountCriteria("personId": personId)
		def loginCounts = objectService.find(criteria)
		def count 
		
		log.info "handleEvent: find results: " + loginCounts.size()	
		
		if (loginCounts.size() > 0) {
			log.info "handleEvent: found loginCount: " + loginCounts
			count = loginCounts[0]
			count.countValue++
		} else {
			count = new LoginCount(personId: personId, countValue:1)		
			log.info "handleEvent: created new loginCount: " + count			
		}
		
		log.info "handleEvent: saving loginCount: " + count		
		
		objectService.save(count)		
	}
}