package com.davai.merit.criteria

import com.davai.merit.*
import com.davai.merit.criteria.UnlockedAchievementCriteria

class UnlockedAchievementCriteriaTests extends GroovyTestCase {
	def objectService
	def objectHive
	
	public void setUp() throws Exception {
		super.setUp()
		this.objectHive = new ObjectHive()
	}
	
	void testFindAll() {
		def achievements = objectService.find(new UnlockedAchievementCriteria())
		
		achievements.each{it ->
			log.trace "found all, heres one: " + it
		}
	}
	
	void testFindAllWithMaxResults() {
		this.objectHive.provideUnlockedAchievements(5)
		def criteria = new UnlockedAchievementCriteria(maxResults:1)
		
		assertTrue(UnlockedAchievement.findAll().size() > 1)
		
		//EXECUTE
		def achievements = objectService.find(criteria)
		
		assertEquals(1, achievements.size())
		achievements.each{it ->
			log.trace "found something, heres one: " + it
		}		
	}
	
	void testFindByPerson_NoMaxResults() {
        def existingPerson = objectHive.providePeople(1)[0]
        def unlockedAchievements = objectHive.provideUnlockedAchievements(5, existingPerson)
        
		unlockedAchievements.each{it->
			assert it.save(flush:true)
		}

        def unlockedCriteria = new UnlockedAchievementCriteria(
        	queryString: "from UnlockedAchievement a where a.person = :person",
        	arguments: [person: existingPerson])
        
		//EXECUTE
		def achievements = objectService.find(unlockedCriteria)
			
		//VERIFY
 		assertEquals(5, achievements.size())
 		assertEquals(unlockedAchievements[0].messageKey, achievements[0].messageKey)
	}	
	
	void testFindByPerson() {
        def existingPerson = objectHive.providePeople(1)[0]
        def unlockedAchievements = objectHive.provideUnlockedAchievements(5, existingPerson)
        
		unlockedAchievements.each{it->
			log.trace "trying to save one: " + it
			assert it.save(flush:true)
		}

        def unlockedCriteria = new UnlockedAchievementCriteria(
        	queryString: "from UnlockedAchievement a where a.person = :person",
        	arguments: [person: existingPerson],
        	maxResults: 2)
        
		//EXECUTE
		def achievements = objectService.find(unlockedCriteria)
		
		achievements.each{it ->
			log.trace "found some, heres one: " + it
		}
		
		//VERIFY
 		assertEquals(2, achievements.size())
 		assertEquals(unlockedAchievements[0].messageKey, achievements[0].messageKey)
	}
}