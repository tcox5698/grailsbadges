package com.davai.merit.criteria

import com.davai.merit.*

public class PersonCriteria extends Criteria {

	def alias = "p"
	def baseQuery = " from Person ${this.alias} "

	Class<?> getDomainClass() {
		return Person.class
	}
}