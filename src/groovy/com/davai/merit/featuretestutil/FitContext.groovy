package com.davai.merit.featuretestutil

import com.davai.merit.*

def class FitContext {

	static singleton

	def appCtx
	def user
	def objectService
	
	public static initialize(appCtx) {
		singleton = new FitContext(
			appCtx: appCtx, 
			objectService:appCtx.getBean("objectService"))
	}
	
	static giveUser() {
		def person
		if (!singleton.user) {
			singleton.user = singleton.givePerson()
		}
		return singleton.user        			
	}
	
	def givePerson() {
		def person = singleton.objectService.save(new Person(
			username: "cucumberUser",
			password: "inputPassword", 
			name: "inputName",
			accountLocked:"false",
			accountExpired:"false",
			enabled:"true"))
		assert person != null	
		return person
	}
}