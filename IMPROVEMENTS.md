# Application Improvements
## Run application
- Start SpringBoot server
- To test grant award rollback on failing register activity on ActivityService consider sending null
  as a way to test the error and the rollback process
- Consider on the test a delay of 10 seconds added to improve visibly on the process
  ```
  public class ActivityService {
  ...
      activityRepository.save(null);
  ...
  ```
- The endpoint is:
  ```
  curl --location 'http://localhost:3000/awards/grant/2' \
  --header 'Content-Type: application/json' \
  --data '{
  "numberOfAwards": 3
  }'
  ```
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
- Added configuration to see sql statements on logs
- Added input validation on request and GlobalExceptionHandler
- Added business validation on service layer like not allowing employees with the same first and last name
- Added Logs in Main Application and EmployeeService
- Updating total awards
- Register activity on grant awards endpoint on a separate thread and consider rollback mechanism
- Added mappers to convert entities to dtos and vice-versa
- Use of AoP to handle cache for awards total update
## Other suggestions
- Use of Liquibase to keep track of the scripts and its benefits for configuring different environments
  like local, development, qa, stage and production
- Use of environment variables could be to connect to other APIs or set templates that change by environment
- Use of authentication provider as Okta or Keycloak depending on the use case 
