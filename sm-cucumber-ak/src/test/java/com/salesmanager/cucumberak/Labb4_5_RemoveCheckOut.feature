#Author: anna@kry.nu

@selenium5
Feature: Labb4_5_RemoveCheckOut
	In order to remove product from my cart
	As a user 
	I want my cart to be empty

Scenario: Title of your scenario
Given Product "Spring-in-Action.html" is loaded in cart
When I push remove-button
Then cart will be empty