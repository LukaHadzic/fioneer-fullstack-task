# Single Risk Module (SRM) — Full-Stack Challenge (Frontend)

Build a **React + TypeScript + Vite** web application using **ui5-webcomponents-react**, connected to **your own backend**.  
The goal is to design and implement a small CRUD system for managing **Accumulations** and their **Coverages**.

---

## Tech Constraints

- **Language:** TypeScript
- **Framework:** React
- **Bundler:** Vite
- **Design System:** [ui5-webcomponents-react](https://sap.github.io/ui5-webcomponents-react/v2/?path=/docs/getting-started--docs)
- **Charts:** [ui5-webcomponents-react-charts](https://www.npmjs.com/package/@ui5/webcomponents-react-charts) - documentation available within ui5-webcomponents-react docs
- **(Optional)** Tests with Cypress or Vitest Testing Library

---

## Overview

Implement an app that allows users to:

1. View a list of **Accumulations**
2. View **Accumulation Details** and their coverages
3. **Create** a new accumulation
4. **Delete** an accumulation
5. **Create** a coverage and assign it to an accumulation
6. **Edit** a coverage

Use a clean, consistent UI with **ui5-webcomponents-react**.

---

## Pages & Features

### 1) Global Header

A persistent header must be present across the app:

- **App Name** (your choice). Clicking it should navigate to the **home screen**.
- A **button** that navigates to the **Accumulations Library** (the list of accumulations page).

---


### 2) Home Dashboard

**Route:** `/`

Display a dashboard with **two charts**, backed by the backend API:

1. **Bar chart** — **Average sum insured by accumulation status**  
   (e.g., `ACTIVE`, `INACTIVE`, `DRAFT` → average of coverage sum insured per status)

2. **Line chart** — **Number of created accumulations per day**  
   (time series by creation date)

Show a **BusyIndicator** while loading, and reasonable empty/error states.  
Chart styling is up to you; use any charting library.

---

### 3) Accumulations List

**Route:** `/accumulations`

Display a table of accumulations fetched from the backend.

**Each row includes:**
- **ID**
- **Name**
- **Class of Business** (e.g., `FIRE`, `STORM`, `FLOOD`)
- **Number of Coverages**
- **Minimum Sum Insured** (lowest sum insured of coverages within accumulation, formatted as currency e.g., `$100.00`)
- **Status** (e.g., `ACTIVE`, `INACTIVE`, `DRAFT`) shown as a Tag/Badge
- **Actions:** View Details, Edit, Delete

**Requirements:**
- Sorting, filtering, and pagination are **server-side**.
- Show a **BusyIndicator** while loading and an **IllustratedMessage** for empty states.
- Confirm before deleting.
- **Optional** Filtering by Class of Business and Status.

---

### 4) Accumulation Details

**Route:** `/accumulations/:id`

Use a **DynamicPage** (or **ObjectPage**) to show:

**Header (key facts):**
- Name
- Class of Business
- Status
- Description (optional field)

**Content: Coverages Table**
- **Coverage ID**
- **Coverage Name**
- **Sum Insured** (currency)
- **Actions** (delete, edit coverage)

**Optional** Remove multiple coverages here (table multi select).

---

### 5) Create Accumulation

**Route:** `/accumulations/new`

Form to create an accumulation using **Form**, **Input**, **Button**.

**Fields:**
- **Name**
- **Class of Business**
- **Status**
- **Description** (optional)

---

### 6) Create Coverage

**Route:** `/coverages/new`

Form to create a coverage using **Form**, **Input**, **Select**, **Button**.

**Fields:**
- **Name**
- **Class of Business**
- **Sum Insured**
- **Accumulation** (dropdown of existing accumulations, handle error if class of business doesn't match)

**On submit:** Create the coverage and redirect to the accumulation details page.

---

### 7) Edit Coverage

**Route:** `/coverages/:id`

Same form as creation, prefilled with existing data.  
Allow editing coverages fields and reassigning to a different accumulation.
Business class should not be editable.

**On submit:** persist the changes and switch to details view.

---

### 8) Delete Accumulation

Allow deletion from the list and/or details page with a confirmation dialog.

---

### 9) Delete Coverages

Allow deletion from the list and/or details page with a confirmation dialog.

---

### 10) Accumulation History
 
  On the Accumulation Details page, allow users to see the accumulation's history and go back to an earlier state
  Design and implement this as you see fit.

---

## UI Component Suggestions

| Area | Components |
| --- | --- |
| Tables | `AnalyticalTable` |
| Forms | `Form`, `FormItem`, `Input`, `Select`, `CheckBox`, `Button` |
| Layout | `DynamicPage`, `ObjectPage`, `FlexBox`, `VBox` |
| Loading | `BusyIndicator` |
| Empty/Error | `IllustratedMessage`, `MessageStrip` |
| Icons | `add`, `edit`, `delete`, `accept`, `decline`, `navigation-right-arrow` |

---

## Functional Requirements

- Full **CRUD** against your backend.
- **Server-side** sorting, filtering, and pagination for list tables.
- Clear TypeScript models (e.g., `Accumulation`, `Coverage`).
- Robust async state handling (loading, error, empty).
- Clean project structure and modular code.

---

## Expectations

- Idiomatic React + TypeScript.
- Consistent **ui5-webcomponents-react** usage.
- Thoughtful UX (validation, confirmations, errors).
- Clean commit history and clear instructions to run the app.

---

## Optional Enhancements

- React Query (caching, mutations).
- Component/Cypress tests.
- Dockerfile.
- Toasts for feedback on save/delete.
