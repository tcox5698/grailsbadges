import cucumber.runtime.PendingException
import com.davai.merit.*
import com.davai.merit.criteria.*
import com.davai.merit.featuretestutil.*
import pages.userDashboard.*
import java.text.*


this.metaClass.mixin (cucumber.runtime.groovy.EN)

Given(~/^I have logged in$/) { ->
	browser.go ("j_spring_security_logout")

	to UserDashboardPage
	
	$("input", name: "j_username").value(FitContext.giveUser().username)	
	$("input", name: "j_password").value(FitContext.giveUserPassword())		
	
	$("a", id: "loginButton").click()

	sleep(2000)

	at UserDashboardPage
}

When(~/^I display my achievement list$/) { ->
	to UserAchievementListPage
    at UserAchievementListPage
}
Then(~/^I see the following in the list$/) { Object dataTable ->
	List<Map<String, String>> expectedRows = dataTable.asMaps()
	def expectedListOfMaps = []
	
	for (row in expectedRows) {
		def date = row.get("Date")
		if ("^today".equals(date)) {
			date = DateFormat.getDateInstance(DateFormat.SHORT).format(new Date())
		}
	
		expectedListOfMaps.add([
			Date:date,
			Description:row.get("AchievementName"),
			Categories:row.get("Categories"),
			"Skill Level":row.get("SkillLevelName"),
			Multiplier:row.get("Multiplier")
		])
	}
	
	def actualHeaderRow = $("table", id:"userAchievementListTable").find("tr",id:"columnHeaders").find("th")
	def headers = actualHeaderRow.collect{
		it.text()
	}
	
	def actualRows = $("table", id:"userAchievementListTable").find("tbody").find("tr")
	
	actualRows = actualRows.collect{
		def rowMap = [:]
		it.find("h3").eachWithIndex(){obj, i -> 
			rowMap.put(headers.get(i), obj.text())
		}
		return rowMap
	}

	assert expectedListOfMaps.size() == actualRows.size()
	
	expectedListOfMaps.eachWithIndex(){obj, i -> 
		def actual = actualRows.get(i)
		
		headers.each() {header ->		
			assert obj[header]?:"".equals(actual[header])
		}
	}	
}
