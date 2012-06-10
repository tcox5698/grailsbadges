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
		
		log.error "before sort"		
		
		resultsByDate = resultsByDate.sort{
			dateFormat.parse(it.key)
		}

		log.error "before eachWithIndex: "
		
 		resultsByDate.eachWithIndex{it, i ->
 			log.error "eachWithIndex: " + it
			def currentDate = dateFormat.parse(it.key)
			while (previousDate != null && previousDate + 1 != currentDate) {
				def fillerDate = previousDate + 1
				def fillerDateString = (i==0?dateFormat.format(fillerDate):"")
				def result = [label:fillerDateString, value:runningTotal]
				log.error "filler result: " + result
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
