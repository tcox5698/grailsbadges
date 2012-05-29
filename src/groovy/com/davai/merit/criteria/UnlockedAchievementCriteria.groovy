package com.davai.merit.criteria

import com.davai.merit.*

public class UnlockedAchievementCriteria extends Criteria {
	
	def doFindAll() {		
		return UnlockedAchievement.findAll([max:maxResults])
	}
	
	def doFindAll(queryString, arguments) {
		log.trace "finding: " + ["queryString":queryString,"arguments":arguments,"maxResults":maxResults]
		
		def pagingParams = [:]
		
		if (maxResults) pagingParams.put('max', maxResults)
		
		return UnlockedAchievement.findAll(queryString, arguments, pagingParams)
	}	
}