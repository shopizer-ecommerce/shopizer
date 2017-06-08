#Author: Anna

@selenium4
Feature: Labb4_4_ChangeCheckOut
	In order to change quantity in my cart
	As a user 
	I want number of items to increase

Scenario: ChangeNumberOfItemsInCart
Given Cart is loaded with product "Spring-in-Action.html"
When user increase quantity "300"
Then Quantity be changed

