package com.davai.merit

class UnlockedAchievement implements Serializable {
    Person person
    String name
    String description
    String messageKey
    String messageArguments
    Date unlockedDate
    SkillLevel skillLevel
    static hasMany = [categories:Category]
    
    public UnlockedAchievement(Person person, String messageKey, String messageArguments, Date unlockedDate) {
    	person = person
    	messageKey = messageKey
    	messageArguments = messageArguments
	   	unlockedDate = unlockedDate
    }
   
    public String toString() {
        return [id, person, messageKey, messageArguments, unlockedDate, skillLevel].join(":") 
    }
    
    def getMessageArgs() {
    	if (messageArguments != null && messageArguments.contains(",")) {
    		return messageArguments.split(",")
    	}
    	
    	return [messageArguments]
    }
    
    static constraints = {
    	unlockedDate()
	    name(nullable:true)
        messageKey(nullable:true)
        messageArguments(nullable:true)
        description(nullable:true)
        skillLevel(nullable:true)
    }    
}
