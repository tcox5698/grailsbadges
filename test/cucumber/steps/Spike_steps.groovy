import cucumber.runtime.PendingException

this.metaClass.mixin (cucumber.runtime.groovy.EN)

def cukeGetter = new CukeGetter()
def result

Given(~/^I have no cucumbers$/) { ->
    
}
When(~/^I ask for (\d+) cucumbers$/) { int arg1 ->
    result = cukeGetter.getCucumbers(arg1)
}
Then(~/^I get (\d+) cucumbers$/) { int arg1 ->
    assert arg1 == result.size
}

When(~/^I ask for -(\d+) cucumbers$/) { int arg1 ->
    result = cukeGetter.getCucumbers(arg1)
}

Then(~/^I get (\d+) number of cucumbers$/) { int arg1 ->
    assert arg1 == result.size
}

Then(~/^I get -(\d+) number of cucumbers$/) { int arg1 ->
    assert arg1 == result.size
}


def class CukeGetter {

	def getCucumbers(count) {
		def cukes = []
	
		for (i in 0..<count) {
			cukes.add("cuke" + i)
		} 
		
		return cukes
	}

}