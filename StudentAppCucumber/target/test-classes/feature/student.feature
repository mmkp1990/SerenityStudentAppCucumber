Feature: Testing different requests on the Student Application

@Smoke
Scenario: Check if the student application can be accessed by users
When User sends a GET request to the list endpoint, they must get back a valid status code 200

Scenario Outline: Create a new student and verify that the student has been added
When I create a new student by providing the information firstName <firstName>, lastName <lastName>, email <email>, programme <programme> and courses <courses>
Then I verify that the student with email containing <email> is created

Examples:
| firstName | lastName | email                | programme | courses |
| Tim       | Merlin   | TestMerlin@gmail.com | ICT       | Java    |
| Janet     | Bee      | TestBee@gmail.com    | Computing | SCALA   |