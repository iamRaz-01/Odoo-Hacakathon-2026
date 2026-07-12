# 01_Business_Requirements.md

# TransitOps
## Business Requirements Specification (AI-First)

Version: 1.0
Project Type: Transport Management System (TMS)
Target Database: MySQL 8+
Architecture: Modular Monolith (Expandable to Microservices)
Primary Audience: AI Software Engineering Agents
Secondary Audience: Software Engineers, Architects, QA Engineers

---

# 1. Document Purpose

This document defines the business requirements for TransitOps.

Its purpose is to provide AI implementation agents with sufficient business context to generate a production-ready application.

This document intentionally avoids implementation details such as SQL schemas and API contracts. Those are defined in subsequent documents.

This document is considered the source of truth for business logic.

---

# 2. Project Overview

TransitOps is a Smart Transport Operations Platform designed to digitize fleet operations for logistics companies.

The platform replaces spreadsheet-based fleet management with a centralized application capable of managing:

- Vehicles
- Drivers
- Dispatch Operations
- Trips
- Maintenance
- Fuel Consumption
- Operational Expenses
- Analytics
- Compliance
- User Access Control

The objective is to improve fleet utilization, operational visibility, regulatory compliance, and cost control while minimizing manual processes.

---

# 3. Business Objectives

The system shall:

- Centralize transport operations.
- Prevent scheduling conflicts.
- Prevent invalid dispatches.
- Track complete vehicle lifecycle.
- Track driver lifecycle.
- Reduce maintenance delays.
- Monitor fuel consumption.
- Record operational expenses.
- Produce operational analytics.
- Improve operational efficiency.
- Support organizational growth.

Success Metrics

- Reduced dispatch conflicts
- Reduced maintenance delays
- Increased fleet utilization
- Reduced manual effort
- Accurate operational reporting

---

# 4. Business Scope

## Included

✔ Vehicle Management

✔ Driver Management

✔ Dispatch

✔ Trips

✔ Fuel

✔ Maintenance

✔ Expenses

✔ Dashboard

✔ Analytics

✔ Authentication

✔ Role-Based Access Control

✔ Reporting

---

## Out of Scope

The following features are intentionally excluded from Version 1:

- GPS Tracking
- Route Optimization
- Live Maps
- IoT Sensors
- Payroll
- HR Management
- Billing Integration
- Accounting Integration
- Vehicle Leasing
- Customer Portal
- Mobile Offline Mode

These may be added in future releases.

---

# 5. User Personas

## Administrator

Purpose

Manages application configuration.

Responsibilities

- Manage users
- Manage roles
- Configure permissions
- View all data

Permissions

Full system access.

---

## Fleet Manager

Purpose

Responsible for fleet assets.

Responsibilities

- Register vehicles
- Retire vehicles
- Update odometers
- Schedule maintenance
- Review fleet reports

---

## Dispatcher

Purpose

Coordinates transport operations.

Responsibilities

- Create trips
- Assign drivers
- Assign vehicles
- Dispatch trips
- Cancel trips
- Complete trips

---

## Driver

Purpose

Executes assigned trips.

Responsibilities

- Drive assigned vehicle
- Complete delivery
- Report fuel usage

The Driver does NOT manage system configuration.

---

## Safety Officer

Purpose

Ensures compliance.

Responsibilities

- Monitor licenses
- Review safety scores
- Suspend drivers
- Monitor violations

---

## Finance Officer

Purpose

Tracks operational costs.

Responsibilities

- Record expenses
- Record fuel
- Review ROI
- Export reports

---

# 6. Business Modules

The system is composed of the following modules.

1 Authentication

2 User Management

3 Fleet Management

4 Driver Management

5 Dispatch

6 Trip Management

7 Maintenance

8 Fuel Management

9 Expense Management

10 Reporting

11 Dashboard

12 Notifications

Each module should be independently maintainable.

---

# 7. Business Processes

## Vehicle Registration

Actor

Fleet Manager

Preconditions

User authenticated.

Flow

Register vehicle

↓

Validate registration number

↓

Validate capacity

↓

Save vehicle

↓

Vehicle becomes AVAILABLE

Postconditions

Vehicle can be dispatched.

---

## Driver Registration

Actor

Fleet Manager

Flow

Create driver

↓

Validate license

↓

Store profile

↓

Driver becomes AVAILABLE

---

## Trip Dispatch

Actor

Dispatcher

Flow

Create Trip

↓

Select Vehicle

↓

Select Driver

↓

Validate Driver

↓

Validate Vehicle

↓

Validate Cargo

↓

Dispatch Trip

↓

Vehicle Status = ON_TRIP

↓

Driver Status = ON_TRIP

---

## Trip Completion

Actor

Dispatcher

Flow

Complete Trip

↓

Enter Final Odometer

↓

Record Fuel

↓

Vehicle AVAILABLE

↓

Driver AVAILABLE

↓

Update Dashboard

---

## Maintenance Workflow

Fleet Manager

↓

Create Maintenance

↓

Vehicle IN_SHOP

↓

Maintenance Completed

↓

Vehicle AVAILABLE

---

# 8. Business Rules

This section defines mandatory business constraints.

Vehicle Rules

- Registration number must be unique.
- Vehicle capacity must be positive.
- Retired vehicles cannot be dispatched.
- Vehicles in maintenance cannot be dispatched.
- Vehicle may only have one active trip.

Driver Rules

- Driver license must be unique.
- Expired licenses cannot dispatch.
- Suspended drivers cannot dispatch.
- Driver may only have one active trip.

Trip Rules

- Cargo must not exceed vehicle capacity.
- Trip must reference one driver.
- Trip must reference one vehicle.
- Completed trips are immutable except for administrative correction.

Maintenance Rules

- Opening maintenance moves vehicle to IN_SHOP.
- Closing maintenance restores vehicle to AVAILABLE unless RETIRED.

Fuel Rules

- Fuel quantity must be positive.
- Fuel cost must be positive.

Expense Rules

- Expense amount must be positive.

---

# 9. State Machines

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

Allowed transitions

...

Forbidden transitions

...

## Driver

AVAILABLE

↓

ON_TRIP

↓

AVAILABLE

↓

SUSPENDED

## Trip

DRAFT

↓

DISPATCHED

↓

COMPLETED

Allowed

DRAFT → CANCELLED

DISPATCHED → CANCELLED

Forbidden

COMPLETED → DRAFT

COMPLETED → DISPATCHED

---

# 10. Business Events

VehicleRegistered

DriverRegistered

TripCreated

TripDispatched

TripCompleted

TripCancelled

MaintenanceStarted

MaintenanceCompleted

FuelLogged

ExpenseRecorded

LicenseExpired

VehicleRetired

---

# 11. Notifications

Email reminders

License expiry

Insurance expiry

Maintenance due

Vehicle document expiry

Future support

SMS

Push Notifications

---

# 12. Reporting Requirements

Fleet Utilization

Fuel Efficiency

Driver Performance

Vehicle ROI

Maintenance Cost

Operational Cost

Monthly Expense

Trip Summary

License Expiry

Maintenance Schedule

---

# 13. Non-Functional Requirements

Availability

99.9%

Response Time

<500ms

Authentication

JWT

Database

MySQL 8

Scalability

100M+ Trips

Concurrent Users

10,000+

---

# 14. AI Implementation Instructions

The AI implementation agent SHALL:

- Preserve all business rules.
- Never bypass validation.
- Treat business rules as mandatory.
- Never hardcode status transitions.
- Never duplicate business logic.
- Build reusable modules.
- Ensure future extensibility.
- Keep business logic separate from persistence logic.
- Design with production scalability in mind.

The AI implementation agent SHALL NOT:

- Skip validation.
- Ignore lifecycle rules.
- Introduce duplicate workflows.
- Create hidden business logic.
- Store derived analytics unless explicitly requested.

---

# 15. Acceptance Criteria

The implementation satisfies this document only if:

- All business modules exist.
- All user roles are implemented.
- Every workflow is supported.
- Every business rule is enforced.
- All state transitions are validated.
- Reports are supported.
- Notifications are supported.
- The application is extensible.
- The application is production-ready.