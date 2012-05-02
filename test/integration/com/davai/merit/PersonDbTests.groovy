package com.davai.merit

import static org.junit.Assert.*
import org.junit.*
import com.davai.secure.SecUser

class PersonDbTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSomething() {
        def inputEmailAddress = "inputEmailAddress"
		def existingPerson = new Person(
			username: inputEmailAddress,
			password: "inputPassword", 
			name: "inputName",
			accountLocked:"false",
			accountExpired:"false",
			enabled:"true")
            
        existingPerson.save(flush:true)
        
        assertFalse("should be no errors: " + existingPerson.errors, existingPerson.errors.hasErrors())
        
        assertEquals "inputName", existingPerson.name 
        
        log.info "saved person id: " + existingPerson.id 
        log.info "saved person username: " + existingPerson.username         
               
        def p = Person.read(existingPerson.id)
        
        log.info "readed a person: " + p
		assertEquals(existingPerson.username, p.username)
                        
        p = Person.findByUsername(inputEmailAddress)
        
        assertEquals "inputName", p.name
    }
}
