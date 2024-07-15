package com.example.toDoList.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.toDoList.Models.User;
import com.example.toDoList.Repositories.UserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository ur;

	@Override
	public List<User> findAllUsers() {
		return ur.findAll();
	}

	@Override
	public User getUser(int id) {
		return ur.findById(id).orElse(null);
	}

	@Override
	public boolean findByUserName(String userName) {
		User user = ur.findByUserName(userName);
		if (user != null) {
			System.out.println(user);
			return true;
		}
		System.err.println("no se ha encontrado");
		return false;
	}

//	public boolean correctPassword(String password, User user) {
//		if (user.getPassword().equals(password)) {
//			System.out.println("password OK" + user.getPassword());
//			return true;
//		}
//		System.err.println("password WRONG");
//		return false;
//	}

	@Override
	public User getUser(String userName) {
		User user = ur.findByUserName(userName);
		return user;
	}

	@Override
	public void addNewUser(User user) {
		ur.save(user);		
	}


}
