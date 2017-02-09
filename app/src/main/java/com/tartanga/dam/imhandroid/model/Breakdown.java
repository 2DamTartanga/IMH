package com.tartanga.dam.imhandroid.model;

import java.io.Serializable;
import java.util.Date;

public class Breakdown implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Date date;
	private String failureType;
	private String description;
	private String equipmentAvailable;
	private String subject;
	private Machine machine;
	private User reporter;
	
	public Breakdown(int id, Date date, User reporter, String failureType, String subject, String description,
			Machine machine, String equipmentAvailable) {
		this.id = id;
		this.date = date;
		this.reporter = reporter;
		this.failureType = failureType;
		this.subject = subject;
		this.description = description;
		this.machine = machine;
		this.equipmentAvailable = equipmentAvailable;
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public User getReporter() {
		return reporter;
	}

	public String getFailureType() {
		return failureType;
	}

	public String getSubject() {
		return subject;
	}

	public String getDescription() {
		return description;
	}

	public Machine getMachine() {
		return machine;
	}

	public String getEquipmentAvailable() {
		return equipmentAvailable;
	}

	public String toString(){
		return getMachine().toString();
	}
	
	
}
