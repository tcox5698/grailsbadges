package com.davai.merit

import com.davai.merit.criteria.*

import java.text.SimpleDateFormat

class AchieveController {
	def transient objectService
	def transient springSecurityService
	def transient categoryService

	def populateAchievement(conversation, params) {
		conversation.unlockedAchievement = conversation.unlockedAchievement ? conversation.unlockedAchievement : new UnlockedAchievement(params)
		conversation.unlockedAchievement.person = springSecurityService.currentUser	
	}
	
	def populateCategory(conversation, params) {
		def catNames = params.selectedCategories.split(',')
		
		catNames.each { catName ->
			def categoryCriteria = new CategoryCriteria(
				queryString: "from Category c where c.name = :name",
				arguments: [name:catName]
			)		
			
			def category = objectService.find(categoryCriteria)[0]
			
			log.error "category: " + category
			
			if (!category) {
				category = objectService.save(new Category(name:catName))
			}
			conversation.unlockedAchievement.addToCategories(category)			
		}
	}

	def categoryList() {
		
		def results = objectService.find(
			new CategoryCriteria(
				queryString:"from Category c where upper(c.name) like :name",
				arguments:[name:"%" + params.term.toUpperCase() + "%"]))
	
		render(contentType: "text/json") {
			def options = []
 			results.each{r ->
 				options.add(r.name)
 			}
 			return options
		}	
	}

	def achieveFlow = {
		enterName {
			on("continue"){
				log.error "continue event on enterName"			
				populateAchievement(conversation, params)
				def achievementStrings = [conversation.unlockedAchievement.name,conversation.unlockedAchievement.description]
				conversation.suggestedCategories=categoryService.suggestCategories(achievementStrings.findAll{it != null})
			}.to "enterCategory"
			on("saveAndDone"){			
				populateAchievement(conversation, params)
			}.to "saveAndDone"
			on("cancel").to "cancel"

		}
	
		enterCategory {
			on("continue"){
				log.error "continue event on enterCategory"
				populateCategory(conversation, params)				
				}.to("skillify")
			on("saveAndDone"){
				log.error "saveAndDone event on enterCategory"
				populateCategory(conversation, params)				
				}.to("saveAndDone")			
			on("cancel").to("cancel")						
		}
		
		skillify {
		}
		
		cancel {
			redirect (controller:"userDashboard", action: "index")				
		}
	
		saveAndDone {	
			action {
				log.error "here it is:" +conversation.unlockedAchievement
				log.error "does it have errors:" + conversation.unlockedAchievement.hasErrors()
				populateAchievement(conversation, params)				
				if (conversation.unlockedAchievement.hasErrors()) {
					conversation.unlockedAchievement.errors.each{
						log.error "each error: " + it
					}
				}
				objectService.save(conversation.unlockedAchievement)				
			}
			on("success").to "finish"
			

		}
		
		finish {
			redirect (controller:"userDashboard", action: "index")		
		}
	}
}