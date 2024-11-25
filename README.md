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

## Running the Application

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd tms
