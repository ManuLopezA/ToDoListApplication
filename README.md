# ToDo List Project

This is a simple task management project (ToDo List) developed with Spring Boot. It allows users to create, read, update, and delete tasks.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- Create and manage tasks.
- User authentication.
- User-friendly interface.
- Persistent storage in a database.

## Technologies

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Java](https://www.oracle.com/java/)
- [MySQL](https://www.mysql.com/)
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
