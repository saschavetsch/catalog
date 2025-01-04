# **Catalog Service Documentation**

## Overview

The Catalog Service is a Spring Boot-based application that provides a catalog of books, which can be searched, retrieved, and filtered by various fields like title, author, and description. 
The service is designed to be integrated with other services, such as the Order Service, to provide book data for order creation and management.



## Features
- Book Management: The Catalog Service allows for the creation, retrieval, and management of books in the catalog.
- Search Books: Users can search for books by title, author, or description. The search is case-insensitive and supports partial matching.
- Integration with Order Service: The Catalog Service works in conjunction with the Order Service to provide book details when placing an order.
- CRUD Operations: The service allows performing CRUD operations on book entries.

## Architecture
- Domain Layer: Represents the Book entity with attributes like ISBN, title, author, and description.
- Repository Layer: Provides a data layer for interacting with the database using Spring Data JPA. It allows querying books using different search criteria.
- Service Layer: Contains business logic for book management, including search functionality and data retrieval.
- Controller Layer: Exposes RESTful endpoints for interacting with the book catalog. These endpoints allow for searching, retrieving, and manipulating book data.
- Integration Layer: When integrated with the Order Service, this layer provides the necessary data to support order creation.


# Project Wiki: Book Store

Leitet von den folgenden Anforderungen Epics ab:

- Ein benutzerfreundliches Interface für die Buchsuche und den Kaufprozess
- Eine persönliche Bibliothek für registrierte Nutzer, um gekaufte Bücher zu verwalten
- Empfehlungsalgorithmen, die auf dem Leseverhalten der Nutzer basieren
- Eine sichere und reibungslose Zahlungsabwicklung
- Ein einfach zu bedienendes Backend für die Verwaltung von Büchern, Bestellungen und Nutzern

## Bounded Context for Requirements

###### *Based on the requirements provided, the following bounded contexts can be identified for the book search and purchasing system:*

### User Interface Context

- Responsible for the book search and purchase process.
- Manages user interactions and displays relevant information.

### Personal Library Context

- Manages the personal library of registered users.
- Handles the organization and retrieval of purchased books.

### Recommendation Engine Context

- Analyzes user reading behavior to generate personalized book recommendations.
- Interacts with user data to improve suggestions.

### Payment Processing Context

- Handles secure payment transactions and order processing.
- Ensures compliance with security standards for financial data.

### Backend Management Context

- Manages the administration of books, orders, and user accounts.
- Provides tools for inventory management and reporting.

## Modeling Bounded Contexts

*The following diagram illustrates the bounded contexts and their relationships:*

![image](https://github.com/user-attachments/assets/d8c93b4b-3dd8-4a02-af6f-63369762c9e5)

## Defining Essential Entities

### User Interface Context

###### *Entities:*

- User
- Search Query
- Book Listing

###### Dependencies:

Relies on the Recommendation Engine for personalized suggestions.

### Personal Library Context

###### Entities:

- User Library
- Book Item

###### Dependencies:

- Depends on Backend Management for book details.

### Recommendation Engine Context

###### Entities:

- User Profile
- Reading History
- Recommendations

###### Dependencies:

- Requires data from User Interface and Personal Library contexts.

### Payment Processing Context

###### Entities:

- Payment Transaction
- Order Confirmation

###### Dependencies:

- Interacts with Backend Management for order details.

### Backend Management Context

###### Entities:

- Book Inventory
- User Accounts
- Order Records




###### Dependencies:

- Supports all other contexts by providing necessary data.

# Defining Interactions Between Bounded Contexts (Context Map)

###### *The context map outlines how these bounded contexts interact:*

##### User Interface Context <-->  Personal Library Context

The User Interface requests book details from the Personal Library to display to users.

##### User Interface Context <--> Recommendation Engine Context

The User Interface sends user preferences to the Recommendation Engine to fetch personalized recommendations.

##### User Interface Context <--> Payment Processing Context

The User Interface initiates payment requests and receives transaction confirmations.

##### Backend Management <--> All Other Contexts

Acts as a central hub for data exchange, ensuring that all contexts have access to up-to-date information about users,
books, and orders.

##### Payment Processing <--> Backend Management

Payment processing confirms transactions and updates order records in the Backend Management context.


## Core Components

### Book Entity
The Book entity represents the structure of a book in the catalog, including fields such as ISBN, title, author, and description. This entity is mapped to a database table using JPA annotations and is the primary unit of interaction for both the catalog and the order services.

### Book Repository
The BookRepository interface extends JpaRepository and provides methods for querying books in the catalog. This includes methods to search for books based on fields such as title, author, and description using case-insensitive partial matching.

### Book Service
The BookService contains the business logic for handling book-related operations, including adding new books to the catalog, searching for books, and retrieving book details. It interacts with the repository to perform database operations.

### Book Controller
The BookController exposes the REST API for interacting with the catalog. It provides endpoints for searching books by various fields, retrieving a single book by its ID, and adding new books to the catalog. The controller serves as the interface for other services, such as the Order Service, to retrieve book data.


# CI/CD Pipeline
The Catalog Service integrates with GitHub Actions for continuous integration and continuous deployment (CI/CD). The pipeline ensures that the service is built, tested, and deployed automatically.

1. Code Checkout: The latest code is fetched from the repository.
2. Set up Java: Java 21 is set up for building the application.
3. Cache Maven Dependencies: Maven dependencies are cached to speed up builds.
4. Build the Application: The application is built using Maven.
5. Start the Application: The service is started for end-to-end testing.
6. Run Unit Tests: Unit tests are executed to validate the functionality of the service.
7. Run Load Tests: Load tests are executed to simulate high traffic and ensure the service handles high load.
8. Build and Push Docker Image: Docker image for the Catalog Service is built and pushed to DockerHub.
9. Stop Application: The service is stopped after the tests are completed.
10. Generate Code Coverage Reports: JaCoCo is used to generate code coverage reports.


# Docker-Compose Configuration
The Catalog Service is defined as one of the services in the docker-compose.yml file. It runs on port 8080 and exposes its endpoints through that port. It is configured to interact with the Order Service through the internal network.

# Testing
### Unit Testing
Unit tests for the Catalog Service ensure the correctness of the application's core functionality, such as CRUD operations on books, searching functionality, and interacting with the repository. These tests are automatically executed through the CI/CD pipeline to validate the service before deployment.

### Controller Testing
Controller tests simulate HTTP requests to the BookController and check if the API endpoints return the expected results. These tests cover searching for books, retrieving book details, and adding books to the catalog.

### Load Testing
Load tests are conducted using Gatling to simulate a high volume of search requests. This helps identify any performance issues when the Catalog Service is queried with a large number of requests. The Catalog Service needs to handle these requests efficiently, especially in a production environment where high concurrency is expected.


