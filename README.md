# Product Solution

**Product Solution** is a dynamic business website and content management application developed using **Java and Spring Boot**.

The application provides a complete website management system where authorized administrators can manage the website's content dynamically without requiring changes to the source code after deployment.

Administrators can manage products, product images, sliders, banners, company information, YouTube content, and other website sections through the admin panel.

The application also provides REST APIs for different application functionalities, secure administrator authentication, image upload and management, email communication through Gmail SMTP, database persistence using PostgreSQL, and API documentation using Swagger/OpenAPI.

---

## 📌 Project Overview

Traditional business websites often require a developer to modify source code whenever the client wants to update:

* Products
* Product information
* Product images
* Website banners
* Slider images
* Company information
* YouTube content
* Other website content

**Product Solution** solves this problem by providing a dynamic administrative interface.

The administrator can log in through the admin panel and manage website content from the application itself.

### Main Concept

```text
Administrator
     │
     ▼
Admin Login
/admin/signin
     │
     ▼
Spring Security
     │
     ▼
Admin Panel
     │
     ├── Manage Sliders
     ├── Manage Banners
     ├── Manage Products
     ├── Manage Product Images
     ├── Manage Company Information
     ├── Manage YouTube Content
     └── Manage Website Content
              │
              ▼
          PostgreSQL
```

Normal users can access the website and consume the dynamically managed content without having administrative access.

---

# 🚀 Key Features

## 1. Dynamic Website Content

The website is designed to be dynamic rather than hard-coded.

Administrators can manage different sections of the website from the admin panel.

Supported content includes:

* Slider images
* Website banners
* Banner information
* Product information
* Product images
* Product categories
* Product descriptions
* Product specifications
* Company information
* YouTube content
* Other website content

This allows the website to be maintained by the administrator without requiring a developer for every content update.

---

## 2. Admin Authentication

The administrator can access the admin panel through:

```text
/admin/signin
```

Authentication and authorization are handled using **Spring Security**.

Passwords are protected using **BCrypt password hashing** instead of storing plain-text passwords.

### Authentication Flow

```text
Admin
  │
  ▼
/admin/signin
  │
  ▼
Spring Security
  │
  ▼
Validate Credentials
  │
  ▼
BCrypt Password Verification
  │
  ▼
Authenticated Admin
  │
  ▼
Admin Panel
```

---

# 🛍️ Product Management

The application provides complete product management functionality.

Administrators can:

* Add products
* Edit products
* Delete products
* Manage product images
* Manage product categories
* Update product descriptions
* Maintain product specifications
* Update product-related information

This allows the product catalog to be maintained dynamically from the admin panel.

### Product Management Flow

```text
Admin
  │
  ▼
Admin Panel
  │
  ├── Add Product
  ├── Edit Product
  ├── Delete Product
  └── Manage Product Image
        │
        ▼
    Spring Boot REST API
        │
        ▼
    PostgreSQL
```

---

# 🖼️ Image Management

One of the important features of Product Solution is dynamic image management.

Images are uploaded by the administrator and stored on the application server, while the corresponding image path/name is stored in PostgreSQL.

### Image Upload Flow

```text
Admin selects image
       │
       ▼
Spring Boot receives MultipartFile
       │
       ▼
Image is saved on local server
       │
       ▼
Image path/name stored in PostgreSQL
       │
       ▼
Frontend retrieves image information
       │
       ▼
Image displayed on website
```

This approach allows administrators to change website and product images without modifying application source code.

---

# 📧 Contact & Suggestion Email

The application provides Contact Us / Suggestion functionality for normal users.

When a user submits the contact or suggestion form, the request is processed by the Spring Boot application and the message is sent through Gmail SMTP.

### Email Flow

```text
User
  │
  ▼
Contact / Suggestion Form
  │
  ▼
REST API
  │
  ▼
Spring Boot
  │
  ▼
Gmail SMTP
  │
  ▼
Company Email
```

This provides a direct communication channel between website visitors and the business.

---

# 🎨 Website Content Management

The administrator can dynamically manage major website sections.

### Slider Management

Administrators can manage slider images and related content displayed on the website.

### Banner Management

Website banners and their associated information can be updated through the admin panel.

### Company Information

Business/company information can be managed dynamically.

### YouTube Section

YouTube-related website content can also be managed through the administration system.

---

# 🔐 Security

The application uses **Spring Security** for securing administrative functionality.

Password security is implemented using **BCrypt**.

### Security Features

* Spring Security
* BCrypt password hashing
* Protected admin functionality
* Authentication-based administrative access
* Separation between administrative and normal-user functionality

> JWT authentication is **not used** in the current implementation.

---

# 🌐 REST API Architecture

The application exposes REST APIs for different areas of functionality.

The API structure is separated according to application responsibilities.

```text
/api/admin/**
/api/user/**
/api/public/**
```

### Admin APIs

```text
/api/admin/**
```

Used for administrative operations such as:

* Product management
* Image management
* Website content management
* Slider management
* Banner management
* Other administrative operations

### User APIs

```text
/api/user/**
```

Used for authenticated/user-related application functionality.

### Public APIs

```text
/api/public/**
```

Used for publicly accessible website functionality and content.

This separation helps keep the API structure organized and makes access control easier to maintain.

---

# 📚 API Documentation

The project uses **Swagger/OpenAPI** for API documentation.

Swagger UI is available at:

```text
/swagger-ui/index.html
```

Swagger allows developers to:

* Explore available APIs
* Understand request/response structures
* Test REST endpoints
* Verify API behavior
* Understand API parameters

---

# ⚠️ Exception Handling

The application uses Spring's exception-handling mechanism through:

```java
@ExceptionHandler
```

Exception handling helps prevent raw application errors from being directly exposed to clients and allows API responses to be handled in a controlled manner.

The application can therefore provide appropriate responses when errors occur during:

* API requests
* Database operations
* Product operations
* Image operations
* Authentication
* Validation
* Other application processes

---

# 📝 Logging

The application uses the standard **Spring Boot logging mechanism** for application logs.

Logging helps during:

* Application startup
* Debugging
* API troubleshooting
* Runtime issue investigation
* Production support
* Error analysis

---

# 🏗️ Technology Stack

| Category                  | Technology          |
| ------------------------- | ------------------- |
| Programming Language      | Java 17             |
| Backend Framework         | Spring Boot 3       |
| Security                  | Spring Security     |
| Password Security         | BCrypt              |
| ORM / Persistence         | Spring Data JPA     |
| Database                  | PostgreSQL          |
| Frontend / Server-side UI | Thymeleaf           |
| API                       | REST APIs           |
| API Documentation         | Swagger / OpenAPI   |
| Validation                | Spring Validation   |
| Email                     | Gmail SMTP          |
| Build Tool                | Maven               |
| Boilerplate Reduction     | Lombok              |
| Server-side Image Storage | Local File System   |
| Logging                   | Spring Boot Logging |
| Exception Handling        | `@ExceptionHandler` |

---

# 🧩 Application Architecture

The application follows a layered Spring Boot architecture.

```text
                    ┌─────────────────────┐
                    │      Browser        │
                    │  Admin / User       │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Thymeleaf UI      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    REST APIs        │
                    │                     │
                    │ /api/admin/**       │
                    │ /api/user/**        │
                    │ /api/public/**     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Controllers     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Services       │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │    Repositories     │
                    │    Spring Data JPA  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     PostgreSQL      │
                    └─────────────────────┘
```

---

# 📁 Major Application Components

A typical request flows through the following layers:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

### Controller Layer

Responsible for:

* Receiving HTTP requests
* Processing API requests
* Returning HTTP responses
* Handling request validation

### Service Layer

Responsible for:

* Business logic
* Application processing
* Coordinating different components

### Repository Layer

Responsible for:

* Database interaction
* CRUD operations
* Entity persistence

### Database Layer

PostgreSQL is used for persistent application data.

---

# 🗄️ Database

The application uses **PostgreSQL** as its primary database.

Database persistence is implemented using:

```text
Spring Data JPA
        ↓
Hibernate / JPA provider
        ↓
PostgreSQL
```

The database stores application information such as:

* Product information
* Product categories
* Product descriptions
* Product specifications
* Image paths/names
* Website content
* Company information
* Other application data

Actual image files are stored on the server, while their relevant path/name information is maintained in the database.

---

# 🔄 Example Product Request Flow

When an administrator creates a product:

```text
Admin
  │
  ▼
Admin Panel
  │
  ▼
Product API
  │
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
PostgreSQL
```

For product image upload:

```text
Admin
  │
  ▼
Select Product Image
  │
  ▼
MultipartFile
  │
  ▼
Spring Boot
  │
  ├──────────────► Local Server
  │                    │
  │                    ▼
  │               Image File
  │
  ▼
PostgreSQL
  │
  ▼
Image Path / Name
```

---

# 🛠️ Local Development

## Prerequisites

Before running the application, install:

* Java 17
* Maven
* PostgreSQL
* Git

Make sure PostgreSQL is running before starting the application.

---

# ⚙️ Configuration

Application configuration should contain the required database and email settings.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<database-name>
spring.datasource.username=<username>
spring.datasource.password=<password>

spring.jpa.hibernate.ddl-auto=update

# Gmail SMTP configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=<gmail-address>
spring.mail.password=<app-password>
```

**Do not commit real database passwords, Gmail passwords, API keys, or other secrets to GitHub.**

Use environment variables or an appropriate secret-management solution for production deployments.

---

# ▶️ Running the Application

Clone the repository:

```bash
git clone <repository-url>
```

Navigate into the project:

```bash
cd product-solution
```

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

After startup, access the application through the configured application URL.

Swagger UI:

```text
/swagger-ui/index.html
```

Admin login:

```text
/admin/signin
```

---

# 🧪 API Testing

REST APIs can be tested using tools such as:

* Swagger UI
* Postman

Swagger provides an interactive way to inspect and test available APIs.

---

# 🔀 Git Workflow

The project can be maintained using Git-based development practices.

Typical workflow:

```text
Developer
   │
   ▼
Git Repository
   │
   ▼
Feature / Bug Fix
   │
   ▼
Commit
   │
   ▼
Push
   │
   ▼
GitHub
```

---

# 🚀 DevOps Roadmap

Docker and DevOps automation are **planned for the next phase** of Product Solution.

The objective is to transform the existing Java/Spring Boot application into a complete production-oriented DevOps project.

### Planned roadmap

```text
Product Solution
       │
       ▼
Docker
       │
       ▼
Docker Compose
       │
       ▼
GitHub Actions
       │
       ▼
CI/CD Pipeline
       │
       ▼
AWS
       │
       ▼
Kubernetes
       │
       ▼
Prometheus
       │
       ▼
Grafana
```

---

# 🐳 Phase 1 — Docker

The first planned step is containerizing the Spring Boot application.

Planned architecture:

```text
Spring Boot Application
          │
          ▼
       Docker
          │
          ▼
    Application Container
```

The goal is to make the application environment-independent and easier to deploy.

Docker Compose can later be used to manage:

```text
Spring Boot Container
        +
PostgreSQL Container
```

---

# 🔄 Phase 2 — CI/CD

GitHub Actions will be introduced for continuous integration and deployment.

Planned pipeline:

```text
Developer Push
      │
      ▼
GitHub
      │
      ▼
GitHub Actions
      │
      ├── Checkout
      ├── Setup Java
      ├── Maven Build
      ├── Run Tests
      ├── Build Docker Image
      └── Deploy
```

This will provide practical experience with automated software delivery.

---

# ☁️ Phase 3 — AWS

The application will eventually be deployed to AWS.

Possible architecture:

```text
                 Internet
                    │
                    ▼
                 AWS
                    │
                    ▼
             Application
                    │
                    ▼
              PostgreSQL
```

AWS deployment will provide hands-on experience with:

* Cloud infrastructure
* Linux servers
* Application deployment
* Networking
* Security
* Monitoring
* Containerized workloads

---

# ☸️ Phase 4 — Kubernetes

After Docker and CI/CD are implemented, Kubernetes will be introduced.

Planned architecture:

```text
                    Kubernetes Cluster
                           │
              ┌────────────┴────────────┐
              │                         │
         Product Solution          Product Solution
             Pod                       Pod
              │                         │
              └────────────┬────────────┘
                           │
                           ▼
                      PostgreSQL
```

Kubernetes will be used to learn:

* Pods
* Deployments
* Services
* ConfigMaps
* Secrets
* Namespaces
* Scaling
* Rolling updates
* Health checks

---

# 📊 Phase 5 — Monitoring

Monitoring will be added using:

```text
Prometheus
     │
     ▼
Metrics
     │
     ▼
Grafana
     │
     ▼
Dashboards
```

The goal is to monitor application and infrastructure health and gain practical experience with production observability.

---

# 🎯 DevOps Learning Objectives

The Product Solution project will be used as a practical learning project for:

### Linux

* Server administration
* Process management
* File permissions
* Networking
* Application troubleshooting
* Log analysis

### Git & GitHub

* Branching
* Pull requests
* Version control
* Collaboration
* Git workflows

### Docker

* Dockerfiles
* Images
* Containers
* Volumes
* Networks
* Docker Compose

### CI/CD

* GitHub Actions
* Automated builds
* Automated testing
* Docker image builds
* Deployment automation

### AWS

* EC2
* IAM
* Networking
* Security
* Cloud deployment

### Kubernetes

* Pods
* Deployments
* Services
* ConfigMaps
* Secrets
* Scaling
* Rolling deployments

### Monitoring

* Prometheus
* Grafana
* Application metrics
* Infrastructure monitoring
* Alerting

---

# 🔐 Production Security Considerations

Before production deployment, the following should be configured securely:

* Database credentials through environment variables
* Gmail credentials through environment variables/secrets
* No passwords committed to Git
* Secure file upload validation
* Proper authorization for admin APIs
* HTTPS
* Secure server configuration
* Database access restrictions
* Proper logging
* Production exception handling
* Backup strategy
* Monitoring and alerting

---

# 📈 Future Improvements

Planned improvements include:

* Docker containerization
* Docker Compose
* CI/CD using GitHub Actions
* AWS deployment
* Kubernetes deployment
* Prometheus monitoring
* Grafana dashboards
* Automated testing
* Production-grade logging
* Centralized configuration
* Improved security
* Automated deployment
* Application health monitoring

---

# 💡 Why This Project Is Valuable

Product Solution is not only a CRUD application.

It demonstrates a complete business application containing:

* Secure administrator authentication
* Dynamic website management
* Product management
* Image upload and storage
* REST API architecture
* PostgreSQL database integration
* Email integration
* Server-side web rendering
* API documentation
* Exception handling
* Application logging

The next DevOps phase will extend the same application into a complete deployment and operations workflow:

```text
Java
  ↓
Spring Boot
  ↓
REST APIs
  ↓
PostgreSQL
  ↓
Linux
  ↓
Docker
  ↓
CI/CD
  ↓
AWS
  ↓
Kubernetes
  ↓
Prometheus
  ↓
Grafana
```

This makes Product Solution a practical project for demonstrating both **Java backend development and DevOps/Cloud skills**.

---

# 👨‍💻 Author

**Deepesh Kumar**

Java Software Engineer
Java | Spring Boot | REST APIs | PostgreSQL | Linux | DevOps

---

# 📌 Project Status

| Area                    | Status        |
| ----------------------- | ------------- |
| Java Backend            | ✅ Implemented |
| Spring Boot             | ✅ Implemented |
| Spring Security         | ✅ Implemented |
| BCrypt                  | ✅ Implemented |
| PostgreSQL              | ✅ Implemented |
| REST APIs               | ✅ Implemented |
| Admin Panel             | ✅ Implemented |
| Product Management      | ✅ Implemented |
| Image Management        | ✅ Implemented |
| Dynamic Website Content | ✅ Implemented |
| Gmail SMTP              | ✅ Implemented |
| Swagger/OpenAPI         | ✅ Implemented |
| Exception Handling      | ✅ Implemented |
| Application Logging     | ✅ Implemented |
| Docker                  | 🚀 Planned    |
| Docker Compose          | 🚀 Planned    |
| GitHub Actions CI/CD    | 🚀 Planned    |
| AWS Deployment          | 🚀 Planned    |
| Kubernetes              | 🚀 Planned    |
| Prometheus              | 🚀 Planned    |
| Grafana                 | 🚀 Planned    |

---

# ⭐ Project Vision

The long-term goal of Product Solution is to evolve the application from a traditional Spring Boot business application into a **containerized, automated, cloud-deployed and monitored production system**.

```text
                PRODUCT SOLUTION
                       │
       ┌───────────────┴───────────────┐
       │                               │
   Application                     DevOps
       │                               │
       ├── Java                       ├── Docker
       ├── Spring Boot                ├── CI/CD
       ├── Spring Security            ├── AWS
       ├── REST APIs                  ├── Kubernetes
       ├── PostgreSQL                 ├── Prometheus
       ├── Thymeleaf                  └── Grafana
       └── Gmail SMTP
```

The project therefore serves as both a **real-world business application** and a practical platform for implementing modern **Java, Cloud and DevOps engineering practices**.
