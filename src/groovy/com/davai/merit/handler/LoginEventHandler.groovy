package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.criteria.*
import com.davai.merit.*

class LoginEventHandler implements EventHandler {
	def objectService

	void handleEvent(Event event) {
		def personId = event.personId
		
		def LoginCountCriteria criteria = new LoginCountCriteria(personId: personId)
		
		def loginCounts = objectService.find(criteria)
		
		def count 
		
		System.out.println("counts: " + loginCounts)
		System.out.println("counts: " + loginCounts.getClass())
		
		if (loginCounts instanceof LoginCount) {
			count = loginCounts
			count.countValue++
		} else {
			count = new LoginCount(personId: personId, countValue:1)		
		}
		objectService.save(count)		
	}
}