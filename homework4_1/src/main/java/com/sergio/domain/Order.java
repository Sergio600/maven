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
	private String customer;
	private List<Product> products = new ArrayList<>();
	private double totalPrice;

	public Order(String customer) {
		this.customer = customer;
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

	public String getCustomer() {
		return customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
