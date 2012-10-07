import cucumber.runtime.PendingException
import com.davai.merit.*
import com.davai.merit.featuretestutil.*
import com.davai.merit.criteria.*
import java.text.DateFormat

this.metaClass.mixin (cucumber.runtime.groovy.EN)

def achieveController
def results
def expected
def objectService

Given(~/^the following achievements exist$/) { Object dataTable ->
	List<Map<String, String>> rows = dataTable.asMaps()
	
	for (row in rows) {
		def achievementName = row.get("AchievementName")
		def unlockedDate = new java.util.Date()
		def category = FitContext.giveCategory("cucumberCategory" + achievementName)
		def skillLevel = FitContext.giveSkillLevel("2")
		def achievement = FitContext.giveUnlockedAchievement(achievementName, FitContext.givePerson(), [category], 
			skillLevel, unlockedDate)
	}
}

When(~/^I type achievement name "([^"]*)"$/) { String arg1 ->
	achieveController = new AchieveController()
	achieveController.params << [term:arg1]
	achieveController.achievementList()
	
    FitContext.results = achieveController.response.json
}

Then(~/^the application suggests no achievements$/) { ->
    assert FitContext.results.length() == 0
}

Then(~/^the application suggests the following	achievements$/) { Object dataTable ->
	List<Map<String, String>> expectedRows = dataTable.asMaps()
	def expectedList = []
	def actualList = []
	
	for (row in expectedRows) {
		expectedList.add(row.get("AchievementName"))
	}
	
	FitContext.results.each() {
		actualList.add(it)
	}
	
	assert expectedList.size() == actualList.size()
	expectedList.eachWithIndex(){it, i ->
		assert it.equals(actualList.get(i))
	}
}




