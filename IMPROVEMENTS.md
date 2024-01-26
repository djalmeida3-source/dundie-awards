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
### Clean architecture structure
## Testing
### Configuring Cucumber to show coverage with test application of BDD
## Improvements flow
- ? Validations to know required fields
- ? Validation to not store duplicated values by name on employees and organizations
- ? Use of Dto on controller for request and response
- Enable h2 console
- Added configuration to see sql statements on logs
- Use of Lombok to maintain clean code practices
- Use of Builder pattern
- ? Logs

--

- Unit test services esencial, no 100%, 
- Pruebas integracion
- Controller Advice para excepciones mapear todas y se hagan http, como un notFound
- Agregar exceciones customizadas con anotaciones y unas de negocio, 
- Agrewgar dto response y request
- En el request validar consistencia de datos 1era 
- 2da validaci{on si Jhon Dow viene 2 veces esa es validacion de negocio
- Eso con pruebas
- Registrar con un campo lista para los awards
- Quitar lombok
- Liquibase para migraciones
- logs
- aplicar el publisher con lo de ChatGPT
- Documentaci{on con pruebas no todo pero si lo importante
- 

crear otra tabla y crear un nuevo servico y unir las 2 tablas

