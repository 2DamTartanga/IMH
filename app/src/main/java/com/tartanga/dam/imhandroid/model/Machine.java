package com.tartanga.dam.imhandroid.model;

import java.io.Serializable;

public class Machine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//TODO esto es seguramente va a cambiar
	private String id;
	private char status;
	private int importance;
	private String model;
	private String manufacturer;
	private String hydraulicConnection;
	private String pneumaticConnection;
	private String dimmensions;
	private String powerSource;
	private String electricConnection;
	private String workingPressure;
	private String machineFamilly;
	private String fileSearch;
	private String telephone;
	private String responsable;
	private String weight;
	private String typeOfOil;
	private String serialNumber;
	private String year;
	private Section section; //??
	public String getId() {
		return id;
	}
	public char getStatus() {
		return status;
	}
	public int getImportance() {
		return importance;
	}
	public String getModel() {
		return model;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public String getHydraulicConnection() {
		return hydraulicConnection;
	}
	public String getPneumaticConnection() {
		return pneumaticConnection;
	}
	public String getDimmensions() {
		return dimmensions;
	}
	public String getPowerSource() {
		return powerSource;
	}
	public String getElectricConnection() {
		return electricConnection;
	}
	public String getWorkingPressure() {
		return workingPressure;
	}
	public String getMachineFamilly() {
		return machineFamilly;
	}
	public String getFileSearch() {
		return fileSearch;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getResponsable() {
		return responsable;
	}
	public String getWeight() {
		return weight;
	}
	public String getTypeOfOil() {
		return typeOfOil;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public String getYear() {
		return year;
	}
	public Section getSection() {
		return section;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public void setHydraulicConnection(String hydraulicConnection) {
		this.hydraulicConnection = hydraulicConnection;
	}
	public void setPneumaticConnection(String pneumaticConnection) {
		this.pneumaticConnection = pneumaticConnection;
	}
	public void setDimmensions(String dimmensions) {
		this.dimmensions = dimmensions;
	}
	public void setPowerSource(String powerSource) {
		this.powerSource = powerSource;
	}
	public void setElectricConnection(String electricConnection) {
		this.electricConnection = electricConnection;
	}
	public void setWorkingPressure(String workingPressure) {
		this.workingPressure = workingPressure;
	}
	public void setMachineFamilly(String machineFamilly) {
		this.machineFamilly = machineFamilly;
	}
	public void setFileSearch(String fileSearch) {
		this.fileSearch = fileSearch;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public void setTypeOfOil(String typeOfOil) {
		this.typeOfOil = typeOfOil;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
	
	
}
