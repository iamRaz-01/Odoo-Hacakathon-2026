# 🚚 TransitOps - Smart Transport Operations Platform

## Credentials to Login
Admin: admin@transitops.local
Fleet Manager: fleet@transitops.local
Dispatcher: dispatcher@transitops.local
Driver: driver@transitops.local
Safety Officer: safety@transitops.local
Finance: finance@transitops.local 
password = TransitOps123!

## Overview

TransitOps is an enterprise-grade Transport Operations Management System designed to digitize and streamline fleet operations for logistics organizations.

The platform enables organizations to efficiently manage vehicles, drivers, trips, maintenance, fuel consumption, operational expenses, and analytics through a centralized ERP system.

---

# Features

## Authentication & Authorization

- JWT Authentication
- Role-Based Access Control (RBAC)
- Secure Login
- Protected APIs

Supported Roles

- Admin
- Fleet Manager
- Driver
- Safety Officer
- Financial Analyst

---

## Fleet Management

- Register Vehicles
- Vehicle Status Tracking
- Vehicle Assignment
- Vehicle Availability Management
- Vehicle Lifecycle Management

---

## Driver Management

- Driver Registration
- License Validation
- Driver Status Management
- Driver Assignment
- Driver Dashboard

---

## Trip Management

- Trip Creation
- Trip Assignment
- Trip Lifecycle Management
- Driver Trip Progress
- Automatic Status Updates

---

## Maintenance

- Maintenance Scheduling
- Vehicle Maintenance History
- Maintenance Status
- Vehicle Availability Control

---

## Fuel & Expense Management

- Fuel Logs
- Operational Expenses
- Vehicle Cost Tracking
- Fuel Efficiency

---

## Dashboard

- Fleet Utilization
- Vehicle Status
- Driver Status
- Active Trips
- Maintenance Summary
- KPI Cards
- Charts & Reports

---

# Technology Stack

| Layer | Technology |
|---------|------------|
| Frontend | Next.js 15 |
| Language | TypeScript |
| Styling | Tailwind CSS |
| UI Components | shadcn/ui |
| Icons | Lucide React |
| Charts | Recharts |
| Backend | Spring Boot 3 |
| Language | Java 21 LTS |
| Build Tool | Maven |
| Database | MySQL 8 |
| ORM | Spring Data JPA + Hibernate |
| Security | Spring Security + JWT |
| API Documentation | Swagger OpenAPI |
| Version Control | Git |
| Hosting | Vercel / Render / Railway |

---

# Project Structure

```
TransitOps/

├── frontend/
│   ├── app/
│   ├── components/
│   ├── hooks/
│   ├── services/
│   ├── lib/
│   └── public/
│
├── backend/
│   ├── src/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── dto/
│   ├── security/
│   ├── config/
│   ├── util/
│   └── resources/
│
└── README.md
```

---

# Prerequisites

Install the following software before running the project.

## Java

Java 21 LTS

Verify

```bash
java --version
```

---

## Maven

Verify

```bash
mvn -version
```

---

## Node.js

Recommended Version

```
v22+
```

Verify

```bash
node -v
```

---

## npm

```bash
npm -v
```

---

## MySQL

Version

```
MySQL 8+
```

---

## Git

```bash
git --version
```

---

# Clone Repository

```bash
git clone <repository-url>

cd TransitOps
```

---

# Backend Setup

Navigate

```bash
cd backend
```

---

## Configure Database

Create a MySQL database.

Example

```sql
CREATE DATABASE transitops;
```

Update

```
src/main/resources/application.properties
```

Example

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/transitops

spring.datasource.username=root

spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
```

---

## Install Dependencies

```bash
mvn clean install
```

---

## Run Backend

```bash
mvn spring-boot:run
```

Backend runs at

```
http://localhost:8080
```

---

## Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# Frontend Setup

Navigate

```bash
cd frontend
```

Install packages

```bash
npm install
```

---

## Configure Environment Variables

Create

```
.env.local
```

Example

```env
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080/api
```

---

## Start Frontend

```bash
npm run dev
```

Frontend

```
http://localhost:3000
```

---

# Running the Complete Application

## Step 1

Start MySQL

---

## Step 2

Run Backend

```bash
mvn spring-boot:run
```

---

## Step 3

Run Frontend

```bash
npm run dev
```

---

## Step 4

Open Browser

```
http://localhost:3000
```

---

# API Documentation

Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---

# Default Ports

| Service | Port |
|-----------|------|
| Frontend | 3000 |
| Backend | 8080 |
| MySQL | 3306 |

---

# Build Commands

## Backend

```bash
mvn clean package
```

---

## Frontend

```bash
npm run build
```

---

# Production Build

Backend

```bash
java -jar target/transitops.jar
```

Frontend

```bash
npm run start
```

---

# Troubleshooting

## Backend does not start

Check

- Java version
- Maven installation
- Database connection
- application.properties

---

## Frontend cannot connect to backend

Verify

```
NEXT_PUBLIC_API_BASE_URL
```

Verify backend is running.

---

## Database Connection Failed

Check

- MySQL running
- Username
- Password
- Database exists

---

## JWT Authentication Issues

Verify

- JWT Secret
- Authorization Header
- Token Expiration

---

## Port Already in Use

Backend

```bash
server.port=8081
```

Frontend

```bash
npm run dev -- --port 3001
```

---

# Development Workflow

```
Create Feature

↓

Implement Backend

↓

Test API

↓

Implement Frontend

↓

Test UI

↓

Commit Changes

↓

Push Repository
```

---

# Future Enhancements

- AI Route Optimization
- Predictive Maintenance
- Driver Performance Analytics
- Fuel Consumption Prediction
- Live GPS Tracking
- Mobile Application
- IoT Vehicle Integration

---

# Contributors

Team TransitOps

Built for the Odoo Hackathon 2026.

---
