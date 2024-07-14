package com.example.toDoList.Models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable= false)
	private String name;
	@Column(nullable= false)
	private String userName;
	@Column(nullable= false)
	private String password;
	
	//Adress
	@Column
	private String street;
	@Column
	private String city;
	@Column(length = 10)
	private String zipCode;
	@Column
	private String country;
	
	@OneToMany(mappedBy = "user")
	private Set<ToDo> toDoList;	
	
	
	public User() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userName=" + userName + ", password=" + password + ", city="
				+ city + "]";
	}
	
	
	
	
	
	
}
