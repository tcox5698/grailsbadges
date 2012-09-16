import cucumber.runtime.PendingException
import com.davai.merit.*
import com.davai.merit.criteria.*
import com.davai.merit.featuretestutil.*
import pages.userDashboard.*


this.metaClass.mixin (cucumber.runtime.groovy.EN)

Given(~/^I have logged in$/) { ->
	browser.go ("j_spring_security_logout")

	to UserDashboardPage
	
	$("input", name: "j_username").value(FitContext.giveUser().username)	
	$("input", name: "j_password").value("inputPassword")		
	
	$("a", id: "loginButton").click()
	
	at UserDashboardPage
}

When(~/^I display my achievement list$/) { ->
	to UserAchievementListPage
    at UserAchievementListPage
}
Then(~/^I see the following in the list$/) { Object arg1 ->
    // Express the Regexp above with the code you wish you had
    throw new PendingException()
}
