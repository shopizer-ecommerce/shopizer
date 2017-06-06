
@selenium
Feature: Customer registration
	IN ORDER TO store my user information
	AS A Custmer
	I WANT TO be able to register my profile

Scenario Outline: Register new customer
	Given the user is logged out
	When the user register with "<name>"
	Then the user should receive a greeting with "<name>"
	
Examples:
	| name	|
	| Jonas	|
	| Kalle	|
	

