Feature: Achievements are suggested to user
	As a User
	I want existing Achievements to be suggested to me
	So that naming an Achievement is consistent and convenient
	
Scenario: type first characters of existing achievement
	Given the achievement "Built application using gradle" exists
	When I type "B"
	Then the application suggests "Built application using gradle" 
	
Scenario: typing more reduces suggested achievements
	Given the following achievements exist
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type "JavaSc"
	Then the application suggests "JavaScript"
		
Scenario: suggest nothing when no matches on single letter
	Given the following achievements exist
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type "x"
	Then the application suggests nothing
	
Scenario: suggest multiple matches
	Given the following achievements exist
	| Java		| 
	| Database 	| 	
	| JavaScript|
	When I type "v"
	Then the application suggests the following	
	| Java		| 
	| JavaScript|
	
Scenario: suggest nothing when no matches achievement name
	Given the following achievements exist
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type "smacked john upside the head so hard his cousin fell down"
	Then the application suggests nothing	