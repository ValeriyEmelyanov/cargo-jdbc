# cargo-jdbc

Cargo service, training project with Spring Boot, JDBC and Kotlin.

To run the program on the local host, 
PostgreSQL must be installed 
and the cargo_db database created

The script for creating the cargo table:

```
create table cargo
(
    id serial constraint cargo_pk primary key,
    title varchar(100) not null,
    passenger_count integer
);
```

### Tools and Technologies Used
* Kotlin
* Spring Boot
* JDBC
* PostgreSQL
* Swagger

### Endpoints
* http://localhost:8080/swagger-ui/ - documentation and ui
* http://localhost:8080/cargo - get all cargo
* http://localhost:8080/cargo/1 - get the cargo with id = 1
* and so no
 