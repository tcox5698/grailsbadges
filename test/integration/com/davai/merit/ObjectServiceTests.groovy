package com.davai.merit



class ObjectServiceTests extends GroovyTestCase {
	def objectService
	
	void testSomething() {
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
		def foundPerson = objectService.read(Person.class, existingPerson.getId())
		
		//VERIFY
		assert existingPerson.equals(foundPerson)
	}
}