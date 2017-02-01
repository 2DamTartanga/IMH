package com.tartanga.dam.imhandroid.model;

import java.util.ArrayList;

public class Group {
	private int id;
	private ArrayList<User> users;
	ArrayList<Repair> repairs;
	
	public Group(int id) {
		this.id = id;
	}

	public Group(int id, ArrayList<User> users) {
		this.id = id;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public ArrayList<User> getUsers() {
		return users;
	}
	
	
	
}
