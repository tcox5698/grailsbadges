package com.davai.merit

import com.davai.merit.criteria.*

class CategoryService {
	def objectService

	def filterCats(java.util.List cats, String string) {

    	def perfectCats = []
    	
		perfectCats.addAll(
			cats.findAll {cat ->
				cat.name.startsWith(string)
			}
		)
	
    	if (!perfectCats.isEmpty()) {
    		return perfectCats
    	}
    	
    	return filterCats(cats, [string])	
	}

	def filterCats(java.util.List cats, java.util.List strings) {
		def searchStrings = []
		def returnCats = []
		
		strings.each{
			searchStrings.addAll(it.split(" "))
		}
		
		searchStrings = searchStrings.findAll {
			!(it ==~ /(?i)on|in|a|the|for|with|to|up/)			
		}
		
		
		def matchString = searchStrings.join(".*")
		matchString = /(?i).*${matchString}.*/
				
		returnCats = cats.findAll {
			def result = it.name ==~ matchString		
			return result
		}
		
		if (!returnCats.isEmpty()) {
			return returnCats	
		}
		
		
		searchStrings = searchStrings.findAll{it.size() > 1}
		if (searchStrings.size() > 0) {
			matchString = searchStrings.join(".*|.*")
			matchString = /(?i).*${matchString}.*/		
			returnCats = cats.findAll {
				def result = it.name ==~ matchString		
				return result
			}
			
			if (!returnCats.isEmpty()) {
				return returnCats	
			}
			
			matchString = searchStrings
				.collect{it.replaceAll(/[aeiouAEIOU]/, "")}
				.findAll{it.size() > 1}
				.join(".*|.*")
			matchString = /(?i).*${matchString}.*/						
			
			returnCats = cats.findAll{
				def checkName = it.name.replaceAll(/[aeiouAEIOU]/, "")
	
				def result = checkName ==~ matchString
				
				return result
			}
			
			if (!returnCats.isEmpty()) {
				return returnCats	
			}
		}		
		
		return []
	}
	
	def collectAsCharacters(List strings) {
		def string = strings.join("")
		
		return string.collect{
			it
		}
	}
	
	

    def suggestCategories(strings) {
    	def cats = objectService.find(new CategoryCriteria())

		return filterCats(cats, strings)		
    }
}
