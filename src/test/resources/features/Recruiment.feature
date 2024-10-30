Feature: Recruitment process
     as user is logged into the OrangeHRM system
     for register the information required

  @Recruitment
  Scenario: Successfully hiring a new candidate
    Given the user is on the Recruitment page
    When the user clicks the Add button to start the hiring process
    And the user fills out the candidate's information with the following details:
      | firstName | middleName | lastName | vacancy              | email                | contactNumber  | resumePath            |
      | Oscar     | Andres     | Roa      | Payroll Administrator| oscarandres@gmail.com| 453465464347   | path/to/resume.pdf    |
    Then the candidate should be listed under the Recruitment section
    Then the candidate {string} should be listed under the Recruitment section with status
