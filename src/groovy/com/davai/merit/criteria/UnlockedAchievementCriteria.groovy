package com.davai.merit.criteria

import com.davai.merit.*

public class UnlockedAchievementCriteria extends Criteria {
	def alias = "a"
	def baseQuery = " from UnlockedAchievement ${this.alias} "
	
	Class<?> getDomainClass() {
		return UnlockedAchievement.class
	}
}