package com.davai.merit.criteria

import com.davai.merit.*

public class LoginCountCriteria extends Criteria {

	def alias = "c"
	def baseQuery = " from LoginCount ${this.alias} "
	
	Class<?> getDomainClass() {
		return LoginCount.class
	}
	

}