# GoAir

Air reservation system website to practice microservices implementation

## Java 8, Spring Boot 2.1.2, Hibernate, MySQL

* Store configuration details on cloud using CloudConfig.
* Load balance requests between microservices using Ribbon.
* Discover services in cloud using Eureka.
  * Ribbon with Eureka.
* Increase resilience through Hystrix
  * Circuit Breaker pattern, Fail Silent approach.

## Inside GoAir

### Services

* eureka-server
* zuul-server

### Microservices

* credit-card-ms
* flight-ms
* customer-ms
* passenger-ms
* ticket-ms

## REST Endpoints

* Registration
  * URL `http://localhost:4242/InfyGoBoot/register`
  * POST
  * ```json
     {
     "userId":"C10011",
     "password":"Greg^InfyGo",
     "name":"Greg B",
      "city":"LA",
      "email":"greg@smail.com",
      "phone":"8887654320"
      }
   ```

* Login
  * URL `http://localhost:4242/InfyGoBoot/login`
  * POST
  * ```json
     {
     "userId":"C10011",
     "password":"Greg^InfyGo"
     }
