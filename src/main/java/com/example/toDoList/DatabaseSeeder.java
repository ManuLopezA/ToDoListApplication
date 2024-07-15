package com.example.toDoList;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.toDoList.Models.ToDo;
import com.example.toDoList.Models.User;
import com.example.toDoList.Services.ToDoService;
import com.example.toDoList.Services.UserService;
import com.example.toDoList.Utils.Color;

/**
 * This seeder is executed when the application starts.
 * 
 * It checks if the database is empty by looking for existing users.
 * If no users are found, it adds initial users and their to-do items
 * to the database. If users are already present, it logs that the
 * database is already initialized.
 * 
 * If you delete all users manually, this seeder will be executed again
 * and will insert the initial users and their to-do lists.
 */
@Component
public class DatabaseSeeder implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private ToDoService toDoService;

	@Override
	public void run(String... args) throws Exception {
		if (userService.findAllUsers().isEmpty()) {
			System.out.println(Color.MAGENTA);
			System.out.println("-----------------------------------------------");
			System.out.println("DATABASE SEEDER EXECUTED: Initializing Database");
			System.out.println("-----------------------------------------------");
			seedUsersAndTodos();
			System.out.println(Color.GREEN + "Database seeding completed successfully!" + Color.RESET);
		} else {
			System.out.println(Color.CYAN);
			System.out.println("------------------------------------------------------------");
			System.out.println("DATABASE SEEDER SKIPPED: Users already exist in the database");
			System.out.println("------------------------------------------------------------");
			System.out.println(Color.RESET);
		}
	}

	private void seedUsersAndTodos() {
		User user1 = new User("Anais Watterson", "anais", "pw123", "Elmore", "USA");
		userService.addNewUser(user1);

		User user2 = new User("Carrie Krueger", "carrie", "pw123", "Springfield", "USA");
		userService.addNewUser(user2);

		User user3 = new User("Banana Joe", "joe", "pw123", "Manaos", "BRAZIL");
		userService.addNewUser(user3);

		List<ToDo> todosList = new ArrayList<>();
		todosList.add(new ToDo("Walk my dog", false, user1));
		todosList.add(new ToDo("Read a comic", true, user1));
		todosList.add(new ToDo("Workout", false, user1));
		todosList.add(new ToDo("Call mom", true, user1));
		todosList.add(new ToDo("Wash the car", false, user2));
		todosList.add(new ToDo("Buy breed", true, user2));
		todosList.add(new ToDo("Do homework", false, user2));
		todosList.add(new ToDo("Go for a walk", false, user2));
		todosList.add(new ToDo("Meeting", true, user3));
		todosList.add(new ToDo("Clean", false, user3));
		todosList.add(new ToDo("Watch TV", true, user3));
		todosList.add(new ToDo("Visit friends", false, user3));

		for (ToDo todo : todosList) {
			toDoService.addNewToDo(todo);
		}
	}
}
