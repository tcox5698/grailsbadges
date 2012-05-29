package com.davai.merit

class SkillLevel implements Serializable {
	String name
	String description
	Integer rank
	Integer multiplier
	
	public String toString() {
        return "SkillLevel:[name:" + name + ",rank:"+rank+",multiplier:" + multiplier+"]"
    } 
    
    static constraints = {
        name(unique:true)        
    }    
}