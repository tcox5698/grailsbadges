package com.davai.merit.criteria

public class UnlockedAchievementCriteria extends Criteria {
	def person
	
	public DQuery buildQuery() {
		def query = new DQuery(fromClause: " from UnlockedAchievement a ")
		
		if (person) {
			query.addWhereParameter("person", person)
		}
		
		return query	
	}
}