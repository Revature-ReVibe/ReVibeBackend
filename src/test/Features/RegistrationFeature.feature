#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@reg
Feature: Registration

	@reg1
	Scenario: A user wants to register for the site with valid information
		Given A user is visiting the the login page
		When A user clicks on the "create new account" button
		Then The user is redirected to the "registration page"
		
	@reg2
	Scenario: A user want to fill out the registration form
	Given A user is on the registration page
	When a user enter a valid "name", "username", "password", "email address" and "profile picture"
	And a user clicks "submit"
	Then a user will be rerouted to the "login page"
