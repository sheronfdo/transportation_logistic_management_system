# Transportation Logistic Management System

## Overview

The **Transportation Logistic Management System** is a Java EE-based application designed to streamline logistics operations. It supports three user roles—**Admin**, **Customer**, and **Driver**—and provides functionalities for inventory management, order processing, shipment tracking, and route optimization. The system leverages real-time data to enhance supply chain visibility, operational efficiency, and customer satisfaction.

## Features

- **User Roles**:
    - **Admin**: Manages inventory by adding, updating, or removing items.
    - **Customer**: Views inventory, places orders, and specifies delivery locations using latitude and longitude.
    - **Driver**: Views and manages assigned shipments, starts deliveries, and marks orders as complete.
- **Inventory Management**: Admins can add and manage items in the inventory.
- **Order Management**: Customers can place orders, which are automatically processed for shipment arrangement.
- **Route Optimization**: Uses the Haversine formula to calculate distances and optimize delivery routes based on GPS coordinates.
- **Real-Time Shipment Tracking**: Automatically identifies non-shipped orders using Java Timer Service and assigns them to drivers.
- **Transaction Management**: Implements JTA (Java Transaction API) to ensure ACID-compliant transactions for reliable operations.
- **Security**: Utilizes role-based access control (RBAC) with `@RolesAllowed` for user authentication and authorization.
- **Exception Handling**: Manages errors like `ItemNotFoundException` and `ItemOutOfStockException` to ensure smooth operations.
- **Modular Architecture**: Organized into modules (e.g., Order, Shipment, Inventory, Core) for maintainability and scalability.

## Technologies

- **Backend**: Java EE, EJB (Stateless Session Beans), JTA, JPA
- **Frontend**: Servlets, JSP
- **Database**: MySQL
- **Interceptors**: Route optimization and logging using Java Interceptors
- **Build Tool**: Maven
- **Other Libraries**: ModelMapper for object mapping, JUnit for testing
- **Standards**: Java 11, Maven WAR plugin

## High-Level Process

1. **Admin** adds items to the inventory.
2. **Customer** browses inventory, places orders, and specifies delivery locations (latitude/longitude).
3. The system uses **Java Timer Service** to identify non-shipped orders and arranges shipments.
4. **Route Optimization Interceptor** sorts delivery destinations based on proximity using GPS coordinates.
5. **Driver** views assigned shipments, starts deliveries, and completes orders.
6. Transactions are managed with JTA to ensure data integrity.
7. Role-based access control ensures secure access for all users.

## Architecture

The system follows a modular architecture with the following components:

- **TranLogisticManagementSystem-web**: Handles the web interface (Servlets, JSP).
- **TranLogisticManagementSystem-Inventory**: Manages inventory-related operations.
- **TranLogisticManagementSystem-Shipment**: Handles shipment creation and tracking.
- **TranLogisticManagementSystem-Order**: Processes customer orders.
- **TranLogisticManagementSystem-Core**: Contains DTOs and remote interfaces for shared functionality.
- **TranLogisticManagementSystem-ear**: Packages the application for deployment.

## Prerequisites

- Java 11
- Maven 3.3.2 or higher
- MySQL 8.0 or compatible
- Java EE-compatible application server (e.g., WildFly, GlassFish)
- Git

## Setup Instructions

1. **Clone the Repository**:
    
    ```bash
    git clone https://github.com/sheronfdo/transportation_logistic_management_system.git
    cd transportation_logistic_management_system
    ```
    
2. **Configure MySQL**:
    - Create a database named `tranLogisticSys`.
    - Update the database configuration in `persistence.xml` with your MySQL credentials.
3. **Build the Project**:
    
    ```bash
    mvn clean install
    ```
    
4. **Deploy to Application Server**:
    - Deploy the generated `.ear` file from the `TranLogisticManagementSystem-ear` module to your Java EE application server.
5. **Access the Application**:
    - Open the application URL (e.g., `http://localhost:8080/TranLogisticManagementSystem`) in a web browser.
    - Log in using credentials for Admin, Customer, or Driver roles.

## Usage

- **Admin**: Log in to add or manage inventory items and monitor system activities.
- **Customer**: Browse available items, place orders, and provide delivery coordinates.
- **Driver**: View assigned shipments, start deliveries, and update order statuses.
- The system automatically optimizes routes and ensures secure, transactional operations.

## Security

- **Authentication**: Users must log in to access the system.
- **Authorization**: Role-based access control (`@RolesAllowed`) restricts actions to authorized users (Admin, Customer, Driver).
- **Data Security**: Implements encryption and access control to protect cargo data.

## Exception Handling

The system handles errors such as:

- `ItemNotFoundException`: When an item is not available in the inventory.
- `ItemOutOfStockException`: When an item is out of stock during order processing.

## Project Structure

```
TranLogisticManagementSystem/
├── TranLogisticManagementSystem-web/    # Web interface (Servlets, JSP)
├── TranLogisticManagementSystem-Inventory/  # Inventory management
├── TranLogisticManagementSystem-Shipment/   # Shipment management
├── TranLogisticManagementSystem-Order/      # Order processing
├── TranLogisticManagementSystem-Core/       # DTOs and remote interfaces
├── TranLogisticManagementSystem-ear/        # EAR packaging
├── pom.xml                                 # Maven configuration
```

## Contributing

Contributions are welcome! Please fork the repository, create a feature branch, and submit a pull request with your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](https://grok.com/chat/LICENSE) file for details.

## Contact

For questions or support, contact the project maintainer at [sheronfdo@example.com](mailto:sheronfdo@example.com).
