package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.*
import com.davai.merit.criteria.*

import groovy.mock.interceptor.*

class LoginEventHandlerTests extends GroovyTestCase {
	void testHandleEvent_SavesNewLoginStatistic() {
		def inputPersonId = "inputPersonId"
		def LoginEventHandler handler = new LoginEventHandler()
		def loginEvent = new LoginEvent(personId:inputPersonId)
		
		def objectServiceController = new MockFor(ObjectService)
				 
		objectServiceController.demand.findThem { countCriteria ->
			assertEquals(inputPersonId, countCriteria.personId)
			return []
		}
				
		objectServiceController.demand.save { loginCount ->
			assertEquals(1, loginCount.value, 0.01)	
			assertEquals(inputPersonId, loginCount.personId)			
		}
		
		objectServiceController.use {
			handler.objectService = new ObjectService()
		
			//EXECUTE
			handler.handleEvent(loginEvent)
		}
	}
}