Feature: cucumber spike
	As a product developer
	I want to create acceptance tests that will be executed automatically
	So I can trust that the product features work as we enhance the product
	
Scenario: first cucumber tests
	Given I have no cucumbers
	When I ask for 2 cucumbers
	Then I get 2 cucumbers
