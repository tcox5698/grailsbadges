import cucumber.runtime.PendingException
import com.davai.merit.*

this.metaClass.mixin (cucumber.runtime.groovy.EN)

def userDashboardController
def results
def expected

Given(~/^I have achievement "([^"]*)" in category "([^"]*)"$/) { String arg1, String arg2 ->
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