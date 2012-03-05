package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.*

class LoginEventHandler implements EventHandler {
	def objectService

	void handleEvent(Event event) {
		def personId = event.personId
		
		Person person = objectService.read(Person.class, personId)
	}
}