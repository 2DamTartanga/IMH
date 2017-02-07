package com.tartanga.dam.imhandroid.model;
import java.io.Serializable;

public class Message implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 2L;


	public static final String ADD = "add";
	public static final String MOD = "mod";
	public static final String DEL = "delete";
	public static final String GET = "get";
	public static final String LOGIN = "login";

	public static final String WORK_ORDER= "work order";
	public static final String REPAIR = "repair";
	public static final String USER = "user";
	public static final String GROUP = "group";
	public static final String MACHINE = "machine";
	public static final String WORK_ZONE = "work zone";
	public static final String BREAKDOWN = "breakdown";

	//public static final String ENGLISH = "en";
	//public static final String EUSKARA = "eus";

	//private String language;
	private String action;
	private String object;
	private Object content;
	/*
	public Message(String language, String action, String object, Object content) {
		this.language = language;
		this.action = action;
		this.object = object;
		this.content = content;
	}*/

	public Message(String action, String object, Object content) {
		//language = ENGLISH;
		this.action = action;
		this.object = object;
		this.content = content;
	}

	public String getAction() {
		return action;
	}

	public String getObject() {
		return object;
	}

	public Object getContent() {
		return content;
	}

	/*public String getLanguage() {
		return this.language;
	}*/

}

