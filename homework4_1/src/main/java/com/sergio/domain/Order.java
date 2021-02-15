package com.sergio.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class that represents POJO of order.
 */
public class Order {

	private static AtomicInteger count = new AtomicInteger(0);

	private int id;
	private User user;
	private List<Product> products = new ArrayList<>();
	private double totalPrice;

	public Order(User user) {
		this.user = user;
		this.id = count.incrementAndGet();
	}

	public int getId() {
		return id;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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
