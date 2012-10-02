package com.davai.merit

import com.davai.merit.criteria.*

import java.text.SimpleDateFormat

class AchieveController {
	def transient objectService
	def transient springSecurityService
	def transient categoryService

	def populateAchievement(conversation, params) {
		conversation.unlockedAchievement = conversation.unlockedAchievement ?: new UnlockedAchievement(params)
		conversation.unlockedAchievement.person = springSecurityService.currentUser	
	}
	
	def populateCategory(conversation, params) {
		if (params.selectedCategories == "") {
			return
		}
	
		def catNames = params.selectedCategories.split(',')
		
		catNames.each { catName ->
			def categoryCriteria = new CategoryCriteria(arguments: [name:catName])		
			
			def category = objectService.find(categoryCriteria)[0]
			
			if (!category) {
				category = new Category(name:catName)
			}
			conversation.categories = conversation.categories?:[]
			conversation.categories.add(category)			
		}
	}

	def populateSkillLevel(conversation, params) {
		conversation.unlockedAchievement.skillLevel = SkillLevel.read(params.skillLevel)
	}

	def achievementList() {		
		def likeArguments = ["name":params.term]
		def results = objectService.find(new UnlockedAchievementCriteria(likeArgs:likeArguments))
	
		render(contentType: "text/json") {
			def options = []
 			results.each{r ->
 				options.add(r.name)
 			}
 			return options
		}	
	}

	def categoryList() {		
		def likeArguments = ["name":params.term]
		def results = objectService.find(new CategoryCriteria(likeArgs:likeArguments))
	
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
					populateCategory(conversation, params)		
					conversation.skillLevels = []
					SkillLevel.findAll().each{
						conversation.skillLevels.add([id:it.id, name:it.name + ":" + it.description])
					}	
				}.to("skillify")
			on("saveAndDone"){
				populateCategory(conversation, params)				
				}.to("saveAndDone")			
			on("cancel").to("cancel")						
		}
		
		skillify {
			on("saveAndDone") 
			{
				populateSkillLevel(conversation, params)
			}.to("saveAndDone")
		}
		
		cancel {
			redirect (controller:"userDashboard", action: "index")				
		}
	
		saveAndDone {	
			action {		
				if (conversation.unlockedAchievement.hasErrors()) {
					conversation.unlockedAchievement.errors.each{
						log.error "each error: " + it
					}
				}
				
				def mergedCategories = []
				
				conversation.categories.each{
					mergedCategories.add(it.merge())
				}
				
				mergedCategories.each{
					conversation.unlockedAchievement.addToCategories(it)
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