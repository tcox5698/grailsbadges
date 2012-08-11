import cucumber.runtime.PendingException
import com.davai.merit.*
import com.davai.merit.criteria.*

this.metaClass.mixin (cucumber.runtime.groovy.EN)

def userDashboardController
def results
def expected
def user
def objectService

Given(~/^I have achievement "([^"]*)" in category "([^"]*)"$/) { String arg1, String arg2 ->
	user = giveUser()
	expected = [[label:arg2,value:1]]
}
When(~/^I display my strongest categories$/) { ->
	userDashboardController = new UserDashboardController()
	userDashboardController.userBreadthChartData()
	results = userDashboardController.response.json
}
Then(~/^I see "([^"]*)"$/) { String arg1 ->
	//assert "nancy" == results.class.name
	assert 1 == results.length()
	def actual = results.getJSONObject(0)
	assert actual.value == "1"
	assert actual.label == arg1
}

Given(~/^I have the following achievements$/) { Object dataTable ->
	user = giveUser()
	
	List<Map<String, String>> rows = dataTable.asMaps()
	
	for (row in rows) {
		def achievementName = row.get("Name")
		def category = giveCategory(row.get("Category"))
		def skillLevel = giveSkillLevel(row.get("SkillLevelMultiplier"))
		def achievement = giveUnlockedAchievement(achievementName, user, category, skillLevel)
		System.out.println("createdAchievement!:" + achievement)
	}
}
Then(~/^I see the following in the chart$/) { Object dataTable ->
	List<Map<String, String>> expectedRows = dataTable.asMaps()
	def expectedMap = [:]
	def actualMap = [:]
	
	for (row in expectedRows) {
		expectedMap.put(row.get("Category"), row.get("SkillPoints"))
	}
	
	for (int i = 0; i < results.length(); i++) {
		def result = results.getJSONObject(i)
		actualMap.put(result.label, result.value)
	}
	
	assert expectedMap.equals(actualMap)

}

def giveUnlockedAchievement(String achievementName, Person user, Category category, SkillLevel level) {
    def achievement = objectService.find(new UnlockedAchievementCriteria(
    	queryString: "from UnlockedAchievement u left join u.categories as c "
    		+ " where u.person = :person and c.name = :categoryName and u.skillLevel = :skillLevel ",
    	arguments:[person:user,categoryName:category.name,skillLevel:level]
    ))
    
    if (!achievement) {
    	achievement = objectService.save(new UnlockedAchievement(
			person: user,
			categories: [category],
			skillLevel: level,
			name: achievementName,
			unlockedDate: new java.util.Date()    		
    	))
    }
    
	assert achievement != null    
    
    return achievement
}

def giveSkillLevel(String skillLevelMultiplier) {
	def multiplierInt = Integer.parseInt(skillLevelMultiplier)

	def level = objectService.find(new SkillLevelCriteria(
		queryString: "from SkillLevel s where s.multiplier = :multiplier ",
		arguments:[multiplier: multiplierInt]))
	
	if (level) {
		System.out.println("level exists: " + level)
		return level[0]
	}
	
	level = objectService.save(new SkillLevel(
		name:"cukeskilllevel" + skillLevelMultiplier,
		description:"cukedesc:" + skillLevelMultiplier,
		rank:multiplierInt,
		multiplier:multiplierInt,
	))
	
	assert level != null
	System.out.println("created skill level: " + level)
	return level
}

def giveCategory(String categoryName) {
	def exists = objectService.find(new CategoryCriteria(
		queryString: "from Category c where c.name = :name",
		arguments: [name:categoryName]
	))
	
	System.out.println("category exists?" + exists)
	
	if (!exists) {
		exists = objectService.save(new Category(name:categoryName))
		System.out.println("created category:" + exists)		
	}
	
	return exists
}

def giveUser() {
	objectService = appCtx.getBean("objectService")
	def person = objectService.save(new Person(
		username: "cucumberUser",
		password: "inputPassword", 
		name: "inputName",
		accountLocked:"false",
		accountExpired:"false",
		enabled:"true"))
	assert person != null
	return person            
}
