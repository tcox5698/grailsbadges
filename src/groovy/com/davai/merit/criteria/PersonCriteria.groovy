package com.davai.merit.criteria

import com.davai.merit.*

public class PersonCriteria extends Criteria {
	
	def doFindAll() {		
		return Person.findAll()
	}
	
	def doFindAll(queryString, arguments) {
		log.trace "finding: " + ["queryString":queryString,"arguments":arguments]
		return Person.findAll(queryString, arguments)
	}
}