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
        def existingPerson = new Person(
            username: inputEmailAddress,
            password: "inputPassword", 
            name: "inputName",
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true")
        
        mockForConstraintsTests(Person, [existingPerson])
        
        def newPerson = new Person(
            username: inputEmailAddress,
            password: "newPassword", 
            name: "newName", 
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true"
        )

        newPerson.validate()
        
        println newPerson.errors
        
        assert "unique" == newPerson.errors["username"]       
    }
    
    void testNameMustBeUnique() {
        def inputName = "inputName"
        def existingPerson = new Person(
            username: "inputEmailAddress",
            password: "inputPassword", 
            name: inputName,
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true"        
        )
        
        mockForConstraintsTests(Person, [existingPerson])
        
        def newPerson = new Person(
            username: "newEmailAddress",
            password: "newPassword", 
            name: inputName,
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true"  )

        newPerson.validate()
        
        println newPerson.errors
        
        assert "unique" == newPerson.errors["name"]
    }    
}
