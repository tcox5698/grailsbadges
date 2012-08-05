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
	results = categoryService.suggestCategories(arg1)
}
Then(~/^the application suggests "([^"]*)"$/) { String arg1 ->
    assert results.size == 1
    assert results[0].name.equals(arg1)
}
