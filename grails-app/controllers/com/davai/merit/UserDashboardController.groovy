package com.davai.merit

import com.davai.merit.criteria.*
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import java.text.*

class UserDashboardController {
	def objectService
	def springSecurityService

    def index() { 
    	def person = springSecurityService.currentUser
    	def arguments = [person:person]
    	
    	def criteria = new UnlockedAchievementCriteria(
    			queryString: "from UnlockedAchievement a where a.person = :person "
    				+ " order by a.unlockedDate desc",
    			arguments:arguments,
    			maxResults:4		
    	)
    	
    	def unlockedAchievements = objectService.find(criteria)

    	render view:"index", model: [unlockedAchievements: unlockedAchievements]	
    }

	def userBreadthChartData() {
   		def results = [[label:"Java",value:"3"],[label:"Database",value:"2"],[label:"Release",value:"7"]]
   
		render(contentType: "text/json") {
			return results
		}     
    }
    
    def userProgressDepthChartData() {
    	def person = springSecurityService.currentUser
    	def arguments = [person:person]
		def dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)		    	
    
    	def criteria = new UnlockedAchievementCriteria(
    		queryString: "from UnlockedAchievement a where a.person = :person",
			arguments: arguments
    	)
    	
    	def unlockedAchievements = objectService.find(criteria)
    
		def resultsByDate = [:]

		unlockedAchievements.each{	
			def dateString = dateFormat.format(it.unlockedDate)
			def value = resultsByDate.get(dateString)?:0
			value+= it.skillLevel?.multiplier?:0			
			
			resultsByDate.put(dateString, value)
		}

		def results = []
		def runningTotal = 0
		def previousDate
			
		resultsByDate = resultsByDate.sort{
			dateFormat.parse(it.key)
		}
	
 		resultsByDate.eachWithIndex{it, i ->
			def currentDate = dateFormat.parse(it.key)
			while (previousDate != null && previousDate + 1 != currentDate) {
				def fillerDate = previousDate + 1
				def fillerDateString = (i==0?dateFormat.format(fillerDate):"")
				def result = [label:fillerDateString, value:runningTotal]
				results.add(result)
				previousDate = fillerDate
			}
			
			runningTotal += it.value
			def result = [label:(i==0?it.key:""), value:runningTotal]
			results.add(result)		
			
			previousDate = dateFormat.parse(it.key)
 		}
		
		results.last()["label"]=dateFormat.format(previousDate)
		
		render(contentType: "text/json") {
			return results
		} 
    }
}
