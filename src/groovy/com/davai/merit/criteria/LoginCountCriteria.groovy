package com.davai.merit.criteria

import com.davai.merit.*

public class LoginCountCriteria extends Criteria {

	def doFindAll() {
		return LoginCount.findAll()
	}
	
	def doFindAll(queryString, arguments) {
		log.trace "finding: " + ["queryString":queryString,"arguments":arguments]
		return LoginCount.findAll(queryString, arguments)
	}
}