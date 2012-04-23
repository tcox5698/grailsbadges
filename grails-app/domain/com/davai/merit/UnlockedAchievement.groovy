package com.davai.merit

class UnlockedAchievement {
    Person person
    String messageKey
    String messageArguments
    Date unlockedDate
    
    public UnlockedAchievement(Person person, String messageKey, String messageArguments, Date unlockedDate) {
    	person = person
    	messageKey = messageKey
    	messageArguments = messageArguments
    	unlockedDate = unlockedDate
    }
   
    public String toString() {
        return [id, person, messageKey, messageArguments].join(":") 
    }
}
