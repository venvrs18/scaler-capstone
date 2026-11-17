# Scaler Capstone Backend Project [E-Commerce]

A scalable, robust Spring Boot backend application designed for deployment on AWS. This project serves as the core API service, featuring a modular architecture and built with the latest enterprise technology stack.

## 🚀 Tech Stack
*   **Language:** Java 17+
*   **Framework:** Spring Boot 3 (REST APIs, Data JPA)
*   **Database:** MySQL (Configured for AWS RDS in production)
*   **Build Tool:** Maven / Gradle
*   **Cloud (Planned):** AWS (EC2/ECS for hosting, RDS for DB, SES for emails)
*   **Architecture:** Layered (Controller, Service, Repository, Entity, DTO)

---

## 📦 Project Structure
```text
project.scaler.capstone
 ┣ 📂 controller    # REST API endpoints (e.g., AuthController, UserController)
 ┣ 📂 service       # Business logic and transaction management
 ┣ 📂 repository    # Spring Data JPA interfaces for DB operations
 ┣ 📂 model         # JPA Entities representing database tables
 ┗ 📂 dto           # Data Transfer Objects (using Java Records)
```

---

## ✨ Features

**1. User Management**
*   **Registration:** Create user accounts with duplicate email validation.
*   **Login:** Authenticate users with email and password.
*   **Profile Management:** Fetch and update user profile details.
*   **Password Reset:** Generate secure reset tokens and update passwords.

---

## ⚙️ Setup and Installation

**1. Prerequisites:** 
Java 17+, MySQL Server, and Maven.

**2. Database & Configuration:**
Create a MySQL database (e.g., `capstone_db`). Update your `src/main/resources/application.properties` with your credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/capstone_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

## 📖 API Endpoints 

**Base URL:** `http://localhost:8080/api/v1`

### Authentication
*   **Register:** `POST /auth/register` 
    *   *Body:* `{ "email", "password", "firstName", "lastName" }`
*   **Login:** `POST /auth/login` 
    *   *Body:* `{ "email", "password" }`
*   **Forgot Password:** `POST /auth/forgot-password` 
    *   *Body:* `{ "email" }`
*   **Reset Password:** `POST /auth/reset-password` 
    *   *Body:* `{ "token", "newPassword" }`

### User Profile
*   **Get Profile:** `GET /users/{userId}/profile`
*   **Update Profile:** `PUT /users/{userId}/profile` 
    *   *Body:* `{ "firstName", "lastName" }`