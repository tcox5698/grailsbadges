Feature: Categories are suggested to user
	As a User
	I want existing Categories to be suggested to me
	So that associating a category is consistent and convenient
	
Scenario: type first characters of existing category
	Given the category "Java" exists
	When I type "J"
	Then the application suggests "Java" 