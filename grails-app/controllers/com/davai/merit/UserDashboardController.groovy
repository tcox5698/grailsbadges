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

	def userStrengthsChartData() {
	    def person = springSecurityService.currentUser
	    def results = []
   		
   		def queryResults = objectService.find(new CategoryStrengthChartCriteria(arguments:[person:person]))
   
   		queryResults.each() {
   			def catname = it.key
   			def value = it.value
   			results.add([label:catname,value:value])   			
   		}
   		
   		results.sort{a, b ->
   			a.value == b.value ? a.label.compareTo(b.label) : (a.value < b.value? -1:(a.value > b.value? 1: 0))
   		}
   		   
		render(contentType: "text/json") {
			if (results.isEmpty()) {
				return results
			}
		
			def start = results.size()<5?0:results.size() - 5
			def end = results.size() - 1
			return results[start..end]
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
