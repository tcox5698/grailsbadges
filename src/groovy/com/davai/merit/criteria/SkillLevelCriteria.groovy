package com.davai.merit.criteria

import com.davai.merit.*

public class SkillLevelCriteria extends Criteria {

	def doFindAll() {
		return SkillLevel.findAll()
	}
	
	def doFindAll(queryString, arguments) {
		log.trace "finding: " + ["queryString":queryString,"arguments":arguments]
		return SkillLevel.findAll(queryString, arguments)
	}
}