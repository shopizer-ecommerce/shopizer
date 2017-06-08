
#Author: anna@kry.nu
@selenium1
Feature: Labb4_1_gotoHome
IN ORDER TO go to startpage
AS A user
I WANT TO be redirected to startpage when symbol with a house is clicked

Scenario: GoToHome
Given I am on the productpage
When click on the house symbol
Then I will be redirected to startpage
	
