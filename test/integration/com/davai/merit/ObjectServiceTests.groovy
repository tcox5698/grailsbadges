package com.davai.merit

import static org.junit.Assert.*
import org.junit.*
import com.davai.merit.criteria.*

class ObjectServiceTests {
	def objectService
	
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