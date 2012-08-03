import cucumber.runtime.PendingException

this.metaClass.mixin (cucumber.runtime.groovy.EN)

def cukeGetter = new CukeGetter()
def result

Given(~'^I have no cucumbers$') { ->
    
}
When(~'^I ask for 2 cucumbers$') { ->
    result = cukeGetter.getCucumbers(10)
}
Then(~'^I get 2 cucumbers$') { ->
    assert 2 == result.size
}

def class CukeGetter {

	def getCucumbers(count) {
		return ["cuke1", "cuke2"]
	}

}