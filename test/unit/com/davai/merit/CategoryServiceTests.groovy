package com.davai.merit



import grails.test.mixin.*
import org.junit.*
import com.davai.merit.criteria.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CategoryService)
@Mock(Category)
class CategoryServiceTests {
	def hive
	def objectServiceController

	@Before
    void setUp() {
    	hive = new ObjectHive(flush:false)
		objectServiceController = mockFor(ObjectService)
    }


    void testSuggestCategories() {
    	def inputStrings = ["Checked in some java code","Sat ON a log","Climbed UP a tree"]
    	def expectedCategoryStrings = ["Java Development","Java Performance Tuning"] as Set
    	def expectedCategories = hive.provideCategories([
    		"Java Development"
    		,"Database Development"
    		,"Java Performance Tuning"
    		,"Ruby on Rails"])      		    	
    	
		objectServiceController.demand.find(1) { CategoryCriteria categoryCriteria ->
    		assertEquals("from Category c", categoryCriteria.queryString)
    		log.info "mock returning cats: " + expectedCategories
    		return expectedCategories
		}  	
		
		service.objectService = objectServiceController.createMock()
    	
    	//EXECUTE
		def actualCategories = service.suggestCategories(inputStrings)
		
		//VERIFY
		def actualCategoryStrings = [] as Set
		actualCategories.each{
			actualCategoryStrings.add(it.name)
		}
		
		assertEquals(expectedCategoryStrings, actualCategoryStrings)
    }
}
