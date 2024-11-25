# Task Management System (TMS)

This project is a **Task Management System** built with Spring Boot. It provides a RESTful web service to manage tasks, including creating, retrieving, updating, and deleting tasks. The application uses an H2 in-memory database for data persistence.

---

## Features
- **Endpoints**:
  - **POST /tasks**: Create a new task.
  - **GET /tasks**: Retrieve a list of all tasks.
  - **GET /tasks/{id}**: Retrieve a specific task by ID.
  - **PUT /tasks/{id}**: Update an existing task (e.g., mark as completed).
  - **DELETE /tasks/{id}**: Delete a task.
- **Task Object Structure**:
  - `id` (auto-generated UUID)
  - `title` (String, required)
  - `description` (String, optional)
  - `status` (Enum: `PENDING`, `COMPLETED`)

---

refer to this url to access the documentation for the enpoint.

https://holiday-service.postman.co/workspace/0cf48abd-b89c-4d78-a289-3e953b3a1a7b/request/15320288-0de79ef2-aee3-42ac-97bc-475e9e671814?tab=body


## Tech Stack
- **Java Version**: 17
- **Spring Boot Version**: 3.4.0
- **Database**: H2 in-memory database
- **Build Tool**: Maven

---

## Prerequisites
Ensure you have the following installed:
1. **Java 17** or higher.
2. **Maven** 3.x.

---
## H2 Database configuration

- **URL: http://localhost:8081/h2-console**
- **JDBC URL: jdbc:h2:mem:testdb**
- **Username: sa**
- **Password: <leave blank>**


## Running the Application

```bash
# Clone the repository
git clone https://github.com/Osinachi-cloud/tms.git
cd tms

# Build the project
mvn clean install

# Run the Spring Boot application in the background
mvn spring-boot:run &

# Run the tests while the application is running
mvn test


Once the application is running, you can access the API at the following URL:

Base URL: http://localhost:8081

# Now exit the bash session
exit



