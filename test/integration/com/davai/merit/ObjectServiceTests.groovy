package com.davai.merit

import static org.junit.Assert.*
import org.junit.*
import com.davai.merit.criteria.*

class ObjectServiceTests extends GroovyTestCase {
	def objectService
	def objectHive
	
	@Before
	void before() {
		this.objectHive = new ObjectHive()
	}
	
	@Test
	void testFreeFormQueryWithParams() {
		def category = this.objectHive.provideCategories(["Bob"])[0]
		def otherCategory = this.objectHive.provideCategories(["Nancy"])[0]
		
		//EXECUTE
		def results = objectService.select("select c.name \
			from Category c\
			where c.name = :name",[name:"Bob"])
			
		//VERIFY
		assert results.size == 1
		
		assert results[0].equals("Bob")	
	}
	
	@Test
	void testFreeFormQuery() {
		def category = this.objectHive.provideCategories(["Bob"])[0]		
		assert null != category
		def unlockedAchievement = this.objectHive.provideUnlockedAchievements(1)[0]
		assert null != unlockedAchievement
		unlockedAchievement.addToCategories(category)
		objectService.save(unlockedAchievement)
	
		//EXECUTE
		def results = objectService.select("select c, count(a.id) \
		from UnlockedAchievement a \
		left join a.categories as c \
		where c is not null \
		group by c")
		
		//VERIFY
		assert results.size == 1
		
		results.each{
			assertEquals Category, it[0].class
			assertEquals Long, it[1].class
		}
	}
	
	@Test
	void testCreateReadFind() {
        def inputEmailAddress = "inputEmailAddress"
        
        def existingPerson = new Person(
            username: inputEmailAddress,
            password: "inputPassword", 
            name: "inputName",
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true")
            
        assert existingPerson.save(flush:true)
        
		//EXECUTE
		def readPerson = objectService.read(Person.class, existingPerson.getId())
		
		//VERIFY
		assert existingPerson.equals(readPerson)
		
		def criteria = new PersonCriteria(
			queryString: " from Person p where p.name = :name", 
			arguments:[name: readPerson.name]
		)
		
		def foundPeople = objectService.find(criteria)
		
		assertEquals(1, foundPeople.size)
		
		assertEquals(existingPerson.name, foundPeople[0].name)
	}
}