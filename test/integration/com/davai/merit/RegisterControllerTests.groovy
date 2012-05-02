package com.davai.merit

import static org.junit.Assert.*
import org.junit.*
import com.davai.merit.*
import grails.test.mixin.*

class RegisterControllerTests {

    def String inputUsername = "fake@fakemail.fake"
    def controller

    @Before
    void setUp() {
		controller = new RegisterController()
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSomething() {
        def person = new Person([
            username:inputUsername,
            password:"password",
            name:"fakename",
            enabled:true,
            accountLocked:false,
            accountExpired:false
            ])
            
        person.errors.allErrors.each {
            log.error it
        }           
            
        person.save() 
    
        person.errors.allErrors.each {
            log.error it
        }           
    
        def foundPerson = Person.findByUsername(inputUsername)
        
        log.info foundPerson
     
        //controller.request.setMethod "POST"
        //controller.params.username = inputUsername

        //EXECUTE    
        controller.forgotPassword()
        
        //VERIFY
        assertNull controller.flash.error
    }
}
