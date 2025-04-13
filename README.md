# BookStoreAPI

A backend-only Bookstore API built using Spring WebFlux and Project Reactor, providing essential RESTful operations for managing a bookstore. This project allows users to perform various actions on books such as adding, removing, updating, deleting, and searching.

## Features

- **Add Books**: Add new books to the bookstore.
- **Remove Books**: Delete a book from the bookstore.
- **Update Books**: Update the details of an existing book.
- **Search Books**: Search for books based on specific criteria.
- **REST APIs**: Exposes several RESTful endpoints for CRUD operations.
- **MySQL Database**: Utilizes MySQL for storing book data.
- **Postman Tested**: All endpoints are tested using Postman for verifying the functionality.

## Technologies Used

- **Spring WebFlux**: Reactive programming framework that utilizes Project Reactor.
- **MySQL**: Relational database used for storing and managing book data.
- **Postman**: Used for testing the REST APIs.

## Getting Started

### Prerequisites

- Java 11 or higher
- MySQL
- Postman for API testing

### Clone the repository

```bash
git clone https://github.com/your-username/bookstore-api.git
```

## Set up the MySQL Database
Create a MySQL database for the project.

## sql

CREATE DATABASE bookstore;
Configure the database connection in application.properties:

## properties

spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
spring.datasource.username=root
spring.datasource.password=yourpassword
Run the Application
Navigate to the project directory and run the application:

bash
```
./mvnw spring-boot:run
```
The application will start on http://localhost:8080.

## API Endpoints

Here are the main RESTful API endpoints:

POST /api/books: Add a new book.

GET /api/books: Retrieve a list of all books.

GET /api/books/{id}: Retrieve a book by its ID.

PUT /api/books/{id}: Update an existing book by its ID.

DELETE /api/books/{id}: Delete a book by its ID.

GET /api/books/search?name={name}: Search for books by name.

## Testing with Postman
You can import the Postman collection for testing all the above API endpoints. Ensure you have the application running before testing.

## Future Enhancements

Security: Integration of authentication and authorization to secure API endpoints.

Frontend: Add a user interface to interact with the API.

Advanced Search: Implement more advanced search features, such as filtering by category, author, and price.

## License

Feel free to modify any sections according to your specific project details!






