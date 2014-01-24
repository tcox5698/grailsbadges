Feature: cucumber spike
	As a product developer
	I want to create acceptance tests that will be executed automatically
	So I can trust that the product features work as we enhance the product
	
Scenario: ask for 2 cucumbers
	Given I have no cucumbers
	When I ask for 2 cucumbers
	Then I get 2 cucumbers

Scenario: ask for 3 cucumbers
	Given I have no cucumbers
	When I ask for 3 cucumbers
	Then I get 3 cucumbers

Scenario Outline: ask for a number of cucumbers
	Given I have no cucumbers
	When I ask for <some> cucumbers
	Then I get <that> number of cucumbers

Examples: some
	| some	| that	|
	| 0		| 0		|
	| 2		| 2		|
	| -1	| -1	|
		
