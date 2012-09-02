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
			it.name ==~ matchString
		}
		
		if (!returnCats.isEmpty()) {
			return returnCats	
		}
		
		matchString = searchStrings.join(".*|.*")
		matchString = /(?i).*${matchString}.*/		
		returnCats = cats.findAll {
			it.name ==~ matchString
		}
		
		if (!returnCats.isEmpty()) {
			return returnCats	
		}
		
		def characterList = collectAsCharacters(strings)
		
		if (characterList.containsAll(strings)) {
			return []
		}
				
		filterCats(cats, characterList)
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
