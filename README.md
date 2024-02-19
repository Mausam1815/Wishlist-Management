# Wishlist-Management
This is a Spring Boot application for managing wishlists. Users can add, view, and delete items from their wishlist.

**Swagger UI Documentation** -> http://localhost:8080/swagger-ui/index.html

## Project Structure
The project follows a standard Spring Boot application structure with the following main components:

- **`DTOs (Data Transfer Objects)` :** Represent data exchanged between the client and the server.
- **`Entities`:** JPA entities representing database tables.
- **`Repositories` :** Spring Data JPA repositories for CRUD operations on entities.
- **`Services` :** Service classes containing business logic.
- **`Controllers` :** Spring MVC controllers for handling HTTP requests.
- **`Exceptions` :** Custom exception classes.
- **`JWT (JSON Web Token) Configuration` :** Configuration for JWT-based authentication.
- **`Global Exception Handler` :** Centralized exception handling for the application.
- **`Security Configuration` :** Configuration for Spring Security.

## How to Run

1. Clone the repository. For help [click here](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository).
2. Make sure you have MySQL installed and running.
3. Set up the database.
4. Update the `application.properties` file with your MySQL database credentials.
5. Build the project using Maven: `mvn clean install`.
6. Run the application: `WishlistManagementApplication.java`.

## Tech Stack
- `Java` -> Primary Programming language used for backend development.
- `Spring Boot` ->  A powerful framework for building Java-based applications, providing features such as dependency injection, web MVC, security, and more.
- `JPA` -> Provides an easy and efficient way to interact with relational databases using Java Persistence API (JPA).

- `Spring Security` -> A highly customizable authentication and access control framework for securing Spring-based applications. In this project, it's used for implementing JWT-based authentication.

- `MySQL` -> Relational Database Management System used for storing user information and wishlist data.

- `JSON Web Tokens (JWT)` -> A compact, URL-safe means of representing claims to be transferred between two parties. JWTs are used for stateless authentication in the application.

- `Lombok` -> It is used to reduce boilerplate code by generating getters, setters, constructors, and other repetitive code.

- `ModelMapper` -> A convenient library used for mapping DTOs (Data Transfer Objects) to entity classes and vice versa.

- `Swagger` -> Used for API documentation and testing. It generates interactive API documentation directly from your Spring Boot application.

- `JUnit` and `Spring Test` -> Libraries used for unit testing and integration testing, ensuring the reliability and correctness of the application.

- `Maven` -> A build automation tool used for managing dependencies and building the project.

## Functionality
1. **`User Management`**
- Users can register with their name, email and password.
- Passwords are securely hashed using Spring Security's `PasswordEncoder`.

2. **`Wishlist Management`**
- Users can add items to their wishlist by providing a name, description, and price.
- Users can view their wishlist, which displays all items they have added.
- Users can delete items from their wishlist.

3. **`Authentication and Authorization`**
- JWT-based authentication is used to secure the API endpoints.
- Upon successful login, users receive a JWT token, which they include in subsequent requests for authentication.
- Access to certain endpoints is restricted based on the user's role.

4. **`Exception Handling`**
- Custom exceptions are defined for different error scenarios, such as user not found or item not present in wishlist.
- A global exception handler centrally manages exception handling and maps exceptions to appropriate HTTP responses.
