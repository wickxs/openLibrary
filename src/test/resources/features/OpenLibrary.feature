@openLibrary
Feature: OpenLibrary test

  Scenario Outline: Get book by title and year published
    Given User goes to the OpenLibrary page
    And User sets website in "<language>"
    When User searches using Title option for "<book>"
    And User chooses book published in "<year>"
    And Get author from API
    Then Author from API matches author on book page
    Examples:
      | language | book                                     | year |
      | en       | Harry Potter and the Prisoner of Azkaban | 1633 |