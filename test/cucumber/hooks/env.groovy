import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import com.davai.merit.*
import com.davai.merit.featuretestutil.*
import geb.binding.BindingUpdater
import geb.Browser

this.metaClass.mixin (cucumber.runtime.groovy.Hooks)


GrailsTestRequestEnvironmentInterceptor scenarioInterceptor

Before () {
    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor (appCtx)
    scenarioInterceptor.init ()
    FitContext.initialize(appCtx)
    
    bindingUpdater = new BindingUpdater (binding, new Browser ())
    bindingUpdater.initialize ()    
}

After () {
    scenarioInterceptor.destroy ()

	def achvs = UnlockedAchievement.findAll()
		
	for (achv in achvs) {
		achv.delete(flush:true)
	}
	
	LoginCount.executeUpdate("delete LoginCount c")
	SkillLevel.executeUpdate("delete SkillLevel s")
    Category.executeUpdate("delete Category c")
	Person.executeUpdate("delete Person p")
	
	
	bindingUpdater.remove()
}