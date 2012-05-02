package com.davai.merit.criteria

import com.davai.merit.*

public class CategoryCriteria extends Criteria {

	def doFindAll() {
		return Category.findAll()
	}
	
	def doFindAll(queryString, arguments) {
		log.trace "finding: " + ["queryString":queryString,"arguments":arguments]
		return Category.findAll(queryString, arguments)
	}
}