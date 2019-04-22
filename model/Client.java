/**
 * O clasa care simuleaza un client din viata reala
 * Sunt prezente settere si gettere pentru toate campurile clasei
 */


package model;

public class Client {
	private int id;
	private String name;
	private String surname;
	
	public Client(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}
	
	public Client(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String toString() {
		return this.id + " " + this.name + " "  + this.surname;
	}
}
