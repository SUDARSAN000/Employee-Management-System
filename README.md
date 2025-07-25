# Employee Management System (Spring Boot + MySQL)

This is a real-world backend project for managing employee data using Spring Boot and MySQL. 
It includes advanced features like search, filter, bulk insertion, pagination, sorting, and active/inactive status toggling.

## Features
- CRUD operations for Employee (Create, Read, Update, Delete)
- Role-based filtering and salary range querying
- Pagination with sorting
- Toggle active/inactive status for employees
- Bulk employee insertion
- Average salary by role calculation
- Global Exception Handling
- DTO pattern for clean separation
- Input validation using Jakarta Validation
- Swagger UI documentation

## Tech Stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Lombok
- Swagger (SpringDoc OpenAPI 3)

## API Endpoints

| Method | Endpoint                                    | Description                   |
|--------|---------------------------------------------|-------------------------------|
| POST   | /api/employees                              | Create new employee           |
| GET    | /api/employees                              | Get all employees             |
| GET    | /api/employees/{id}                         | Get employee by ID            |
| PUT    | /api/employees/{id}                         | Update employee               |
| DELETE | /api/employees/{id}                         | Delete employee               |
| GET    | /api/employees/role/{role}                  | Get employees by role         |
| GET    | /api/employees/salary?min=val&max=val       | Filter by salary range        |
| GET    | /api/employees/page?page=0&size=5&sort=name | Paginated sorted list         |
| POST   | /api/employees/bulk                         | Bulk employee insertio        |
| PUT    | /api/employees/{id}/status?active=true      | Activate/Deactivate employee  |
| GET    | /api/employees/active                       | Get all active employees      |
| GET    | /api/employees/average-salary/{role}        | Get average salary for role   |

## Swagger UI
- URL: http://localhost:8080/swagger-ui.html

## Database
- MySQL DB: `employeedb`
- Automatically created using `spring.jpa.hibernate.ddl-auto=update`

