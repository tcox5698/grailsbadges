import cucumber.runtime.PendingException
import com.davai.merit.*

this.metaClass.mixin (cucumber.runtime.groovy.EN)

def CategoryService categoryService
def ObjectService objectService
def results

Given(~/^the category "([^"]*)" exists$/) { String arg1 ->
	categoryService = appCtx.getBean ("categoryService")
	objectService = appCtx.getBean("objectService")
	objectService.save(new Category(name:arg1))
}
When(~/^I type "([^"]*)"$/) { String arg1 ->
	System.out.println("typed: " + arg1)
	results = categoryService.suggestCategories(arg1)
}
Then(~/^the application suggests "([^"]*)"$/) { String expectedSuggestion ->
	assert results.size == 1
	def actualCategory = results.get(0)
	
	assert actualCategory.name == expectedSuggestion
}

Given(~'^the following categories exist$') { Object dataTable ->
	objectService = appCtx.getBean("objectService")

	List<List<String>> rows = dataTable.raw()
	
	for (row in rows) {
		objectService.save(new Category(name:row.get(0)))	
	}
}

Then(~'^the application suggests the following$') { Object dataTable ->
	List<List<String>> rows = dataTable.raw()
	def expectedCats = rows.collect{it ->
		it.get(0)
	}	
	
	def actualCats = results.collect{it -> 
		System.out.println("class: " + it.class.name)
		System.out.println("collection actual name: " + it.name)
		return it.name
	}
	
	System.out.println("got actual cats: " + actualCats)
	
	assert actualCats.containsAll(expectedCats)
	assert expectedCats.containsAll(actualCats)
}


Then(~'^the application suggests nothing$') { ->
    assert results.size == 0
}
