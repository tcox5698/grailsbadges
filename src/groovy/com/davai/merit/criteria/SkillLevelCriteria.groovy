package com.davai.merit.criteria

import com.davai.merit.*

public class SkillLevelCriteria extends Criteria {
	def alias = "s"
	def baseQuery = "from SkillLevel ${alias}"
	
	Class<?> getDomainClass() {
		return SkillLevel.class
	}
}