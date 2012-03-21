package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.*
import com.davai.merit.criteria.*

import groovy.mock.interceptor.*

class LoginEventHandlerTests extends GroovyTestCase {
	def inputPersonId = 7
	def LoginEventHandler handler = new LoginEventHandler()
	def loginEvent = new LoginEvent(personId:inputPersonId)	
	def objectServiceController = new MockFor(ObjectService)
	def existingCountValue = 7

	def testHandleEvent_SavesNewLoginStatistic() {				 
		objectServiceController.demand.find(1) { countCriteria ->
			assertEquals(inputPersonId, countCriteria.personId)
			return []
		}
				
		objectServiceController.demand.save { loginCount ->
			assertEquals(1, loginCount.countValue)	
			assertEquals(inputPersonId, loginCount.personId)			
		}
		
		objectServiceController.use {
			handler.objectService = new ObjectService()
		
			//EXECUTE
			handler.handleEvent(loginEvent)
		}
	}
	
	def testHandleEvent_UpdatesLoginStatistic() {
	
		def expectedLoginCount = new LoginCount(personId: inputPersonId,
			countValue: existingCountValue)
	
		objectServiceController.demand.find(1) { countCriteria ->
			assertEquals(inputPersonId, countCriteria.personId)
			return expectedLoginCount
		}
		
		objectServiceController.demand.save(1) { loginCount ->
			assertEquals(existingCountValue + 1, loginCount.countValue)	
			assertEquals(inputPersonId, loginCount.personId)		
		}
		
		objectServiceController.use {
			handler.objectService = new ObjectService()
		
			//EXECUTE
			handler.handleEvent(loginEvent)
		}		
	}
}