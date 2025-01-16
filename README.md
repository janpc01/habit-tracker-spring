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
- Lombok
- JUnit 5

## Database Configuration

The application is configured to connect to a PostgreSQL database with the following default settings:

Database name: habitdb
Username: admin
Password: habit
Port: 5432

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
