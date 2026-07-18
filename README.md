# CSCB869_Winter_Olympics

A full-stack web application for managing Winter Olympic competitions, athletes, participation, and rankings.

The application is developed using a modern enterprise architecture with a React frontend, Spring Boot REST backend, Hibernate/JPA persistence layer, MySQL database, and Keycloak-based OAuth2 authentication and authorization.

The project demonstrates secure user management, role-based access control, REST API development, relational database design, and full-stack application integration.

---

# Features

## Authentication and Authorization

The application integrates Keycloak as an Identity Provider using OAuth2 and OpenID Connect.

Implemented security features:

- OAuth2 authentication flow
- OpenID Connect integration
- JWT-based authentication
- Secure REST endpoints
- Role-based authorization
- User identity synchronization between Keycloak and the application database

Supported roles:

- ATHLETE
- ADMIN

Authentication flow:

```
React Application
        |
        | OAuth2 Authentication
        ↓
Keycloak Identity Provider
        |
        | JWT Access Token
        ↓
Spring Boot Backend
        |
        ↓
Application Services
```

---

# System Architecture

The application follows a layered full-stack architecture.

```
Frontend
React + Vite
        |
        | REST API + JWT
        ↓
Backend
Spring Boot
        |
        | Hibernate/JPA
        ↓
Database
MySQL


Authentication Layer

Keycloak
OAuth2 / OpenID Connect
```

---

# Technologies

## Backend

- Java
- Spring Boot
- Spring MVC
- Spring Security
- OAuth2 Resource Server
- Hibernate ORM
- JPA
- REST APIs
- Bean Validation
- Lombok
- ModelMapper
- MySQL

## Frontend

- React.js
- Vite
- Axios
- React Router
- React DatePicker
- JavaScript / JSX

## Authentication

- Keycloak
- OAuth2
- OpenID Connect
- JWT

## Development Tools

- Maven
- Git
- IntelliJ IDEA
- MySQL Workbench
- Postman

---

# Project Structure

```
CSCB869_Winter_Olympics

├── backend
│
├── controller
│   └── REST API controllers
│
├── service
│   └── business logic layer
│
├── repository
│   └── database access layer
│
├── entity
│   └── JPA entities
│
├── dto
│   └── request and response objects
│
├── security
│   └── Spring Security configuration
│
└── frontend
    |
    ├── components
    ├── pages
    ├── api
    └── router
```

---

# Domain Model

## Athlete Management

The application allows authenticated users to create and manage athlete profiles.

Athlete information includes:

- First name
- Last name
- Country
- Gender
- Date of birth
- Competition participation


User relationship:

```
Keycloak User

        |
        |

Application UserAccount

        |
        |

Athlete Profile
```

The Keycloak user handles authentication, while the application database stores domain-specific information.

---

# Competition Management

The system supports different Winter Olympic disciplines.

Implemented competitions:

## Ski Slalom

Features:

- Athlete registration
- Participation tracking
- Result calculation
- Ranking generation
- Top 30 qualification logic


## Biathlon

Features:

- Athlete participation
- Time-based results
- Shooting penalties
- Final ranking calculation


Competition inheritance model:

```
          Competition

              |
      ----------------
      |              |

   Slalom        Biathlon
```

The application uses JPA inheritance to model different competition types.

---

# Database Design

Main entities:

```
UserAccount

Athlete

Competition

Participation

Result
```

Entity relationships:

```
Athlete
    |
    |
Participation
    |
    |
Competition


Participation
    |
    |
Result
```

Hibernate and JPA are used for database persistence and relationship management.

---

# Security Design

Authentication and application data are separated.

Keycloak is responsible for:

- User authentication
- Password management
- Identity management
- Token generation
- Role assignment


Spring Boot is responsible for:

- JWT validation
- API protection
- Authorization rules
- Application business logic


Example protected endpoint:

```
GET /api/athletes
```

Access is controlled through Spring Security rules.

---

# REST API Examples

## Create Athlete

```
POST /api/athletes
```

Example request:

```json
{
    "firstName": "Angel",
    "lastName": "Valentinov",
    "country": "Bulgaria",
    "gender": "MALE",
    "birthDate": "2001-01-01"
}
```


## Get Athletes

```
GET /api/athletes
```

---

# Running the Application

## Requirements

Install:

- Java 21+
- Node.js
- MySQL
- Maven
- Keycloak


---

# Backend Setup

Configure the database and application settings in:

```
application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/winter_olympics

spring.datasource.username=root

spring.jpa.hibernate.ddl-auto=update

server.port=8080
```

Run the backend:

```bash
mvn spring-boot:run
```

---

# Frontend Setup

Navigate to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

Frontend:

```
http://localhost:5173
```

---

# Keycloak Setup

Create a Keycloak realm:

```
winter-olympics-application-realm
```

Create an OpenID Connect client:

```
winter-olympics-application
```

Configure:

- Authorization Code Flow
- Valid redirect URIs
- Web origins
- Client roles


Example redirect URI:

```
http://localhost:5173/*
```

---

# Future Improvements

Possible future features:

- Real-time competition updates
- Athlete statistics dashboard
- Medal ranking system
- Advanced analytics
- Admin management panel
- Docker deployment
- CI/CD integration
- Cloud deployment

---

# Learning Objectives

This project demonstrates practical experience with:

- Full-stack web development
- REST API design
- Spring Boot application architecture
- OAuth2 and OpenID Connect authentication
- Spring Security
- Hibernate/JPA database modeling
- React frontend development
- Secure enterprise application design

---

# Author

Angel Valentinov

CSCB869 - Java Web Services Project

New Bulgarian University