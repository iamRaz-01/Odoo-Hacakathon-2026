# 02_Database_Requirements.md

# TransitOps
## Database Requirements Specification (AI-First)

Version: 1.0

Target Database: MySQL 8+

Storage Engine: InnoDB

Character Set: utf8mb4

Collation: utf8mb4_unicode_ci

Primary Audience:
- AI Software Engineering Agents
- Backend Developers
- Database Architects

---

# 1. Purpose

This document defines the complete database requirements for TransitOps.

It serves as the single source of truth for the persistence layer.

The AI implementation agent shall generate the database schema, relationships, constraints, indexes, transactions, and data integrity mechanisms strictly according to this specification.

This document intentionally does not define API behavior or UI implementation.

---

# 2. Database Goals

The database must:

- Preserve business integrity
- Prevent invalid data
- Eliminate duplication
- Support millions of records
- Optimize read/write performance
- Support auditing
- Be horizontally scalable
- Be easy to maintain
- Be production-ready

---

# 3. Database Design Principles

The implementation SHALL follow these principles.

## Normalization

Minimum Third Normal Form (3NF)

BCNF where practical.

Avoid unnecessary denormalization.

Derived values shall not be stored unless explicitly required.

---

## Data Integrity

Always enforce

- Primary Keys
- Foreign Keys
- Unique Constraints
- CHECK Constraints
- Transactions

No orphan records shall exist.

---

## Naming Standards

Use snake_case.

Table names

Singular.

Examples

vehicle

trip

driver

expense

fuel_log

maintenance_log

Column examples

vehicle_id

driver_id

created_at

updated_at

deleted_at

status

---

## Primary Keys

Every table SHALL have

BIGINT AUTO_INCREMENT

Primary Key.

Example

vehicle_id

driver_id

trip_id

---

## Audit Columns

Transactional tables SHALL contain

created_at

updated_at

deleted_at (when soft delete is required)

---

## Money

Always use

DECIMAL

Never FLOAT.

---

## Dates

DATE

For business dates.

DATETIME

For timestamps.

TIMESTAMP

For audit fields.

---

# 4. Database Modules

The database is divided into logical modules.

Security

- role
- user

Fleet

- vehicle
- vehicle_type
- vehicle_document

Personnel

- driver

Operations

- trip

Maintenance

- maintenance_type
- maintenance_log

Finance

- fuel_log
- expense_category
- expense

Audit

- audit_log

---

# 5. Entity Specifications

## Role

Purpose

Defines application permissions.

Responsibilities

- RBAC

Relationships

1:N User

Constraints

Unique name.

---

## User

Purpose

Authentication.

Responsibilities

- Login
- Authorization
- Audit ownership

Relationships

Many Trips

Many Expenses

Many Fuel Logs

Many Maintenance Logs

Business Rules

Email unique.

Password hashed.

Never store plaintext passwords.

---

## Vehicle

Purpose

Represents fleet assets.

Business Responsibilities

Participate in trips.

Undergo maintenance.

Generate expenses.

Generate fuel logs.

Generate ROI.

Lifecycle

AVAILABLE

↓

ON_TRIP

↓

AVAILABLE

↓

IN_SHOP

↓

AVAILABLE

↓

RETIRED

Constraints

Registration unique.

Capacity positive.

Odometer non-negative.

Relationships

VehicleType

Trip

MaintenanceLog

FuelLog

Expense

VehicleDocument

---

## Driver

Purpose

Represents licensed operators.

Business Rules

License unique.

License expiry mandatory.

One active trip only.

Suspended drivers cannot dispatch.

Relationships

Trip

---

## Trip

Purpose

Represents transportation operations.

Business Responsibilities

Assign vehicle.

Assign driver.

Track revenue.

Track distance.

Track fuel.

Relationships

Vehicle

Driver

User

Business Rules

One vehicle.

One driver.

Cargo ≤ Capacity.

Vehicle available.

Driver available.

---

## Maintenance

Purpose

Represents servicing events.

Business Rules

Opening maintenance moves vehicle to IN_SHOP.

Closing maintenance restores AVAILABLE unless retired.

---

## Fuel Log

Purpose

Represents fuel purchases.

Rules

Positive liters.

Positive cost.

Optional trip association.

---

## Expense

Purpose

Represents operational expenses.

Rules

Positive amount.

Categorized.

Optional trip association.

---

# 6. Relationship Matrix

| Parent | Child | Cardinality |
|----------|---------|------------|
| Role | User | 1:N |
| VehicleType | Vehicle | 1:N |
| Vehicle | Trip | 1:N |
| Driver | Trip | 1:N |
| Vehicle | FuelLog | 1:N |
| Vehicle | Expense | 1:N |
| Vehicle | MaintenanceLog | 1:N |
| MaintenanceType | MaintenanceLog | 1:N |
| ExpenseCategory | Expense | 1:N |
| User | Trip | 1:N |

---

# 7. Business Rules

## Vehicle

Registration unique.

Cannot dispatch retired vehicle.

Cannot dispatch maintenance vehicle.

Only one active trip.

Capacity > cargo.

---

## Driver

License unique.

License valid.

Not suspended.

Only one active trip.

---

## Trip

Vehicle mandatory.

Driver mandatory.

Cannot complete before dispatch.

Completed immutable.

---

## Maintenance

Open

↓

Vehicle In Shop

Close

↓

Vehicle Available

---

## Fuel

Positive quantity.

Positive cost.

---

## Expense

Positive amount.

Category mandatory.

---

# 8. State Machines

## Vehicle

AVAILABLE

↓

ON_TRIP

↓

AVAILABLE

↓

IN_SHOP

↓

AVAILABLE

↓

RETIRED

Forbidden

RETIRED → AVAILABLE

ON_TRIP → RETIRED

---

## Driver

AVAILABLE

↓

ON_TRIP

↓

AVAILABLE

↓

SUSPENDED

---

## Trip

DRAFT

↓

DISPATCHED

↓

COMPLETED

Allowed

Draft → Cancelled

Dispatched → Cancelled

Forbidden

Completed → Draft

Completed → Dispatch

Cancelled → Dispatch

---

# 9. Transactions

Dispatch Trip

Atomic

Steps

Validate Driver

↓

Validate Vehicle

↓

Validate Capacity

↓

Update Trip

↓

Update Vehicle

↓

Update Driver

↓

Commit

Rollback

Entire transaction.

---

Complete Trip

Update Trip

↓

Update Vehicle

↓

Update Driver

↓

Insert Fuel

↓

Commit

---

Open Maintenance

Insert Maintenance

↓

Vehicle In Shop

↓

Commit

---

Close Maintenance

Maintenance Complete

↓

Vehicle Available

↓

Commit

---

# 10. Constraints

Required

Primary Keys

Foreign Keys

Unique Constraints

Check Constraints

Not Null Constraints

Optional

Generated Columns

Views

Stored Procedures

Triggers

---

# 11. Index Strategy

Primary

Every PK.

Unique

Registration Number

License Number

Email

Composite

Vehicle(Status, VehicleType)

Trip(Status, DispatchTime)

Driver(Status, LicenseExpiry)

FuelLog(Vehicle, Date)

Expense(Vehicle, Date)

Maintenance(Vehicle, Status)

Avoid

TEXT indexes

Unused indexes

Duplicate indexes

---

# 12. Reporting Requirements

Database must support

Fleet Utilization

Fuel Efficiency

Vehicle ROI

Operational Cost

Maintenance Cost

Trip Summary

Driver Summary

Monthly Fuel

Monthly Expenses

Vehicle History

Driver History

License Expiry

Maintenance Schedule

Dashboard KPIs

---

# 13. Performance Targets

Dashboard

<500ms

Vehicle Search

<200ms

Driver Search

<200ms

Dispatch

<1 second

Trip Completion

<2 seconds

---

# 14. Scalability Targets

Vehicles

100,000+

Drivers

250,000+

Trips

100 Million+

Fuel Logs

500 Million+

Expenses

500 Million+

Concurrent Users

10,000+

Read Replicas

Supported

Sharding

Future Support

---

# 15. Security Requirements

Passwords

Argon2id or bcrypt.

Never plaintext.

SQL Injection

Prepared statements only.

Encryption

Sensitive documents.

RBAC mandatory.

Audit logging required.

---

# 16. AI Implementation Rules

The AI SHALL

✓ Normalize database

✓ Use BIGINT PKs

✓ Use InnoDB

✓ Use utf8mb4

✓ Create Foreign Keys

✓ Create Indexes

✓ Create CHECK constraints

✓ Create Audit Columns

✓ Preserve Referential Integrity

✓ Implement Transactions

✓ Generate Production SQL

The AI SHALL NOT

✗ Skip constraints

✗ Duplicate entities

✗ Duplicate columns

✗ Use FLOAT for money

✗ Store passwords

✗ Store derived analytics

✗ Break normalization

✗ Ignore business rules

---

# 17. Deliverables

The implementation agent shall generate

- ER Diagram

- Database Schema

- SQL DDL

- Indexes

- Views

- Stored Procedures

- Triggers

- Seed Data

- Migration Scripts

- Rollback Scripts

- Sample Queries

- Performance Documentation

---

# 18. Acceptance Criteria

Database is accepted only if

✓ All entities implemented

✓ All relationships valid

✓ No orphan records

✓ Fully normalized

✓ Business rules enforced

✓ Foreign Keys implemented

✓ Transactions implemented

✓ Indexes optimized

✓ SQL executable

✓ MySQL 8 compatible

✓ Production ready