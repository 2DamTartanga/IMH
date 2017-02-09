package com.tartanga.dam.imhandroid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class WorkOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int severity;
	private String others;
	private String typeOfMaintenance;
	private Date creationDate;
	private ArrayList<Repair> repairs;
	private Breakdown breakdown;
	
	public WorkOrder(Breakdown breakdown, int severity, Date date, String others, String typeOfMaintenance) {
		this.breakdown = breakdown;
		this.severity = severity;
		this.creationDate = date;
		this.others = others;
		this.typeOfMaintenance = typeOfMaintenance;
	}
	public WorkOrder(Breakdown breakdown, int severity, Date date, String others, String typeOfMaintenance,
			ArrayList<Repair> repairs) {
		this.breakdown = breakdown;
		this.severity = severity;
		this.creationDate = date;
		this.others = others;
		this.typeOfMaintenance = typeOfMaintenance;
		this.repairs = repairs;
	}
	
	public Breakdown getBreakdown() {
		return breakdown;
	}
	public int getSeverity() {
		return severity;
	}
	public Date getDate() {
		return creationDate;
	}
	public String getOthers() {
		return others;
	}
	public String getTypeOfMaintenance() {
		return typeOfMaintenance;
	}
	public ArrayList<Repair> getRepairs() {
		return repairs;
	}
	


	
}
