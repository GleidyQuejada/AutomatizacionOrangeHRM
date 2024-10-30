Feature: Login Functionality
  As a user
  I want to log into the application
  So that I can access to the account

  @Login
  Scenario: Successful login with valid credentials to OrangeHRM
    Given the user is on the OrangeHRM login page
    When the user enters a valid username and password
    Then the user should be redirected to the home page
