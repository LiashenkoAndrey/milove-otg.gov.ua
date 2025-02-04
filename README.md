Here's the updated **README.md** file with the added organization details and new developer information:

---

# miloverada.gov.ua
https://miloverada.gov.ua

A Spring Boot-based Java application designed for efficient and secure server-side operations, supporting the development and recovery of the Milivska Rural Territorial Community in Ukraine.

---

## Table of Contents
- [About the Organization](#about-the-organization)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [Development](#development)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)
- [Authors](#authors)

---

## About the Organization

**Milivska Rural Territorial Community** is a territorial community in Ukraine, located in the Beryslav district of the Kherson region.

### Key Facts:
- **Established**: July 15, 2019, through the merger of Kachkarivska and Milivska rural councils.
- **Administrative Center**: Milove village.
- **Villages**: Includes 10 villages: Dudzchany, Kachkarivka, Milove, Nova Kam’yanka, Novohryhorivka, Novokairy, Respublikanets, Sablukivka, Sukhanove, and Chervonyi Yar.
---

## Features
- **OAuth2 Authentication**: Secure integration with Okta for user authentication.
- **WebSocket Support**: Real-time communication with WebSocket.
- **Database Support**: PostgreSQL for relational data and MongoDB for document-based storage.
- **Data Migration**: Managed with Flyway.
- **Logging**: Configured with SLF4J and Logback.
- **Testing**: Comprehensive unit tests with JUnit and Spring Boot Test.
- **Lombok Integration**: Simplifies Java code with annotations.

---

## Technologies Used
- **Java 17**
- **Spring Boot 3.x**
    - Spring Web
    - Spring Data JPA
    - Spring Data MongoDB
    - Spring Security (OAuth2 Client)
    - Spring WebSocket
- **PostgreSQL**
- **MongoDB**
- **Flyway for database migration**
- **Lombok** for reducing boilerplate code
- **JUnit 4** and **Spring Boot Test** for testing
- **SLF4J** and **Logback** for logging
- **Blaze-Persistence** for advanced query capabilities

---

## Setup and Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/LiashenkoAndrey/miloverada.gov.ua.git
   cd miloverada.gov.ua
   ```

2. **Install dependencies**:
   Ensure you have [Maven](https://maven.apache.org/) installed, then run:
   ```bash
   mvn clean install
   ```

3. **Database Configuration**:
    - Update `application.properties` or `application.yml` with your PostgreSQL and MongoDB connection details.

4. **Run Flyway Migrations**:
   ```bash
   mvn flyway:migrate
   ```

---

## Running the Application

1. Start the application:
   ```bash
   mvn spring-boot:run
   ```

2. Access the application:
    - Web Application: [http://localhost:8080](http://localhost:8080)

---

## Development

### Prerequisites
- Java 17
- Maven 3.8+
- PostgreSQL and MongoDB databases

### Build the project:
```bash
mvn clean install
```

### Run with hot-reloading:
Enable the **Spring Boot DevTools** plugin:
```bash
mvn spring-boot:run
```

---

## Testing

Run unit tests:
```bash
mvn test
```

---

## Contributing

Contributions are welcome! Please fork the repository and create a pull request for your changes. Ensure all tests pass before submitting.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Authors

- **Andrew Liashenko**
    - Role: Java Developer
    - Email: [a.liashenko@e-u.edu.ua](mailto:a.liashenko@e-u.edu.ua)
    - GitHub: [LiashenkoAndrey](https://github.com/LiashenkoAndrey)

- **Maxym Zelinskiy**
    - Role: Java Developer
    - GitHub: [JavaDevMZ](https://github.com/JavaDevMZ)

