# GoRest API Automation Framework

This project is a Java-based API automation framework designed to test the [GoRest API](https://gorest.co.in/). It leverages popular tools and libraries to provide a robust and maintainable solution for API testing.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [How to Run Tests](#how-to-run-tests)
- [Project Structure](#project-structure)
- [Future Enhancements](#future-enhancements)

## Features

*   **API Testing:** Comprehensive testing of GoRest API endpoints.
*   **CRUD Operations:** Automated tests for Create, Update, and Delete operations on user resources.
*   **Verification:** Includes verification steps to ensure resources are created, updated, and deleted as expected.
*   **Data-Driven Testing:** Utilizes TestNG's `@Factory` and `@DataProvider` to run tests with multiple sets of data.
*   **Realistic Test Data:** Integrates the [Java Faker](https://github.com/DiUS/java-faker) library to generate realistic and varied test data (names, emails, etc.).
*   **Maven Project:** Managed with Apache Maven for easy dependency management, build, and test execution.
*   **TestNG Framework:** Uses TestNG for test organization, execution, and reporting.

## Prerequisites

Before you can run this project, ensure you have the following installed:

*   **Java Development Kit (JDK) 11 or higher:** [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
*   **Apache Maven:** [Download Maven](https://maven.apache.org/download.cgi)
*   **GoRest API Access Token:** You need a personal access token from the GoRest API.
    *   Visit [https://gorest.co.in/](https://gorest.co.in/).
    *   Register or log in to obtain your access token.

## Setup Instructions

1.  **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    cd GoRestAPIAutomation
    ```

2.  **Configure GoRest API Token:**
    *   Create a new file named `config.properties` inside the `src/test/resources/` directory.
    *   Add the following line to the `config.properties` file, replacing `YOUR_PERSONAL_ACCESS_TOKEN_HERE` with the actual token you obtained from GoRest:
        ```properties
        gorest.token=YOUR_PERSONAL_ACCESS_TOKEN_HERE
        ```
    *   **Security Note:** The `config.properties` file is typically added to `.gitignore` to prevent committing sensitive API tokens to version control. If you intend to share your repository publicly, ensure this file is not committed with your actual token.

## How to Run Tests

Navigate to the root directory of the project in your terminal and execute the following Maven command:

```bash
mvn clean test
```

This command will:
*   Clean the project's build artifacts.
*   Compile the source code and test code.
*   Execute all TestNG tests defined in `testng.xml`.

Test results and reports will be generated in the `target/surefire-reports/` and `test-output/` directories.

## Project Structure

*   `pom.xml`: Maven Project Object Model file, defining dependencies and build configurations.
*   `src/main/java`: Contains main application code (if any, currently empty).
*   `src/test/java/basics`: Contains the core TestNG test classes (`Base.java`, `BasicAPITests.java`, `User.java`).
*   `src/test/java/data`: Contains data provider classes (`UserFactoryData.java`) for test data generation.
*   `src/test/resources`: Contains configuration files (`config.properties`) and test data.
*   `testng.xml`: TestNG suite configuration file, defining which tests to run and their execution order.
*   `target/`: Directory for compiled classes and build artifacts (ignored by Git).
*   `test-output/`: Directory for TestNG reports (ignored by Git).

## Future Enhancements

Consider adding the following features to further enhance the framework:

*   **Comprehensive GET/Read Operations:** Implement tests for retrieving all users, negative scenarios for getting non-existent users, and search/filter functionalities.
*   **Negative Test Scenarios:** Expand tests to cover invalid data inputs, unauthorized access attempts, and duplicate user creation scenarios.
*   **External Data Sources:** Integrate reading test data from external files (e.g., CSV, JSON) for more flexible data management.
*   **Advanced Reporting:** Integrate advanced reporting tools like ExtentReports or Allure Reports for more detailed and interactive test results.
*   **API Chaining/Workflow Tests:** Develop complex test scenarios involving multiple API calls to validate end-to-end business workflows.
*   **API Client Layer:** Refactor the API interaction into a dedicated client layer for better code organization and reusability.
*   **Response Schema Validation:** Implement JSON Schema validation to ensure API responses conform to expected structures.
*   **Configuration Management:** Externalize more configurations into environment-specific files.
