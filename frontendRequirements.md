I actually think we can do **much better** than a normal frontend requirements document.

Most AI agents fail on frontend generation because they don't know:

* the design system,
* component hierarchy,
* state management,
* page flow,
* permissions,
* UX behavior,
* validation,
* loading states,
* error states,
* reusable components.

Instead of a simple "Frontend Requirements", I'd create an **AI Frontend Engineering Specification**.

This is the document I would hand to a team at Google, Stripe, or Vercel if the implementation team consisted entirely of AI agents.

---

# 04_Frontend_Requirements.md

**Project:** TransitOps

**Frontend Framework:** React 19

**Language:** TypeScript

**UI Library:** Material UI (MUI)

**Build Tool:** Vite

**State Management:** TanStack Query + Zustand

**Routing:** React Router v7

**Forms:** React Hook Form + Zod

**Charts:** Recharts

**Tables:** MUI Data Grid

**Icons:** Material Icons

**Authentication:** JWT

**Target Audience:** AI Implementation Agents

---

# 1. Purpose

```markdown
This document defines the frontend implementation requirements for TransitOps.

The frontend SHALL consume the REST APIs defined by the backend.

The frontend SHALL be fully component-based.

The frontend SHALL separate presentation, business state, routing, and API communication.

The AI implementation agent SHALL strictly follow this specification.
```

---

# 2. Technology Stack

```text
Framework
---------
React 19

Language
--------
TypeScript

Bundler
-------
Vite

UI
--
Material UI

Forms
-----
React Hook Form

Validation
----------
Zod

API
---
Axios

Data Fetching
-------------
TanStack Query

Global State
------------
Zustand

Charts
------
Recharts

Routing
-------
React Router v7

Authentication
--------------
JWT

Testing
-------
Vitest
React Testing Library

Lint
----
ESLint

Formatting
----------
Prettier
```

---

# 3. Frontend Architecture

```
Browser

↓

App

↓

Router

↓

Layout

↓

Page

↓

Feature

↓

Components

↓

Hooks

↓

API

↓

Spring Boot
```

The UI **never** talks directly to the database.

---

# 4. Folder Structure

```
src/

app/

assets/

components/

layouts/

pages/

features/

services/

hooks/

store/

contexts/

routes/

theme/

types/

utils/

validators/

constants/

config/

api/
```

---

# 5. Feature Modules

Each business module owns its pages, components, hooks, API, and validation.

```
features/

vehicle/

driver/

trip/

maintenance/

fuel/

expense/

dashboard/

authentication/
```

Each feature contains:

```
vehicle/

VehiclePage.tsx

VehicleForm.tsx

VehicleTable.tsx

VehicleCard.tsx

VehicleDialog.tsx

vehicleApi.ts

vehicleHooks.ts

vehicleSchema.ts

types.ts
```

---

# 6. Layout Structure

```
Login

↓

Dashboard Layout

├── Sidebar
├── Top Navigation
├── Content
├── Footer
```

Sidebar Modules

```
Dashboard

Vehicles

Drivers

Trips

Maintenance

Fuel

Expenses

Reports

Administration

Settings
```

---

# 7. Page Requirements

## Login

Functions

* Login
* Remember Me
* Forgot Password

---

## Dashboard

Widgets

* Active Vehicles
* Available Vehicles
* Active Trips
* Fleet Utilization
* Drivers On Duty
* Fuel Efficiency
* ROI
* Maintenance Alerts

Charts

* Trip Trend
* Fuel Cost
* Monthly Expenses

---

## Vehicle Page

Functions

* Search
* Filter
* Sort
* Create
* Edit
* View
* Delete
* Export CSV

Table Columns

* Registration
* Type
* Capacity
* Status
* Odometer

---

## Driver Page

Functions

* CRUD
* License Status
* Safety Score
* Filters

---

## Trip Page

Functions

* Draft
* Dispatch
* Complete
* Cancel

Validation

* Vehicle Available
* Driver Available
* Capacity Check

---

## Maintenance Page

Functions

* Create Maintenance
* Close Maintenance
* View History

---

## Fuel Page

Functions

* Add Fuel
* Fuel History
* Vehicle Fuel Summary

---

## Expense Page

Functions

* Add Expense
* Categories
* Monthly Summary

---

## Reports Page

Charts

Fleet Utilization

ROI

Fuel Cost

Maintenance Cost

Trip Summary

---

# 8. Component Standards

Components SHALL be:

* Reusable
* Stateless where possible
* Typed
* Small
* Testable

Example

```
Button

Input

Dialog

Table

Card

Chip

StatusBadge

LoadingSpinner

ErrorMessage

ConfirmationDialog

SearchBar

FilterPanel
```

---

# 9. Form Standards

Use:

```
React Hook Form

+

Zod
```

Validation Example

```
Registration Number

Required

Unique

------------

Capacity

Positive

------------

License Expiry

Future Date

------------

Fuel Cost

Positive
```

---

# 10. API Layer

Never call Axios directly inside components.

```
Component

↓

Hook

↓

API Service

↓

Axios

↓

Spring Boot
```

Example

```
VehiclePage

↓

useVehicles()

↓

vehicleApi.getVehicles()
```

---

# 11. State Management

Global State

```
User

JWT

Theme

Sidebar

Notifications
```

Zustand

Server State

TanStack Query

Never duplicate state.

---

# 12. Routing

```
/

↓

login

↓

dashboard

↓

vehicles

↓

drivers

↓

trips

↓

maintenance

↓

fuel

↓

expenses

↓

reports

↓

settings
```

Protected Routes

JWT required.

---

# 13. Permissions

Admin

Everything

Fleet Manager

Vehicles

Maintenance

Dispatcher

Trips

Drivers

Finance

Expenses

Reports

Safety Officer

Drivers

Licenses

Unauthorized menus should not be rendered.

---

# 14. UX Standards

Every action must provide feedback.

Loading

Skeleton

Success

Snackbar

Failure

Dialog

Confirmation

Delete

Dispatch

Complete Trip

Close Maintenance

---

# 15. Error Handling

Display friendly messages.

Never expose stack traces.

Retry failed API requests when appropriate.

---

# 16. Search & Filtering

Every data grid supports:

Search

Sort

Pagination

Filtering

Column Visibility

Export CSV

Density Toggle

---

# 17. Dashboard Requirements

Cards

```
Vehicles

Drivers

Trips

Maintenance

Fuel

Expenses
```

Charts

```
Fleet Utilization

Monthly Trips

Fuel Consumption

Expense Trend

ROI
```

Auto refresh

5 minutes.

---

# 18. Theme

Support

Light Mode

Dark Mode

Material Design 3

Responsive

Desktop First

Tablet

Mobile

---

# 19. Performance

Target

First Load

<2 seconds

Page Navigation

<300ms

Search

<200ms

Dashboard

<500ms

Lazy Loading

Required

Code Splitting

Required

---

# 20. Accessibility

WCAG AA

Keyboard Navigation

ARIA Labels

Focus Management

Color Contrast

---

# 21. AI Implementation Rules

The AI SHALL

* Use functional components.
* Use TypeScript everywhere.
* Use React Hooks only.
* Use feature-based architecture.
* Use reusable UI components.
* Keep business logic out of components.
* Use React Query for server state.
* Use Zustand for global state.
* Validate forms with Zod.
* Build responsive layouts.
* Use Material UI consistently.
* Lazy-load routes and heavy components.
* Follow SOLID principles in component design.

The AI SHALL NOT

* Call Axios directly from components.
* Duplicate UI logic.
* Duplicate API calls.
* Use inline styles excessively.
* Store JWT in local component state.
* Mix server state with UI state.
* Hardcode colors or spacing.
* Bypass permission checks.

---

# 22. Acceptance Criteria

The frontend is accepted only if:

* All business modules are implemented.
* Responsive layout works on desktop, tablet, and mobile.
* Authentication and authorization are enforced.
* All forms use React Hook Form + Zod.
* All API calls use the service layer.
* Data fetching uses TanStack Query.
* Global state uses Zustand.
* Loading, empty, and error states are handled.
* Material UI components are used consistently.
* Accessibility requirements are met.
* Performance targets are achieved.
* Code is modular, typed, and production-ready.

---

# 🚀 My Enterprise Recommendation

If I were building this with AI from scratch today, I would **slightly modernize the stack** beyond the common React setup:

### Frontend Stack

* **React 19**
* **TypeScript**
* **Vite**
* **Material UI v7**
* **TanStack Query**
* **Zustand**
* **React Hook Form**
* **Zod**
* **Axios**
* **React Router v7**
* **Recharts**
* **React Error Boundary**
* **MSW (Mock Service Worker)** for API mocking during development
* **Vitest + React Testing Library** for testing
* **Storybook** for isolated component development
* **ESLint + Prettier + Husky + lint-staged** for code quality
* **Docker** for consistent development environments

This combination is lightweight, highly maintainable, AI-friendly, and aligns well with a Spring Boot backend. It gives AI agents a clear architectural target and minimizes ambiguity, resulting in cleaner, more consistent code generation.
