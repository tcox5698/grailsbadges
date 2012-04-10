package com.davai.merit.criteria

import com.davai.merit.LoginCount
import com.davai.merit.Person

class LoginCountCriteriaTests extends GroovyTestCase {
	def objectService
	
	void testFindByPersonId() {
        
        def existingPerson = new Person(
            username: "inputEmailAddress",
            password: "inputPassword", 
            name: "inputName",
            accountLocked:"false",
            accountExpired:"false",
            enabled:"true")
            
        assert existingPerson.save(flush:true)
        
        def existingLoginCount = new LoginCount(personId: existingPerson.getId(),
        	countValue: new BigDecimal("23"))
        
        assert existingLoginCount.save(flush:true)
        
        def countCriteria = new LoginCountCriteria(personId: existingPerson.getId())
        
		//EXECUTE
		def readLoginCounts = objectService.find(countCriteria)
		
		//VERIFY
		assertEquals(1, readLoginCounts.size())
		assertEquals(23, readLoginCounts[0].countValue.intValue())
	}
}