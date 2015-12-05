package com.davai.merit.handler

import com.davai.merit.event.*
import com.davai.merit.*
import com.davai.merit.criteria.*

import groovy.mock.interceptor.*
import grails.test.mixin.*

@TestFor(LoginEventHandlerService)
@Mock(Person)
class LoginEventHandlerServiceTests {
	def inputUsername = "inputUsername"
	def loginEvent = new LoginEvent(username:inputUsername)	
	def objectServiceController
	def existingCountValue = 6

	def expectedPerson

	void setUp() {
		objectServiceController = mockFor(ObjectService)
	
		expectedPerson = new Person(
            username: inputUsername,
            password: "expectedPassword", 
            name: "expectedName",
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true"
	    ).save()
	}
    

	void testHandleEvent_AwardsAchievementOnFirstLogin() {
		objectServiceController.demand.find(1) { PersonCriteria personCriteria ->
			assertEquals(inputUsername, personCriteria.arguments.username)
			return [expectedPerson]
		}
					 
		objectServiceController.demand.find(1) { LoginCountCriteria countCriteria ->
			assertEquals(expectedPerson, countCriteria.arguments.person)
			return []
		}
				
		objectServiceController.demand.save(1) { LoginCount loginCount ->
			assertEquals(1, loginCount.countValue)	
			assertEquals(expectedPerson, loginCount.person)			
		}
		
		objectServiceController.demand.save(1) { UnlockedAchievement unlockedAchievement ->
			assertEquals(expectedPerson, unlockedAchievement.person)
			assertEquals("achievement.msg.login.singular", unlockedAchievement.messageKey)
			assertEquals("1", unlockedAchievement.messageArguments)
		}
		
		service.objectService = objectServiceController.createMock()
		
		//EXECUTE
		service.handleEvent(loginEvent)
		
		//VERIFY
		objectServiceController.verify()
	}
	
	void testHandleEvent_UpdatesLoginStatistic_IfNonFibonacci_ThenNoAchievement() {
		objectServiceController.demand.find(1) { PersonCriteria personCriteria ->
			assertEquals(inputUsername, personCriteria.arguments.username)
			return [expectedPerson]
		}	
	
		def expectedLoginCount = new LoginCount(person: expectedPerson,
			countValue: existingCountValue)
	
		objectServiceController.demand.find(1) { LoginCountCriteria countCriteria ->
			assertEquals(expectedPerson, countCriteria.arguments.person)
			return [expectedLoginCount]
		}
		
		objectServiceController.demand.save(1) { LoginCount loginCount ->
			assertEquals(existingCountValue + 1, loginCount.countValue)	
			assertEquals(expectedLoginCount.person, loginCount.person)		
		}
		
		service.objectService = objectServiceController.createMock()

		//EXECUTE
		service.handleEvent(loginEvent)
		
		//VERIFY
		objectServiceController.verify()
	}
	
	void testIsFibonacci() {
		//EXECUTE
		assertTrue("zero should be fib",service.isFibonacci(0)) 
		assertTrue("1 should be fib",service.isFibonacci(1)) 
		assertTrue("2 should be fib",service.isFibonacci(2)) 
		assertTrue("3 should be fib",service.isFibonacci(3)) 
		assertFalse("4 should NOT be fib",service.isFibonacci(4)) 
		assertTrue("5 should be fib",service.isFibonacci(5)) 						
		assertFalse("6 should NOT be fib",service.isFibonacci(6)) 						
		assertFalse("7 should NOT be fib",service.isFibonacci(7)) 						
		assertTrue("8 should be fib",service.isFibonacci(8)) 						
		assertFalse("9 should NOT be fib",service.isFibonacci(9)) 						
		assertFalse("10 should NOT be fib",service.isFibonacci(10)) 														
		assertFalse("11 should NOT be fib",service.isFibonacci(11)) 			
		assertFalse("12 should NOT be fib",service.isFibonacci(12)) 			
		assertTrue("13 should be fib",service.isFibonacci(13)) 			
		assertFalse("14 should NOT be fib",service.isFibonacci(14)) 			
		assertFalse("15 should NOT be fib",service.isFibonacci(15)) 													
		assertFalse("16 should NOT be fib",service.isFibonacci(16)) 		
	}
}