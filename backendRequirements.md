I think this is the **most important document** for AI-assisted development.

For Spring Boot specifically, I would **not** call it `03_Backend_Requirements.md`.

I would call it:

> **03_Backend_Requirements.md**
> **Spring Boot AI Implementation Specification**

This document should **not** describe how Spring Boot works. Instead, it should instruct an AI (Cursor, Claude Code, GitHub Copilot, Cline, RooCode, Windsurf, etc.) **exactly how to build the backend**.

This document becomes the **single source of truth** for every Java package, service, controller, DTO, validation, transaction, security rule, exception, event, and API.

---

# Recommended Structure

```text
03_Backend_Requirements.md

1. Purpose

2. Technology Stack

3. Architecture

4. Project Structure

5. Coding Standards

6. Package Structure

7. Layer Responsibilities

8. Module Design

9. Entity Mapping

10. DTO Standards

11. Validation Rules

12. Service Layer Rules

13. Repository Rules

14. Controller Rules

15. Security Architecture

16. Authentication

17. Authorization

18. Exception Handling

19. Transactions

20. Event Handling

21. API Standards

22. Response Standards

23. Logging

24. Configuration

25. Testing Requirements

26. Performance

27. AI Implementation Rules

28. Acceptance Criteria
```

---

# 1. Purpose

```markdown
# Purpose

This document defines the backend implementation requirements for TransitOps.

The backend SHALL be implemented using Spring Boot following modern enterprise architecture.

The backend SHALL expose RESTful APIs that satisfy the Business Requirements and Database Requirements documents.

This document is the implementation contract for all backend services.

The AI implementation agent SHALL follow this specification without deviation unless explicitly instructed.
```

---

# 2. Technology Stack

```markdown
# Technology Stack

Framework

- Spring Boot 3.x

Language

- Java 21 LTS

Database

- MySQL 8

ORM

- Spring Data JPA
- Hibernate 6

Migration

- Flyway

Security

- Spring Security 6
- JWT

Validation

- Jakarta Validation

Documentation

- OpenAPI 3
- Swagger

Build Tool

- Maven

Caching

- Redis (Future)

Storage

- Local
- Amazon S3 (Future)

Email

- Spring Mail

Logging

- SLF4J
- Logback

Testing

- JUnit 5
- Mockito
- Testcontainers

Monitoring

- Spring Boot Actuator

Containerization

- Docker
```

---

# 3. Architecture

The backend SHALL follow **Clean Architecture**.

```text
                 REST API

                     │

             Controller Layer

                     │

              Service Layer

                     │

          Business Validation

                     │

            Repository Layer

                     │

             MySQL Database
```

Each layer has one responsibility.

Controllers must never contain business logic.

Repositories must never contain validation logic.

---

# 4. Project Structure

```text
src/main/java/com/transitops

config/

security/

controller/

service/

service/impl/

repository/

entity/

dto/

mapper/

exception/

validator/

event/

listener/

util/

constant/

specification/

scheduler/
```

Resources

```text
application.yml

db/migration/

messages.properties

logback.xml
```

---

# 5. Coding Standards

The AI SHALL

* Use constructor injection only
* Never use field injection
* Never use `@Autowired` on fields
* Use Lombok sparingly (`@Getter`, `@Setter`, `@Builder`)
* Prefer immutable DTOs (Java records)
* Use `ResponseEntity`
* Use `Optional` appropriately
* Keep methods small and cohesive
* Avoid business logic in controllers
* Avoid native SQL unless necessary

---

# 6. Package Responsibilities

## controller

Expose REST APIs.

No business logic.

---

## service

Business rules.

Transactions.

Validation.

---

## repository

Database access.

Only persistence logic.

---

## entity

JPA entities.

No business logic.

---

## dto

API request and response objects.

---

## mapper

MapStruct mappers.

Entity ↔ DTO.

---

## validator

Custom validation.

Example:

```text
CargoCapacityValidator
DriverAvailabilityValidator
VehicleAvailabilityValidator
```

---

## event

Domain events.

Example:

```
TripDispatchedEvent
TripCompletedEvent
MaintenanceStartedEvent
```

---

# 7. Module Design

Each business module should be self-contained.

```text
Vehicle Module

VehicleController

VehicleService

VehicleRepository

VehicleMapper

VehicleValidator

VehicleDTO

VehicleEntity

----------------------------

Trip Module

TripController

TripService

TripRepository

TripMapper

TripValidator

TripDTO

TripEntity
```

No module should directly manipulate another module's repository.

---

# 8. Entity Mapping

```text
Vehicle

↓

VehicleEntity

↓

VehicleRepository

↓

VehicleService

↓

VehicleController

↓

REST API
```

The same applies to every aggregate.

---

# 9. DTO Standards

Never expose JPA entities.

Use DTOs exclusively.

Example

```java
public record VehicleRequest(

String registrationNumber,

Long vehicleTypeId,

BigDecimal capacity,

BigDecimal acquisitionCost

){}
```

Response

```java
public record VehicleResponse(

Long id,

String registrationNumber,

String status

){}
```

---

# 10. Validation Rules

Use Jakarta Validation.

Example

```java
@NotBlank

@Email

@NotNull

@Positive

@Size

@Past

@Future

@Pattern
```

Business validation belongs in services.

Example

```text
Vehicle Available

Driver Available

Cargo Capacity

License Expiry
```

---

# 11. Service Layer Rules

Services own business logic.

Example

```text
Dispatch Trip

↓

Validate Vehicle

↓

Validate Driver

↓

Validate Capacity

↓

Update Trip

↓

Update Vehicle

↓

Update Driver
```

Controllers must never perform these steps.

---

# 12. Repository Rules

Repositories SHALL extend

```java
JpaRepository
```

Use Specifications for filtering.

Avoid native queries unless performance requires it.

---

# 13. Controller Rules

Controllers

Only

* Receive request
* Validate request
* Call service
* Return response

No business logic.

---

# 14. Security

Spring Security 6

JWT Authentication

RBAC

Method Security

```java
@PreAuthorize("hasRole('FLEET_MANAGER')")
```

Passwords

BCrypt or Argon2.

Never plaintext.

---

# 15. Authentication Flow

```text
Login

↓

Authenticate

↓

Generate JWT

↓

Return Token

↓

Subsequent Requests

↓

JWT Validation

↓

Authorize
```

---

# 16. Authorization Matrix

| Role          | Vehicle | Driver | Trip     | Maintenance |
| ------------- | ------- | ------ | -------- | ----------- |
| Admin         | CRUD    | CRUD   | CRUD     | CRUD        |
| Fleet Manager | CRUD    | Read   | Read     | CRUD        |
| Dispatcher    | Read    | Read   | CRUD     | Read        |
| Driver        | Read    | Self   | Assigned | Read        |
| Finance       | Read    | Read   | Read     | Read        |

---

# 17. Exception Handling

Use Global Exception Handler.

```java
@RestControllerAdvice
```

Example

```text
VehicleNotAvailableException

DriverUnavailableException

CapacityExceededException

LicenseExpiredException

ResourceNotFoundException
```

---

# 18. Transactions

```java
@Transactional
```

Used for

Dispatch Trip

Complete Trip

Open Maintenance

Close Maintenance

Never use transactions in controllers.

---

# 19. Domain Events

Publish

```text
TripDispatchedEvent

TripCompletedEvent

MaintenanceStartedEvent

FuelLoggedEvent
```

Future integration with Kafka.

---

# 20. API Standards

REST

Plural endpoints.

Example

```
GET /api/vehicles

POST /api/trips

PATCH /api/trips/{id}/dispatch
```

Versioning

```
/api/v1/
```

---

# 21. Response Standard

Every API returns

```json
{
  "success": true,
  "message": "Trip dispatched successfully.",
  "timestamp": "2026-01-01T10:00:00Z",
  "data": {}
}
```

Error

```json
{
  "success": false,
  "errorCode": "DRIVER_UNAVAILABLE",
  "message": "Driver is already assigned to another trip.",
  "timestamp": "2026-01-01T10:00:00Z"
}
```

---

# 22. Logging

Use SLF4J.

Never use

```java
System.out.println()
```

Log

* Authentication
* Dispatch
* Maintenance
* Errors
* Warnings

Do not log passwords or JWTs.

---

# 23. Configuration

Use

```
application.yml
```

Profiles

```
local

dev

qa

prod
```

Secrets must come from environment variables.

---

# 24. Testing

Every module must have:

* Unit Tests
* Service Tests
* Repository Tests
* Integration Tests
* Security Tests

Minimum coverage: **80%**

Use:

* JUnit 5
* Mockito
* Testcontainers

---

# 25. Performance

Target response times:

| API            | Target  |
| -------------- | ------- |
| Login          | <500 ms |
| Vehicle Search | <200 ms |
| Driver Search  | <200 ms |
| Dispatch Trip  | <1 s    |
| Dashboard      | <500 ms |

---

# 26. AI Implementation Rules

The AI SHALL:

* Generate one package per module.
* Keep controllers thin.
* Place all business rules in services.
* Use constructor injection.
* Use DTOs for all API boundaries.
* Use MapStruct for mapping.
* Validate input with Jakarta Validation.
* Apply `@Transactional` only at the service layer.
* Return standardized API responses.
* Use global exception handling.
* Write Flyway migrations for schema changes.
* Generate OpenAPI documentation.
* Follow SOLID principles.
* Follow Clean Architecture.

The AI SHALL NOT:

* Put SQL in controllers.
* Expose entities through APIs.
* Duplicate business logic.
* Use field injection.
* Swallow exceptions.
* Hardcode configuration values.
* Store secrets in source code.

---

# 27. Acceptance Criteria

The backend is accepted only if:

* All REST APIs are implemented.
* Controllers contain no business logic.
* Services encapsulate all business rules.
* Repositories handle persistence only.
* DTOs are used for all requests and responses.
* Authentication and RBAC are enforced.
* Transactions protect multi-step operations.
* Global exception handling is in place.
* OpenAPI documentation is generated.
* Unit and integration tests pass.
* Flyway migrations execute successfully.
* The application is production-ready.

---

