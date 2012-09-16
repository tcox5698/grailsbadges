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
		def category = giveCategory(row.get("Category"))
		def skillLevel = giveSkillLevel(row.get("SkillLevelMultiplier"))
		def achievement = giveUnlockedAchievement(achievementName, FitContext.giveUser(), [category], 
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

def giveUnlockedAchievement(String achievementName, Person person, categories, SkillLevel level, Date unlockedDate) {
    def achievement = objectService.find(new UnlockedAchievementCriteria(
    	arguments:[person:person,skillLevel:level,name:achievementName]
    ))
    
    if (!achievement) {
    	achievement = objectService.save(new UnlockedAchievement(
			person: person,
			categories: categories,
			skillLevel: level,
			name: achievementName,
			unlockedDate: unlockedDate?:new java.util.Date()    		
    	))
    } else {
    	categories.each() {cat ->
    		achievement.each() {achv ->
    			System.out.println("achv: " + achv.getClass()) 
	    		achv.addToCategories(cat)
	    	}
    	}
    }
    
	assert achievement != null    
    
    return achievement
}

def giveSkillLevel(String skillLevelMultiplier) {
	def multiplierInt = Integer.parseInt(skillLevelMultiplier)

	def level = objectService.find(new SkillLevelCriteria(
		arguments:[multiplier: multiplierInt]))
	
	if (level) {
		return level[0]
	}
	
	level = objectService.save(new SkillLevel(
		name:"cukeskilllevel" + skillLevelMultiplier,
		description:"cukedesc:" + skillLevelMultiplier,
		rank:multiplierInt,
		multiplier:multiplierInt,
	))
	
	assert level != null
	return level
}

def giveCategory(String categoryName) {
	objectService = appCtx.getBean("objectService")

	def exists = objectService.find(new CategoryCriteria(
		arguments: [name:categoryName]
	))
	
	if (exists.size() > 0) {
		return exists[0]
	}

	exists = objectService.save(new Category(name:categoryName))
	
	return exists
}


