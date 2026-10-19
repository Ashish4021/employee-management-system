Employee Management System

A RESTful backend application built using Java, Spring Boot, and MySQL to manage employee records. This project demonstrates CRUD operations, request validation, pagination, sorting, automated testing, API documentation, and application logging.

Features

- Employee CRUD Operations — Create, retrieve, update, and delete employee records.
- Request Validation — Validate incoming request data using Bean Validation annotations.
- Pagination — Retrieve employee records in manageable pages.
- Sorting — Sort employee records based on supported fields and directions.
- Global Exception Handling — Handle application errors and provide meaningful error responses.
- Database Integration — Persist employee data using MySQL and Spring Data JPA.
- API Documentation — Document and explore REST endpoints using OpenAPI and Swagger UI.
- Application Logging — Record application events and diagnostic information using logging.
- Automated Testing — Test application behavior using JUnit.
- Integration Testing — Verify interactions between application components and their integrations.

Technologies Used

Technology| Purpose
Java| Backend programming language
Spring Boot| Application framework
Spring Web| REST API development
Spring Data JPA| Database access and persistence
Hibernate| ORM implementation
MySQL| Relational database
Maven| Dependency management and build
Bean Validation| Request validation
JUnit| Automated testing
Integration Testing| Verify component interactions
OpenAPI| REST API specification
Swagger UI| Interactive API documentation
Logging| Application diagnostics
Git and GitHub| Version control and source code hosting

Project Structure

Employee-Management-System/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ... application packages
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md

Prerequisites

Make sure you have the following installed:

- Java JDK
- MySQL Server
- IntelliJ IDEA or another Java IDE
- Git

Setup and Installation

1. Clone the Repository

git clone https://github.com/Ashish4021/employee-management-system.git


2. Open the Project

Open the cloned project in IntelliJ IDEA and allow Maven to load the dependencies.

3. Configure MySQL

- Start your MySQL server.
- Create a database for the application.
- Configure the database URL, username, and password in your local "application.properties" file.


4. Run the Application

Run the main Spring Boot application class from IntelliJ IDEA.

Alternatively, on Windows, use the Maven Wrapper:

mvnw.cmd spring-boot:run

API Operations

HTTP Method| Operation
POST| Create an employee
GET| Retrieve all employees
GET| Retrieve an employee by ID
PUT| Update an employee
DELETE| Delete an employee


Pagination and Sorting

The employee listing endpoint supports pagination and sorting.

Pagination helps retrieve records page by page, while sorting organizes results using a supported field and direction.

Check the actual controller parameters for the supported page number, page size, sort field, and sort direction.

API Documentation

The application uses OpenAPI and Swagger UI to document and explore REST API endpoints interactively.

Once the application is running, open the Swagger UI URL configured by the project. A common default is:

"http://localhost:8080/swagger-ui/index.html"

The exact URL depends on the project's configuration and dependencies.

Testing

The project includes:

- JUnit Tests — Verify application logic and expected behavior.
- Integration Tests — Check interactions between application components and configured dependencies.

Run the test suite using the Maven Wrapper on Windows:

mvnw.cmd test

Logging and Exception Handling

- Application logging helps record events and diagnose problems.
- Global exception handling provides consistent error responses.
- Request validation helps reject invalid input.

Learning Outcomes

This project demonstrates practical experience with:

- Java backend development
- Spring Boot REST API design
- CRUD operations with MySQL and JPA
- Request validation and exception handling
- Pagination and sorting
- JUnit and integration testing
- OpenAPI and Swagger UI
- Application logging
- Git and GitHub workflows

Author

Java Backend Development Project
