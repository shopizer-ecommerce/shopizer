#Author: anna@kry.nu
@selenium3
Feature: Labb4_3_AddToCart
	In order to buy
	As a user 
	I want to product be placed  in cart

Scenario: AddToChart
Given user is on productpage "Spring-in-Action.html"
When user adds to cart
Then product is placed in cart


