# Banking Services Application

A backend REST API application built using Java, Spring Boot, Spring Data JPA, Hibernate, and PostgreSQL to manage customers, bank accounts, financial transactions, and money transfers.

## 🚀 Technologies Used

- **Java** – Programming language
- **Spring Boot** – Backend application development
- **Spring Web** – REST API development
- **Spring Data JPA** – Database operations
- **Hibernate** – ORM (Object-Relational Mapping)
- **PostgreSQL** – Relational database
- **Maven** – Project and dependency management
- **IntelliJ IDEA** – Development environment
- **Git & GitHub** – Version control

## ✨ Features

### Customer Management
- Create a new customer
- Retrieve all customers
- Retrieve a customer by ID
- Update customer details
- Delete a customer

### Account Management
- Create a bank account for an existing customer
- Retrieve all accounts
- Retrieve an account by ID
- Update account details
- Delete an account
- Retrieve transactions associated with an account

### Transaction Management
- Create deposit transactions
- Create withdrawal transactions
- Retrieve all transactions
- Retrieve a transaction by ID
- Retrieve transactions by account ID
- Maintain account balances after transactions
- Validate transaction amounts and available balances

### Money Transfer
- Transfer money between bank accounts
- Validate transfer amounts and available balances
- Update account balances based on the transfer
- Maintain transaction records for transfers

### Exception Handling
- Handle resource-not-found errors
- Validate transaction requests
- Return appropriate HTTP status codes and error messages

## 🏗️ Project Architecture

The application follows a layered architecture to separate responsibilities.

- **Controller Layer** – Handles HTTP requests and API responses.
- **Service Layer** – Implements business logic and transaction processing.
- **Repository Layer** – Performs database operations using Spring Data JPA.
- **Entity Layer** – Represents database tables and relationships.
- **DTO Layer** – Transfers request and response data between API clients and the application.

## 🗄️ Database

PostgreSQL is used to store customer, account, and transaction information.

**Database name:** `banking_services`

Spring Data JPA and Hibernate manage entity persistence and database interactions.

## ⚙️ Getting Started

### Prerequisites

Make sure you have installed:

- Java JDK
- Maven
- PostgreSQL
- IntelliJ IDEA or another Java IDE

### 1. Clone the repository

```bash
git clone https://github.com/smdrafi765/banking-services-application.git
```

### 2. Open the project

Open the cloned project in IntelliJ IDEA or your preferred Java IDE.

### 3. Configure PostgreSQL

Create a PostgreSQL database named:

```sql
CREATE DATABASE banking_services;
```

Configure your database connection in `src/main/resources/application.properties` using your local PostgreSQL credentials. Keep passwords and other sensitive credentials out of version control.

### 4. Run the application

Run the main Spring Boot application class from your IDE, or use the Maven wrapper.

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

### 5. Test the REST APIs

Use Postman or another API client to send HTTP requests to the running application.

Base URL (assuming the default Spring Boot port):

```text
http://localhost:8080
```

## 🔌 API Modules

| Module | Operations |
|---|---|
| Customers | Create, retrieve, update, delete |
| Accounts | Create, retrieve, update, delete |
| Transactions | Create deposits and withdrawals, retrieve transaction records |
| Money Transfers | Transfer money between accounts |
| Account Transactions | Retrieve transactions for a specific account |

Refer to the controller classes in the source code for the exact endpoint paths and request formats.

## 🎯 Project Objective

The objective of this project is to apply Java backend development concepts by building REST APIs, implementing business logic, managing relational database entities, and handling financial transactions using Spring Boot and PostgreSQL.

## 👨‍💻 Author

**Shaik Mahammed Rafi**

GitHub: [smdrafi765](https://github.com/smdrafi765)

Repository: [Banking Services Application](https://github.com/smdrafi765/banking-services-application)
