# aplazo-payments
### Commands
- ./gradlew bootJar
- docker build -t aplazo/payments .
- docker run -p 8080:8080 aplazo/payments:latest

### URL
 - [POST] http://localhost:8080/payments

#### Health url
 - http://localhost:8080/actuator/health

#### H2 Console
 - http://localhost:8080/h2
  
