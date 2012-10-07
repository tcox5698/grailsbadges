package com.davai.merit

import com.davai.merit.criteria.*

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(AchieveController)
@Mock([Person, Category, UnlockedAchievement])
class AchieveControllerUnitTests {
	def objectServiceController
	def expectedPerson 	
	def hive

	@Before
    void setUp() {
    	hive = new ObjectHive(flush:false)
		objectServiceController = mockFor(ObjectService)
    	expectedPerson = hive.providePeople()[0]
    }

	@After
    void tearDown() {
        // Tear down logic here
    }

    void testPopulateAchievement() {
    	def conversation = [:]
    	params.name = "achievementName"
    	params.description = "achievementDescription"
    	def inputDate = new Date()
    	params.unlockedDate = inputDate

    	controller.springSecurityService = [currentUser:expectedPerson]
    
    	//EXECUTE
        controller.populateAchievement(conversation, params)
        
        //VERIFY
        assertEquals(params.name, conversation.unlockedAchievement.name)   
		assertEquals(params.description, conversation.unlockedAchievement.description)               
		assertEquals(inputDate, conversation.unlockedAchievement.unlockedDate)
		assertEquals(expectedPerson, conversation.unlockedAchievement.person)
    }
    
	void testPopulateCategory_OneExistingCategory() {
    	def conversation = [:]
    	params.selectedCategories = "testCategory"
    	def expectedCategory = hive.provideCategories()[0]
    	
		objectServiceController.demand.find(1) { CategoryCriteria categoryCriteria ->
			assertEquals(params.selectedCategories, categoryCriteria.arguments.name)
			return [expectedCategory]
		}   
		
		controller.objectService = objectServiceController.createMock()		 	
    
    	//EXECUTE
        controller.populateCategory(conversation, params)
        
        //VERIFY
        log.info "did i save the category? here: " + conversation.categories
        assertEquals([expectedCategory], conversation.categories)   
    }  
    
    void testCategoryList() {
    	def expectedCategories = hive.provideCategories(2)
    	def expectedCategoryNames = []
    	def inputCategorySearchTerm = "inputTerm"
    	
    	params.term = inputCategorySearchTerm
    	
    	expectedCategories.each{
    		expectedCategoryNames.add(it.name)
    	}
    
    	response.format = "json"
    	
    	objectServiceController.demand.find(1) {CategoryCriteria categoryCriteria ->
    		assertEquals(["name":inputCategorySearchTerm],categoryCriteria.likeArgs)
			return expectedCategories
    	}
    	
		controller.objectService = objectServiceController.createMock()		 	    	
    	
    	//EXECUTE
    	controller.categoryList()
    	assertEquals(2, response.json.length())
    	
    	assertTrue("whoops: " + response.json, response.json.containsAll(expectedCategoryNames))
    }
    
    void testAchievementList_SuggestsDistinctNames() {
    	def inputAchievementSearchTerm = "inputTerm"
    	def expectedAchievementName = "expectedAchievementName"
    	def expectedAchievements = hive.provideUnlockedAchievements(2)
    	
    	params.term = inputAchievementSearchTerm
    	
    	
    	expectedAchievements.each{
    		it.name = expectedAchievementName
    	}
    	
    	response.format = "json"
    	objectServiceController.demand.find(1) {UnlockedAchievementCriteria achievementCriteria ->
    		assertEquals(["name":inputAchievementSearchTerm],achievementCriteria.likeArgs)
			return expectedAchievements
    	}
    	
		controller.objectService = objectServiceController.createMock()    	
    		
    
    	//EXECUTE
    	controller.achievementList()
    	
    	assertEquals(1, response.json.length())
    	
    	assertTrue("whoops: " + response.json, response.json.contains(expectedAchievementName))    	
    }
    
    void testAchievementList_SuggestsNamesInAlphaOrder() {
    	def inputAchievementSearchTerm = "inputTerm"
    	def expectedAchievementName = "expectedAchievementName"
    	def expectedAchievements = hive.provideUnlockedAchievements(4)
    	def names = ["Bradley","Apple","Darwin","Curlington"]
    	
    	params.term = inputAchievementSearchTerm
    	
    	expectedAchievements.eachWithIndex{it, i ->
    		it.name = names[i]
    	}
    	
    	
    	response.format = "json"
    	objectServiceController.demand.find(1) {UnlockedAchievementCriteria achievementCriteria ->
    		assertEquals(["name":inputAchievementSearchTerm],achievementCriteria.likeArgs)
			return expectedAchievements
    	}
    	
		controller.objectService = objectServiceController.createMock()    	
    		
    
    	//EXECUTE
    	controller.achievementList()
    	
    	assertEquals(4, response.json.length())
    	
    	assertTrue("whoops: " + response.json, response.json.containsAll(names))    	
    	
    	names.sort()
    	
    	response.json.eachWithIndex{it, i ->
    		assert it.equals(names[i])
    	}
    }    
    
	void testPopulateCategory_OneNewCategory() {
    	def conversation = [:]
    	params.selectedCategories = "testCategory"
    	
		objectServiceController.demand.find(1) { CategoryCriteria categoryCriteria ->
			assertEquals(params.selectedCategories, categoryCriteria.arguments.name)
			return []
		}   
			
		controller.objectService = objectServiceController.createMock()		 	
    
    	//EXECUTE
        controller.populateCategory(conversation, params)
        
        //VERIFY
        assertEquals("testCategory", conversation.categories.iterator().next().name)   
    }       
}
