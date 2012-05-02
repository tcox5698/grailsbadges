package com.davai.merit

import com.davai.merit.criteria.*

class CategoryService {
	def objectService

    def suggestCategories(strings) {
    	log.error "suggest categories for strings: " + strings
    	def cats = objectService.find(new CategoryCriteria(queryString:"from Category c"))

		def searchStrings = []
		
		strings.each{
			searchStrings.addAll(it.split(" "))
		}
		
		searchStrings = searchStrings.findAll {
			!(it ==~ /(?i)on|in|a|the|for|with|to|up/)			
		}
		
		def matchString = searchStrings.join(".*|.*")
		matchString = /(?i).*${matchString}.*/
				
		cats = cats.findAll {
			it.name ==~ matchString
		}
				
		return cats
    }
}
