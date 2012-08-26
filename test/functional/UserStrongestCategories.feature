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
	
Scenario: Multiple achievements in more than 5 categories.
	Given I have the following achievements
	| Name 			| SkillLevelMultiplier 	| 	Category 		|
	| Read Java code 	| 1						|	Java 	|	
	| Saw Javascript Presentation 	| 1						|	JavaScript 	|		
	| Examined database schema | 1			|   Database		|
	| Designed Car 	| 4						|	Engineering 	|
	| Built Car		| 1						|	Manufacturing 	|
	| Recycled Car	| 1						|	Manufacturing 	|
	| Cleaned Car 	| 3						|	Service 		|
	| Fixed Car		| 3						|	Service	 		|
	| Delivered Car	| 3						|	Delivery 		|
	| Marketed Car	| 1						|	Marketing 		|
	| Sold Car 	    | 5						|	Sales 			|
	When I display my strongest categories	
	Then I see the following in the chart
	| Category			| SkillPoints	|
	|	Manufacturing 	|	2			|
	|	Delivery 		|	3			|
	|	Engineering 	|	4			|
	|	Sales	 		|	5			|
	|	Service 		|	6			|	
	
Scenario: New user with no achievements	
	Given I am a new user with no achievements
	When I display my strongest categories
	Then I see the following in the chart
	| Category			| SkillPoints	|
	| No Categorized Achievements | 0   |