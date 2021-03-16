package com.sergio.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents POJO of order.
 */
@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column (unique = true, nullable = false)
	private Integer id;

	@Column(name = "TOTAL_PRICE", nullable = false)
	private double totalPrice;

	@Column(name = "USER_ID", nullable = false)
	private int userID;


	private User user;

	private List<Product> products = new ArrayList<>();


	public Order(){}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Order(User user) {
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}


}
