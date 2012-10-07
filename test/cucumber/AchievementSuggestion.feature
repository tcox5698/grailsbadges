Feature: Achievements are suggested to user
	As a User
	I want existing Achievements to be suggested to me
	So that naming an Achievement is consistent and convenient
	
Scenario: type first characters of existing achievement
	Given the following achievements exist
	|AchievementName |	
	| Built application using gradle |
	When I type achievement name "B"
	Then the application suggests the following	achievements
	| AchievementName |
	| Built application using gradle |
	
Scenario: typing more reduces suggested achievements
	Given the following achievements exist
	|AchievementName |	
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type achievement name "JavaSc"
	Then the application suggests the following	achievements
	|AchievementName |
	| JavaScript |
		
Scenario: suggest nothing when no matches on single letter
	Given the following achievements exist
	|AchievementName |	
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type achievement name "x"
	Then the application suggests no achievements
	
Scenario: suggest multiple matches
	Given the following achievements exist
	|AchievementName |	
	| Java		| 
	| Database 	| 	
	| JavaScript|
	When I type achievement name "v"
	Then the application suggests the following	achievements
	|AchievementName |	
	| Java		| 
	| JavaScript|
	
Scenario: suggest nothing when no matches achievement name
	Given the following achievements exist
	|AchievementName |	
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type achievement name "smacked john upside the head so hard his cousin fell down"
	Then the application suggests no achievements	