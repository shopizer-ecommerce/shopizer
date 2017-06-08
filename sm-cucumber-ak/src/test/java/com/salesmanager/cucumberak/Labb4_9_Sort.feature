#Author: anna@kry.nu

@selenium9
Feature: Labb4_9_Sort
	In order to sort product by price
	As a user 
	I want products with lowest price to be displayed first

Scenario: Title of your scenario
Given Select product categories "computer-books.html"
When I sort product by price
Then Check first book "Programming for paas"