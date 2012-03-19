package com.davai.merit.criteria

public class LoginCountCriteria extends Criteria {
	def personId
	
	def buildQueryString() {
		"from LoginCount c where c.personId = $personId"
	}
}