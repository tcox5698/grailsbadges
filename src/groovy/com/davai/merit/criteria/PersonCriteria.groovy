package com.davai.merit.criteria

public class PersonCriteria extends Criteria {
	def name
	
	public String buildQueryString() {
		"from Person p where p.name = '$name'"
	}
}