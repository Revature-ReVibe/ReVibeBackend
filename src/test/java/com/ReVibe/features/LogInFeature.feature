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
@tag
Feature: Login
  Background: 
  Given A user is on the login page

  @tag1
  Scenario: A user wants to login to the application with correct credentials
   	Given A user is on the login page
    
    When A user enters valid "username" and "password"
   
    Then A user is redirected to the "profilepage"
    

  @tag2
  Scenario Outline: A user wants to login to the application but uses incorrect credentials
  	Given A user is currently on the login page
  	
  	When A user enters a invalid "username" and "password"
  	
  	Then A user is still on the "homepage"