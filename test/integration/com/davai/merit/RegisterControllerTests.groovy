package com.davai.merit

import static org.junit.Assert.*
import org.junit.*
import com.davai.merit.*

class RegisterControllerTests extends grails.test.ControllerUnitTestCase{

    def String inputUsername = "fake@fakemail.fake"

/*    @Before
    void setUp() {

    }*/

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSomething() {
        log.info 'TESTING SOMETHING!'
        
        log.info inputUsername
       
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
