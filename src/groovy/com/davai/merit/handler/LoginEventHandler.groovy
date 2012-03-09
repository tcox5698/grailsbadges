package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.criteria.*
import com.davai.merit.*

class LoginEventHandler implements EventHandler {
	def objectService

	void handleEvent(Event event) {
		def personId = event.personId
		
		def LoginCountCriteria criteria = new LoginCountCriteria(personId: personId)
		
		def loginCounts = objectService.findThem(criteria)

		System.out.println("loginEventHandler personId: " + personId.class)
			
		def LoginCount count = new LoginCount(personId: personId, value:1)
		
		System.out.println("loginEventHandler count: " + count)
		
		objectService.save(count)		
	}
}