package com.davai.merit

import com.davai.secure.*

class Person extends SecUser implements Serializable {

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
		return "Person: [" + id + ": " + this.name + "]"
	} 
    
	public void setPassword(String inputPassword) {
	    if (null != inputPassword && inputPassword.trim().length() > 0 
	        && !inputPassword.equals(getPassword())) {
                super.setPassword(inputPassword)
	    }
	}    
}
