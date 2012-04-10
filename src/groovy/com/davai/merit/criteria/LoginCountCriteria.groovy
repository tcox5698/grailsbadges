package com.davai.merit.criteria

public class LoginCountCriteria extends Criteria {
	Integer personId
	
	def buildQuery() {
		def query = new DQuery(fromClause: " from LoginCount c  ")
		
		if (personId) {
			query.addWhereParameter("personId", personId)
		}
		
		return query			
		
	}
}