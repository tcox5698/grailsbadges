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
		
		System.out.println("searchStrings: " + searchStrings)
		
		def matchString = searchStrings.join(".*")
		matchString = /(?i).*${matchString}.*/
		
		System.out.println("matchString: " + matchString)
				
		returnCats = cats.findAll {
			it.name ==~ matchString
		}
		
		if (!returnCats.isEmpty()) {
			System.out.println("FOUND by matchString: " + matchString)		
			return returnCats	
		}
		
		matchString = searchStrings.join(".*|.*")
		matchString = /(?i).*${matchString}.*/		
		returnCats = cats.findAll {
			it.name ==~ matchString
		}
		
		if (!returnCats.isEmpty()) {
			System.out.println("FOUND by matchString: " + matchString)		
			return returnCats	
		}
		
		def characterList = collectAsCharacters(strings)
		
		System.out.println("characterList: " + characterList)
		System.out.println("strings: " + strings)
		
		if (characterList.containsAll(strings)) {
			return []
		}
		
		System.out.println("found nothing by matchString: " + matchString)
		
		filterCats(cats, characterList)
	}
	
	def collectAsCharacters(List strings) {
		def string = strings.join("")
		
		return string.collect{
			it
		}
	}
	
	

    def suggestCategories(strings) {
    	def cats = objectService.find(new CategoryCriteria(queryString:"from Category c"))

		return filterCats(cats, strings)		
    }
}
