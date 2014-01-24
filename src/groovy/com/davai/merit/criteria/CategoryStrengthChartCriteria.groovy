package com.davai.merit.criteria

import com.davai.merit.*

/**
	Returns simple map of data to populate Category Strength Charts.  Coupled to 
	model and to UI chart.  Format of return data is:
	[[categoryName:pointValue],[categoryName:pointValue]]
*/
public class CategoryStrengthChartCriteria extends Criteria {
	def whereClause = " and u.person = :person "
	def baseQuery = """
			select c.name, sum(s.multiplier)  as points
			from UnlockedAchievement u
				left join u.categories as c
				left join u.skillLevel as s
			where			
				c is not null
			
			${writer -> 
				writer << this.whereClause
			}
			group by 
				c.name
			order by 
				points desc   		
   		"""
	
	def find() {
		if (arguments.isEmpty()) {
			whereClause = ""
		}
		
		def queryResults = Category.executeQuery(baseQuery, arguments)
		def results = [:]
		
		queryResults.each() {
   			def catname = it[0]
   			def value = it[1]
   			results.put(catname, value)
   		}
		
		return results
	}
}