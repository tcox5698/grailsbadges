package com.davai.merit.criteria

import com.davai.merit.*
import com.davai.merit.criteria.UnlockedAchievementCriteria

class CategoryCriteriaTests extends GroovyTestCase {
	def objectService
	def objectHive
	
	public void setUp() throws Exception {
		super.setUp()
		this.objectHive = new ObjectHive()
	}

	void testLikeQuery() throws Exception {
		def names = ["Java","JavaScript","Database","Build","Gradle","Maven","BingoJava"]
		def categories = objectHive.provideCategories(names)
		def expectedNames = ["Java","JavaScript","BingoJava"]
		
		//EXECUTE
		def actualCategories = objectService.find(
			new CategoryCriteria(likeArgs:["name":"jav"]))
		
		//VERIFY
		assert actualCategories.size() == 3
		
		def actualNames = actualCategories.collect{it.name}

		assert actualNames.containsAll(expectedNames)
		assert expectedNames.containsAll(actualNames)	
	}
	
}