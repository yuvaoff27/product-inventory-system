# 📦 Product Inventory System

A modern, responsive **Spring Boot** web application for warehouse asset tracking, secure login and full CRUD operations on a MySQL database.

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)

## 📸 Screenshots

### Login page
![Login page](screenshots/login.png)

### Dashboard
![Dashboard](screenshots/dashboard.png)

### Edit a product
![Edit product](screenshots/edit-product.png)

### Live search
![Live search](screenshots/search.png)

### Delete confirmation
![Delete confirmation](screenshots/delete-confirm.png)

## 🚀 Key Features

- **Full CRUD**: add products, edit existing records and delete them after a confirmation prompt.
- **Secure login**: Spring Security form login with a custom login page and protected pages.
- **Live search**: a JavaScript filter that searches the stock table by name or category as you type.
- **Dark and light theme**: a toggle button switches the whole interface.
- **Automatic schema**: JPA and Hibernate create and update the database tables for you.

## 🛠️ Technology Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Framework | Spring Boot 3.3.4 |
| Security | Spring Security |
| Data access | Spring Data JPA and Hibernate |
| Views | Thymeleaf |
| Database | MySQL |
| Build tool | Maven |

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- MySQL Server running on `localhost:3306`

## ⚙️ Configuration

Open `src/main/resources/application.properties` and set your own database details:

```properties
server.port=8082
spring.datasource.url=jdbc:mysql://localhost:3306/add_a8?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD_HERE

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> 🔒 Never commit your real database password to GitHub. Keep it only on your own computer.

## 🏃 Getting Started

1. Clone the project:
   ```bash
   git clone https://github.com/yuvaoff27/product-inventory-system.git
   cd product-inventory-system
   ```
2. Update `application.properties` with your MySQL password.
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run `App.java` from your IDE, or use:
   ```bash
   mvn spring-boot:run
   ```
5. Open your browser and go to: **http://localhost:8082/**
6. Sign in with the demo account:
   - **Username:** `admin`
   - **Password:** `password123`

> ⚠️ These are demo credentials for local testing only. Change them before using the project anywhere real.

## 📁 Project Structure

```
product-inventory-system
├── src/main/java/School/Student_Application
│   ├── App.java                  # application entry point
│   ├── Product.java              # entity
│   ├── ProductRepository.java    # Spring Data JPA repository
│   ├── ProductController.java    # web controller
│   └── SecurityConfig.java       # Spring Security setup
├── src/main/resources
│   ├── application.properties
│   └── templates                 # Thymeleaf pages
├── screenshots                   # images used in this README
└── pom.xml
```

## 👨‍💻 Author

**Yuvaraj K** · [GitHub](https://github.com/yuvaoff27) · [LinkedIn](https://www.linkedin.com/in/yuvaoff27)
