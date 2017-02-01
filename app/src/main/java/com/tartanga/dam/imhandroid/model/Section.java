package com.tartanga.dam.imhandroid.model;

import java.util.ArrayList;

public class Section {

	private String id;
	private String name;
	private ArrayList<Machine> machines;
	public Section(String id, ArrayList<Machine> machines) {
		this.id = id;
		this.machines = machines;
	}
	
	public String getId() {
		return id;
	}
	public ArrayList<Machine> getMachines() {
		return machines;
	}
	
	
	
}
