package com.davai.merit.criteria

import com.davai.merit.*

/**
	Returns simple map of data to populate Category Strength Charts.  Coupled to 
	model and to UI chart.  Format of return data is:
	[[categoryName:pointValue],[categoryName:pointValue]]
*/
public class CategoryStrengthChartCriteria extends Criteria {
	def whereClause = " where u.person = :person "
	def hardQueryString = """
			select c.name, count(u.id) * max(s.multiplier)  as points
			from UnlockedAchievement u
				left join u.categories as c
				left join u.skillLevel as s
			${writer -> 
				writer << this.whereClause
			}
			group by 
				c.name
			order by 
				points desc   		
   		"""

	def doFindAll() {
		whereClause = ""
		def queryResults = Category.executeQuery(hardQueryString, arguments)
		def results = [:]
		
		queryResults.each() {
   			System.out.println("dealing with row: " + it)
   			def catname = it[0]
   			def value = it[1]
   			results.put(catname, value)
   			
   			System.out.println("just popped results: " + results)
   		}
		
		return results
	}
	
	def doFindAll(queryString, arguments) {	
		def queryResults = Category.executeQuery(hardQueryString, arguments)
		def results = [:]
		
		queryResults.each() {
   			System.out.println("dealing with row: " + it)
   			def catname = it[0]
   			def value = it[1]
   			results.put(catname, value)
   			
   			System.out.println("just popped results: " + results)
   		}
		
		return results
	}
}