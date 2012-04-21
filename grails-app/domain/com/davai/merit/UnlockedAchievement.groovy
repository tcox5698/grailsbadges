package com.davai.merit

class UnlockedAchievement {
    Person person
    String messageKey
    String messageArguments
    
    public UnlockedAchievement(Person person, String messageKey, String messageArguments) {
    	person = person
    	messageKey = messageKey
    	messageArguments = messageArguments
    	log.error this.toString()
    }
   
    public String toString() {
        return [person, messageKey, messageArguments].join(":") 
    }
}
