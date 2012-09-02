package com.davai.merit.criteria

import com.davai.merit.*

class SkillLevelCriteriaTests extends GroovyTestCase {
	def objectService
	def objectHive
	
	public void setUp() throws Exception {
		super.setUp()
		this.objectHive = new ObjectHive()
	}	
	
	void testFindAll() {
		def expectedSkillLevels = objectHive.provideSkillLevels(5)		
		
		assert expectedSkillLevels.size() == 5
		
		//EXECUTE
		def actualSkillLevels = objectService.find(new SkillLevelCriteria())
		
		//VERIFY
		assert expectedSkillLevels.size() == actualSkillLevels.size()
		assert expectedSkillLevels.containsAll(actualSkillLevels)
	}
	
	void testFindByName() {
		def expectedSkillLevels = objectHive.provideSkillLevels(5)		
		
		assert expectedSkillLevels.size() == 5
		
		//EXECUTE
		def actualSkillLevels = objectService.find(new SkillLevelCriteria(
			arguments:[name:expectedSkillLevels[2].name]
			))
		
		//VERIFY
		assert 1 == actualSkillLevels.size()
		assert [expectedSkillLevels[2]].equals(actualSkillLevels)
	}

	
}