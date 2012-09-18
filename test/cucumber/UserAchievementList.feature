Feature: Display User's complete list of achievements
	As a User
	I want to see a list of all my achievements
	So that I can see the details behind my scores.

Scenario: User has one achievement.
	Given I have logged in 
	And I have the following achievements
	| Date 		| Name 			| SkillLevelMultiplier 	| 	Category 		|
	| 3/15/12 	| Built Bridge 	| 2						|	Construction 	|	
	| 5/27/12 	| Designed Car 	| 3						|	Engineering 	|
	When I display my achievement list
	Then I see the following in the list
	| Date 		| AchievementName 	| Categories 	| SkillLevelName 	| Multiplier 	|
	| ^today	| You have logged in 1 times! |		|					|				|
	| 5/27/12 	| Designed Car 		| 	Engineering | SkillLevel3 		| 3	 			|
	| 3/15/12	| Built Bridge		|	Construction| SkillLevel2		| 2				|
