# Project Manager

## Overview

The **Project Manager** is a Java Enterprise Edition (JEE) application designed to facilitate project management for teams and organizations. This project was developed to explore and apply Java EE concepts, including JPA, JDBC, Hibernate, and Servlets, while also enhancing understanding of object-oriented programming principles such as polymorphism, encapsulation, inheritance, and abstraction.

## Project Architecture

The application is divided into two main sub-projects:

1. **GestionProjectEJB (Enterprise JavaBeans)**
   - This sub-project contains the business logic of the application, which includes:
     - **Entities:** 
       - 'Project`
       - 'Task`
     - **Interfaces:**
       - 'ProjectDao`
       - 'TaskDao`
     - **Business LAyer Implementation:**
         - 'ProjectDaoImpl'
         - 'TaskDaoImpl`
   - This structure allows us to implement object-oriented programming principles through the use of interfaces and their implementations.

2. **GestionProjectWeb (Web Service)**
   - This sub-project handles the web interface based on the MVC architecture, managing user interactions and connecting to the EJB sub-project to process business logic. It includes:
     - **Controllers:** 
       - Responsible for handling requests and responses between the web interface and the business logic.
     - **Models:** 
       - Represent the application's data structure and interactions.

## Features

- **Project and Task Management:** Create, manage, and track projects and tasks; assign team members and manage project timelines.
- **JPA Integration:** Utilizes Java Persistence API (JPA) for effective relational data management.
- **DAO Pattern:** Implements the Data Access Object (DAO) pattern for structured data handling.
- **JDBC and Hibernate:** Applies JDBC and Hibernate for seamless database interactions.

## Technologies Used

- **Java EE**
- **EJB (Enterprise JavaBeans)**
- **JPA (Java Persistence API)**
- **JDBC (Java Database Connectivity)**
- **Hibernate**

