package com.davai.merit

def class ObjectHive {
	def incrementer = 0

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
		
		return provideUnlockedAchievements(count, person)
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
        	achievement.save()
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
			assert existingPerson.save(flush:true)	
			people.add(existingPerson)
		}
		
		return people
	}	
}