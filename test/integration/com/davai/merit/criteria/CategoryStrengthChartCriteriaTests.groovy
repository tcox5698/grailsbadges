package com.davai.merit.criteria

import com.davai.merit.*
import com.davai.merit.criteria.CategoryStrengthChartCriteria

class CategoryStrengthChartCriteriaTests extends GroovyTestCase {
	def objectService
	def objectHive
	
	public void setUp() throws Exception {
		super.setUp()
		this.objectHive = new ObjectHive()
	}
	
	void testFindAll() {
		def achievement = objectHive.provideUnlockedAchievements()[0]
		        
        def chartCriteria = new CategoryStrengthChartCriteria()
        
		//EXECUTE
		def results = objectService.find(chartCriteria)		
		
		//VERIFY
		assert achievement.skillLevel.multiplier == results.get(
			achievement.categories.iterator().next().name
		)
	}
	
	void testFindForPerson() {
		def achievement1 = objectHive.provideUnlockedAchievements()[0]
		def achievement2 = objectHive.provideUnlockedAchievements()[0]
		
		assert !achievement1.person.name.equals(achievement2.person.name)
		
		def chartCriteria = new CategoryStrengthChartCriteria(arguments:[person:achievement2.person])
        
		//EXECUTE
		def results = objectService.find(chartCriteria)		
		
		//VERIFY
		assert achievement2.skillLevel.multiplier == results.get(
			achievement2.categories.iterator().next().name
		)
	}
}