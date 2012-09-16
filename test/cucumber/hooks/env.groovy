import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import com.davai.merit.*
import com.davai.merit.featuretestutil.*

this.metaClass.mixin (cucumber.runtime.groovy.Hooks)


GrailsTestRequestEnvironmentInterceptor scenarioInterceptor

Before () {
    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor (appCtx)
    scenarioInterceptor.init ()
    FitContext.initialize(appCtx)
}

After () {
    scenarioInterceptor.destroy ()

	def achvs = UnlockedAchievement.findAll()
		
	for (achv in achvs) {
		achv.delete(flush:true)
	}
	
	SkillLevel.executeUpdate("delete SkillLevel s")
    Category.executeUpdate("delete Category c")
	Person.executeUpdate("delete Person p")
}