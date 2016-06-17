Feature: Groups

  Scenario Outline: Group creation
    Given a set of groups
    When I create a new group with name <name>, header <header> and footer <footer>
    Then the new set of groups is equal to the old set with added group

    Examples:
    | name       | header       | footer       |
    | test name  | test header  | test footer  |
    | test name1 | test header1 | test footer1 |
    | test name2 | test header2 | test footer2 |