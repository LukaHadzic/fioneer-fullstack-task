# Single Risk Module (SRM) — Full-Stack Challenge (Backend)

The task involves creating a **RESTful web service** for managing insurance **Accumulations** and their related **Coverages**.  
The application should be implemented using the **Java or Kotlin** programming language and the **Spring Boot** framework.  
A **relational database** must be used — **SQLite** is required.

---

## Managing Accumulations

An Accumulation represents an insurance object, that accumulating one or more coverages.
Accumulation is used to group coverages together, so insurance company can easily reinsure them.
Therefore, Accumulation can contain one or more coverages.

Each accumulation must have a unique name, and there is no limit to the number of coverages that can be created.

An accumulation should include the following information:
- **Name** - Unique
- **Class of Business** (e.g., FIRE, STORM, FLOOD, EARTHQUAKE, SPACE)
- **Status** (e.g., ACTIVE, INACTIVE, DRAFT)
- **Description** (optional field)

Users should be able to:
- Create new accumulation
- View all accumulation
- View individual accumulation details, including all related coverages
- Delete accumulation

Searching and filtering by accumulation name, class of business, and status must be supported.  
Pagination and sorting must also be implemented on the backend.
Validation must be implemented on the backend.

---

## Managing Coverages

Each accumulation can have one or more coverages.  
Coverage represent one accumulated risk within an accumulation.

A coverage should include the following information:
- **Name**
- **Sum insured** (decimal number)
- **Class of Business** (e.g., FIRE, STORM, FLOOD, EARTHQUAKE, SPACE)

Class of Business must follow the accumulation's class of business.

Users should be able to:
- Create new coverages and assign to the proper accumulation
- If user try to assign coverage to accumulation with different class of business, the system should return an error
- Create, update and delete coverages
- View all coverages with a specific accumulation

Coverages must always belong to a specific accumulation.  
Deleting an accumulation should remove all of its associated coverages.

---

## Analytics

Provide REST endpoints to support the frontend dashboard:

1. **Average sum insured by accumulation status**  
   Return data suitable for a bar chart that shows the average of coverage sum insured grouped by accumulation status.

2. **Daily count of created accumulations**  
   Return data suitable for a time-series line chart that shows the number of accumulations created per day based on the stored created date.

Design the exact API shapes and response models yourself. Ensure results are efficiently computed and can be filtered by reasonable parameters if needed.

---

## Accumulation History
 
  Users want to see how an accumulation has changed over time and be able to go back to an earlier state.
 
  Design and implement this as you see fit.

---

## Setup

- Java 17 or newer is required
- Maven Wrapper (`mvnw`) is included; Maven installation is not required
- Run application:
  ```bash
  ./mvnw clean spring-boot:run
  ```
- By default, the application should run on port `8080`
- Include OpenAPI (Swagger) documentation  
  Default path: `http://localhost:8080/swagger-ui/index.html`

---

## Evaluation Criteria

### Compilation/Execution
- Can the application be built and run successfully?

### Code Structure
- **Separation of Concerns:** Clear distinction between controllers, services, and repositories.
- **Modularity:** Logical organization of components and layers.
- **Clarity:** Consistent and understandable project layout.

### Code Quality
- **Readability:** Code is easy to follow and consistent in style.
- **Naming Conventions:** Proper use of meaningful names.
- **DRY Principle:** No unnecessary repetition.
- **SOLID Principles:** Applied appropriately.

### Design
- **Entity Relationships:** Correct and consistent modeling of domain relationships.
- **Consistency:** Adherence to REST conventions and best practices.

### Functionalities
- **Expected Behavior:** CRUD and filtering work as intended.
- **Accuracy:** Data integrity and referential relationships are maintained.

### Documentation
- **README:** Instructions for running and using the application.
- **Swagger/OpenAPI:** Clear documentation of endpoints and payloads.
- **Comments:** Present where needed for complex logic.
- **Logging:** Meaningful logs for key application events.
