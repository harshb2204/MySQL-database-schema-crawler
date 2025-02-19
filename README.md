# MySQL Database Schema Crawler

## Overview
This is a Spring Boot application designed to extract and display metadata from a MySQL database. It retrieves information about database tables, including column names, data types, sizes, and nullable constraints, and exposes this data through a REST API.

## Features
- Fetches database metadata dynamically.
- Retrieves table names and column details.
- REST API to expose metadata information.
- Uses Spring Boot, JDBC, and MySQL.

## Technologies Used
- **Spring Boot** - Backend framework
- **JDBC** - establishes a connection to the database, executes queries, and retrieves results.
- **MySQL** - Database
- **Maven** - Dependency management
- **REST API** - Web service architecture

## Project Structure
```

│── src
│   ├── main
│   │   ├── java/com/example/vistora
│   │   │   ├── config/DatabaseConfig.java
│   │   │   ├── controllers/MetaDataController.java
│   │   │   ├── models/ColumnMetaData.java
│   │   │   ├── models/TableMetaData.java
│   │   │   ├── services/MetaDataService.java
│   ├── resources
│   │   ├── application.properties
│── pom.xml
│── README.md
```


### Configuration
Edit `src/main/resources/application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

### Build & Run
#### Build the application:
```sh
mvn clean install
```
#### Run the application:
```sh
mvn spring-boot:run
```

## API Endpoints
| Endpoint        | Method | Description                         |
|---------------|--------|-------------------------------------|
| `/metadata/tables` | GET    | Retrieves a list of database tables and their columns |

## How It Works
1. **Database Configuration (`DatabaseConfig.java`)**
    - Loads database credentials from application properties.
2. **Service Layer (`MetaDataService.java`)**
    - Connects to MySQL and extracts metadata using JDBC.
3. **Model Classes (`TableMetaData.java`, `ColumnMetaData.java`)**
    - Represents table and column metadata.
4. **Controller (`MetaDataController.java`)**
    - Exposes REST endpoints to fetch metadata.


