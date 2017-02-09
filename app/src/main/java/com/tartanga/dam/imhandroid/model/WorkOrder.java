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
	private Repair repair;
	private Breakdown breakdown;
	
	public WorkOrder(Breakdown breakdown, int severity, Date date, String others, String typeOfMaintenance) {
		this.breakdown = breakdown;
		this.severity = severity;
		this.creationDate = date;
		this.others = others;
		this.typeOfMaintenance = typeOfMaintenance;
	}
	public WorkOrder(Breakdown breakdown, int severity, Date date, String others, String typeOfMaintenance,
			Repair repairs) {
		this.breakdown = breakdown;
		this.severity = severity;
		this.creationDate = date;
		this.others = others;
		this.typeOfMaintenance = typeOfMaintenance;
		this.repair = repairs;
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
	public Repair getRepairs() {
		return repair;
	}
	


	
}
