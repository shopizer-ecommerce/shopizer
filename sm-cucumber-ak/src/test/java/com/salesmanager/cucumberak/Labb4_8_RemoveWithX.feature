#Author: anna@kry.nu

@selenium8
Feature: Labb4_8_RemoveWithX
	In order to change my cart
	As a user 
	I want product to be removed from my cart

Scenario: Title of your scenario
Given Product add to cart "Spring-in-Action.html"
When I remove product in cart with X
Then Cart will have one product less