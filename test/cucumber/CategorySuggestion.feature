Feature: Categories are suggested to user
	As a User
	I want existing Categories to be suggested to me
	So that associating a category is consistent and convenient
	
Scenario: type first characters of existing category
	Given the category "Java" exists
	When I type "J"
	Then the application suggests "Java" 
	
Scenario: typing more reduces suggested Categories
	Given the following categories exist
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type "JavaSc"
	Then the application suggests "JavaScript"
	
Scenario: suggests categories that contain all letters typed
	Given the following categories exist
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type "JvS"
	Then the application suggests "JavaScript"
		
Scenario: suggest nothing when no matches on single letter
	Given the following categories exist
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type "x"
	Then the application suggests nothing
	
Scenario: suggest multiple matches
	Given the following categories exist
	| Java		| 
	| Database 	| 	
	| JavaScript|
	When I type "v"
	Then the application suggests the following	
	| Java		| 
	| JavaScript|
	
Scenario: suggest multiple matches for achievement names
	Given the following categories exist
	| Java		| 
	| Database 	| 	
	| JavaScript|
	When I type "checked in java code with refactoring and pretty flowers"
	Then the application suggests the following	
	| Java		| 	
	| JavaScript|	
	
Scenario: suggest nothing when no matches achievement name
	Given the following categories exist
	| Java		| 
	| Database Development		| 	
	| JavaScript|
	When I type "smacked john upside the head so hard his cousin fell down"
	Then the application suggests nothing	