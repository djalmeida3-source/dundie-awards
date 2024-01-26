# Application Improvements
## Architecture on layers
### Controller
- Have to use request and response dtos
- Call only services
### Model
- Entities form the application
### Services
- Has the business logic
- Can call repositories and other services
### Repository
- Perform actions with the database
### Suggestions
- Use of UseCases layer from clean architecture to increase testability and increase single 
  responsibility principle
- Entity layer could be the domain layer and validations could be configured there
## Testing
- Adding Unit test for Service Layer
- Added Jacoco plugin to see the coverage of the application
### Improvement suggestions
- If using BDD and TDD, can configure Cucumber to show coverage on unit test, integrates with Jira and 
  can leverage Living Documentation
- Adding integration tests
## Improvements in application
- Enable h2 console to perform sql queries 
- Added configuration to see sql statements on logs
- Added input validation on request and GlobalExceptionHandler
- Added business validation on service layer like not allowing employees with the same first and last name
- Added Logs in Main Application and EmployeeService
## Other suggestions
- Use of Liquibase to keep track of the scripts and its benefits for configuring different environments
  like local, development, qa, stage and production

- aplicar el publisher con lo de ChatGPT

crear otra tabla y crear un nuevo servico y unir las 2 tablas

