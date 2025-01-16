# Tempo

A Spring Boot application that provides RESTful API endpoints for user and habit management with PostgreSQL database integration.

## Prerequisites

- Java 21
- PostgreSQL
- Gradle

## Tech Stack

- Spring Boot 3.4.1
- Spring Data JPA
- PostgreSQL
- JUnit 5

## Database Configuration

The application is configured to connect to a PostgreSQL database with the following default settings:

- Database name: habitdb
- Username: admin
- Password: habit
- Port: 5432

## Getting Started

1. Clone the repository
2. Postgresql setup
    - Install postgresql
    ```bash
    brew install postgresql
    ```
    - Start postgresql
    ```bash
    brew services start postgresql
    ```
    - Start psql
    ```bash
    psql postgres
    ```
    - Create user
    ```bash
    CREATE USER admin WITH PASSWORD 'habit';
    ```
    - Create database
    ```bash
    CREATE DATABASE habitdb;
    ```
    - Grant all privileges on the database to the user
    ```bash
    GRANT ALL PRIVILEGES ON DATABASE habitdb TO admin;
    ```
    - Exit psql
    ```bash
    \q
    ```
    - Connect to the database
    ```bash
    psql -d habitdb -U admin -W
    ```
3. Run the application:
```bash
./gradlew bootRun
```
The application will be available at `http://localhost:8080`

## API Endpoints

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/users` | Get all users |
| GET | `/users/{id}` | Get user by ID |
| POST | `/users` | Create a new user |
| PUT | `/users/{id}` | Update an existing user |
| DELETE | `/users/{id}` | Delete a user |
| GET | `/users/exists/{id}` | Check if user exists |

### Request/Response Examples

#### Create User

Curl Request:
```bash
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"email": "user@example.com", "password": "password"}'
```

#### Get User by ID

Curl Request:
```bash
curl http://localhost:8080/users/1
```

## Database Schema
The Postgres JDBC driver uses the URL, username, and password for connecting to your new Postgres database. The dialect is PostgreSQLDialect so Hibernate knows which SQL dialect to use when generating and executing SQL queries. The ddl-auto property sets the behavior of Hibernate’s schema generation tool and has five possible values:


- create – On application start-up, drop all tables managed by Hibernate, then create them from scratch.
- create-drop – On application start-up, create all tables managed by Hibernate. On shutdown, drop all of them.
- update – On application start-up, update the existing tables to match the schema Hibernate expects if necessary.
- validate – On application start-up, check that the existing tables match the schema Hibernate expects, and throw an exception if they do not match.
- none – Do not perform any automatic schema management.


The correct value for spring.jpa.hibernate.ddl-auto depends on your specific use case. You can use create-drop, which cleans up after itself when the Spring Boot application shuts down:
```bash
spring.jpa.hibernate.ddl-auto=create-drop
```
