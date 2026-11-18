# Scaler Capstone Backend Project [E-Commerce]

A scalable, robust Spring Boot backend application designed for deployment on AWS. This project serves as the core API service, featuring a modular architecture and built with the latest enterprise technology stack.

## 🚀 Tech Stack
*   **Language:** Java 17+
*   **Framework:** Spring Boot 3 (REST APIs, Data JPA)
*   **Database:** MySQL (Configured for AWS RDS in production)
*   **API Documentation:** SpringDoc OpenAPI (Swagger UI)
*   **Build Tool:** Maven
*   **Cloud (Planned):** AWS (EC2/ECS for hosting, RDS for DB, SES for emails)
*   **Architecture:** Layered (Controller, Service, Repository, Entity, DTO)

---

## 📦 Project Structure
```text
project.scaler.capstone
 ┣ 📂 config        # Application configurations (e.g., OpenAPI/Swagger config)
 ┣ 📂 controller    # REST API endpoints
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

**2. Product Catalog**
*   **Browsing:** Retrieve all products or filter products by specific categories.
*   **Product Details:** View detailed pages with images, prices, descriptions, and specifications.
*   **Search:** Search for products using keywords matching the product name or description.

**3. Cart & Checkout**
*   **Add to Cart:** Add products to the user's cart (updates quantity if already present).
*   **Cart Review:** View selected items, individual item totals, and the comprehensive cart total.
*   **Checkout:** Finalize the purchase with a delivery address and payment method, generating an order reference.

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

**3. Run the Application:**
```bash
git clone <your-repository-url>
cd <your-project-folder>
./mvnw clean install -U
./mvnw spring-boot:run
```
*The server will start on `http://localhost:8080`.*

---

## 📚 API Documentation (Swagger)

This project uses SpringDoc OpenAPI to automatically generate interactive API documentation.

Once the application is running, you can access the documentation here:
*   **Swagger UI (Interactive Console):** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
*   **OpenAPI JSON Specification:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📖 API Endpoints Summary

**Base URL:** `http://localhost:8080/api/v1`

### Authentication
*   `POST /auth/register` - Register a new user
*   `POST /auth/login` - User login
*   `POST /auth/forgot-password` - Generate password reset token
*   `POST /auth/reset-password` - Reset password securely

### User Profile
*   `GET /users/{userId}/profile` - Get user profile
*   `PUT /users/{userId}/profile` - Update user profile

### Product Catalog
*   `GET /products` - Get all products
*   `GET /products/{id}` - Get product details by ID
*   `GET /products/category/{categoryId}` - Get products by category ID
*   `GET /products/search?keyword={keyword}` - Search products by keyword

### Cart & Checkout
*(Note: Cart endpoints require a `UserId` header until JWT integration is complete)*
*   `POST /cart/add` - Add item to cart
*   `GET /cart` - View current cart and totals
*   `POST /cart/checkout` - Checkout and place order