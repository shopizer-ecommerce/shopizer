#Author: anna@kry.nu
@selenium2
Feature: Labb4_2_login
IN ORDER TO open my useraccount
AS A customer
I WANT TO login

Scenario: login
Given the user is logged out
When the user enters username "annapanna"
Then the user should recive a greeting message with "WELCOME 'ANNA'"



