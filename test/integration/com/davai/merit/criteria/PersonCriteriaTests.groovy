package com.davai.merit.criteria

import com.davai.merit.Person
import com.davai.merit.criteria.PersonCriteria

class PersonCriteriaTests extends GroovyTestCase {
	def objectService
	
	void testFindByUsername() {
        
        def inputUsername = "inputUsername"
        def existingPerson = new Person(
            username: inputUsername,
            password: "inputPassword", 
            name: "inputName",
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true")
            
        def otherPerson = new Person(
            username: "otherpersonUsername",
            password: "otherPassword", 
            name: "otherName",
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true")    
            
        assert existingPerson.save(flush:true)
        assert otherPerson.save(flush:true)
        
        def personCriteria = new PersonCriteria(
        	arguments:[username: existingPerson.username]
        )
        
		//EXECUTE
		def persons = objectService.find(personCriteria)
		
		//VERIFY
		assertEquals(1, persons.size())
		assertEquals(existingPerson.name, persons[0].name)
	}
}