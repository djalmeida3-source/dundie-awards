# Application Improvements
## Architecture on layers
### Controller
- Use of request and response dtos
- Call only services
### Model
- Entities for the database
### Services
- Has the business logic
- Can call repositories and other services
### Repository
- Perform actions with the database
### Suggestions
- Use of UseCases layer from Clean Architecture to increase testability and increase single 
  responsibility principle
- Entity layer could be the domain layer and validations could be configured there
## Testing
- Added Unit test for Service Layer
- Added Jacoco plugin to see the coverage of the application on report
### Improvement suggestions
- If using BDD and TDD, can configure Cucumber to show coverage on unit test, integrates with Jira and 
  can leverage Living Documentation
- Adding integration tests
## Improvements made on application
- Separate business logic from controller to service layer
- Enable h2 console to perform sql queries 
- Added configuration to see sql statements on logs
- Added input validation on request and GlobalExceptionHandler
- Added business validation on service layer like not allowing employees with the same first and last name
- Added Logs in Main Application and EmployeeService
- Using SpringBoot events, register on commit into Queue messages and created an endpoint to process messages
  and create activities
- Updating total awards using event commit listener
## Other suggestions
- Use of Liquibase to keep track of the scripts and its benefits for configuring different environments
  like local, development, qa, stage and production
- Use of environment variables could be to connect to other APIs or set templates that change by environment
- Use of authentication provider as Okta or Keycloak depending on the use case 
