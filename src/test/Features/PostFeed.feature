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

Feature: Actions on the post feed
 


	
  @tag1
  Scenario: Testing Check Vibe feature
  Given A user has logged in with valid credentials for firstUser
  When a user clicks the first check button
  And then goes to profile page
  And returns to the home page
  Then check will go down by one
  
  @tag1
  Scenario: Create a new Vibe
  Given A user has logged in with valid credentials for Firstuser
    When a user clicks the new vibe button
    And then enters a message into the whats up box
    And clicks the add image box
    And enters a image url
    And clicks submit
    Then A new vibe will be created at the bottom of the feed
    
   @tag1
   Scenario: A user wants to respond to a vibe
   Given A user has logged in with valid credentials for firstuser
   	When a user clicks the first vibe
   	And a user clicks the respond button in the corner
   	And then enters a reply in the text box
   	And clicks the image url box
   	And adds a link to a image url
   	And clicks the submit button
   	Then A reply will be created and the user will be back on the homepage
   	
   @tag1
  	Scenario: A user chooses not to create a vibe
  	Given A user has logged in with valid credentials for FirstUser
  	When a user clicks on the new vibe button
  	And then clicks on the x button
  	Then the user will be back on the homepage
  