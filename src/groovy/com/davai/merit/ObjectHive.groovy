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
		if (flush) {
			category.save(flush:flush, validate:false)
		}
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

	def provideSkillLevels(int want = 1) {
		def list = SkillLevel.list()
		def have = 0
				
		if (!list.isEmpty()) {
			log.trace " some skills exist: " + list					
			if (list.size() >= want) {
				return list[0..want]
			}
			have = list.size()
		}
		
		list = []
	
		for (i in have..want) {
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
	
	def provideUnlockedAchievements(int want = 1) {	
		def person = providePeople()[0]
		return provideUnlockedAchievements(want, person)
	}
	
	def provideUnlockedAchievements(int count = 1, Person person) {	
		return provideUnlockedAchievements(count, person, null, null)
	}	
	
	def provideUnlockedAchievementsFull(int want = 1) {
		def person = providePeople()[0]
		def category = provideCategories(1)[0]
		def level = provideSkillLevels(1)[0]
	
		return provideUnlockedAchievements(want, person, category, level)
	}
	
	def provideUnlockedAchievements(int count = 1, Person person, Category category, SkillLevel level ) {
		def counter = 0
		def list = []
		
		log.trace " provideUnlockedAchievements count: " + count			
		log.trace " provideUnlockedAchievements person: " + person					
				
		while (counter++ < count) {
			log.trace " provideUnlockedAchievements counter: " + counter		
			incrementer++
        	def achievement = new UnlockedAchievement(
				person: person,
				skillLevel: level,
				messageKey: "test.achievement.key" + incrementer,
				messageArguments: "args" + incrementer,
				unlockedDate: new java.util.Date()
        	)
        	if (null != category) {
	        	achievement.addToCategories(category)
	        }
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