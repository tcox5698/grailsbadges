Feature: Display User's strongest categories
	As a User
	I want to see a chart of my strongest categories
	So that I can make better decisions about what to achieve next
	
Scenario: One achievement in one category
	Given I have achievement "Built Car" in category "Manufacturing"
	When I display my strongest categories
	Then I see "Manufacturing"
