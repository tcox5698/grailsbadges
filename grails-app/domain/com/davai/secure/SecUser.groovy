package com.davai.secure

abstract class SecUser implements Serializable{

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true
		password nullable: true
		enabled blank: false
		accountExpired blank: false
		accountLocked blank: false	
	}

	static mapping = {
		password column: '`password`'
        table 'person'		
	}

	Set<SecRole> getAuthorities() {
		SecUserSecRole.findAllBySecUser(this).collect { it.secRole } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}
	
	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}	  
}
