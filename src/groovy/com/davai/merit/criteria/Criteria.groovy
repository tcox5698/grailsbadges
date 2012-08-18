package com.davai.merit.criteria

public class Criteria {
	def maxResults
	def arguments = [:]
	def queryString	

	final def find() {

		if (!arguments || arguments.isEmpty()) {
			return doFindAll()
		} else {
			log.trace "finding: " + ["queryString":queryString,"arguments":arguments]
			return doFindAll(queryString, arguments)
		}
	}	
	
	def doFindAll() {
		throw new RuntimeException(" Criteria " + this.getClass().getName() + " does not implement doFindAll() ")
	}
}