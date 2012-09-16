import cucumber.runtime.PendingException
import com.davai.merit.*
import com.davai.merit.criteria.*
import com.davai.merit.featuretestutil.*

this.metaClass.mixin (cucumber.runtime.groovy.EN)

When(~/^I display my achievement list$/) { ->
	userDashboardController = new UserDashboardController()
	userDashboardController.springSecurityService = [currentUser:FitContext.giveUser()]
	userDashboardController.userAchievementList()
	results = userDashboardController
	System.out.println("achv list results:  " + results)
}
Then(~/^I see the following in the list$/) { Object arg1 ->
    // Express the Regexp above with the code you wish you had
    throw new PendingException()
}
