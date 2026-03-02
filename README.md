# jwebapp-backend

This project serves as the backend for a web application, providing API endpoints for user authentication and data management. It's built using Spring Boot and secured with JSON Web Tokens (JWT).

## Technologies Used

*   Java 21
*   Spring Boot 3.5.0 (Web, Data JPA, Security Starters)
*   Maven
*   PostgreSQL (Database)
*   Spring Security (Authentication and Authorization)
*   JWT (JSON Web Tokens) for API Security
*   Lombok (To reduce boilerplate code)
*   Jakarta Validation (For request payload validation)

## Prerequisites

Before running this application, ensure you have the following installed:

*   Java Development Kit (JDK) 21 or higher
*   Apache Maven 3.x or higher
*   PostgreSQL database instance

## How to Run

1.  **Clone the repository:**

    ```bash
    git clone <repository-url>
    cd jwebapp-backend
    ```

2.  **Configure Database:**

    Update the database connection properties in [`src/main/resources/application.properties`](src/main/resources/application.properties:1) to match your PostgreSQL setup.

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Build the project:**

    ```bash
    mvn clean install
    ```

4.  **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

    The application will start on port 8080 by default.

## API Endpoints

### Authentication

*   **`POST /api/auth/login`**
    *   **Description**: Authenticates a user and returns a JWT token.
    *   **Request Body**: [`LoginRequest`](src/main/java/com/jwebapp/jwebappbackend/payload/request/LoginRequest.java:1) (username, password)
    *   **Response**: [`JwtResponse`](src/main/java/com/jwebapp/jwebappbackend/payload/response/JwtResponse.java:1) (jwt, id, username, email)

*   **`POST /api/auth/register`**
    *   **Description**: Registers a new user.
    *   **Request Body**: [`RegisterRequest`](src/main/java/com/jwebapp/jwebappbackend/payload/request/RegisterRequest.java:1) (username, email, password)
    *   **Response**: Success message.
