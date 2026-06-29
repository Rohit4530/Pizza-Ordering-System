# Pizza Ordering System

This repository contains a Spring Boot microservices-based pizza ordering application. The services are organized around core business capabilities such as product catalog, beverages, sides, cart, coupons, and order handling.

## Project Structure

- [api-gateway](api-gateway) - Routes requests to the appropriate backend service
- [service-registry](service-registry) - Eureka service discovery server
- [PizzaHomeStore](PizzaHomeStore) - Main pizza store service
- [beverages](beverages) - Beverage catalog and management
- [sides-service](sides-service) - Side dish service
- [cart-service](cart-service) - Shopping cart operations
- [coupon](coupon) - Coupon and discount handling
- [order-service 4](order-service%204) - Order placement and processing

## Service Ports

- Service Registry: 8761
- API Gateway: 9900
- Pizza Home Store: 8080
- Beverages Service: 8181
- Sides Service: 9090
- Cart Service: 9001
- Coupon Service: 8282
- Order Service: 7878

## Prerequisites

- Java 17 or later
- Maven
- MySQL running locally
- Appropriate databases created for the services (for example, `testdb` and `orderdb` as used in the configuration)

## Running the Application

1. Start the service registry:
   - `cd service-registry`
   - `./mvnw spring-boot:run`

2. Start the API gateway:
   - `cd api-gateway`
   - `./mvnw spring-boot:run`

3. Start the backend services in separate terminals:
   - `cd PizzaHomeStore && ./mvnw spring-boot:run`
   - `cd beverages && ./mvnw spring-boot:run`
   - `cd sides-service && ./mvnw spring-boot:run`
   - `cd cart-service && ./mvnw spring-boot:run`
   - `cd coupon && ./mvnw spring-boot:run`
   - `cd "order-service 4" && ./mvnw spring-boot:run`

4. Access the application through the API gateway at:
   - `http://localhost:9900`

## Notes

- The root folder also contains a frontend archive named `FrontEnd.zip`.
- If your local MySQL credentials differ from the defaults in the service configuration files, update them in the relevant `application.properties` files before running the services.
