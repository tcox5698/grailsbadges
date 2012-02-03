package com.davai.merit

import com.davai.secure.*

class Person extends SecUser {

    String name
    
    String email

    static constraints = {
        name(unique:true)
        email(display:false, nullable: true)
    }
    
	def beforeInsert() {
	    super.beforeInsert()
		email = username;
	}

	def beforeUpdate() {
	    super.beforeUpdate()
		email = username;
	}    
    
    public String toString() {
        return id + ": " + this.name
    } 
    
}
