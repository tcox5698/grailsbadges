package com.davai.merit

def class ObjectHive {
	def incrementer = 0
	def flush = true

	def validateIt(obj) {
		if (!obj.validate()) {
			obj.errors.allErrors.each {
				println "\n" + it 
			}	
			throw new RuntimeException("test object failed validation" + obj.errors.allErrors)		
		}
	}

	def provideUnlockedAchievements(int count = 1) {
		def person = providePeople(1)[0]
		
		def achv = provideUnlockedAchievements(count, person)
		
		assert null!= achv
		
		return achv
	}

	def provideCategories(names) {
		def list = []
		names.each{
			list.add(createCategory(it))
		}
		return list
	}

	def createCategory(name) {
		def category = new Category(name: name)
		//validateIt(category)
		category.save(flush:flush, validate:false)
		assert category
        return category
	}

	def provideCategories(int count = 1) {
		def counter = 0
		def list = []
		
		while (counter++ < count) {
			log.trace " provideCategories counter: " + counter		
			incrementer++			
        	def category = createCategory("testCategory" + incrementer)
			list.add(category)		
		}
		return list		
	}

	def provideSkillLevels(int count = 1) {
		def list = []
	
		for (i in 0..count) {
			def skillLevel = new SkillLevel(
				name: "skill" + i,
				description:"skillDescription" + i,
				rank: i,
				multiplier: i+1
			)
			skillLevel.save(flush:flush)
			assert skillLevel
			list.add(skillLevel)
		}
		
		return list
	}
	
	def provideUnlockedAchievements(int count = 1, Person person ) {
		def counter = 0
		def list = []
		
		log.trace " provideUnlockedAchievements count: " + count			
		log.trace " provideUnlockedAchievements person: " + person					
				
		while (counter++ < count) {
			log.trace " provideUnlockedAchievements counter: " + counter		
			incrementer++
        	def achievement = new UnlockedAchievement(
				person: person,
				messageKey: "test.achievement.key" + incrementer,
				messageArguments: "args" + incrementer,
				unlockedDate: new java.util.Date()
        	)
        	validateIt(achievement)
        	achievement.save(flush:flush)
        	assert achievement
			list.add(achievement)		
		}
		return list	
	}
	

	def providePeople(count = 1) {
		def counter = 0
		def people = []
		
		while (counter++ < count) {
			incrementer++
			def existingPerson = new Person(
				username: "inputUsername" + incrementer,
				password: "inputPassword" + incrementer, 
				name: "inputName" + incrementer,
				accountLocked:"false",
				accountExpired:"false",
				enabled:"true")
			validateIt(existingPerson)
			
			existingPerson.springSecurityService = [encodePassword:{
				log.trace "someone's encoding a password"
			}]
			
			assert existingPerson.save(flush:flush)	
			people.add(existingPerson)
		}
		
		return people
	}	
}