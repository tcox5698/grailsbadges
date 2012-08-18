Feature: Display User's strongest categories
	As a User
	I want to see a chart of my strongest categories
	So that I can make better decisions about what to achieve next

Scenario: Two achievements in two categories
	Given I have the following achievements
	| Name 			| SkillLevelMultiplier 	| 	Category 		|
	| Designed Car 	| 3						|	Engineering 	|
	| Built Car		| 1						|	Manufacturing 	|
	When I display my strongest categories
	Then I see the following in the chart
	| Category		| SkillPoints	|
	| Engineering  	| 3				|
	| Manufacturing	| 1				|
	
	