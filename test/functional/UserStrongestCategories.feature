Feature: Display User's strongest categories on the dashboard
	As a User
	I want to see a chart of my strongest categories on the dashboard
	So that I can make better decisions about what to achieve next

Scenario: Two achievements in two categories shows me both categories on the Dashboard.
	Given I have the following achievements
	| Name 			| SkillLevelMultiplier 	| 	Category 		|
	| Designed Car 	| 3						|	Engineering 	|
	| Built Car		| 1						|	Manufacturing 	|
	When I display my strongest categories
	Then I see the following in the chart
	| Category		| SkillPoints	|
	| Manufacturing	| 1				|
	| Engineering  	| 3				|
	
Scenario: Multiple achievements in more than 5 categories shows me the top 5 categories on the Dashboard in order of Strength and then Alphabetically.
	Given I have the following achievements
	| Name 			| SkillLevelMultiplier 	| 	Category 		|
	| Designed Car 	| 3						|	Engineering 	|
	| Built Car		| 1						|	Manufacturing 	|
	| Recycled Car	| 1						|	Manufacturing 	|
	| Cleaned Car 	| 3						|	Service 		|
	| Fixed Car		| 1						|	Service	 		|
	| Delivered Car	| 3						|	Delivery 		|
	| Marketed Car	| 1						|	Marketing 		|
	| Sold Car 	    | 3						|	Sales 			|
	When I display my strongest categories	
	Then I see the following in the chart
	| Category			| SkillPoints	|
	|	Manufacturing 	|	2			|
	|	Delivery 		|	3			|
	|	Engineering 	|	3			|
	|	Sales	 		|	3			|
	|	Service 		|	4			|	
	
	