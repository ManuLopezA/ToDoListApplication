# ToDo List Project

This is a simple task management project (ToDo List) developed with Spring Boot as a technical test for the selection process. It allows users to create, read, update, and delete tasks.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Developed By](#developed-by)

## Features

- Create and manage tasks.
- User authentication.
- User-friendly interface.
- Persistent storage in a database.

## Technologies

- [Spring Boot](https://spring.io/projects/spring-boot) - Version 3.3.1
- [Java](https://www.oracle.com/java/) - Version 17
- [MySQL](https://www.mysql.com/) - Version 8.0.32
- [Thymeleaf](https://www.thymeleaf.org/)

## Prerequisites

Before you proceed with the installation and running of the project, ensure you have the following tools installed:

1. **Java 17** - Download from [Oracle](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
2. **MySQL 8.0.32** - Install MySQL from [here](https://dev.mysql.com/downloads/installer/).
3. **Spring Tool Suite 4 for Eclipse(STS4)** - Download and install the latest version of STS4 from [here](https://spring.io/tools).
   - **STS4** is recommended for running and debugging Spring Boot applications easily.

## Installation

1. **Download the repository**
   - Clone the repository using the following command:
     ```
     git clone https://github.com/ManuLopezA/ToDoListApplication.git
     ```
     
2. **Open the project in Spring Tool Suite 4 (STS4)**
   - Open **Spring Tool Suite 4**.
   - Import the project as a Maven project:
     - Go to `File > Import > Existing Maven Projects`.
     - Select the folder where you cloned the project.


3. **Open your database management tool**
   - Use a tool like MySQL Workbench or DBeaver.
   - Create a new schema named **todolistapp** (or any name you prefer).


4. **Reconfigure the `application.properties` file**
   - Open the file located at `src/main/resources/application.properties`.
   - Update the following lines with the appropriate **schema name**, **username**, and **password**:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/todolistapp?serverTimezone=UTC
     spring.datasource.username=user
     spring.datasource.password=password
     server.port=8080
     ```
   - If necessary, reconfigure the **ports**.

5. **Run the application**
   - In STS4, navigate to the main class located at `src/main/java/com/example/toDoList/ToDoApplication.java`.
   - Right-click the class and select **Run As > Spring Boot App** to start the application.

6. **Access the application**
   - Once the application is running, open your browser and go to `http://localhost:8080`.

## Developed By

**[Manu López](https://linktr.ee/manulopeza)**  
Connect with me on **[LinkedIn](https://www.linkedin.com/in/manuellopezaguilar/)**
