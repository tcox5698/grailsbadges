import cucumber.runtime.PendingException
import com.davai.merit.*
import com.davai.merit.featuretestutil.*

this.metaClass.mixin (cucumber.runtime.groovy.EN)

def CategoryService categoryService
def ObjectService objectService

Given(~/^the category "([^"]*)" exists$/) { String arg1 ->

	appCtx.getBean ("objectService").save(new Category(name:arg1))
}

When(~/^I type "([^"]*)"$/) { String arg1 ->
	FitContext.results = appCtx.getBean ("categoryService").suggestCategories(arg1)
}

Then(~/^the application suggests "([^"]*)"$/) { String expectedSuggestion ->
	System.out.println("RESULTS:" + FitContext.results)
	def actualSuggestion = FitContext.results.get(0)
	
	assert actualSuggestion.name == expectedSuggestion
}

Given(~'^the following categories exist$') { Object dataTable ->
	List<List<String>> rows = dataTable.raw()
	
	for (row in rows) {
		appCtx.getBean ("objectService").save(new Category(name:row.get(0)))	
	}
}

Then(~'^the application suggests the following$') { Object dataTable ->
	List<List<String>> rows = dataTable.raw()
	def expectedSuggestions = rows.collect{it ->
		it.get(0)
	}	
	
	def actualSuggestions = FitContext.results.collect{it -> 
		return it.name
	}
	
	assert actualSuggestions.containsAll(expectedSuggestions)
	assert expectedSuggestions.containsAll(actualSuggestions)
}


Then(~'^the application suggests nothing$') { ->
    assert FitContext.results.size == 0
}