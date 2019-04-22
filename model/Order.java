
/**
 * O clasa care simuleaza o comanda din viata reala
 * Sunt prezente settere si gettere pentru toate campurile clasei
 */

package model;

import java.time.LocalDate;

public class Order {
	private int id;
	private String clientName;
	private String productName;
	private int quantity;
//	private LocalDate date;
	
	public Order(int id, String clientName, String productName, int quantity) {
		super();
		this.id = id;
		this.productName = productName;
		this.clientName = clientName;
		this.quantity = quantity;
	//	this.date = date;
	}
	
	public Order(String clientName, String productName, int quantity) {
		super();
		this.productName = productName;
		this.clientName = clientName;
		this.quantity = quantity;
	//	this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return this.quantity;
	}
	
}
