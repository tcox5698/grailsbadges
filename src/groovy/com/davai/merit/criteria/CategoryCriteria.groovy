package com.davai.merit.criteria

import com.davai.merit.*

public class CategoryCriteria extends Criteria {
	def alias = "c"
	def baseQuery = " from Category ${this.alias} "
	
	Class<?> getDomainClass() {
		return Category.class
	}

}