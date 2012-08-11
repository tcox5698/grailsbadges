import org.codehaus.groovy.grails.test.support.GrailsTestRequestEnvironmentInterceptor
import com.davai.merit.*

this.metaClass.mixin (cucumber.runtime.groovy.Hooks)


GrailsTestRequestEnvironmentInterceptor scenarioInterceptor

Before () {
    scenarioInterceptor = new GrailsTestRequestEnvironmentInterceptor (appCtx)
    scenarioInterceptor.init ()
}

After () {
    scenarioInterceptor.destroy ()
    Category.executeUpdate("delete Category c")
}