package com.tartanga.dam.imhandroid.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Section implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private ArrayList<Machine> machines;
	private int[] status;

	public Section(String id){
		this.id = id;
	}

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


	public int[] getStatus() {
		return status;
	}

	public void setStatus(int[] status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
