package com.tartanga.dam.imhandroid.model;

public class User {

	private String username;
	private String password;
	private String email;
	private String name;
	private String surname;
	private String course;
	private Group group;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String name, String surname, String email, String course) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.course = course;
	}

	public User(String username, String password, String name, String surname, String email, String course,
			Group group) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.course = course;
		this.group = group;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getCourse() {
		return course;
	}

	public Group getGroup() {
		return group;
	}
}
