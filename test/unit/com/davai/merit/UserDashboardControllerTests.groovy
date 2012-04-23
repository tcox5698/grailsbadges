package com.davai.merit



import grails.test.mixin.*
import org.junit.*
import groovy.mock.interceptor.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserDashboardController)
class UserDashboardControllerTests {
	def objectServiceController = new MockFor(ObjectService)

    void testSomething() {
    	def expectedPerson = new Person(name: "inputName",email:"inputEmail")
    	def returnedPerson = new Person(name: "returnedName", email:"returnedEmail")
    	def expectedUnlockedAchievement = new UnlockedAchievement(
    			person: returnedPerson,
    			messageKey: "returnedMessageKey",
    			messageArguments: "bob"
    	)
    	controller.springSecurityService = [currentUser:expectedPerson]
    	
    	objectServiceController.demand.find(1) { unlockedAchievementCriteria ->
    		assertEquals(expectedPerson, unlockedAchievementCriteria.arguments.person)
    		assertEquals(5, unlockedAchievementCriteria.maxResults)
    		return [expectedUnlockedAchievement]
    	}
    	
    	//EXECUTE
    	objectServiceController.use {
    		controller.objectService = new ObjectService()
    		
    		controller.index()	
    	}


		//VERIFY
    	assertEquals(200, response.status)
    	assertEquals("/userDashboard/index", view)
    	assertEquals([expectedUnlockedAchievement], model.unlockedAchievements)
    }
}
