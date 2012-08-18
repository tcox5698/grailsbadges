package com.davai.merit

import com.davai.merit.criteria.*

import grails.test.mixin.*
import org.junit.*
import groovy.mock.interceptor.*
import java.text.*
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserDashboardController)
@Mock([UnlockedAchievement,SkillLevel])
class UserDashboardControllerTests {
	def objectServiceController
	def hive
	def inputPerson
	def returnedPerson

	@Before
    void setUp() {
		objectServiceController = mockFor(ObjectService)
		hive = new ObjectHive()
		
		inputPerson = new Person(name: "inputName",email:"inputEmail")
		returnedPerson = new Person(name: "returnPerson", email:"returnEmail")

    	controller.springSecurityService = [currentUser:inputPerson]
    			
    }

	void testUserBreadthChartData_ForOneAchievement() {
    	objectServiceController.demand.select(1) {queryString, arguments ->
    		assert "bob".equals(queryString)
    		assert "bingo".equals(arguments.person)
    	
			return ["Sally","Arnold"]
    	} 	
	
	    controller.objectService = objectServiceController.createMock()
	    
		//EXECUTE
		controller.userBreadthChartData()
		
		//VERIFY
		//objectServiceController.verify()
		assertEquals(1, response.json.length())	
	}

	//TODO test only returns achievements within period range
	//TODO test multi zero pointed returns
	//TODO test mix of zero and non-zero pointed returns

	void testUserProgressDepthChartData_CalculatesDatedWeightedProgress() {
		def expectedDate = new Date();
    	def expectedAchievements = hive.provideUnlockedAchievements(6, returnedPerson)
    	def skillLevels = hive.provideSkillLevels(6)
    	//skilllevels: 1,2,3,4,5,6
    	
    	def n = 0
    	for (i in expectedAchievements) {   		
			i.unlockedDate = expectedDate + n
			i.unlockedDate.putAt(Calendar.MINUTE, n+1)
    		i.skillLevel = skillLevels[n]
    		n+=2
    	}	
    	
		def mc = [
			compare:{b,a -> 
				def aMultiplier = a.skillLevel?.multiplier?:0
				def bMultiplier = b.skillLevel?.multiplier?:0
				aMultiplier.equals(bMultiplier)? 0: Math.abs(aMultiplier)<Math.abs(bMultiplier)? -1: 1
			}
		] as Comparator
		expectedAchievements.sort(mc)
 
    	response.format = "json"
    	
    	objectServiceController.demand.find(1) {UnlockedAchievementCriteria achievementCriteria ->
    		assertEquals("from UnlockedAchievement a where a.person = :person", achievementCriteria.queryString)
    		assertEquals(["person":inputPerson],achievementCriteria.arguments)
			return expectedAchievements
    	}    	
	
		controller.objectService = objectServiceController.createMock()
	
		//EXECUTE
		controller.userProgressDepthChartData()
		
		//VERIFY
		objectServiceController.verify()
		assertEquals(11, response.json.length())
			
		def dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
			
    	//skilllevels: 1,2,3,4,5,6
		n = 0
		def expectedRunningTotals = [1,1,4,4,9,9,16,16,16,16,16,16]
		response.json.eachWithIndex{it, i ->
			def expectedLabel = i==0 || i == response.json.size() -1 ? dateFormat.format(expectedDate+n):""		
			assertEquals(expectedLabel, it.label)
			assertEquals("failed on run: " + n + "full json: " + response.json, expectedRunningTotals[n], it.value)

			n++
		}
	}

	void testUserProgressDepthChartData_CalculatesWeightedProgress() {
		def expectedDate = new Date();
    	def expectedAchievements = hive.provideUnlockedAchievements(6, returnedPerson)
    	def skillLevels = hive.provideSkillLevels(6)
    	
    	def n = 0
    	for (i in expectedAchievements) {
			i.unlockedDate = expectedDate
    		i.skillLevel = skillLevels[n++]
    		//1,2,3,4,5,6
    	}
 
    	response.format = "json"
    	
    	objectServiceController.demand.find(1) {UnlockedAchievementCriteria achievementCriteria ->
    		assertEquals("from UnlockedAchievement a where a.person = :person", achievementCriteria.queryString)
    		assertEquals(["person":inputPerson],achievementCriteria.arguments)
			return expectedAchievements
    	}    	
	
		controller.objectService = objectServiceController.createMock()
	
		//EXECUTE
		controller.userProgressDepthChartData()
		
		//VERIFY
		objectServiceController.verify()
		assertEquals(1, response.json.length())
			
		assertEquals(DateFormat.getDateInstance(DateFormat.SHORT).format(expectedDate), response.json[0].label)
		assertEquals(21, response.json[0].value)
	}

	void testUserProgressDepthChartData_ZeroPointedAchievement_ZeroProgress() {
		def expectedDate = new Date();
    	def expectedUnlockedAchievement = new UnlockedAchievement(
    			person: returnedPerson,
    			messageKey: "returnedMessageKey",
    			messageArguments: "bob",
    			unlockedDate: expectedDate
    	)		

    	response.format = "json"
    	
    	objectServiceController.demand.find(1) {UnlockedAchievementCriteria achievementCriteria ->
    		assertEquals("from UnlockedAchievement a where a.person = :person", achievementCriteria.queryString)
    		assertEquals(["person":inputPerson],achievementCriteria.arguments)
			return [expectedUnlockedAchievement]
    	}    	
	
		controller.objectService = objectServiceController.createMock()
	
		//EXECUTE
		controller.userProgressDepthChartData()
		
		//VERIFY
		objectServiceController.verify()
		assertEquals(1, response.json.length())
			
		assertEquals(DateFormat.getDateInstance(DateFormat.SHORT).format(expectedDate), response.json[0].label)
		assertEquals(0, response.json[0].value)
	}

    void testRecentAchievements() {
    	def expectedUnlockedAchievement = new UnlockedAchievement(
    			person: returnedPerson,
    			messageKey: "returnedMessageKey",
    			messageArguments: "bob"
    	)
    	objectServiceController.demand.find(1) { unlockedAchievementCriteria ->
    		assertEquals(inputPerson, unlockedAchievementCriteria.arguments.person)
    		assertEquals(4, unlockedAchievementCriteria.maxResults)
    		return [expectedUnlockedAchievement]
    	}
    	
    	controller.objectService = objectServiceController.createMock()
    	
    	//EXECUTE
   		controller.index()	


		//VERIFY
		objectServiceController.verify()
    	assertEquals(200, response.status)
    	assertEquals("/userDashboard/index", view)
    	assertEquals([expectedUnlockedAchievement], model.unlockedAchievements)
    }
}
