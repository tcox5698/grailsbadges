grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.groupId = "com.davai.merit"
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    
	def gebVersion = "0.7.2"
    def seleniumVersion = "2.25.0"    

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        //runtime 'mysql:mysql-connector-java:5.1.16'
        runtime 'postgresql:postgresql:8.4-702.jdbc3'
        
		test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") {
			exclude "xml-apis"
		}
        test ("org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion")      

        test ("org.codehaus.geb:geb-junit4:$gebVersion")

        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"

        compile "org.grails:grails-webflow:$grailsVersion"

    }

    plugins {
        compile ":jquery:1.7.1"
        runtime ":jquery-ui:1.8.7"
        compile ":famfamfam:1.0"
        
        test ":cucumber:0.6.0"
        test ":geb:$gebVersion"
        test(":spock:0.7") {
            exclude "spock-grails-support"
        }

        compile ':webflow:2.0.0', {
            exclude 'grails-webflow'
        }
    
        runtime ":hibernate:$grailsVersion"
        runtime ":resources:1.1.5"
        runtime ":twitter-bootstrap:2.0.2.25"

        build ":tomcat:$grailsVersion"
    }
}
