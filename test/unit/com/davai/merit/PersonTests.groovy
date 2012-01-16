package com.davai.merit



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Person)
class PersonTests {

    void testEmailAddressMustBeUnique() {
        def inputEmailAddress = "inputEmailAddress"
        def existingPerson = new Person(emailAddress: inputEmailAddress,
            password: "inputPassword", name: "inputName")
        
        mockForConstraintsTests(Person, [existingPerson])
        
        def newPerson = new Person(name: "newName", password: "newPassword", 
            emailAddress:inputEmailAddress)

        newPerson.validate()
        
        println newPerson.errors
        
        assert "unique" == newPerson.errors["emailAddress"]
    }
    
    void testNameMustBeUnique() {
        def inputName = "inputName"
        def existingPerson = new Person(emailAddress: "inputEmailAddress",
            password: "inputPassword", name: inputName)
        
        mockForConstraintsTests(Person, [existingPerson])
        
        def newPerson = new Person(name: inputName, password: "newPassword", 
            emailAddress:"newEmailAddress")

        newPerson.validate()
        
        println newPerson.errors
        
        assert "unique" == newPerson.errors["name"]
    }
}
