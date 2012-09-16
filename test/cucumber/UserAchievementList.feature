Feature: Display User's complete list of achievements
	As a User
	I want to see a list of all my achievements
	So that I can see the details behind my scores.

@notxn
Scenario: User has one achievement.
	Given I have logged in 
	And I have the following achievements
	| Date 		| Name 			| SkillLevelMultiplier 	| 	Category 		|
	| 5/27/12 	| Designed Car 	| 3						|	Engineering 	|
	When I display my achievement list
	Then I see the following in the list
	| Date 		| AchievementName 	| Categories 	| SkillLevelName 	| Multiplier 	|
	| 5/27/12 	| Designed Car 		| 	Engineering | SkillLevel3 		| 3	 			|
