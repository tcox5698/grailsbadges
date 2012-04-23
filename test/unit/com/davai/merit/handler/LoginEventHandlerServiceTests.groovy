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
	def existingCountValue = 6

	def expectedPerson = new Person(
            username: inputUsername,
            password: "expectedPassword", 
            name: "expectedName",
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true"
    )

	def testHandleEvent_AwardsAchievementOnFirstLogin() {
	    mockDomain(Person,[expectedPerson])
	
		objectServiceController.demand.find(1) { personCriteria ->
			assertEquals(inputUsername, personCriteria.arguments.username)
			return [expectedPerson]
		}
					 
		objectServiceController.demand.find(1) { countCriteria ->
			assertEquals(expectedPerson, countCriteria.arguments.person)
			return []
		}
				
		objectServiceController.demand.save(1) { loginCount ->
			assertEquals(1, loginCount.countValue)	
			assertEquals(expectedPerson, loginCount.person)			
		}
		
		objectServiceController.demand.save(1) { unlockedAchievement ->
			assertEquals(expectedPerson, unlockedAchievement.person)
			assertEquals("achievement.msg.login.singular", unlockedAchievement.messageKey)
			assertEquals("1", unlockedAchievement.messageArguments)
		}
		
		objectServiceController.use {
			handler.objectService = new ObjectService()
		
			//EXECUTE
			handler.handleEvent(loginEvent)
		}	
	}
	
	def testHandleEvent_UpdatesLoginStatistic_IfNonFibonacci_ThenNoAchievement() {
		objectServiceController.demand.find(1) { personCriteria ->
			assertEquals(inputUsername, personCriteria.arguments.username)
			return [expectedPerson]
		}	
	
		def expectedLoginCount = new LoginCount(person: expectedPerson,
			countValue: existingCountValue)
	
		objectServiceController.demand.find(1) { countCriteria ->
			assertEquals(expectedPerson, countCriteria.arguments.person)
			return [expectedLoginCount]
		}
		
		objectServiceController.demand.save(1) { loginCount ->
			assertEquals(existingCountValue + 1, loginCount.countValue)	
			assertEquals(expectedLoginCount.person, loginCount.person)		
		}
		
		objectServiceController.use {
			handler.objectService = new ObjectService()
		
			//EXECUTE
			handler.handleEvent(loginEvent)
		}		
	}
	
	def testIsFibonacci() {
		//EXECUTE
		assertTrue("zero should be fib",handler.isFibonacci(0)) 
		assertTrue("1 should be fib",handler.isFibonacci(1)) 
		assertTrue("2 should be fib",handler.isFibonacci(2)) 
		assertTrue("3 should be fib",handler.isFibonacci(3)) 
		assertFalse("4 should NOT be fib",handler.isFibonacci(4)) 
		assertTrue("5 should be fib",handler.isFibonacci(5)) 						
		assertFalse("6 should NOT be fib",handler.isFibonacci(6)) 						
		assertFalse("7 should NOT be fib",handler.isFibonacci(7)) 						
		assertTrue("8 should be fib",handler.isFibonacci(8)) 						
		assertFalse("9 should NOT be fib",handler.isFibonacci(9)) 						
		assertFalse("10 should NOT be fib",handler.isFibonacci(10)) 														
		assertFalse("11 should NOT be fib",handler.isFibonacci(11)) 			
		assertFalse("12 should NOT be fib",handler.isFibonacci(12)) 			
		assertTrue("13 should be fib",handler.isFibonacci(13)) 			
		assertFalse("14 should NOT be fib",handler.isFibonacci(14)) 			
		assertFalse("15 should NOT be fib",handler.isFibonacci(15)) 													
		assertFalse("16 should NOT be fib",handler.isFibonacci(16)) 		
	}
}