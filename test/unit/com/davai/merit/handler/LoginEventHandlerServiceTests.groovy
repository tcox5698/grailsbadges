package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.*
import com.davai.merit.criteria.*

import groovy.mock.interceptor.*
import grails.test.GrailsUnitTestCase

class LoginEventHandlerServiceTests extends GrailsUnitTestCase {
	def inputUsername = "inputUsername"
	def handler = new LoginEventHandlerService()
	def loginEvent = new LoginEvent(username:inputUsername)	
	def objectServiceController = new MockFor(ObjectService)
	def existingCountValue = 7

	def expectedPerson = new Person(
            username: inputUsername,
            password: "expectedPassword", 
            name: "expectedName",
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true"
    )


	def testHandleEvent_SavesNewLoginStatistic() {
	    mockDomain(Person,[expectedPerson])
	
		objectServiceController.demand.find(1) { personCriteria ->
			assertEquals(inputUsername, personCriteria.username)
			return [expectedPerson]
		}
					 
		objectServiceController.demand.find(1) { countCriteria ->
			assertEquals(expectedPerson.id, countCriteria.personId)
			return []
		}
				
		objectServiceController.demand.save { loginCount ->
			assertEquals(1, loginCount.countValue)	
			assertEquals(expectedPerson.id, loginCount.personId)			
		}
		
		objectServiceController.use {
			handler.objectService = new ObjectService()
		
			//EXECUTE
			handler.handleEvent(loginEvent)
		}
	}
	
	def testHandleEvent_UpdatesLoginStatistic() {
		objectServiceController.demand.find(1) { personCriteria ->
			assertEquals(inputUsername, personCriteria.username)
			return [expectedPerson]
		}	
	
		def expectedLoginCount = new LoginCount(personId: "existingLoginCount",
			countValue: existingCountValue)
	
		objectServiceController.demand.find(1) { countCriteria ->
			assertEquals(expectedPerson.id, countCriteria.personId)
			return [expectedLoginCount]
		}
		
		objectServiceController.demand.save(1) { loginCount ->
			assertEquals(existingCountValue + 1, loginCount.countValue)	
			assertEquals(expectedLoginCount.personId, loginCount.personId)		
		}
		
		objectServiceController.use {
			handler.objectService = new ObjectService()
		
			//EXECUTE
			handler.handleEvent(loginEvent)
		}		
	}
}