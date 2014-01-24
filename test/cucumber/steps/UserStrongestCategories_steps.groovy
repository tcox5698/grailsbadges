import cucumber.runtime.PendingException
import com.davai.merit.*
import com.davai.merit.featuretestutil.*
import com.davai.merit.criteria.*
import java.text.DateFormat

this.metaClass.mixin (cucumber.runtime.groovy.EN)

def userDashboardController
def results
def expected
def objectService

When(~/^I display my strongest categories$/) { ->
	userDashboardController = new UserDashboardController()
	userDashboardController.springSecurityService = [currentUser:FitContext.giveUser()]
	userDashboardController.userStrengthsChartData()
	results = userDashboardController.response.json
}

Given(~/^I have the following achievements$/) { Object dataTable ->
	List<Map<String, String>> rows = dataTable.asMaps()
	
	for (row in rows) {
		def dateString = row.get("Date")
		def unlockedDate = dateString != null ? DateFormat.getDateInstance(DateFormat.SHORT).parse(row.get("Date")) : null
		def achievementName = row.get("Name")
		def category = FitContext.giveCategory(row.get("Category"))
		def skillLevel = FitContext.giveSkillLevel(row.get("SkillLevelMultiplier"))
		def achievement = FitContext.giveUnlockedAchievement(achievementName, FitContext.giveUser(), [category], 
			skillLevel, unlockedDate)
	}
}

Given(~/^I am a new user with no achievements$/) { ->
	
	
	objectService = appCtx.getBean("objectService")
	
    def achievements = objectService.find(new UnlockedAchievementCriteria(
    	arguments:[person:FitContext.giveUser()]
    ))	
    
    assert achievements.isEmpty()
}


Then(~/^I see the following in the chart$/) { Object dataTable ->
	List<Map<String, String>> expectedRows = dataTable.asMaps()
	def expectedListOfMaps = []
	def actualListOfMaps = []
	
	for (row in expectedRows) {
		expectedListOfMaps.add([label:row.get("Category"), value:row.get("SkillPoints")])
	}
	
	results.each() {
		actualListOfMaps.add(label:it.label, value:it.value)
	}
	
	assert expectedListOfMaps.size() == actualListOfMaps.size()
	expectedListOfMaps.eachWithIndex(){it, i ->
		assert it.label.equals(actualListOfMaps.get(i).label)
		assert String.valueOf(it.value).equals(String.valueOf(actualListOfMaps.get(i).value))		
	}
}
	


