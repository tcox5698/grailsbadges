package com.davai.merit.criteria

public class PersonCriteria extends Criteria {
	def name
	def username
	
	public DQuery buildQuery() {
		def query = new DQuery(fromClause: " from Person p ")
		
		if (name) {
			query.addWhereParameter("name", name)
		}
		
		if (username) {
			query.addWhereParameter("username", username)
		}	
		
		return query	
	}
}