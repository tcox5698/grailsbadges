package com.davai.merit

import static org.junit.Assert.*
import org.junit.*
import com.davai.secure.SecUser

class UnlockedAchievementDbTests {

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
    	def person = providePerson()
    
    	def unlockedAchievement = new UnlockedAchievement(
    		person: person,
    		messageKey: "test.messageKey",
    		messageArguments: "arg1, arg2",
    		unlockedDate: new Date()
    	)
    	
    	unlockedAchievement.save(flush:true)
    	
        assertFalse("should be no errors: " + unlockedAchievement.errors, unlockedAchievement.errors.hasErrors())
        
        def u = UnlockedAchievement.read(unlockedAchievement.id)
        
        assertEquals("test.messageKey", u.messageKey)    
    }
    
	def providePerson() {
		def Person person = new Person(
            username: "omUsername",
            password: "omPassword", 
            name: "omPersonName",
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true"
		)
		
		person.save(flush:true)
		
		return person
	}    
}
