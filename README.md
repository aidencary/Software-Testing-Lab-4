## The Library Alert System

- Link to the Google Sheet of TCIs and test cases: https://docs.google.com/spreadsheets/d/1Pa1RpECsFbD6hepNs88ODvzf_RYQtkbSSnrxWX08SOs/edit?usp=sharing

## Specification

- Books (and other Library Resources) are identified with UUIDs.
- The checkoutResource function takes a UUID identifying the resource, and a String memberEmail to send an email to the person checking out the book.  The function returns a boolean indicating whether or not the operation was successful.
- If the ResourceID is null, then the function should return false.
- If the Resource is not available in the repository/database (e.g. it is already checked out), then the function should return false.
- If the Book is available, its status should be updated, and an email should be sent to the memberEmail.  Then, the function should return true.
- If either the update status or the sendEmail function fails, then an exception of appropriate type should be thrown.


## Requirements

- Java 21+
- Maven (or included wrapper)

## Test Files

- LibraryService.java: The core application logic.
- LibraryServiceTest: The JUnit 5 implementation utilizing Mockito and ParameterizedTest
- libraryTestCases.csv: Externalized data source containing all test cases.

## Setup

- Clone the project and build it
- Run tests using "./mvnw test" in the terminal or right-click on LibraryServiceTest class and click "Run"

- JaCoCo HTML report should be found in target->site->jacoco->index.html and open it in a browser of your choice
- Use "mvn clean verify" to make sure the tests meet the 100% line coverage requirement
