# ToDo List Project

This is a simple task management project (ToDo List) developed with Spring Boot as a technical test for the selection process. It allows users to create, read, update, and delete tasks.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
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

## Installation

1. **Download the repository**
   - Clone the repository using the following command:
     ```
     git clone https://github.com/your-username/your-repo-name.git
     ```

2. **Open your database IDE**
   - Use a database management tool like MySQL Workbench or DBeaver.
   - Create a new database named `todolistapp` (or any name you prefer).

3. **Reconfigure the `application.properties` file**
   - Open the file located at `src/main/resources/application.properties`.
   - **Update the following lines with the appropriate schema name, username, and password:**
     ```
     spring.jpa.hibernate.ddl-auto=update
     spring.datasource.url=jdbc:mysql://localhost:3306/todolistapp?serverTimezone=UTC
     spring.datasource.username=user
     spring.datasource.password=password
     server.port=8080
     ```
   - **If necessary, reconfigure the ports.**

4. **Run the application**
   - Start your application by running the main class located at `src/main/java/com/example/toDoList/ToDoApplication.java`.

## Developed By

__[Manuel López]__(https://github.com/ManuLopezA)  
Connect with me on __[LinkedIn]__(https://www.linkedin.com/in/manuellopezaguilar/)
