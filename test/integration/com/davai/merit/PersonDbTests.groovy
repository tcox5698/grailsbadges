package com.davai.merit

import static org.junit.Assert.*
import org.junit.*

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
            
        existingPerson.save()
        
        def p = com.davai.secure.SecUser.findByUsername(inputEmailAddress)
        
        assertEquals "inputName", p.name
    }
}
